package com.zsc.example.nobody.thred.Callable;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {
	public static void main(String[] args) {
		// 你可以根据自己的需要来创建壹個 ExecutorService ，也可以使用 Executors 工厂方法来创建壹個
		// ExecutorService 实例。这里有几個创建 ExecutorService 的例子：
		// ExecutorService executorService1 =
		// Executors.newSingleThreadExecutor();
		// ExecutorService executorService3 =
		// Executors.newScheduledThreadPool(10);

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		// 方法 execute(Runnable) 接收壹個 java.lang.Runnable
		// 对象作为参数，并且以异步的方式执行它。如下是壹個使用 ExecutorService 执行 Runnable 的例子：
		// 使用这种方式没有办法获取执行 Runnable 之后的结果，如果你希望获取运行之后的返回值，就必须使用 接收 Callable 参数的
		// execute() 方法，后者将会在下文中提到。
		
//		 executorService.execute(new Runnable() { public void run() {
//		 System.out.println("Asynchronous task"); } });

		// 方法 submit(Runnable) 同样接收壹個 Runnable 的实现作为参数，但是会返回壹個 Future 对象。这個
		// Future 对象可以用于判断 Runnable 是否结束执行。如下是壹個 ExecutorService 的 submit()
		// 方法的例子：
		/*
		 * Future future = executorService.submit(new Runnable() { public void
		 * run() { System.out.println("Asynchronous task"); } }); // 如果任务结束执行则返回
		 * null try { System.out.println("future.get()=" + future.get()); }
		 * catch (InterruptedException e) { e.printStackTrace(); } catch
		 * (ExecutionException e) { e.printStackTrace(); }
		 */

		// 方法 submit(Callable) 和方法 submit(Runnable)
		// 比较类似，但是区别则在于它们接收不同的参数类型。Callable 的实例与 Runnable 的实例很类似，但是 Callable 的
		// call() 方法可以返回壹個结果。方法 Runnable.run() 则不能返回结果。

		// Callable 的返回值可以从方法 submit(Callable) 返回的 Future 对象中获取。如下是壹個
		// ExecutorService Callable 的样例：
		/*
		 * @SuppressWarnings({ "unchecked", "rawtypes" }) Future future2 =
		 * executorService.submit(new Callable() { public Object call() throws
		 * Exception { System.out.println("Asynchronous Callable"); return
		 * "Callable Result"; } });
		 * 
		 * try { System.out.println("future.get() = " + future2.get()); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (ExecutionException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// 上述样例代码会输出如下结果：
		// Asynchronous Callable
		// future.get() = Callable Result

		/**
		 * 方法 invokeAny() 接收壹個包含 Callable 对象的集合作为参数。调用该方法不会返回 Future
		 * 对象，而是返回集合中某壹個 Callable 对象的结果，而且无法保证调用之后返回的结果是哪壹個 Callable，只知道它是这些
		 * Callable 中壹個执行结束的 Callable 对象。 如果壹個任务运行完毕或者抛出异常，方法会取消其它的 Callable
		 * 的执行。 以下是壹個样例：
		 */

		Set<Callable<String>> callables = new HashSet<Callable<String>>();

		callables.add(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "Task 1";
			}
		});
		callables.add(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "Task 2";
			}
		});
		callables.add(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "Task 3";
			}
		});

		String result = null;
		try {
			result = executorService.invokeAny(callables);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("result = " + result);
		// 以上样例代码会打印出在给定的集合中的某壹個 Callable 的返回结果。我尝试运行了几次，结果都在改变。有时候返回结果是"Task
		// 1"，有时候是"Task 2"，等等。

		executorService.shutdown();

		/**
		 * 当使用 ExecutorService 完毕之后，我们应该关闭它，这样才能保证线程不会继续保持运行状态。 举例来说，如果你的程序通过
		 * main() 方法启动，并且主线程退出了你的程序，如果你还有壹個活动的 ExecutorService
		 * 存在于你的程序中，那么程序将会继续保持运行状态。存在于 ExecutorService 中的活动线程会阻止Java虚拟机关闭。 为了关闭在
		 * ExecutorService 中的线程，你需要调用 shutdown() 方法。ExecutorService
		 * 并不会马上关闭，而是不再接收新的任务，壹但所有的线程结束执行当前任务，ExecutorServie 才会真的关闭。所有在调用
		 * shutdown() 方法之前提交到 ExecutorService 的任务都会执行。 如果你希望立即关闭
		 * ExecutorService，你可以调用 shutdownNow()
		 * 方法。这個方法会尝试马上关闭所有正在执行的任务，并且跳过所有已经提交但是还没有运行的任务。但是对于正在执行的任务，
		 * 是否能够成功关闭它是无法保证 的，有可能他们真的被关闭掉了，也有可能它会壹直执行到任务结束。这是壹個最好的尝试。
		 */
	}
}
