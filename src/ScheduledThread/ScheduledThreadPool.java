package ScheduledThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

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
		
		//在指定的延迟时间后开始任务
		pool.schedule(target, 3, TimeUnit.SECONDS);
		
	}

}
