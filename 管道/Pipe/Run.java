package Pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Run {
    public static void main(String[] args) {
        PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();
        //管道连接
        try {
            pipedWriter.connect(pipedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //开启线程
        Read read = new Read(pipedReader);

        read.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Write write = new Write(pipedWriter);
        write.start();

    }
}
