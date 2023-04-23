package com.azubike.ellipsis.spring_jms.listeners;

import com.azubike.ellipsis.spring_jms.config.JmsConfig;
import com.azubike.ellipsis.spring_jms.model.HelloWorldMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelloListener {
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage , @Headers MessageHeaders headers , Message message){
//        log.info("Consuming message : {}" , helloWorldMessage);

    }
    @JmsListener(destination = JmsConfig.SEND_RECEIVE_QUEUE)
    public void listenAndReply(@Payload  HelloWorldMessage helloWorldMessage , @Headers MessageHeaders headers , Message message,
                               org.springframework.messaging.Message springMessage) throws JMSException {
        final HelloWorldMessage replied_message = HelloWorldMessage.builder().id(UUID.randomUUID()).message("This is a replied message").build();
        log.info("Consuming message : {}" , helloWorldMessage);
        jmsTemplate.convertAndSend(message.getJMSReplyTo() , replied_message);

    }
}
