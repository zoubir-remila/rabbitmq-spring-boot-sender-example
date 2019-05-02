package com.rabbit.sender.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbit.sender.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSender.class);

    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendSimpleMessage() {
        UserDto user = new UserDto("JHON", "DOE", 99);
        try {
            LOGGER.info("----------------------------------------------");
            LOGGER.info("******** Sending message to the queue ********");
            LOGGER.info("|          firstName : {}                   |", user.getFirstName());
            LOGGER.info("|          lastName : {}                     |", user.getLastName());
            LOGGER.info("|              age : {}                       |", user.getAge());
            LOGGER.info("----------------------------------------------");
            rabbitTemplate.convertAndSend(queue.getName(), objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            LOGGER.error("An Error Occurred the Process");
        }

    }
}
