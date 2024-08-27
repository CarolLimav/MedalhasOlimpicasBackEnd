package com.ifba.email.dto;

import com.ifba.email.models.Email;

public record EmailDto(String mailFrom, String mailTo, String mailSubject, String mailText) {
	
	public EmailDto(Email email) {
		this(email.getMailFrom(), email.getMailTo(), email.getMailSubject(), email.getMailText());
	}
}
