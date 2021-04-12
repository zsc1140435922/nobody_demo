package com.zsc.example.nobody.thred.yace;
public interface RequestHandler<T> {

    public void handle(T result);
}