package com.vetologic.cms.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.vetologic.cms.dto.user.UserDto;

@Component
public class MailUtil {
	@Autowired
	private JavaMailSender mailSender;

	public void sendCreateUserEmail(UserDto user) {
		final String emailId = user.getEmailId();
		final String username = user.getUsername();
		final String password = user.getPassword();
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setTo(emailId);
				message.setSubject("Cms ! A new account has been created for you");
				message.setText("Welcome to CMS !" + "<br>Your Username is: <b>" + username + "</b> and <br>"
						+ "Password is: <b>" + password + "</b>", true);
				// message.addCc(adminEmailId);
			}
		});
	}

	public void sendChangePasswordEmail(UserDto user, String newPassword) {
		final String emailId = user.getEmailId();
		final String username = user.getUsername();
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setTo(emailId);
				message.setSubject("Cms ! " + username + " has changed the password");
				message.setText("<br>Password has been changed successfully for Cms !<br>New Password is: <b>"
						+ newPassword + "</b>", true);
			}
		});
	}

	public void sendForgotPasswordEmail(UserDto user, String adminEmailId) {
		final String emailId = user.getEmailId();
		final String username = user.getUsername();
		final String password = user.getPassword();
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setTo(emailId);
				message.setSubject("Cms ! " + username + " has Reset the Password");
				message.setText("<br>Password has been successfully reset for Cms !<br>New Password is: <b>" + password
						+ "</b>", true);
				message.addCc(adminEmailId);
			}
		});
	}

}
