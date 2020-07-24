package test1;

public class MyThread1 extends Thread{
    private int count = 5;
    public MyThread1(String name){
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count > 0){
            count --;
            System.out.println(this.currentThread().getName() + " : " + count);
        }
    }

    public static void main(String[] args) {
        //资源不共享
        MyThread1 a = new MyThread1("A");
        MyThread1 b = new MyThread1("B");
        MyThread1 c = new MyThread1("c");

        a.start();
        b.start();
        c.start();
    }
}


