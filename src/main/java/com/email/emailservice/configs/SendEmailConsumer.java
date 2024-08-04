package com.email.emailservice.configs;

import org.springframework.stereotype.Service;

@Service
public class SendEmailConsumer {

    /*
    1. Add Kafka Support  - Maven dependency for Spring Kafka Support
    2. @KafkaListener(topics = "sendEmail", groupId = "emailService")
       public void listen(String emailDTOAsJSONString) {
            // Handle the message here
            System.out.println("Received message: " + emailDTOAsJSONString);
            // We can parse the message, create EmailDTO, and send an email
            //Code to send an Email to the user.

            EmailDTO emailDto = null;
            try {
                emailDto = objectMapper.readValue(message, EmailDTO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            emailUtil.sendEmail(
                    emailDto.getTo(),
                    emailDto.getSubject(),
                    emailDto.getBody()
            );
        }
     */
}
