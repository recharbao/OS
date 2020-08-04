package PhilosopherEating3;

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
    boolean Chopsticks[] = new boolean[5];
    ChopsticksResources(){
        //初始化资源可用
        for (int i = 0; i < 5; i++){
            Chopsticks[i] = true;
        }
    }

    //拿起筷子
    public synchronized void Wait(int i,int people){
        System.out.println("哲学家" + people + "准备拿起"+ i +"号筷子!");
        if (Chopsticks[i] == false || Chopsticks[(i + 1) % 5] == false){
            try{
                System.out.println("哲学家" + people + "等待拿起"+ i +"号筷子!" + "或者"+ (i + 5) % 5 +"号筷子!");
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("哲学家" + people + "已经拿起"+ i +"号筷子!" + "和"+ (i + 5) % 5 +"号筷子!");
            Chopsticks[i] = false;
            Chopsticks[(i+1) % 5] = false;
        }
    }

    //放下筷子
    public  synchronized void  Release(int i, int people){
        System.out.println("哲学家" + people + "已经拿起"+ i +"号筷子!" + "和"+ (i + 5) % 5 +"号筷子!");
        Chopsticks[i] = true;
        Chopsticks[(i + 1) % 5] = true;
        this.notify();
    }
}

class Resources{
    static ChopsticksResources chopsticksResources = new ChopsticksResources();
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
            Resources.chopsticksResources.Wait(i,i);
            System.out.println("哲学家 " + this.i + " 正在吃饭!");
            /*try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            Resources.chopsticksResources.Release(i,i);
            System.out.println("哲学家 " + this.i + " 正在思考!");
            /*try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}


