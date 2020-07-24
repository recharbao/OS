
import javax.xml.bind.TypeConstraintException;

public class Producer_Consumer {
    public static void main(String[] args) {
        Producer producer1 = new Producer();
        Producer producer2 = new Producer();
        Producer producer3 = new Producer();
        Producer producer4 = new Producer();
        Producer producer5 = new Producer();
        Producer producer6 = new Producer();

        producer1.setName("producer1");
        producer2.setName("producer2");
        producer3.setName("producer3");
        producer4.setName("producer4");
        producer5.setName("producer5");
        producer6.setName("producer6");

        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        Consumer consumer3 = new Consumer();
        Consumer consumer4 = new Consumer();
        Consumer consumer5 = new Consumer();
        Consumer consumer6 = new Consumer();

        consumer1.setName("consumer1");
        consumer2.setName("consumer2");
        consumer3.setName("consumer3");
        consumer4.setName("consumer4");
        consumer5.setName("consumer5");
        consumer6.setName("consumer6");

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        producer6.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        consumer6.start();
    }
}


class pv_op{
    int count = 0;
    pv_op(){}
    pv_op(int s){count = s;}
    public synchronized void P(){
        System.out.println(Thread.currentThread().getName()+"   :   "+count);
        count --;
        if(count < 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void V(){
        count ++;
        if (count <= 0){
            this.notify();
        }
    }
}


class Signal{
    static pv_op mutex = new pv_op(1);
    static pv_op empty = new pv_op(10);
    static pv_op full = new pv_op();
}



class Producer extends Thread{
    static  int prodNum = 0;
    @Override
    public void run() {
        super.run();
        while(prodNum < 900){
            Signal.empty.P();
            Signal.mutex.P();
            int index = prodNum % 10;
            System.out.println("Produnce: buffer:"+ index + "prodNum:" + prodNum);
            prodNum ++;
            try{
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Signal.full.V();
            Signal.mutex.V();
        }
    }
}

class Consumer extends Thread{
    static  int prodNum = 0;
    @Override
    public void run() {
        super.run();
        while(prodNum < 900){
            Signal.full.P();
            Signal.mutex.P();
            int index = prodNum % 10;
            System.out.println("Consumer: buffer:"+ index + "prodNum:" + prodNum);
            prodNum ++;
            try{
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Signal.empty.V();
            Signal.mutex.V();
        }
    }
}