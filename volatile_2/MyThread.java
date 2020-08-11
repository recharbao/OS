package volatile2;

public class MyThread extends Thread{
    volatile public static int count = 0;
    private static void addCount(){           //非原子操作
    //synchronized private static void addCount(){      //原子操作
        for (int i = 0; i < 100; i++){
            count ++;
        }
        System.out.println("count=" + count);
    }

    @Override
    public void run() {
        super.run();
        addCount();
    }
}
