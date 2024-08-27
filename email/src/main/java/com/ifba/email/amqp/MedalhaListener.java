package com.ifba.email.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ifba.email.dto.EmailDto;
import com.ifba.email.dto.PaisEmailDto;
import com.ifba.email.models.Email;
import com.ifba.email.service.EmailService;

@Component
public class MedalhaListener {
	@Autowired
	private EmailService emailService;
	
	@RabbitListener(queues = "email.notificacao")
	public void recebeMensagem(@Payload PaisEmailDto paisEmailDto) {
	
		System.out.println("Recebi a mensagem " + paisEmailDto.toString());
		StringBuilder sb = new StringBuilder("E olha a notícia boa saindo do forno: Medalha nova cadastrada para  " + paisEmailDto.nomePais() + "!!!!\n");
		sb.append("O nosso destaque é " + paisEmailDto.nomeAtleta() + ". Que atleta de alto nível, meus amigos. Super merecido!\n");
		sb.append("A medalha é de " + paisEmailDto.tipoMedalha() + " e o esporte é o " + paisEmailDto.esporte() + ".\n");
		sb.append("E para quem não conhece o esporte, confere aí nosso compilado: " + paisEmailDto.descricao() + "\n");
		sb.append("- Paris, 2024");
		
		
		for (String enderecoEmail : paisEmailDto.emails()){
			Email email = new Email();
			email.setMailFrom("ianbernardolim@gmail.com");
			email.setMailTo(enderecoEmail);
			email.setMailSubject("Medalha Nova!");
			email.setMailText( sb.toString());
			EmailDto emailDto = new EmailDto(email);
			emailService.sendEmail(emailDto) ;
		}
		
		
		return;
	}
}
