package svm.logic.jms;

import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.jms.objects.NewMemberMessage;

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
            topicPublisher.send(message);
        }
    }

    private SvmJMSPublisher instance;

    public static final String subTeamTopic = "svm/subTeam";
    public static final String memberTopic = "svm/member";
    public static final String factoryName = "java:tf";

    public static final String provider = "file:///C:/temp";
    public static final String initialFactory = "com.sun.jndi.fscontext.RefFSContextFactory";

    public SvmJMSPublisher getInstance() throws NamingException, JMSException {
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

    public void sendNewMember(IMember member) throws JMSException {
        memberTopicSession.sendMessage(new NewMemberMessage());
    }
}
