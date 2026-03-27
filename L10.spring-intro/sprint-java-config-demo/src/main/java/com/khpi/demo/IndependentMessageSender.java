package com.khpi.demo;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class IndependentMessageSender
{
    private IMessageSender _messageSender;

    @Autowired
    IndependentMessageSender(IMessageSender ms) {
        _messageSender = ms;
    }

    public void send() {
        _messageSender.send();
    }
}