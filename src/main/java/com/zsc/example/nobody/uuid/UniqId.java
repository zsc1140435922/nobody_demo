package com.zsc.example.nobody.uuid;

import org.apache.commons.lang.StringUtils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-19 16:19
 **/
public class UniqId {

    private static String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static int scale = 62;
    private AtomicLong increase6 = new AtomicLong(0);
    private AtomicLong increase7 = new AtomicLong(0);
    private AtomicLong increase5 = new AtomicLong(0);
    private AtomicLong increase3 = new AtomicLong(0);
    private static UniqId me = new UniqId();
    private String idcTag;
    private final Random random = new SecureRandom();

    private UniqId() {
        String idc = null;//DockerENV.getIDC();
        if(idc==null || idc.equals("")) {
            idcTag = "7";
        }else {
            if(idc.equals("yzsc-hd2")) {
                idcTag = "9";
            }else if(idc.equals("yzsc-hb2")) {
                idcTag = "8";
            }else {
                idcTag = "7";
            }
        }

    }

    /**
     * 获取UniqID实例
     *
     * @return UniqId
     */
    public static UniqId getInstance() {
        return me;
    }

    /**
     * 获得UniqId
     *
     * @return uniqTime-randomNum-hostAddr-threadId
     */
    public String getUniqID16() {
        final StringBuffer sb = new StringBuffer();
        final String t = getCurrentDatetime2();
        sb.append(idcTag);
        sb.append(t);
        sb.append(getIncreaseCode3());
        return sb.toString();
    }
    /**
     * <pre>
     * 获取29位全局唯一ID
     * 机房标识：华东9、华北8 其他7
     * 实现原理：
     * 机房标识+yyMMddHHmmssSSS+6位自增+7位随机
     * </pre>
     * @return 示例值：71907301652419030000009010571
     */
    public String getUniqID29() {
        final StringBuffer sb = new StringBuffer();
        final String t = getCurrentDatetime();
        sb.append(idcTag);
        sb.append(t);
        sb.append(getIncreaseCode6());
        sb.append(random.nextInt(8999999) + 1000000);
        return sb.toString();
    }
    /**
     * <pre>
     * 获取31位全局唯一ID
     * 机房标识：华东9、华北8 其他7
     * 实现原理：
     * 机房标识+yyyyMMddHHmmssSSS+6位自增+7位随机
     * </pre>
     * @return 示例值：7201907301652419030000015606853
     */
    public String getUniqID() {
        final StringBuffer sb = new StringBuffer();
        final String t = getCurrentDatetime3();
        sb.append(idcTag);
        sb.append(t);
        sb.append(getIncreaseCode6());
        sb.append(random.nextInt(8999999) + 1000000);
        return sb.toString();
    }
    /**
     * <pre>
     * 获取16位的全局唯一ID
     * 实现原理：
     * yyyyMMddHHmmss+5位自增+8位随机 转62进制
     * </pre>
     * @return 返回示例值：SOOu8A4NQ0t6laZD
     */
    public String get62Uniq2ID16() {
        final StringBuffer sb = new StringBuffer();
        final String t = getCurrentDatetime4();
        sb.append(encode(Long.valueOf(t),8));
        sb.append(encode(Long.valueOf(getIncreaseCode5()),3));
        sb.append(encode(random.nextInt(99999999) + 10000000,5));
        return sb.toString();
    }
    /**
     * <pre>
     * 获取带机房标识的16位的全局唯一ID
     * 机房标识：华东9、华北8 其他7
     * 实现原理：
     * 机房标识+yyyyMMddHHmmss+3位自增+8位随机 转62进制
     * </pre>
     * @return 返回示例值：75jT5K896001nFmc
     */
    public String getIDC62Uniq2ID16() {
        final StringBuffer sb = new StringBuffer();
        final String t = getCurrentDatetime4();
        sb.append(idcTag);
        sb.append(encode(Long.valueOf(t),8));
        sb.append(encode(Long.valueOf(getIncreaseCode3()),2));
        sb.append(encode(random.nextInt(99999999)+ 10000000,5));
        return sb.toString();
    }
    /**
     * 3位自增
     * @param length
     * @return
     */
    private String getIncreaseCode3() {
        increase3.compareAndSet(1000, 0);
        long curentValue = increase3.getAndIncrement();
        return StringUtils.leftPad(String.valueOf(curentValue), 3, "0");
    }
    /**
     * 5位自增
     * @param length
     * @return
     */
    private String getIncreaseCode5() {
        increase5.compareAndSet(90000, 1);
        long curentValue = increase5.getAndIncrement();
        return StringUtils.leftPad(String.valueOf(curentValue), 5, "0");
    }
    /**
     * 6位自增
     * @param length
     * @return
     */
    private String getIncreaseCode6() {
        increase6.compareAndSet(900000, 1);
        long curentValue = increase6.getAndIncrement();
        return StringUtils.leftPad(String.valueOf(curentValue), 6, "0");
    }
    /**
     * 7位自增
     * @param length
     * @return
     */
    private String getIncreaseCode7() {
        increase7.compareAndSet(9000000, 1);
        long curentValue = increase7.getAndIncrement();
        return StringUtils.leftPad(String.valueOf(curentValue), 7, "0");
    }
    /**
     *
     * @return yyyyMMddHHmmss
     */
    private static String getCurrentDatetime4() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }
    /**
     *
     * @return yyyyMMddHHmmssSSS
     */
    private static String getCurrentDatetime3() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(date);
    }
    /**
     *
     * @return yyMMddHHmmss
     */
    private static String getCurrentDatetime2() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        return df.format(date);
    }
    /**
     * yyMMddHHmmssSSS
     * @return
     */
    private static String getCurrentDatetime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssSSS");
        return df.format(date);
    }
    private static String encode(long num, int length) {
        StringBuilder sb = new StringBuilder();
        int remainder = 0;

        while (num > scale - 1) {
            /**
             * 对 scale 进行求余，然后将余数追加至 sb 中，由于是从末位开始追加的，因此最后需要反转（reverse）字符串
             */
            remainder = Long.valueOf(num % scale).intValue();
            sb.append(chars.charAt(remainder));

            num = num / scale;
        }

        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        String value = sb.reverse().toString();
        return StringUtils.leftPad(value, length, '0');
    }
    public static void main(String[] args) {
        String uid = UniqId.getInstance().get62Uniq2ID16();
        String uid2 = UniqId.getInstance().getIDC62Uniq2ID16();
        System.out.println(uid);
        System.out.println(uid2);
        String uid3 = UniqId.getInstance().getUniqID29();
        System.out.println(uid3);
        String uid4 = UniqId.getInstance().getUniqID();
        System.out.println(uid4);
    }
}
