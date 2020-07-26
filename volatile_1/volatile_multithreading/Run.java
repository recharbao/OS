package Volatitle3;

public class Run {
    public static void main(String[] args) {
        PrintString ps = new PrintString();
        new Thread(ps).start();
        System.out.println("停止线程:" + Thread.currentThread().getName());
        ps.setContinuePrint(false);
    }
}
