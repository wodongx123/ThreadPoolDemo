package CachedThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadTime {

	public static void main(String[] args) throws InterruptedException {
		//实例化一个CachedThreadPool
		final ExecutorService  pool = Executors.newCachedThreadPool();
		
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
		
		//休眠65s之后，会发现active thread为0，这就是从缓存中清除掉了。
		System.out.println(pool);

		
		pool.shutdown();
	}

}
