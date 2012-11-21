package svm.logic.implementation.controller;

import svm.logic.abstraction.controller.IMessageController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferMember;
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
import java.util.Properties;

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
    private String propertyUrl="file:///C:/temp2/";

    public MessageController(ITransferAuth user,String propertyUrl) {
        this.user = user;
        this.propertyUrl=propertyUrl;
    }

    @Override
    public List<TransferMessage> update(ITransferMember member){
        List<TransferMessage> messages=new LinkedList<TransferMessage>();
        try {
            ObjectMessage topicMemberMessage= (ObjectMessage)topicMemberSubscriber.receive();

           messages.add(new TransferMessageMember());

            ObjectMessage topicSubTeamMessage= (ObjectMessage)topicSubTeamSubscriber.receive();
            messages.add(new TransferMessageSubTeam());

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
         tcf = (TopicConnectionFactory) context.lookup("ConnectionFactory");
        //Hole Topic
        topicMember= (Topic) context.lookup("topic/member");
        topicSubTeam = (Topic) context.lookup("topic/contest");
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

        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "com.sun.jndi.fscontext.RefFSContextFactory");
        props.setProperty("java.naming.provider.url",propertyUrl);
        return new InitialContext(SvmJMSPublischer);
    }
}
