package PhilosopherEating2;




public class PhilosopherEating {
    public static void main(String[] args) {

        eatPeople eatpeople0 = new eatPeople(0);
        eatPeople eatpeople1 = new eatPeople(1);
        eatPeople eatpeople2 = new eatPeople(2);
        eatPeople eatpeople3 = new eatPeople(3);
        eatPeople eatpeople4 = new eatPeople(4);


        //五位哲学家，编号0~4
        eatpeople0.start();
        eatpeople1.start();
        eatpeople2.start();
        eatpeople3.start();
        eatpeople4.start();
    }
}

class ChopsticksResources {
    //假设有五根筷子，编号0~4
    int count = 0;
    public ChopsticksResources(int count){
        this.count = count;
    }
    boolean Chopsticks[] = new boolean[5];
    ChopsticksResources(){
        //初始化资源可用
        for (int i = 0; i < 5; i++){
            Chopsticks[i] = true;
        }
    }
    //控制人数
    public synchronized void addPeople(int i){
        //增加吃饭的人数，允许吃饭的人数-1
        if (--count < 0){
            try {
                System.out.println("人数大于等于4个，哲学家" + i +"等待就餐!");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("就餐人数小于4个");
        }
    }
    public synchronized void reducePeople(int i){
        if (++count <= 0){
            System.out.println("哲学家" + i +"就餐完毕，让出位置!");
            this.notify();
        }
    }

    //拿起筷子
    public synchronized void Wait(int i,int people){
        System.out.println("哲学家" + people + "准备拿起"+ i +"号筷子!");
        if (Chopsticks[i] == false){
            try{
                System.out.println("哲学家" + people + "等待拿起"+ i +"号筷子!");
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("哲学家" + people + "已经拿起"+ i +"号筷子!");
            Chopsticks[i] = false;
        }
    }

    //放下筷子
    public  synchronized void  Release(int i, int people){
        System.out.println("哲学家" + people + "释放"+ i +"号筷子!");
        Chopsticks[i] = true;
        this.notify();
    }
}

class Resources{
    static ChopsticksResources chopsticksResources = new ChopsticksResources(4);
    static {
        for (int i = 0; i < 5; i++){
            chopsticksResources.Chopsticks[i] = true;
        }

    }
}


class eatPeople extends Thread{
    int i;
    eatPeople(int i){
        this.i = i;
    }
    @Override
    public void run() {
        super.run();
        while (true){
            System.out.println("哲学家 " + this.i + " 正在思考!");
            Resources.chopsticksResources.addPeople(i);
            Resources.chopsticksResources.Wait(i,i);
            Resources.chopsticksResources.Wait((i + 1) % 5,i);
            System.out.println("哲学家 " + this.i + " 正在吃饭!");
            /*try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            Resources.chopsticksResources.Release(i,i);
            Resources.chopsticksResources.Release((i + 1) % 5,i);
            Resources.chopsticksResources.reducePeople(i);
            System.out.println("哲学家 " + this.i + " 正在思考!");
            /*try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}



