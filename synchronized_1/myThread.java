
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