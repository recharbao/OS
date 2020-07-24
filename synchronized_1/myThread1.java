
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