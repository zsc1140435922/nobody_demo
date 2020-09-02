package com.zsc.example.nobody.exception;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-08-12 09:40
 **/
public abstract class APIException extends KafkaException {

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIException(String message) {
        super(message);
    }

    public APIException(Throwable cause) {
        super(cause);
    }

    public APIException() {
    }
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
