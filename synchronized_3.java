

public class Service {

	public static void print(String stringParam) {
		try{
			synchronized(stringParam){
				while(true){
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(2000);
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}



/*package test2;

public class Service {

	//public static void print(String stringParam) {
	public static void print(Object object) {
		try{
			synchronized(object){
				while(true){
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(2000);
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
*/



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

/*package test2;

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
		service.print("A");
	}
}



/*
package test2;

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
		service.print(new Object());
	}
}*/




public class Run {

	public static void main(String[] args) {
		Service service = new Service();
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();
		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();
	}
}

