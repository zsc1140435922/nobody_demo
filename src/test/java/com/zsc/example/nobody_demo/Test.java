package com.zsc.example.nobody_demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-03-09 10:40
 **/
public class Test {
    public static void main(String[] args) {
//        List<String> a = new ArrayList<>();
//        a.add("1");
//        a.add("2");
//        a.add("3");
//        a.add("4");
//
//        a = a.subList(0, 2);
//
//        List<String> b = new ArrayList<>();
//        b.add("1");
//        b.add("2");
//        b.add("6");
//        a.removeAll(b);
//        System.out.println(a);
//        String w = new String("a");
//        String z = new String("a");
//        System.out.println(w.hashCode() == z.hashCode());
//        System.out.println(w.hashCode());
//
//        System.out.println(BigDecimal.valueOf(1).multiply(new BigDecimal(0.5)).setScale(0, BigDecimal.ROUND_UP).longValue());
//
//
//        int j = 0;
//        for (int i = 0; i < 5; i++) {
//            j = (j++);
//            System.out.println(j);
//
//        }
//        AtomicInteger atomicInteger = new AtomicInteger();
//        atomicInteger.getAndIncrement();
////        System.out.println(j);
//        ConcurrentHashMap m = new ConcurrentHashMap();
//        m.put(1, 1);
//        m.put("asd", "1");


//        String sortStr = "CADFE";
//        char[] arrayCh = sortStr.toCharArray(); //1，把sortStr转换为字符数组
//        Arrays.sort(arrayCh);//2，利用数组帮助类自动排序
//        System.out.println(Arrays.toString(arrayCh));
//        System.out.println(new StringBuffer(sortStr).reverse().toString());


//        try {
//            TimeUnit.SECONDS.sleep(10);
//            System.out.println(new Date());
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Random random = new Random();
//        while (true){
//            System.out.println(random.nextInt(4));
//        }


//        Map<String, String> map = new ConcurrentHashMap<String, String>();
//        map.put("a", "a1");
//        map.put("b", "b1");
//        map.put("c", "c1");
//        map.put("d", "d1");
//        map.put("e", "e1");
//        Iterator<String> iterator = map.keySet().iterator();
//        while (iterator.hasNext()) {
//            String it = iterator.next();
//            if ("b".equals(it)) {
//                map.remove("d");
//            }
//            System.out.println(it);
//        }


//        int[] aaaa = {1, 2, 3, 4};
//        BigDecimal amt = new BigDecimal("0.1234567");
//        System.out.println(amt.setScale(2, BigDecimal.ROUND_HALF_UP));
//
//        Map m1 = new HashMap();
//        m1.put("12", null);
//        System.out.println(m1.getOrDefault("12", "1"));
//
//        BigDecimal num1 = new BigDecimal("10");
//        BigDecimal num2 = new BigDecimal("3");
//        System.out.println(num1.divide(num2, 5, BigDecimal.ROUND_DOWN));
//        System.out.println("----------");
//        System.out.println(m.get("111"));
////        System.out.println(m.get("111").toString());
//        System.out.println(String.valueOf(m.get("111")));
//        System.out.println(StringUtils.isEmpty(m.get("111")));


//        String le = "aabbc";
//        //是否存在相同的字符
//        System.out.println(le.chars().distinct().count() == le.length());
//
//        System.out.println(JSONObject.toJSONString(null));


        ExecutorService singleThreadPool;
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("VoucherVerifyCodeServiceImpl-pool-%d").build();
        singleThreadPool = new ThreadPoolExecutor(3, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
//                test();
            }
        });
        System.out.println("oooooo");


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("j---" + i + "--" + j);
                break;
            }
            System.out.println("i---" + i);
        }

        List<String> list = null;//new ArrayList<>();
//        list.add("zsc");
//        list.add("zsc1");
//        JSONObject shopJson = new JSONObject();
//        shopJson.put("add", list);
//        System.out.println(shopJson);
        System.out.println(CollectionUtils.sizeIsEmpty(list));


        //抢到商品的⽤⼾
//        List<String> shopUsers = new ArrayList<>();
//        //构造很多⽤⼾
//        List<String> users = new ArrayList<>();
//        IntStream.range(0, 100000).parallel().forEach(b -> {
//            users.add("神⽜-" + b);
//        });
//        //初始化库存
//        int nKuCuen = 10;
//        //模拟开抢
//        users.parallelStream().forEach(b -> {
//            String shopUser = "抢单";
//
//        });

        String dd = "12e321%s";
        System.out.println(String.format(dd, "zsc"));

        Map<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("a", "a1");
        map.put("b", "b1");
        map.put("c", "c1");
        map.put("d", "d1");
        map.put("e", "e1");


    }

    public static void test() {
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        } catch (Exception e) {
            System.err.println("xxxxxx");
        }
    }
}
