package com.lng.jms;

import org.springframework.jms.core.JmsTemplate;

public class MessageSender {
	public JmsTemplate jmsTemplate;

	public void sendMessage(String msg) {
		jmsTemplate.convertAndSend(msg);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}