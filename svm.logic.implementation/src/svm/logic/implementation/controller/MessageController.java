package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.domain.abstraction.modelInterfaces.ISubTeamsHasMembers;
import svm.logic.abstraction.controller.IMessageController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.jmsobjects.IMemberMessage;
import svm.logic.abstraction.jmsobjects.ISubTeamMessage;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.implementation.tranferobjects.TransferAuth;
import svm.logic.jms.SvmJMSPublisher;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Projectteam : Team C
 * Date: 21.11.12
 */
public class MessageController implements IMessageController {
    private Integer sessionId;
    private ITransferAuth user;
    private TopicConnectionFactory tcf;
    private TopicConnection tc;
    private Topic topicMember;
    private Topic topicSubTeam;
    private TopicSession ts;
    private TopicSubscriber topicSubTeamSubscriber;
    private TopicSubscriber topicMemberSubscriber;

    public MessageController(ITransferAuth user) {
        this.user = user;
    }

    @Override
    public List<IMemberMessage> updateMemberMessages() throws RemoteException, NoSessionFoundException, JMSException {
        List<IMemberMessage> messages = new LinkedList<IMemberMessage>();


        ObjectMessage topicMemberMessage;
        topicMemberSubscriber = ts.createDurableSubscriber(topicMember, user.getUsername());
        do {

            topicMemberMessage = (ObjectMessage) topicMemberSubscriber.receiveNoWait();
            if (topicMemberMessage != null) {
                IMemberMessage message = (IMemberMessage) topicMemberMessage.getObject();

                IMember messageMember = (IMember) ((IHasModel) message.getMember()).getModel();
                int sessionId = DomainFacade.generateSessionId();
                DomainFacade.reattachObjectToSession(sessionId, messageMember);
                if (!messageMember.getSport().isNull()) {
                    DomainFacade.reattachObjectToSession(sessionId, messageMember.getSport());
                    DomainFacade.reattachObjectToSession(sessionId, messageMember.getSport().getDepartment());
                    messageMember = messageMember.getSport().getDepartment().getDepartmentHead();
                    if (((TransferAuth) user).getModel().equals(messageMember))
                        messages.add((IMemberMessage) topicMemberMessage.getObject());
                }
                DomainFacade.closeSession(sessionId);
            }
        } while (topicMemberMessage != null);
        topicMemberSubscriber.close();
        return messages;
    }

    @Override
    public List<ISubTeamMessage> updateSubTeamMessages() throws RemoteException {

        List<ISubTeamMessage> messages = new LinkedList<ISubTeamMessage>();
        try {
            topicSubTeamSubscriber = ts.createDurableSubscriber(topicSubTeam, user.getUsername());
            ObjectMessage topicSubTeamMessage;
            do {

                topicSubTeamMessage = (ObjectMessage) topicSubTeamSubscriber.receiveNoWait();
                if (topicSubTeamMessage != null) {
                    ISubTeamMessage message = (ISubTeamMessage) topicSubTeamMessage.getObject();
                    List<ISubTeamsHasMembers> memberList = (List<ISubTeamsHasMembers>) ((IHasModel<ISubTeam>) message.getSubTeam()).getModel().getSubTeamMembers();
                    for (ISubTeamsHasMembers m : memberList) {
                        if (m.getMember().equals(((TransferAuth) user).getModel())) {
                            messages.add((ISubTeamMessage) topicSubTeamMessage.getObject());
                        }
                    }
                }

            } while (topicSubTeamMessage != null);

            topicSubTeamSubscriber.close();
            return messages;

        } catch (JMSException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage(), e);
        }

    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException, RemoteException, NotSupportedException, InstantiationException, IllegalAccessException {
        //Init Context
        try {
            Context context = null;

            context = this.getInitialContext();

            //Hole topic factory
            tcf = (TopicConnectionFactory) context.lookup(SvmJMSPublisher.factoryName);
            //Hole Topic
            topicMember = (Topic) context.lookup(SvmJMSPublisher.memberTopic);
            topicSubTeam = (Topic) context.lookup(SvmJMSPublisher.subTeamTopic);
            //Create Topic verbindung
            tc = tcf.createTopicConnection();

            //Setzen der Client ID
            String id = user.getUsername();
            tc.setClientID(id);
            tc.start();
            //Topic Session starten
            ts = tc.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);

        } catch (NamingException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage(), e);
        } catch (JMSException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage(), e);
        }
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException, RemoteException {
        try {
            ts.close();
            tc.close();
        } catch (JMSException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage(), e);
        }
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException, RemoteException {
        try {
            topicMemberSubscriber.close();
            topicSubTeamSubscriber.close();
            ts.close();
            tc.close();
        } catch (JMSException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage(), e);
        }
    }

    public Context getInitialContext() throws NamingException {
        return new InitialContext(SvmJMSPublisher.getContextTable());
    }
}
