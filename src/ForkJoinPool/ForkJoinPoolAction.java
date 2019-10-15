package ForkJoinPool;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


@SuppressWarnings("serial")
public class ForkJoinPoolAction extends RecursiveAction{
	
	//最长的计算长度为10
	private static final int MAX_LENGTH = 10;
	private int start;
	private int end;
	public ForkJoinPoolAction(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected void compute() {
		System.out.println(Thread.currentThread().getName() + "  start:" + start + "   end:" + end);
		//当本线程的长度大于10的时候，再拆成两个子任务
		if (end - start > MAX_LENGTH) {
			int mid = (end + start) / 2;
			ForkJoinPoolAction leftTask = new ForkJoinPoolAction(start, mid);
			ForkJoinPoolAction rightTask = new ForkJoinPoolAction(mid, end);
			//生成子任务
			leftTask.fork();
			rightTask.fork();
			
		}else {
			for (int i = start; i < end; i++) {
				System.out.println(Thread.currentThread().getName() + "  " + i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		
		//创建一个通用池
		ForkJoinPool pool = ForkJoinPool.commonPool();
		
		pool.submit(new ForkJoinPoolAction(0, 100));
		
		System.out.println(pool);
		
		//因为是后台线程，所以要卡住主线程。
		System.in.read();
		pool.shutdown();
	}

	
}
