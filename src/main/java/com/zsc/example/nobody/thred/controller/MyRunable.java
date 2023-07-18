package com.zsc.example.nobody.thred.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @program: nobody_demo
 * @description: 任务
 * @author: zhangsc
 * @create: 2023-07-18 19:12
 * @Copyright: HOSE合思｜易快报
 **/
@Slf4j
public class MyRunable implements Runnable {

    private Map<String, Thread> currentThreadMap;

    private String uuid;

    public MyRunable(Map<String, Thread> currentThreadMap, String uuid) {
        this.currentThreadMap = currentThreadMap;
        this.uuid = uuid;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        currentThreadMap.put(uuid, thread);
        log.info("uuid:{},thread:{}", uuid, thread.getName());

        try {
            for (int i = 1; i <= 50; i++) {
                log.info("uuid:{},当前执行带第{}次",uuid, i);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            throw new Exception("线程运行停止！");
        }
    }
}

