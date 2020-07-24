
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
