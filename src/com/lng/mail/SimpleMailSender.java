package com.lng.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 简单邮件发送器
 */
public class SimpleMailSender extends Thread {

	private MailSenderInfo mailInfo;

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public boolean sendHtmlMail() {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		// Session sendMailSession =
		// Session.getDefaultInstance(pro,authenticator);
		// //不要用getDefaultInstance,这样只能使用一种邮箱，再想改成别的邮箱就不行了
		Session sendMailSession = Session.getInstance(pro, authenticator); // 用这个，程序里可以切换其他邮箱，前提是这些邮箱要开通smtp协议，这是MailSenderInfo里写死的。

		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 添加附件
			// 创建一新的MimeBodyPart
			MimeBodyPart mdp = new MimeBodyPart();
			if (mailInfo.getFileUrl() != null) {
				// 得到文件数据源
				FileDataSource fds = new FileDataSource(mailInfo.getFileUrl());
				// 得到附件本身并至入BodyPart
				mdp.setDataHandler(new DataHandler(fds));
				// 得到文件名同样至入BodyPart
				mdp.setFileName(fds.getName());
				mainPart.addBodyPart(mdp);
			}

			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public void run() {
		sendHtmlMail();
	}

	public MailSenderInfo getMailInfo() {
		return mailInfo;
	}

	public void setMailInfo(MailSenderInfo mailInfo) {
		this.mailInfo = mailInfo;
	}

	public static void send(String address, String subject, String content) {

		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(address);// 收件人
		mailInfo.setSubject(subject);// 邮件主题
		mailInfo.setContent(content);// 邮件内容
		// mailInfo.setFileUrl("E:\\linux\\chinese_11.0.pdf"); // 附件地址

		SimpleMailSender s = new SimpleMailSender();
		s.setMailInfo(mailInfo);
		s.start();

	}

	public static void main(String[] args) {
		System.err.println("邮件发送开始");
		SimpleMailSender.send("514580856@qq.com", "中国LNG电子商务平台",
				"你好，欢迎<a href='http://www.baidu.com'>登录中国LNG电子商务平台</a>");
		System.err.println("邮件发送结束");
		System.err.println("邮件发送开始");
		SimpleMailSender.send("514580856@qq.com", "中国LNG电子商务平台2",
				"你好，欢迎<a href='http://www.baidu.com'>登录中国LNG电子商务平台222</a>");
		System.err.println("邮件发送结束");
		
	}
}
