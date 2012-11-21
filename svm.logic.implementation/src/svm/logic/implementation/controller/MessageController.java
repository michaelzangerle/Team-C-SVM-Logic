package svm.logic.implementation.controller;

import svm.logic.abstraction.controller.IMessageController;

/**
 * Projectteam : Team C
 * Date: 21.11.12
 */
public class MessageController implements IMessageController, MessageListener {

    //Start Consumer
    System.out.println("----> Start");
    //Init Context
    Context context = TopicConsumer.getInitialContext();
    //Hole topic factory
    TopicConnectionFactory tcf = (TopicConnectionFactory) context.lookup("ConnectionFactory");
    //Hole Topic
    Topic topic = (Topic) context.lookup("topic/test");
    //Create Topic verbindung
    TopicConnection tc = tcf.createTopicConnection();

    //Setzen der Client ID
    String id=getID();
    tc.setClientID(id);
    //Topic Session starten
    TopicSession ts = tc.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
    //Topic DurableSubscriber anmelden beim Message Producer
    ts.createDurableSubscriber(topic,id).setMessageListener(new TopicConsumer());

    tc.start();
    System.out.println("---> Ende");





}
