package svm.logic.implementation.controller;

import svm.logic.abstraction.controller.IMessageController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.jmsobjects.IMessage;
import svm.logic.abstraction.jmsobjects.INewMemberMessage;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferMember;
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
    private ITransferMessage transferMessage;
    private ITransferAuth user;
    private  TopicConnectionFactory tcf;
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
    public List<IMessage> update(ITransferMember member){
        List<IMessage> messages=new LinkedList<IMessage>();
        try {
            ObjectMessage topicMemberMessage;
            do{

                topicMemberMessage= (ObjectMessage)topicMemberSubscriber.receiveNoWait();
                IMessage message=(IMessage)topicMemberMessage;



            }while(topicMember!=null);

            ObjectMessage topicSubTeamMessage;
            do{
                topicSubTeamMessage= (ObjectMessage)topicSubTeamSubscriber.receiveNoWait();
                IMessage message=(IMessage )topicSubTeamMessage;

            }while(topicMember!=null);

            return messages;

        } catch (JMSException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
