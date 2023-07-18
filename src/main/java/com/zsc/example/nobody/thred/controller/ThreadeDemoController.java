package com.zsc.example.nobody.thred.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: nobody_demo
 * @description: Java任务启动与停止的实现过程
 * @author: zhangsc
 * @create: 2023-07-18 19:10
 * @Copyright: HOSE合思｜易快报
 **/
@Slf4j
@RestController
@RequestMapping("/thread")
public class ThreadeDemoController {
    private static Map<String, Thread> concurrentHashMap = new ConcurrentHashMap<>();

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public String startExecute(String uuid) {
        try {
            MyRunable myRunable = new MyRunable(concurrentHashMap, uuid);
            myRunable.run();
        } catch (Exception e) {
            return "运行结束";
        }
        // 运行成功之后将当前线程从map中移除
        concurrentHashMap.remove(uuid);
        return "运行成功";
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public String stopExecute(String uuid) {
        Thread thread = concurrentHashMap.get(uuid);
        if (Objects.isNull(thread)) {
            return "任务未在运行中";
        }
        log.info("停止时找到的线程名字:{}", thread.getName());
        try {
            if (thread.isAlive() && !thread.isInterrupted()) {
                thread.interrupt();
                log.info("{}线程已经停止，线程状态:{}", thread, thread.isInterrupted());
            }
        } catch (Exception e) {
            log.error("{}线程停止失败", thread);
            return "停止运行失败!";
        }
        return "停止运行成功!";
    }
}
