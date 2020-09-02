package com.zsc.example.nobody.exception;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-08-12 09:39
 **/
public class KafkaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KafkaException(String message, Throwable cause) {
        super(message, cause);
    }

    public KafkaException(String message) {
        super(message);
    }

    public KafkaException(Throwable cause) {
        super(cause);
    }

    public KafkaException() {
    }
}

