package Pipe;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.io.PipedWriter;

public class Write extends Thread{
    private PipedWriter write;
    public Write(PipedWriter write){
        this.write = write;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("将数据写入管道:");
        for (int i = 0; i < 300; i++){
            String writeData = " " + (i + 1);
            try {
                write.write(writeData);
                System.out.print(writeData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        try {
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
