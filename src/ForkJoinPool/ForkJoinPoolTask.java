package ForkJoinPool;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


@SuppressWarnings("serial")
public class ForkJoinPoolTask extends RecursiveTask<Integer>{
	
	//最长的计算长度为10
	private static final int MAX_LENGTH = 10;
	private int start;
	private int end;
	private int[] arr;
	public ForkJoinPoolTask(int start, int end, int[] arr) {
		this.start = start;
		this.end = end;
		this.arr = arr;
	}
	
	@Override
	protected Integer compute() {
		int sum = 0;
		System.out.println(Thread.currentThread().getName() + "  start:" + start + "   end:" + end);
		//当本线程的长度大于10的时候，再拆成两个子任务
		if (end - start > MAX_LENGTH) {
			int mid = (end + start) / 2;
			ForkJoinPoolTask leftTask = new ForkJoinPoolTask(start, mid, arr);
			ForkJoinPoolTask rightTask = new ForkJoinPoolTask(mid, end, arr);
			leftTask.fork();
			rightTask.fork();
			
			return leftTask.join() + rightTask.join();
		}else {
			for (int i = start; i < end; i++) {
				sum += arr[i];
			}
			System.out.println(Thread.currentThread().getName() + "  start:" + start + "   end:" + end + "   sum:" + sum);
			return sum;
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		int[] arr = new int[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		
		//创建一个ForkJoinPool，不填参数表示ForkJoinPool(Runtime.availableProcessors())
		ForkJoinPool pool = new ForkJoinPool();
		
		ForkJoinTask<Integer> future = pool.submit(new ForkJoinPoolTask(0, arr.length, arr));
		System.out.println(pool);
		//get()方法本身就要等待子线程的结束
		System.out.println(future.get());
		System.out.println(pool);
		pool.shutdown();
	}

	
}
