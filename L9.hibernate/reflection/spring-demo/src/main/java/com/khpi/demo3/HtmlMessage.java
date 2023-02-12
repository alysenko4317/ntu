package com.khpi.demo3;

public class HtmlMessage implements IMessage
{
    private String _text;

    public HtmlMessage(final String msg) {
        _text = "<html><head><title>Message</title></head><body><h1>" + msg + "</h1></body></html>";
    }

    public String getText() {
        return _text;
    }
}
