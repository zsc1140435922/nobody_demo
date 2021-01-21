package com.zsc.example.nobody.stream;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-06-28 10:01
 **/
public class StreamDdemo1 {
    List<Student> stuList=null;

    List<String> wordList;
    @Before
    public void init() {
        Random random = new Random();
        stuList = new ArrayList<Student>() {
            {
                for (int i = 0; i < 100; i++) {
                    add(new Student("student" + i, 0, 0,random.nextInt(50) + 50));
                }
            }
        };

        wordList = new ArrayList<String>() {
            {
                add("a");
                add("b");
                add("c");
                add("d");
                add("e");
                add("f");
                add("g");
            }
        };
    }

    @Test
    public void test(){
        List<String> studentList = stuList.stream()
                .filter(x->x.getScore()>85)
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .map(Student::getName)
                .collect(toList());
        System.out.println(studentList);
    }

    /**
     * 1.stream不存储数据
     * 2.stream不改变源数据
     * 3.stream的延迟执行特性
     * 通常我们在数组或集合的基础上创建stream，stream不会专门存储数据，对stream的操作也不会影响到创建它的数组和集合,
     * 对于stream的聚合、消费或收集操作只能进行一次，再次操作会报错
     */
    @Test
    public void test1(){
        Stream<String> stream = Stream.generate(()->"user").limit(20);
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }

    public boolean filter(Student s) {
        System.out.println("begin compare");
        return s.getScore() > 85;
    }


    /**
     * 延迟执行特性，在聚合操作之前都可以添加相应元素
     */
    @Test
    public void test2() {
        Stream<String> words = wordList.stream();
        wordList.add("END");
        long n = words.distinct().count();
        System.out.println(n);
    }

    /**
     * 延迟执行特性，会产生干扰
     * nullPointException
     */
    @Test
    public void test3(){
        Stream<String> words1 = wordList.stream();
        words1.forEach(s -> {
            System.out.println("s->"+s);
            if (s.length() < 4) {
                System.out.println("select->"+s);
                wordList.remove(s);
                System.out.println(wordList);
            }
        });
    }

    /**
     * 通过数组创建流
     */
    @Test
    public void testArrayStream(){
        //1.通过Arrays.stream
        //1.1基本类型
        int[] arr = new int[]{1,2,34,5};
        IntStream intStream = Arrays.stream(arr);
        //1.2引用类型
        Student[] studentArr = new Student[]{new Student("s1",29),new Student("s2",27)};
        Stream<Student> studentStream = Arrays.stream(studentArr);
        //2.通过Stream.of
        Stream<Integer> stream1 = Stream.of(1,2,34,5,65);
        //注意生成的是int[]的流
        Stream<int[]> stream2 = Stream.of(arr,arr);
        stream2.forEach(System.out::println);
    }

    //创建无限流
    @Test
    public void testUnlimitStream(){
        //创建无限流，通过limit提取指定大小
        Stream.generate(()->"number"+new Random().nextInt()).limit(100).forEach(System.out::println);
        Stream.generate(()->new Student("name",10)).limit(20).forEach(System.out::println);
    }

    /**
     * 产生规律的数据
     */
    @Test
    public void testUnlimitStream1(){
        Stream.iterate(0,x->x+1).limit(10).forEach(System.out::println);
        Stream.iterate(0,x->x).limit(10).forEach(System.out::println);
        //Stream.iterate(0,x->x).limit(10).forEach(System.out::println);与如下代码意思是一样的
        Stream.iterate(0, UnaryOperator.identity()).limit(10).forEach(System.out::println);
    }

    /**
     * map把一种类型的流转换为另外一种类型的流
     * 将String数组中字母转换为大写
     */
    @Test
    public void testMap() {
        String[] arr = new String[]{"yes", "YES", "no", "NO"};
        Arrays.stream(arr).map(x -> x.toLowerCase()).forEach(System.out::println);
    }

    //filter：过滤流，过滤流中的元素
    @Test
    public void testFilter(){
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        Arrays.stream(arr).filter(x->x>3&&x<8).forEach(System.out::println);
    }

    /**
     * flapMap：拆解流
     */
    @Test
    public void testFlapMap1() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"e", "f", "c", "d"};
        String[] arr3 = {"h", "j", "c", "d"};
        // Stream.of(arr1, arr2, arr3).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
        Stream.of(arr1, arr2, arr3).flatMap(Arrays::stream).forEach(System.out::println);
    }


    String[] arr1 = {"abc","a","bc","abcd"};
    /**
     * Comparator.comparing是一个键提取的功能
     * 以下两个语句表示相同意义
     */
    @Test
    public void testSorted1_(){
        /**
         * 按照字符长度排序
         */
        Arrays.stream(arr1).sorted((x,y)->{
            if (x.length()>y.length()){
                return 1;
            }

            else if (x.length()<y.length()) {
                return -1;
            }
            else {
                return 0;
            }
        }).forEach(System.out::println);
        Arrays.stream(arr1).sorted(Comparator.comparing(String::length)).forEach(System.out::println);
    }


    @Test
    public void copy(){
        Student s = new Student();
        s.setName("zsc");

        Student s2 = new Student();
        s2.setAge(10);
        s2.setScore(1);
//        BeanUtil.copyProperties(s,s2);
//        System.out.println(s2);

       BeanUtil.copyProperties(s,s2, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        System.out.println(s2);

    }

}
