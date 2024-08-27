package com.ifba.email.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ifba.email.dto.EmailDto;
import com.ifba.email.models.Email;
import com.ifba.email.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	public ResponseEntity<EmailDto> sendEmail(EmailDto dto) {
		Email data = new Email(dto);
		data.setSendDateEmail(LocalDateTime.now());
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(data.getMailFrom());
		message.setTo(data.getMailTo());
		message.setSubject(data.getMailSubject());
		message.setText(data.getMailText());
		emailSender.send(message);
		emailRepository.save(data);
		return ResponseEntity.ok(new EmailDto(data));
		
	}
}
