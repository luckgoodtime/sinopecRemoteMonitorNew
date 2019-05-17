package com.lng.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.lng.mail.MailSenderInfo;
import com.lng.mail.SimpleMailSender;

public class MessageReceiver implements MessageListener {

	public void onMessage(Message m) {
		System.out.println("[receive message]"+m);

		ObjectMessage om = (ObjectMessage) m;
		try {

			String value = om.getStringProperty("key");
			System.out.println("收到消息："+value);
				
			System.out.println("准备发送邮件："+om.getStringProperty("key"));
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setToAddress("514580856@qq.com");// 收件人
			mailInfo.setSubject("中国LNG电子商务平台");//邮件主题
			mailInfo.setContent("你好，欢迎<a href='http://www.baidu.com'>登录中国LNG电子商务平台</a>"+value);
			// mailInfo.setFileUrl("E:\\linux\\chinese_11.0.pdf"); // 附件地址
//			SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式邮件
			System.out.println("准备发送邮件完毕：");
			
//			 System.out.println("model:" + om.getJMSDeliveryMode());
//			 System.out.println("destination:" + om.getJMSDestination());
//			 System.out.println("type:" + om.getJMSType());
//			 System.out.println("messageId:" + om.getJMSMessageID());
//			 System.out.println("time:" + om.getJMSTimestamp());
//			 System.out.println("expiredTime:" + om.getJMSExpiration());
//			 System.out.println("priority:" + om.getJMSPriority());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
