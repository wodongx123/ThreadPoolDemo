package ScheduledThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadAt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
		//ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
		
		Runnable target = new Runnable() {
			
			public void run(){
				try {
					System.out.println(Thread.currentThread().getName() + "    1");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "    2");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "    3");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "    4");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		//at方法，从任务开始时开始计时，经过指定的时间period后，重新开始任务
		//如果任务本身的执行时间大于period，则执行周期为任务的执行时间，否则为period
		pool.scheduleAtFixedRate(target, 1, 5, TimeUnit.SECONDS);
		//pool.scheduleAtFixedRate(target, 1, 2, TimeUnit.SECONDS);
				
		
	}

}
