package com.zsc.example.nobody.Switch;


/**
 * @program:
 * @description: 二进制开关
 * 使用一个int的二进制形式表示最多32个（32位）开关的状态。10111从右向左是开开开关开，一共五位。10进制用23表示
 * 10111 = 2^4+2^2+2^1+2^0=16+4+2+1=23
 * 第1个开关（10111右边第1位） 23&2^(1-1)=23&1=1   二进制是1   位运算 1>>(1-1)=1 表示开
 * 第2个开关（10111右边第2位） 23&2^(2-1)=23&2=2   二进制是10  位运算 2>>(2-1)=1 表示开
 * 第3个开关（10111右边第3位） 23&2^(3-1)=23&4=4   二进制是100  位运算 4>>(3-1)=1 表示开
 * 第4个开关（10111右边第4位） 23&2^(4-1)=23&8=0   二进制是0    表示关闭
 * 第5个开关（10111右边第5位） 23&2^(5-1)=23&16=16   二进制是10000  位运算 16>>(5-1)=1 表示开
 * 第n个开关（10111右边第n位） 23&2^(n-1)=a         二进制是?    位运算 a>>(n-1)=1 表示X
 * @author: zhangsc
 * @create: 2020-02-18 19:05
 **/
public class BinarySwitch {
    /**
     * 查询当前index状态，［0,1］；
     * 查看index位置上的开关状态 0=false 1=true
     * @param value 存储的开关数据int32
     * @param index 第几位
     * @return 当前位置的值
     */
    public static boolean value_bit(int value, int index) {
        return (value >> --index & 1) > 0;
    }

    /**
     * 处理后的存储数据 int32
     *
     * @param value 存储的开关数据int32
     * @param index 第几位
     * @param set   开关状态
     * @return 当前位置的值［0,1］
     */
    public static int value_bit(int value, int index, boolean set) {
        index--;
        if (set) {
            value = 1 << index | value;
        } else {
            value = ~(1 << index) & value;
        }
        return value;
    }


    public static void main(String[] args) {
        int data = 0;
//        data = BinarySwitch.value_bit(data, 1, true);
//        data = BinarySwitch.value_bit(data, 2, true);
        data = BinarySwitch.value_bit(data, 3, true);
//        data = BinarySwitch.value_bit(data, 4, false);
//        data = BinarySwitch.value_bit(data, 5, true);
        System.out.println("data:" + data + " data_bin:" + Integer.toBinaryString(data));

        data = BinarySwitch.value_bit(data, 1, false);
        data = BinarySwitch.value_bit(data, 3, false);
        data = BinarySwitch.value_bit(data, 4, true);
        System.out.println("data:" + data + " data_bin:" + Integer.toBinaryString(data));

        System.out.println(BinarySwitch.value_bit(data, 1));
        System.out.println(BinarySwitch.value_bit(data, 2));
        System.out.println(BinarySwitch.value_bit(data, 3));
        System.out.println(BinarySwitch.value_bit(data, 4));
        System.out.println(BinarySwitch.value_bit(data, 5));


        /** out :
         data:23 data_bin:10111
         data:26 data_bin:11010
         false
         true
         false
         true
         true
         */
        System.out.println(Integer.toBinaryString(64));
        System.out.println(Integer.toBinaryString(32));
        System.out.println(64&32);//0

    }
}
