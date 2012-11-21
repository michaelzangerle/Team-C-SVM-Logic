package svm.logic.implementation.controller;

import svm.logic.abstraction.controller.IMessageController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.jmsobjects.IMemberMessage;
import svm.logic.abstraction.jmsobjects.IMessage;
import svm.logic.abstraction.jmsobjects.ISubTeamMessage;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.implementation.tranferobjects.TransferMember;
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
public class MessageController implements IMessageController{
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
    public List<IMessage> updateMemberMessages(ITransferMember member) throws RemoteException {

        List<IMessage> messages=new LinkedList<IMessage>();
        try {
            ObjectMessage topicMemberMessage;
            do{

                topicMemberMessage= (ObjectMessage)topicMemberSubscriber.receiveNoWait();
                IMemberMessage message=(IMemberMessage)topicMemberMessage;
                ITransferMember messageMember=message.getMember().getSport().getDepartment().getDepartmentHead();
                if(((TransferMember)member).getModel().equals(((TransferMember)messageMember).getModel()))
                    messages.add((IMemberMessage)topicMemberMessage);

            }while(topicMember!=null);

            return messages;

        } catch (JMSException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage(),e);
        }
    }

    @Override
    public List<ISubTeamMessage> updateSubTeamMessages(ITransferMember member) throws RemoteException {

        List<ISubTeamMessage> messages=new LinkedList<ISubTeamMessage>();
        try {

        ObjectMessage topicSubTeamMessage;
        do{

            topicSubTeamMessage= (ObjectMessage)topicSubTeamSubscriber.receiveNoWait();
            ISubTeamMessage message=(ISubTeamMessage)topicSubTeamMessage;
            List<ITransferMember> memberList= message.getSubTeam().getSubTeamMembers();
            for(ITransferMember m:memberList)
            {
                if(((TransferMember)m).getModel().equals(((TransferMember)member).getModel()))
                {
                    messages.add((ISubTeamMessage)topicSubTeamMessage);
                }
            }


        }while(topicMember!=null);

            return messages;

        }catch (JMSException e) {
                e.printStackTrace();
                throw new RemoteException(e.getMessage(),e);
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
        topicMember= (Topic) context.lookup(SvmJMSPublisher.memberTopic);
        topicSubTeam = (Topic) context.lookup(SvmJMSPublisher.subTeamTopic);
        //Create Topic verbindung
        tc = tcf.createTopicConnection();

        //Setzen der Client ID
        String id=user.getUsername();
        tc.setClientID(id);
        //Topic Session starten
        ts = tc.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        //Topic DurableSubscriber anmelden beim Message Producer

       topicSubTeamSubscriber= ts.createDurableSubscriber(topicSubTeam,id);
        topicMemberSubscriber=ts.createDurableSubscriber(topicMember, id);
        tc.start();

        } catch (NamingException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage(),e);
        } catch (JMSException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage(),e);
        }
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException, RemoteException {

        try {
            topicMemberSubscriber.close();
            topicSubTeamSubscriber.close();
            ts.close();
            tc.close();
        } catch (JMSException e) {
            e.printStackTrace();
            throw new RemoteException(e.getMessage(),e);
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
            throw new RemoteException(e.getMessage(),e);
        }
    }

    public Context getInitialContext() throws NamingException {
        return new InitialContext(SvmJMSPublisher.getContextTable());
    }
}
