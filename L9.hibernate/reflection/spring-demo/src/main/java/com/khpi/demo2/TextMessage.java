package com.khpi.demo2;

public class TextMessage implements IMessage
{
    private String _text;

    public TextMessage(final String msg) {
        _text = msg;
    }

    public String getText() {
        return _text;
    }
}
