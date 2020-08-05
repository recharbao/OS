

//OS线程模拟进程

import java.util.*;

public class Sender_receiver {
    public static void main(String[] args) {

        Sender sender1 = new Sender();
        Sender sender2 = new Sender();
        Sender sender3 = new Sender();

        sender1.setName("sender1");
        sender2.setName("sender2");
        sender3.setName("sender3");

        Receiver receiver1 = new Receiver("sender1","sender3");
        Receiver receiver2 = new Receiver("sender1","sender2");
        Receiver receiver3 = new Receiver("sender2","sender3");
        Receiver receiver4 = new Receiver("sender1","sender1");
        Receiver receiver5 = new Receiver("sender2","sender2");
        Receiver receiver6 = new Receiver("sender3","sender3");

        receiver1.setName("receiver1");
        receiver2.setName("receiver2");
        receiver3.setName("receiver3");
        receiver4.setName("receiver4");
        receiver5.setName("receiver5");
        receiver6.setName("receiver6");


        sender1.start();
        sender2.start();
        sender3.start();

        receiver1.start();
        receiver2.start();
        receiver3.start();
        receiver4.start();
        receiver5.start();
        receiver6.start();
    }
}

class pv_operation{
    int count = 0;
    pv_operation(){}
    pv_operation(int s){count  = s;}
    public synchronized void P(){
        count--;
        if (count < 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void V(){
        count++;
        if (count <= 0){
            this.notify();
        }
    }
}

class SignalSM{
    static pv_operation mutex = new pv_operation(1);
    static pv_operation SM = new pv_operation(0);
    static Queue<ArrayList> queue = new LinkedList<ArrayList>();
}

class Sender extends Thread{
    //发送消息
    //volatile int number = 0; volatile是多个线程操控同一变量发挥作用，而这里是不同对象中的变量，不是同一个变量，易搞混。
    static int number = 0;
    @Override
    public void run() {

        super.run();
        while (number < 900){
            SignalSM.mutex.P();
            //将消息装进Map后发往消息队列
            ArrayList<String> list = new ArrayList<String>();
            list.add(Thread.currentThread().getName());
            list.add(Thread.currentThread().getName() + "的消息" + number);
            SignalSM.queue.offer(list);
            SignalSM.mutex.V();
            SignalSM.SM.V();
            /*if ("sender1".equals(Thread.currentThread().getName())){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            //注意最后结果的现象
            number++;
        }
    }
}



class Receiver extends Thread{
    //接收消息

    //查看是否是给我这个进程(线程模拟进程)发送的消息
    Map<String,Boolean> map = new HashMap<String,Boolean>();
    public Receiver(String r1, String r2){
        map.put(r1,true);
        map.put(r2,true);
    }

    @Override
    public void run() {
        super.run();
        while (true){
            SignalSM.SM.P();
            SignalSM.mutex.P();
            if (map.get(SignalSM.queue.element().get(0)) != null){
                //确保消息队列不为空，并得到消息队列中的消息
                System.out.println(Thread.currentThread().getName() + "收到了" + SignalSM.queue.element().get(0) + "发来的消息:" + SignalSM.queue.poll().get(1));
            }else {
                SignalSM.SM.V();
            }
            SignalSM.mutex.V();
        }
    }
}
