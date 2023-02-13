package com.khpi.demo3;

public class EmailMessageSenderImpl implements IMessageSender
{
    private IMessage _msg;

    EmailMessageSenderImpl(final IMessage msg) {
            _msg = msg;
        }

    public void send() {
        System.err.println("[E-Mail Sender] " + _msg.getText());
    }
}
