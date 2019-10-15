package FixedThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadTime {

	public static void main(String[] args) throws InterruptedException {
		final ExecutorService  pool = Executors.newFixedThreadPool(3);
		
		System.out.println(pool);
		
		for (int i = 0; i < 20; i++) {
			pool.submit(new Runnable() {
				
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}

			});
		}
		System.out.println(pool);

		TimeUnit.SECONDS.sleep(65);
		
		//休眠65s之后,线程数不会减少
		System.out.println(pool);
		
		pool.shutdown();
	}

}
