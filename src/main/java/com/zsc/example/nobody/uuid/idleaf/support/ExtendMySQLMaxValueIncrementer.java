//package com.zsc.example.nobody.uuid.idleaf.support;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.FutureTask;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.concurrent.locks.ReentrantLock;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.framework.recipes.locks.InterProcessMutex;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//import org.apache.log4j.Logger;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowCallbackHandler;
//import org.springframework.jdbc.support.incrementer.AbstractColumnMaxValueIncrementer;
//
//import com.alibaba.fastjson.JSONObject;
////import com.yunzong.zookeeper.distributedlock.NoFairLockDriver;
//
//public class ExtendMySQLMaxValueIncrementer extends AbstractColumnMaxValueIncrementer {
//
//	public ExtendMySQLMaxValueIncrementer() {
//	}
//
//	public ExtendMySQLMaxValueIncrementer(DataSource dataSource, String incrementerName, String columnName) {
//		super(dataSource, incrementerName, columnName);
//	}
//
//	@Override
//	protected long getNextKey() throws DataAccessException {
//		if (asynLoadingSegment) {
//			return asynGetId();
//		} else {
//			return synGetId();
//		}
//	}
//
//	public String nextStringValue() {
//		 String uniq = getUniqID();
//		if (asynLoadingSegment) {
//			long id = asynGetId();//asynGetId();
//			// return uniq + String.valueOf(id);
//			return String.valueOf(id);
//		} else {
//			long id = synGetId();
//			// return uniq + String.valueOf(id);
//			return String.valueOf(id);
//		}
//	}
//
//	public String getUniqID() {
//		final StringBuffer sb = new StringBuffer();
//		final String t = getUniqTime();
//		sb.append(t);
//		sb.append(hostAddr);
//		sb.append(getUniqThreadCode(9));
//		if (log.isDebugEnabled()) {
//			log.debug("[UniqID.getUniqID]" + sb.toString());
//		}
//		return sb.toString();
//	}
//
//	private static String timestamp2Date(long timestamp) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//		return dateFormat.format(new Date(timestamp));
//	}
//
//	private String getUniqTime() {
//		return timestamp2Date(System.currentTimeMillis());
//	}
//
//	private String getUniqThreadCode(int length) {
//		String threadCode = StringUtils.left(String.valueOf(Thread.currentThread().hashCode()), length);
//		if (log.isDebugEnabled()) {
//			log.debug("[UniqID.getUniqThreadCode]" + threadCode + "----length:" + threadCode.length());
//		}
//		return StringUtils.leftPad(threadCode, length, "0");
//	}
//	// ------------------
//
//	// 创建线程池
//	private static ExecutorService es = Executors.newSingleThreadExecutor();
//	private FutureTask<Boolean> asynLoadSegmentTask = null;
//
//	// private long asynGetId() {
//	// String Id = UUID.randomUUID() + "";
//	// final String trackId = Id.replaceAll("-", "");
//	// try {
//	// log.info(trackId + " start " + currentId.longValue() +
//	// JSONObject.toJSONString(segment));
//	// lock.lock();
//	// if (segment[index()].getMiddleId().equals(currentId.longValue()) ||
//	// segment[index()].getMaxId().equals(currentId.longValue())) {
//	//
//	// if (segment[index()].getMiddleId().equals(currentId.longValue())) {
//	// log.info(trackId + " 50% " + currentId.longValue() +
//	// JSONObject.toJSONString(segment));
//	// // 前一段使用了50%
//	//
//	// asynLoadSegmentTask = new FutureTask<>(new Callable<Boolean>() {
//	//
//	// @Override
//	// public Boolean call() throws Exception {
//	// final int currentIndex = reIndex();
//	// segment[currentIndex] = doUpdateNextSegment();
//	// log.info(trackId + " 50% ");
//	// return true;
//	// }
//	//
//	// });
//	// es.submit(asynLoadSegmentTask);
//	// }
//	// log.info(trackId + " 最新号段 " + currentId.longValue() +
//	// JSONObject.toJSONString(segment));
//	// if (segment[index()].getMaxId().equals(currentId.longValue())) {
//	// boolean loadingResult;
//	// try {
//	// loadingResult = asynLoadSegmentTask.get();
//	// if (loadingResult) {
//	// setSw(!isSw()); // 切换
//	// currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//	// log.info(trackId + " 切换1 OK ");
//	// }
//	// log.info(trackId + " 切换号段1 " + currentId.longValue() +
//	// JSONObject.toJSONString(segment));
//	// } catch (InterruptedException e) {
//	//
//	// e.printStackTrace();
//	// // 强制同步切换
//	// final int currentIndex = reIndex();
//	// segment[currentIndex] = doUpdateNextSegment();
//	// setSw(!isSw()); // 切换
//	// currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//	// log.info(trackId + " 切换号段2 " + currentId.longValue() +
//	// JSONObject.toJSONString(segment));
//	// } catch (ExecutionException e) {
//	//
//	// e.printStackTrace();
//	// // 强制同步切换
//	// final int currentIndex = reIndex();
//	// segment[currentIndex] = doUpdateNextSegment();
//	// setSw(!isSw()); // 切换
//	// currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//	// log.info(trackId + " 切换号段3 " + currentId.longValue() +
//	// JSONObject.toJSONString(segment));
//	// }
//	// }
//	//
//	// }
//	// } finally {
//	// lock.unlock();
//	// }
//	//
//	// long id = currentId.incrementAndGet();
//	// log.info(trackId + " result " + id);
//	// return id;
//	//
//	// }
//
//	private long asynGetId() {
//		String Id = UUID.randomUUID() + "";
//		final String trackId = Id.replaceAll("-", "");
//		try {
//			log.info(trackId + " start " + currentId.longValue() + JSONObject.toJSONString(segment));
//			distributedLock.acquire();
//			if (segment[index()].getMiddleId().equals(currentId.longValue()) || segment[index()].getMaxId().equals(currentId.longValue())) {
//
//				if (segment[index()].getMiddleId().equals(currentId.longValue())) {
//					log.info(trackId + " 50% " + currentId.longValue() + JSONObject.toJSONString(segment));
//					// 前一段使用了50%
//
//					asynLoadSegmentTask = new FutureTask<>(new Callable<Boolean>() {
//
//						@Override
//						public Boolean call() throws Exception {
//							final int currentIndex = reIndex();
//							segment[currentIndex] = doUpdateNextSegment();
//							log.info(trackId + " 50% ");
//							return true;
//						}
//
//					});
//					es.submit(asynLoadSegmentTask);
//				}
//				log.info(trackId + " 最新号段 " + currentId.longValue() + JSONObject.toJSONString(segment));
//				if (segment[index()].getMaxId().equals(currentId.longValue())) {
//					boolean loadingResult;
//					try {
//						loadingResult = asynLoadSegmentTask.get();
//						if (loadingResult) {
//							setSw(!isSw()); // 切换
//							currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//							log.info(trackId + " 切换1 OK ");
//						}
//						log.info(trackId + " 切换号段1 " + currentId.longValue() + JSONObject.toJSONString(segment));
//					} catch (InterruptedException e) {
//
//						e.printStackTrace();
//						// 强制同步切换
//						final int currentIndex = reIndex();
//						segment[currentIndex] = doUpdateNextSegment();
//						setSw(!isSw()); // 切换
//						currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//						log.info(trackId + " 切换号段2 " + currentId.longValue() + JSONObject.toJSONString(segment));
//					} catch (ExecutionException e) {
//
//						e.printStackTrace();
//						// 强制同步切换
//						final int currentIndex = reIndex();
//						segment[currentIndex] = doUpdateNextSegment();
//						setSw(!isSw()); // 切换
//						currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//						log.info(trackId + " 切换号段3 " + currentId.longValue() + JSONObject.toJSONString(segment));
//					}
//				}
//
//			}
//			long id = currentId.incrementAndGet();
//			log.info(trackId + " result " + id);
//			return id;
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		} finally {
//			try {
//				distributedLock.release();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return 0L;
//	}
//
//
//	private long asynQueryId() {
//		String Id = UUID.randomUUID() + "";
//		final String trackId = Id.replaceAll("-", "");
//		log.info(trackId + " start " + currentId.longValue() + JSONObject.toJSONString(segment));
//		if (segment[index()].getMiddleId().equals(currentId.longValue())){
//			boolean boo = distributedLock.isAcquiredInThisProcess();
//			try {
//				distributedLock.acquire();
////				distributedLock.acquire(1000, TimeUnit.MILLISECONDS);
//				log.info(trackId + " 50% " + currentId.longValue() + JSONObject.toJSONString(segment));
//				// 前一段使用了50%
//				int ind = index();
//				int index = ind ==0?1:0;
//				if(segment[index] == null || segment[index].getMinId() < currentId.longValue()){
//					asynLoadSegmentTask = new FutureTask<>(new Callable<Boolean>() {
//
//						@Override
//						public Boolean call() throws Exception {
//							final int currentIndex = reIndex();
//							segment[currentIndex] = doUpdateNextSegment();
//							System.out.println(trackId);
//							log.info(trackId + " 50% ");
//							return true;
//						}
//					});
//					es.submit(asynLoadSegmentTask);
////					boolean bo = asynLoadSegmentTask.get();
////					if(bo){
////						distributedLock.release();
////					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		log.info(trackId + " 最新号段 " + currentId.longValue() + JSONObject.toJSONString(segment));
//		if (segment[index()].getMaxId().equals(currentId.longValue())) {
//			boolean loadingResult;
//			try {
//				loadingResult = asynLoadSegmentTask.get();
//				if (loadingResult) {
//					setSw(!isSw()); // 切换
//					currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//					log.info(trackId + " 切换1 OK ");
//				}
//				log.info(trackId + " 切换号段1 " + currentId.longValue() + JSONObject.toJSONString(segment));
//			} catch (InterruptedException e) {
//
//				e.printStackTrace();
//				// 强制同步切换
//				final int currentIndex = reIndex();
//				segment[currentIndex] = doUpdateNextSegment();
//				setSw(!isSw()); // 切换
//				currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//				log.info(trackId + " 切换号段2 " + currentId.longValue() + JSONObject.toJSONString(segment));
//			} catch (ExecutionException e) {
//
//				e.printStackTrace();
//				// 强制同步切换
//				final int currentIndex = reIndex();
//				segment[currentIndex] = doUpdateNextSegment();
//				setSw(!isSw()); // 切换
//				currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//				log.info(trackId + " 切换号段3 " + currentId.longValue() + JSONObject.toJSONString(segment));
//			}
//
//		}
//		long id = currentId.incrementAndGet();
//		log.info(trackId + " result " + id);
//		return id;
//	}
//
//	private int reIndex() {
//		if (isSw()) {
//			return 0;
//		} else {
//			return 1;
//		}
//	}
//
////	private long synGetId1() {
////		try {
////			lock.lock();
////			if (segment[index()].getMiddleId().equals(currentId.longValue()) || segment[index()].getMaxId().equals(currentId.longValue())) {
////				if (segment[index()].getMiddleId().equals(currentId.longValue())) { // 使用50%进行加载
////					final int currentIndex = reIndex();
////					segment[currentIndex] = doUpdateNextSegment();
////				}
////				if (segment[index()].getMaxId().equals(currentId.longValue())) {
////					setSw(!isSw()); // 切换
////					currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
////				}
////
////			}
////			return currentId.incrementAndGet();
////		} finally {
////			lock.unlock();
////		}
////	}
//
//	private long synGetId() {
//		String Id = UUID.randomUUID() + "";
//		final String lockKey = Id.replaceAll("-", "");
//		try {
//			log.info(lockKey + " start " + currentId.longValue() + JSONObject.toJSONString(segment));
//			// distributedLock.acquire();
//			lock.lock();
//			log.info(lockKey + " 分布式锁获取成功 " + currentId.longValue() + JSONObject.toJSONString(segment));
//			if (segment[index()].getMiddleId().equals(currentId.longValue()) || segment[index()].getMaxId().equals(currentId.longValue())) {
//				if (segment[index()].getMiddleId().equals(currentId.longValue())) { // 使用50%进行加载
//					final int currentIndex = reIndex();
//					segment[currentIndex] = doUpdateNextSegment();
//				}
//
//				log.info(lockKey + " 更新 " + currentId.longValue() + JSONObject.toJSONString(segment));
//			}
//			if (segment[index()].getMaxId().equals(currentId.longValue())) {
//				setSw(!isSw()); // 切换
//				currentId = new AtomicLong(segment[index()].getMinId()); // 进行切换
//				log.info(lockKey + " 切换" + currentId.longValue() + JSONObject.toJSONString(segment));
//			}
//			long id = currentId.incrementAndGet();
//			log.info(lockKey + " result " + id);
//			return id;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				// distributedLock.release();
//				lock.unlock();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return 0L;
//
//	}
//
//	private boolean isSw() {
//		return sw;
//	}
//
//	private void setSw(boolean sw) {
//		this.sw = sw;
//	}
//
//	private int index() {
//		if (isSw()) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
//
//	private IdSegment doUpdateNextSegment() {
//		try {
//			return updateId();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	private IdSegment updateId() throws Exception {
//
//		String querySql = String.format("select %s as  p_step , %s as  max_id,%s as last_update_time ," + " %s as current_update_time   from %s where %s=?", stepField, getColumnName(),
//				this.lastUpdateTimeField, this.updateTimeField, getIncrementerName(), this.bizField);
//		String updateSql = String.format("update %s set %s=?  ,%s=?,%s=?  where %s=? and %s=?", getIncrementerName(), getColumnName(), this.lastUpdateTimeField, this.updateTimeField, this.bizField,
//				getColumnName());
//
//		final IdSegment currentSegment = new IdSegment();
//		this.jdbcTemplate.query(querySql, new String[] { bizTag }, new RowCallbackHandler() {
//
//			@Override
//			public void processRow(ResultSet rs) throws SQLException {
//
//				Long step = null;
//				Long currentMaxId = null;
//				step = rs.getLong("p_step");
//				currentMaxId = rs.getLong("max_id");
//				Date lastUpdateTime = new Date();
//				if (rs.getTimestamp("last_update_time") != null) {
//					lastUpdateTime = (Date) rs.getTimestamp("last_update_time");
//				}
//
//				Date currentUpdateTime = new Date();
//				if (rs.getTimestamp("current_update_time") != null) {
//					currentUpdateTime = (Date) rs.getTimestamp("current_update_time");
//				}
//				currentSegment.setStep(step);
//				currentSegment.setMaxId(currentMaxId);
//				currentSegment.setLastUpdateTime(lastUpdateTime);
//				currentSegment.setCurrentUpdateTime(currentUpdateTime);
//
//			}
//		});
//		Long newMaxId = currentSegment.getMaxId() + currentSegment.getStep();
//		int row = this.jdbcTemplate.update(updateSql, new Object[] { newMaxId, currentSegment.getCurrentUpdateTime(), new Date(), bizTag, currentSegment.getMaxId() });
//		if (row == 1) {
//			IdSegment newSegment = new IdSegment();
//			newSegment.setStep(currentSegment.getStep());
//			newSegment.setMaxId(newMaxId);
//
//			return newSegment;
//		} else {
//			return updateId(); // 递归，直至更新成功
//		}
//
//	}
//
//	private JdbcTemplate jdbcTemplate;
//
//	private String bizTag;
//
//	private String stepField;
//	private String bizField;
//
//	public void setBizField(String bizField) {
//		this.bizField = bizField;
//	}
//
//	private String lastUpdateTimeField;
//	private String updateTimeField;
//
//	public void setLastUpdateTimeField(String lastUpdateTimeField) {
//		this.lastUpdateTimeField = lastUpdateTimeField;
//	}
//
//	public void setUpdateTimeField(String updateTimeField) {
//		this.updateTimeField = updateTimeField;
//	}
//
//	public void setStepField(String stepField) {
//		this.stepField = stepField;
//	}
//
//	public void setBizTag(String bizTag) {
//		this.bizTag = bizTag;
//	}
//
//	private boolean asynLoadingSegment;
//
//	public void setAsynLoadingSegment(boolean asynLoadingSegment) {
//		this.asynLoadingSegment = asynLoadingSegment;
//	}
//
//	private volatile IdSegment[] segment = new IdSegment[2]; // 这两段用来存储每次拉升之后的最大值
//	private volatile boolean sw;
//	private AtomicLong currentId;
//	private static ReentrantLock lock = new ReentrantLock();
//	private String hostAddr;
//	// private DLock dLock;
//
//	CuratorFramework client;
//	// ZK分布式锁
//	InterProcessMutex distributedLock;
//
////	private HashMap<String,InterProcessMutex> distributedLockMap = new HashMap<>();
//
//
//	private void init() {
////		Map<String, IdSegment[]> map = null ;
////		map.put("1", segment);
//
//		segment[0] = doUpdateNextSegment();
//
//		setSw(false);
//		currentId = new AtomicLong(segment[index()].getMinId()); // 初始id
//		// WebApplicationContext wac
//		/*
//		 * ApplicationContext context = new ClassPathXmlApplicationContext(new
//		 * String[]{ "classpath:service-lock.xml" } ); ApplicationContext wac =
//		 * ContextLoader.getCurrentWebApplicationContext(); dLock = (DLock)
//		 * context.getBean("dLock");
//		 */
//		// dLock = (DLock) wac.getBean("dlockConfig");
//
//		client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
//		client.start();
//
//		/**
//		 * 实现非公平锁： 重写创建节点的方法
//		 */
//		// distributedLock = new InterProcessMutex(client, "/mylock", new
//		// NoFairLockDriver());
//
//		distributedLock = new InterProcessMutex(client, "/mylock");
//
//		try {
//			final InetAddress addr = InetAddress.getLocalHost();
//			hostAddr = addr.getHostAddress();
//		} catch (final IOException e) {
//			log.error("[UniqID] Get HostAddr Error", e);
//			hostAddr = String.valueOf(System.currentTimeMillis());
//		}
//		if (null == hostAddr || hostAddr.trim().length() == 0 || "127.0.0.1".equals(hostAddr)) {
//			hostAddr = String.valueOf(System.currentTimeMillis());
//		}
//		hostAddr = hostAddr.substring(hostAddr.length() - 2).replace(".", "0");
//		if (log.isDebugEnabled()) {
//			log.debug("[UniqID]hostAddr is:" + hostAddr);
//		}
//	}
//
//	@Override
//	public void afterPropertiesSet() {
//		super.afterPropertiesSet();
//		if (this.bizTag == null) {
//			throw new RuntimeException("bizTag must be not null");
//		}
//
//		if (this.jdbcTemplate == null) {
//			this.jdbcTemplate = new JdbcTemplate(getDataSource());
//		}
//		this.init();
//		log.info("init run success...");
//	}
//
//	private static Logger log = Logger.getLogger("sequence.log");
//
//}
