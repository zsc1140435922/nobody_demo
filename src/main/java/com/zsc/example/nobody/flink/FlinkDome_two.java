//package com.zsc.example.nobody.flink;
//
//import org.apache.flink.api.common.functions.FlatMapFunction;
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.api.java.utils.ParameterTool;
//import org.apache.flink.streaming.api.datastream.DataStream;
//import org.apache.flink.streaming.api.datastream.DataStreamSource;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.api.windowing.time.Time;
//import org.apache.flink.streaming.connectors.redis.RedisSink;
//import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
//import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
//import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
//import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
//import org.apache.flink.util.Collector;
//import redis.clients.jedis.Jedis;
//
///**
// * 1.在终端 执行： nc -l 9000
// * 2. 启动FlinkDome_two
// *
// * @program: 终端数据通过flink 写入redis
// * @description:
// * @author: zhangsc
// * @create: 2020-05-20 15:25
// **/
//public class FlinkDome_two {
//    static Jedis jedis = null;
//    public static void main(String[] args) throws Exception {
//
//        //定义socket的端口号
//        int port;
//
//        try {
//            jedis = new Jedis("127.0.0.1",6379);
////            FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder()
////                    .setNodes(new HashSet<InetSocketAddress>(Arrays.asList(new InetSocketAddress(5601)))).build();
//            ParameterTool parameterTool = ParameterTool.fromArgs(args);
//            port = parameterTool.getInt("port");
//        } catch (Exception e) {
//            System.err.println("没有指定port参数，使用默认值9000");
//            port = 9000;
//        }
//
//        //获取运行环境
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//
//        //连接socket获取输入的数据
//        DataStreamSource<String> text = env.socketTextStream("localhost", port, "\n");
//
//        //计算数据
//        DataStream<WordWithCount> windowCount = text.flatMap(new FlatMapFunction<String, WordWithCount>() {
//            @Override
//            public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
//                String[] splits = value.split("\\s");
//                for (String word : splits) {
//                    WordWithCount n = new WordWithCount(word, 1L);
//                    out.collect(n);
//                }
//            }
//        })//打平操作，把每行的单词转为<word,count>类型的数据
//                .keyBy("word")//针对相同的word数据进行分组
//                .timeWindow(Time.seconds(10), Time.seconds(5))//指定计算数据的窗口大小和滑动窗口大小
//                .sum("count");
//
//        DataStream<Tuple2<String, Integer>> counts = text.flatMap(new LineSplitter()).keyBy(0).sum(1);
//        //实例化Flink和Redis关联类FlinkJedisPoolConfig，设置Redis端口
//        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();
//        //实例化RedisSink，并通过flink的addSink的方式将flink计算的结果插入到redis
//        counts.addSink(new RedisSink<Tuple2<String, Integer>>(conf,new RedisExampleMapper()));
//
//        //把数据打印到控制台
////        windowCount.print()
////                .setParallelism(1);//使用一个并行度
//        counts.print();
//        //注意：因为flink是懒加载的，所以必须调用execute方法，上面的代码才会执行
//        env.execute("streaming word count");
//
//    }
//
//    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
//        private static final long serialVersionUID = 1L;
//        @Override
//        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
//            String[] tokens = value.toLowerCase().split("\\W+");
//            for (String token : tokens) {
//                if (token.length() > 0) {
//                    out.collect(new Tuple2<String, Integer>(token, 1));
//                }
//            }
//        }
//    }
//
//    //指定Redis key并将flink数据类型映射到Redis数据类型
//    public static final class RedisExampleMapper implements RedisMapper<Tuple2<String,Integer>> {
//        @Override
//        public RedisCommandDescription getCommandDescription() {
//            return new RedisCommandDescription(RedisCommand.HSET, "flink");
//        }
//
//        @Override
//        public String getKeyFromData(Tuple2<String, Integer> data) {
//            return data.f0;
//        }
//        @Override
//        public String getValueFromData(Tuple2<String, Integer> data) {
//            return data.f1.toString();
//        }
//    }
//
//    /**
//     * 主要为了存储单词以及单词出现的次数
//     */
//    public static class WordWithCount {
//        public String word;
//        public long count;
//
//        /**
//         * 获取word
//         *
//         * @return the word
//         */
//        public String getWord() {
//            return word;
//        }
//
//        /**
//         * 设置word
//         *
//         * @param the word
//         */
//        public void setWord(String word) {
//            this.word = word;
//        }
//
//        /**
//         * 获取count
//         *
//         * @return the count
//         */
//        public long getCount() {
//            return count;
//        }
//
//        /**
//         * 设置count
//         *
//         * @param the count
//         */
//        public void setCount(long count) {
//            this.count = count;
//        }
//
//        public WordWithCount() {
//        }
//
//        public WordWithCount(String word, long count) {
//            this.word = word;
//            this.count = count;
//        }
//
//        @Override
//        public String toString() {
//            return "WordWithCount{" +
//                    "word='" + word + '\'' +
//                    ", count=" + count +
//                    '}';
//        }
//    }
//}
