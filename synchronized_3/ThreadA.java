
public class ThreadA extends Thread{

	private Service service;
	public ThreadA(Service service){
		super();
		this.service = service;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.print("A");
	}
}

/*

public class ThreadA extends Thread{

	private Service service;
	public ThreadA(Service service){
		super();
		this.service = service;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.print(new Object());
	}
}
*/
