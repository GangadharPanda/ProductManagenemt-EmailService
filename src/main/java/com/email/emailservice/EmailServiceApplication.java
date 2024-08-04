package com.email.emailservice;

import com.email.emailservice.configs.EmailUtil;
import com.email.emailservice.dtos.EmailDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EmailServiceApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(EmailServiceApplication.class, args);

        EmailDTO emailDTO = new EmailDTO("xosab10997@eixdeal.com", "no.replygangadhar@gmail.com", "Welcome to Channel",
                "Hi Gangadhar , Welcome to Channel");
        // Simplest way to fetch the bean from Static context
        EmailUtil emailUtil = applicationContext.getBean("emailUtil", EmailUtil.class);

        emailUtil.sendEmail(
                emailDTO.getTo(),
                emailDTO.getSubject(),
                emailDTO.getBody()
        );
    }

}
