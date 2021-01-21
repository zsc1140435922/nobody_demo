///**
// *
// */
//package com.zsc.example.nobody.uuid.idleaf.test;
//
//import com.zsc.example.nobody.uuid.idleaf.support.IgniteIdLeafServiceImpl;
//import org.junit.Test;
//
///**
// * @author sunff
// *
// *
// */
//public class IgniteIdLeafServiceTest {
//
//	/**
//	 * 基于IgniteAtomicSequence的分布式ID生成器
//	 * 个规则存在一个风险，就是假设不考虑实际应用和实际性能，如果增加操作业务量特别大，会使这个序列值快速进位，
//	 * 如果某个时间节点宕机后瞬间重启，是有可能存在重启后的初始值小于原来的最大值的，这时就无法保证唯一性了。
//	 * 下面就对这个理论情况下的最大值做一个计算，然后开发者就会知道在自己的应用中如何改进这个规则以满足个性化需求了。
//	 */
//	@Test
//	public void getId() {
//
//		IgniteIdLeafServiceImpl idLeaf = new IgniteIdLeafServiceImpl();
//		idLeaf.setBizTag("order");
//		idLeaf.setZkAddress("localhost:2181");
//		idLeaf.init();
//		while (true) {
//			System.out.println(idLeaf.getId());
//		}
//	}
//}
