package test1;

public class MyThread2 extends Thread{
    private int count = 5;
    @Override
    public void run() {
        super.run();
            count --;
            System.out.println(this.currentThread().getName() + " : " + count);
    }

    public static void main(String[] args) {
        //资源共享
        MyThread2 myThread = new MyThread2();

        Thread a = new Thread(myThread,"A");
        Thread b = new Thread(myThread,"B");
        Thread c = new Thread(myThread,"C");
        Thread d = new Thread(myThread,"D");
        Thread e = new Thread(myThread,"E");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();


    }
}

