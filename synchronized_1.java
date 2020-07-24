
public class Task {

	private String getData1;
	private String getData2;
	public synchronized void doLongTimeTask(){
		try{
			System.out.println("begin task");
			Thread.sleep(3000);
			getData1 = "长时间处理任务后从远程返回的值 1 threadName="
					+Thread.currentThread().getName();
			getData2 = "长时间处理任务后从远程返回的值 2 threadName="
					+Thread.currentThread().getName();
			
			System.out.println(getData1);
			System.out.println(getData2);
			
			System.out.println("end task");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}


public class Com {

	public static long beginTime1;
	public static long endTime1;
	public static long beginTime2;
	public static long endTime2;
}



public class MyThread extends Thread{

	private Task task;
	public MyThread(Task task){
		super();
		this.task = task;
	}
	@Override
	public void run(){
		super.run();
		Com.beginTime1 = System.currentTimeMillis();
		task.doLongTimeTask();
		Com.endTime1 = System.currentTimeMillis();
	}
}

public class MyThread1 extends Thread{

	private Task task;
	public MyThread1(Task task){
		super();
		this.task = task;
	}
	@Override
	public void run(){
		super.run();
		Com.beginTime2 = System.currentTimeMillis();
		task.doLongTimeTask();
		Com.endTime2 = System.currentTimeMillis();
	}
}


public class Run {

	public static void main(String[] args) {
		Task task = new Task();
		MyThread thread = new MyThread(task);
		thread.start();
		MyThread1 thread1 = new MyThread1(task);
		thread1.start();
		try{
			Thread.sleep(10000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		long beginTime = Com.beginTime1;
		if(Com.beginTime2 < Com.beginTime1){
			beginTime = Com.beginTime2;
		}
		System.out.println("开始时间:" + beginTime);
		long endTime = Com.endTime1;
		if(Com.endTime1 < Com.endTime2){
			endTime = Com.endTime2;
		}
		System.out.println("结束时间:" + endTime);
		System.out.println("耗时:" + (endTime - beginTime)/1000);
	}
}


/*
public class Task {

	private String getData1;
	private String getData2;
	public  void doLongTimeTask(){
		try{
			System.out.println("begin task");
			Thread.sleep(3000);
			getData1 = "长时间处理任务后从远程返回的值 1 threadName="
					+Thread.currentThread().getName();
			getData2 = "长时间处理任务后从远程返回的值 2 threadName="
					+Thread.currentThread().getName();
			synchronized(this){
				System.out.println(getData1);
				System.out.println(getData2);
			}
			
			System.out.println("end task");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
*/