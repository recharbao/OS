package volatile2;

public class Run {
    public static void main(String[] args) {
        MyThread[] myThreads = new MyThread[100];
        for (int i = 0; i < 100; i++){
            myThreads[i] = new MyThread();
        }
        for (int i = 0; i < 100; i++){
            myThreads[i].start();
        }
    }
}
