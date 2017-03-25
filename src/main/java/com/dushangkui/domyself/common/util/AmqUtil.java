package com.dushangkui.domyself.common.util;

import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class AmqUtil {
	private static ConcurrentHashMap<String, ActiveMQQueue> queueMap=new ConcurrentHashMap<String, ActiveMQQueue>(16);
	
	private static ActiveMQQueue getQueue(String destination){
		if(queueMap.get(destination) == null){
			synchronized (queueMap) {
				if(queueMap.get(destination) == null){
					ActiveMQQueue queue=new ActiveMQQueue(destination);
					queueMap.put(destination, queue);
				}
			}
		}
		return queueMap.get(destination);
	}
	public static void send(String destination, final Object message ,JmsTemplate jmsTemplate){
		jmsTemplate.send(getQueue(destination), new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(JsonUtil.toString(message));
			}
		});
	}
}
