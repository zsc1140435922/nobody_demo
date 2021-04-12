package com.zsc.example.nobody.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-03-30 15:13
 *
 * StampedLock提供了乐观读锁，可取代ReadWriteLock以进一步提升并发性能；
 * StampedLock是不可重入锁。
 **/
public class StampedLockTest {
    private final StampedLock stampedLock = new StampedLock();

    private int x;
    private int y;

    public void move(int deltaX, int deltaY) {
        long stamp = stampedLock.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp); // 释放写锁
        }
    }

    public int distanceFromOrigin() throws InterruptedException {
        long stamp = stampedLock.tryOptimisticRead(); // 获得一个乐观读锁
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        int currentX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        int currentY = y;
        System.out.println(x + "---" + y + "--version--" + stamp);
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的(100,400)
        Thread.sleep(2000L);
        System.out.println(x + "---" + y + "--version--" + stamp);
        if (!stampedLock.validate(stamp)) { // 检查乐观读锁后是否有其他写锁发生
            stamp = stampedLock.readLock(); // 获取一个悲观读锁
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return currentX + currentY;
    }


    public static void main(String[] args) throws InterruptedException {
        StampedLockTest stampedLockTest = new StampedLockTest();
        stampedLockTest.move(2, 3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(stampedLockTest.distanceFromOrigin());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000L);
        stampedLockTest.move(3, 3);


    }
}
