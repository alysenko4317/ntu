package com.khpi.demo;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class IndependentMessageSender
{
    @Autowired
    private IMessageSender _messageSender;

    public void send() {
        _messageSender.send();
    }
}