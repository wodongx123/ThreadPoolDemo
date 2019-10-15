package CachedThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {


	public static void main(String[] args) {
		

		//实例化一个CachedThreadPool
		final ExecutorService  pool = Executors.newCachedThreadPool();
		
		System.out.println(pool);
		
		//开20个任务，结果可以发现同一个线程会多次出现，这就是复用性。
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
