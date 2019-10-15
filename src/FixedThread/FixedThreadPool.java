package FixedThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		//ExecutorService pool = Executors.newSingleThreadExecutor();
		
		System.out.println(pool);
		
		for (int i = 0; i < 20; i++) {
			pool.submit(new Runnable() {
				
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}

			});
			System.out.println(pool);

		}
		
		
		pool.shutdown();
	}

}
