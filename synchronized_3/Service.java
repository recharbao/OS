
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



/*

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
