package WorkStealing;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkStealingPool implements Runnable{
	int time;
	int i;
	
	public WorkStealingPool(int time, int i) {
		this.time = time;
		this.i = i;
	}
	
	public void run() {	
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "   " + i);
		
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		ExecutorService pool = Executors.newWorkStealingPool(4);
		//ExecutorService pool = Executors.newSingleThreadExecutor();
		
		System.out.println(pool);
		
		//按顺序提交任务，但是不一定按顺序执行
		for (int i = 0; i < 10; i++) {
			pool.submit(new WorkStealingPool(1000, i));
		}

		System.out.println(pool);

		//WorkstealingPool是后台线程，要卡住主线程才能看到结果
		System.in.read();
		
		System.out.println(pool);

	}

}
