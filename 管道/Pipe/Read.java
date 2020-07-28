package Pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

//Java中的管道通信机制
public class Read extends Thread{
    //管道的输入端
    private PipedReader read;
    public Read(PipedReader read){
        this.read = read;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("从管道读入数据:");
        char[] array = new char[50];
        try {
            //把字节流读入array
            int length = read.read(array);
            //查看是否有数据,如果有，就读
            while (length != -1){
                String readData = new String(array,0,length);
                length = read.read(array);
                System.out.println(readData +" 本组数目:"+ length + " ");
                //System.out.println(length);
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
