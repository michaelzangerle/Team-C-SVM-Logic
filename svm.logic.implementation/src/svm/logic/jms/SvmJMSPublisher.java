package svm.logic.jms;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.jmsobjects.MessageType;
import svm.logic.abstraction.jmsobjects.objects.MemberMessage;
import svm.logic.abstraction.jmsobjects.objects.SubTeamMessage;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;
import svm.logic.implementation.transferobject.TransferMember;
import svm.logic.implementation.transferobject.TransferSubTeam;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * ProjectTeam: Team C
 * Date: 21.11.12
 */
public class SvmJMSPublisher {

    private class MyTopicSession {
        private Topic topic;
        private TopicSession topicSession;
        private TopicPublisher topicPublisher;

        public MyTopicSession(String topicName) throws NamingException, JMSException {
            topic = (Topic) ctx.lookup(topicName);
            topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            topicPublisher = topicSession.createPublisher(topic);
            topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        }

        public void sendMessage(Serializable obj) throws JMSException {
            ObjectMessage message = topicSession.createObjectMessage();
            message.setObject(obj);
            topicPublisher.publish(message);
        }
    }

    private static SvmJMSPublisher instance;

    public static final String subTeamTopic = "svm/subTeam";
    public static final String memberTopic = "svm/member";
    public static final String factoryName = "java:tf";

    public static final String provider = "file:///C:/temp";
    public static final String initialFactory = "com.sun.jndi.fscontext.RefFSContextFactory";

    public static SvmJMSPublisher getInstance() throws NamingException, JMSException {
        if (instance == null) instance = new SvmJMSPublisher();
        return instance;
    }

    public static Hashtable<String, String> getContextTable() {
        Hashtable<String, String> map = new Hashtable<String, String>();
        map.put("java.naming.provider.url", provider);
        map.put("java.naming.factory.initial", initialFactory);
        return map;
    }

    private InitialContext ctx;
    private TopicConnectionFactory connFactory;
    private TopicConnection topicConn;
    private MyTopicSession subTeamTopicSession;
    private MyTopicSession memberTopicSession;

    private SvmJMSPublisher() throws NamingException, JMSException {
        init();
    }

    private void init() throws NamingException, JMSException {
        // Create InitialContext
        ctx = new InitialContext(getContextTable());
        // lookup the topic connection factory
        connFactory = (TopicConnectionFactory) ctx.lookup(factoryName);
        // create a topic connection
        topicConn = connFactory.createTopicConnection();
        topicConn.start();

        // GET Topics
        subTeamTopicSession = new MyTopicSession(subTeamTopic);
        memberTopicSession = new MyTopicSession(memberTopic);
    }

    public void sendNewMember(IMember member) throws JMSException, IllegalGetInstanceException, NoSessionFoundException {
        sendNewMember((ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member));
    }

    public void sendNewMember(ITransferMember member) throws JMSException, NoSessionFoundException {
        IMember messageMember = ((IHasModel<IMember>) member).getModel();
        int sessionId = DomainFacade.generateSessionId();
        DomainFacade.reattachObjectToSession(sessionId, messageMember.getSport());
        DomainFacade.reattachObjectToSession(sessionId, messageMember.getSport().getDepartment());
        int receiver = messageMember.getSport().getDepartment().getDepartmentHead().getUID();
        DomainFacade.closeSession(sessionId);
        memberTopicSession.sendMessage(new MemberMessage(MessageType.NEW, member.getUID(), receiver, "Member "+member+" added!"));
    }

    public void sendMemberAddToSubTeam(IMember member, ISubTeam subTeam) throws JMSException, IllegalGetInstanceException {
        sendMemberAddToSubTeam((ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member), (ITransferSubTeam) TransferObjectCreator.getInstance(TransferSubTeam.class, subTeam));
    }

    public void sendMemberAddToSubTeam(ITransferMember member, ITransferSubTeam subTeam) throws JMSException {
        subTeamTopicSession.sendMessage(new SubTeamMessage(MessageType.ADDED, member.getUID(), subTeam.getUID(),((IHasModel<ISubTeam>) subTeam).getModel().getContest().toString()));
    }

    public void sendMemberRemoveFormSubTeam(IMember member, ISubTeam subTeam) throws IllegalGetInstanceException, JMSException {
        sendMemberRemoveFormSubTeam((ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member), (ITransferSubTeam) TransferObjectCreator.getInstance(TransferSubTeam.class, subTeam));
    }

    public void sendMemberRemoveFormSubTeam(ITransferMember member, ITransferSubTeam subTeam) throws JMSException {
        subTeamTopicSession.sendMessage(new SubTeamMessage(MessageType.REMOVED, member.getUID(), subTeam.getUID(),((IHasModel<ISubTeam>) subTeam).getModel().getContest().toString()));
    }
}
