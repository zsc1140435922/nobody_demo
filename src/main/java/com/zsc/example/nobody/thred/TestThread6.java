package com.zsc.example.nobody.thred;
import java.util.concurrent.atomic.AtomicLong;

public class TestThread6 {

   static class Counter {
	 //程序显示了在基于线程的环境中使用AtomicLong的计数器的安全实现。
      private AtomicLong c = new AtomicLong(0);
      

      public void increment() {
    	  //原子上增加一个当前值。
         c.getAndIncrement();
      }

      public long value() {
         return c.get();
      }
   }
   public static void main(final String[] arguments) throws InterruptedException {
     /* final Counter counter = new Counter();
      //1000 threads
      for(int i = 0; i < 1000 ; i++) {
         new Thread(new Runnable() {
            public void run() {
               counter.increment();
            }
         }).start();
      }  
      Thread.sleep(6000);

      System.out.println("Final number (should be 1000): " + counter.value());*/
	   AtomicLong cc = new AtomicLong(0);
	   cc.addAndGet(1);
	   cc.getAndIncrement();//返回旧值
	   cc.incrementAndGet();//返回新值
	   System.out.println(cc.get());
	   System.out.println(cc.incrementAndGet());
	   System.out.println(cc.incrementAndGet());
   }  
}

/**
 * public long addAndGet(long delta)	将给定值原子地添加到当前值。
2	public boolean compareAndSet(long expect, long update)	如果当前值与预期值相同，则将该值原子设置为给定的更新值。
3	public long decrementAndGet()	当前值原子减1。
4	public double doubleValue()	以double形式返回指定数字的值。
5	public float floatValue()	以float形式返回指定数字的值。
6	public long get()	获取当前值。
7	public long getAndAdd(long delta)	自动将给定值添加到当前值。
8	public long getAndDecrement()	当前值原子减1。
9	public long getAndIncrement()	当前值原子增加1。
10	public long getAndSet(long newValue)	将原子设置为给定值并返回旧值。
11	public long incrementAndGet()	原子上增加一个当前值。
12	public int intValue()	以int形式返回指定数字的值。
13	public void lazySet(long newValue)	最终设定为给定值。
14	public long longValue()	返回指定数字的值为long类型。
15	public void set(long newValue)	设置为给定值。
16	public String toString()	返回当前值的String表示形式。
17	public boolean weakCompareAndSet(long expect, long update)	如果当前值与预期值相同，则将该值原子设置为给定的更新值。
 * */
