package ScheduledThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadWith {

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
		
		//With方法，在任务执行完后开始计时，经过指定的时间period后重新开始任务
		pool.scheduleWithFixedDelay(target, 1, 3, TimeUnit.SECONDS);
		//pool.scheduleWithFixedDelay(target, 1, 5, TimeUnit.SECONDS);

	}

}
