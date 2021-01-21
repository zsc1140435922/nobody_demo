//package com.zsc.example.nobody.uuid.idleaf.test;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.yunzong.thread.yace.ConcurrentTestUtil;
//import com.yunzong.thread.yace.RequestHandler;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:extendmysqlapplicationContext.xml" })
//public class ExtendMySQLMaxValueIncrementerTest {
//	private static Logger log = Logger.getLogger("test");
//
//	@Autowired
//	// @Qualifier("orderIncrementer")
//	@Qualifier("productNoIncrementer")
//	private DataFieldMaxValueIncrementer incrementer;
//
//	@Test
//	public void test() {
//		int i = 0;
//		while (i < 30) {
//			// System.out.println("long id=" + incrementer.nextLongValue());
//			// System.out.println("int id=" + incrementer.nextIntValue());
//			System.out.println("string id=" + incrementer.nextStringValue());
//			i++;
//		}
//	}
//
//	@Test
//	public void test1() throws InterruptedException, ExecutionException {
//		ConcurrentTestUtil.concurrentTest(2, 2000, new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				return incrementer.nextStringValue();
//			}
//		}, new RequestHandler<String>() {
//			@Override
//			public void handle(String result) {
//				System.out.println("result: " + result);
//				log.info(result);
//			}
//		}, 1000000);
//	}
//}
