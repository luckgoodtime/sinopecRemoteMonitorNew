package com.lng.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class MessageConvertForSys implements MessageConverter {

	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		System.out.println("[toMessage]");
		ObjectMessage objectMessage = session.createObjectMessage();
		objectMessage.setJMSExpiration(1000);
		objectMessage.setStringProperty("key", String.valueOf(object));

		return objectMessage;
	}

	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		System.out.println("[fromMessage]");
		ObjectMessage objectMessage = (ObjectMessage) message;

		return objectMessage.getObjectProperty("key");
	}
}