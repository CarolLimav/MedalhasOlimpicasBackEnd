package com.ifba.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ifba.email.dto.EmailDto;
import com.ifba.email.service.EmailService;

@RestController
@RequestMapping("/send")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/email")
	public ResponseEntity<EmailDto> enviarEmail(@RequestBody EmailDto emailDto){
		return  emailService.sendEmail(emailDto) ;
	}
}
