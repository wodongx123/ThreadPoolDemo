package ThreadLocal.Inheritable;


public class InheritableThreadLocalTest implements Runnable{
	Num num;
	
	public InheritableThreadLocalTest(Num num) {
		this.num = num;
	}
	
	
	@Override
	//run只更改本线程内num的值，对主线程不影响
	public void run() {
		System.out.println(Thread.currentThread().getName() + "   run");
		for (int i = 0; i < 10; i++) {
			num.setLocal(num.getLocal() + i);
		}
		System.out.println(Thread.currentThread().getName() + "   " + num.getLocal());
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + "   run");
		Num num = new Num(100);

		//如果是InheritableThreadLocal，就会把主线程的100传入到子线程中
		InheritableThreadLocalTest test = new InheritableThreadLocalTest(num);

		new Thread(test, "Thread-1").start();
		new Thread(test, "Thread-2").start();
		
		Thread.sleep(3000);
		//主线程的值还是原来的100
		System.out.println(Thread.currentThread().getName() + "   " + num.getLocal());
	}
}

class Num{
	private InheritableThreadLocal<Integer> local = new InheritableThreadLocal<Integer>(){
		//不加入initialValue方法就会报空指针
		protected Integer initialValue() {
			//每当需要这个值的时候，就会先创建一个
			System.out.println(Thread.currentThread().getName() + "   init");
			return 10;
		}
	};
	
	public Num(int i) {
		setLocal(i);
		System.out.println(Thread.currentThread().getName() + "   " + getLocal());

	}

	public void setLocal(int local) {
		System.out.println(Thread.currentThread().getName() + "  set  " + getLocal());
		this.local.set(local);

	}
	
	public int getLocal() {
		return local.get();
	}
}
