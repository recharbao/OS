

/*package test3;

public class Service {

	synchronized public void methodA(){
		System.out.println("methodA begin");
		boolean isrun = true;
		while(isrun){
			
		}
		System.out.println("methodA end");
	}
	synchronized public void methodB(){
		System.out.println("methodB begin");
		System.out.println("methodB end");
	}
}
*/



public class Service {
	
	Object object1 = new Object();

	 public void methodA(){
		synchronized(object1){
			System.out.println("methodA begin");
			boolean isrun = true;
			while(isrun){
				
			}
			System.out.println("methodA end");
		}
	}
	 
	Object object2 = new Object();
	 
	 public void methodB(){
		synchronized(object2){
			System.out.println("methodB begin");
			System.out.println("methodB end");
		}
	}
}


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
		service.methodA();
	}
}






public class ThreadB extends Thread{

	private Service service;
	public ThreadB(Service service){
		super();
		this.service = service;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.methodB();
	}
}





public class Run {

	public static void main(String[] args) {
		Service service = new Service();
		ThreadA a = new ThreadA(service);
		a.start();
		
		ThreadB b = new ThreadB(service);
		b.start();
	}
}

