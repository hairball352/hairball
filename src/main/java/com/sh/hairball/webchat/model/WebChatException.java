package com.sh.hairball.webchat.model;

import java.sql.SQLException;

public class WebChatException extends RuntimeException {

    public WebChatException() {
    }

    public WebChatException(String message) {
        super(message);
    }

    public WebChatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebChatException(Throwable cause) {
        super(cause);
    }

    public WebChatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}