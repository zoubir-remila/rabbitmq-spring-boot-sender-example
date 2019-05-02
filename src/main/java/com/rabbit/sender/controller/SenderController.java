package com.rabbit.sender.controller;

import com.rabbit.sender.service.ServiceSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class SenderController {
    @Autowired
    private ServiceSender serviceSender;


    @GetMapping("/sender")
    public void sendMessage() {
        serviceSender.sendSimpleMessage();
    }

}
