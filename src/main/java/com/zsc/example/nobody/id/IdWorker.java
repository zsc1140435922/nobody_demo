package com.zsc.example.nobody.id;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-03-08 16:31
 **/
public class IdWorker{

    //下面两个每个5位，加起来就是10位的工作机器id
    private long workerId;    //工作id
    private long datacenterId;   //数据id
    //12位的序列号
    private long sequence;

    public IdWorker(long workerId, long datacenterId, long sequence){
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    //初始时间戳
    private long twepoch = 1288834974657L;

    //长度为5位
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    //最大值
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    //序列号id长度
    private long sequenceBits = 12L;
    //序列号最大值
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    //工作id需要左移的位数，12位
    private long workerIdShift = sequenceBits;
    //数据id需要左移位数 12+5=17位
    private long datacenterIdShift = sequenceBits + workerIdBits;
    //时间戳需要左移位数 12+5+5=22位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    //上次时间戳，初始值为负数
    private long lastTimestamp = -1L;

    public long getWorkerId(){
        return workerId;
    }

    public long getDatacenterId(){
        return datacenterId;
    }

    public long getTimestamp(){
        return System.currentTimeMillis();
    }

    //下一个ID生成算法
    public synchronized long nextId() {
        long timestamp = timeGen();

        //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        //获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        //将上次时间戳值刷新
        lastTimestamp = timestamp;

        /**
         * 返回结果：
         * (timestamp - twepoch) << timestampLeftShift) 表示将时间戳减去初始时间戳，再左移相应位数
         * (datacenterId << datacenterIdShift) 表示将数据id左移相应位数
         * (workerId << workerIdShift) 表示将工作id左移相应位数
         * | 是按位或运算符，例如：x | y，只有当x，y都为0的时候结果才为0，其它情况结果都为1。
         * 因为个部分只有相应位上的值有意义，其它位上都是0，所以将各部分的值进行 | 运算就能得到最终拼接好的id
         */
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    //获取时间戳，并与上次时间戳比较
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    //获取系统时间戳
    private long timeGen(){
        return System.currentTimeMillis();
    }

    //---------------测试---------------
    public static void main(String[] args) {
        IdWorker worker = new IdWorker(1,1,1);
        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());
        }
    }

    /**
     * @program: nobody_demo
     * @description:
     * @author: zhangsc
     * @create: 2020-03-08 16:02
     **/
    public static class RandomTest {
        public static void main(String[] args) {
            Set<Integer> set = new HashSet<>();
            //1。
    //        while (true){
    //            System.out.println(Num());
    //            boolean boo = set.add(Num());
    //            if (!boo){
    //                System.out.println("重复了");
    //            }
    //
    //        }



        }

        public static int Num() {
            int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            Random rand = new Random();
            for (int i = 10; i > 1; i--) {
                int index = rand.nextInt(i);
                int tmp = array[index];
                array[index] = array[i - 1];
                array[i - 1] = tmp;
            }
            int result = 0;
            for (int i = 0; i < 6; i++) {
                result = result * 10 + array[i];
            }
            if (String.valueOf(result).length() == 6) {
                return result;
            } else {
                return Num();
            }
        }
    }

    /**
     * @program: nobody_demo
     * @description:
     * @author: zhangsc
     * @create: 2020-01-06 10:24
     **/
    public static class RandomIp {
        public static String getRandomIp() {

            // ip范围
            int[][] range = { { 607649792, 608174079 }, // 36.56.0.0-36.63.255.255
                    { 1038614528, 1039007743 }, // 61.232.0.0-61.237.255.255
                    { 1783627776, 1784676351 }, // 106.80.0.0-106.95.255.255
                    { 2035023872, 2035154943 }, // 121.76.0.0-121.77.255.255
                    { 2078801920, 2079064063 }, // 123.232.0.0-123.235.255.255
                    { -1950089216, -1948778497 }, // 139.196.0.0-139.215.255.255
                    { -1425539072, -1425014785 }, // 171.8.0.0-171.15.255.255
                    { -1236271104, -1235419137 }, // 182.80.0.0-182.92.255.255
                    { -770113536, -768606209 }, // 210.25.0.0-210.47.255.255
                    { -569376768, -564133889 }, // 222.16.0.0-222.95.255.255
            };

            Random rdint = new Random();
            int index = rdint.nextInt(10);
            String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
            return ip;
        }

        /*
         * 将十进制转换成IP地址
         */
        public static String num2ip(int ip) {
            int[] b = new int[4];
            String x = "";
            b[0] = (int) ((ip >> 24) & 0xff);
            b[1] = (int) ((ip >> 16) & 0xff);
            b[2] = (int) ((ip >> 8) & 0xff);
            b[3] = (int) (ip & 0xff);
            x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

            return x;
        }

        public static void main(String[] args) {
            int count = 100000;
            for (int i = 0; i < count; i++) {
                String randomIp = getRandomIp();
                System.err.println(randomIp);
            }

        }
    }
}