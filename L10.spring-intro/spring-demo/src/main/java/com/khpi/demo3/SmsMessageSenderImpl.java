package com.khpi.demo3;

public class SmsMessageSenderImpl implements IMessageSender
{
    private IMessage _msg;

    SmsMessageSenderImpl(final IMessage msg) {
            _msg = msg;
        }

    public void send() {
        System.err.println("[SMS Sender] " + _msg.getText());
    }
}
