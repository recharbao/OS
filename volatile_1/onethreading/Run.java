package Volatile;

import java.io.PrintWriter;

public class Run {
    public static void main(String[] args) {
        PrintString PS = new PrintString();
        PS.printStringMethod();
        System.out.println("我要停止线程:" + Thread.currentThread().getName());
        PS.setContinuePrint(false);
    }
}


