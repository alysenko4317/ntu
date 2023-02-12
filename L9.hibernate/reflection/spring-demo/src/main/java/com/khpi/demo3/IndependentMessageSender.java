package com.khpi.demo3;

import org.springframework.beans.factory.annotation.Autowired;

public class IndependentMessageSender
{
    @Autowired
    private IMessageSender _messageSender;

    public void send() {
        _messageSender.send();
    }
}