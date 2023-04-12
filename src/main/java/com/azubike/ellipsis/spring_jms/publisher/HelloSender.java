package com.azubike.ellipsis.spring_jms.publisher;

import com.azubike.ellipsis.spring_jms.config.JmsConfig;
import com.azubike.ellipsis.spring_jms.model.HelloWorldMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class HelloSender {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
//        final HelloWorldMessage message = HelloWorldMessage.builder().id(UUID.randomUUID()).message("This is a new message").build();
//        log.info("Sending message: {}", message);
//        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessageRecieve() throws JMSException {
        final HelloWorldMessage message = HelloWorldMessage.builder().id(UUID.randomUUID()).message("This is a new message").build();
        log.info("Sending message: {}", message);
        final Message reply = jmsTemplate.sendAndReceive(JmsConfig.SEND_RECEIVE_QUEUE, session -> {
            Message helloMessage = null;
            try {
                helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                helloMessage.setStringProperty("_type", HelloWorldMessage.class.getName());
                return helloMessage;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        log.info("Getting reply : {}", Objects.requireNonNull(reply).getBody(String.class));
    }
}
