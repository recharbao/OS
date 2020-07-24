import java.util.Scanner;

public class BankerAlgorithm {
    static int avail[] = new int[4];    //表示ABCD四类各类可利用资源总数;
    static int max[][] = new int[6][4]; //表示各个进程对ABCD四类资源的最大需求量
    static int allocated[][] = new int[6][4]; //表示个ABCD四类资源对各进程已经分配的资源数
    static int need[][] = new int[6][4]; //表示各个进程对ABCD四类资源的剩余需求量
    static int req[] = new int[4];
    Scanner scanner = new Scanner(System.in);

    public void initDate(){
        System.out.println("初始化ABCD四类各类可利用资源总数:");
        for (int i = 0; i < 4; i++){
            avail[i] = scanner.nextInt();
        }

        System.out.println("初始化各个进程对ABCD四类资源的最大需求量:");
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                max[i][j] = scanner.nextInt();
            }
        }

        System.out.println("初始化ABCD四类资源对各进程已经分配的资源数:");
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 4;j++){
                allocated[i][j] = scanner.nextInt();
            }
        }
        //再次计算ABCD各类资源的可用数
        for (int j = 0; j < 4; j++){
            for (int i = 0; i < 6; i++){
                avail[j] -=  allocated[i][j];
            }
        }
    }
    public void action() {
        System.out.println("输入要分配资源的进程:");
        int processNum = scanner.nextInt();
        if (processNum < 0 || processNum >= 6) {
            System.out.println("该进程不存在，请重新输入！");
            action();
        }
        for (int i = 0; i < 4; i++) {
            req[i] = scanner.nextInt();
            //检查请求
            if (req[i] > need[processNum][i] || req[i] > avail[i]) {
                System.out.println("请求资源数大于该进程所需要的资源数或者大于该资源的剩余数，请重新输入！");
                action();
            }
        }
        //尝试分配资源
        update(processNum);

        if (checkProcess()){
            action();
        }else {
            goBack(processNum);
            action();
        }



    }

    public void update(int processNum){
        for (int i = 0; i < 4; i++){
            avail[i] -= req[i];
            allocated[processNum][i] += req[i];
            need[processNum][i] -= req[i];
        }
    }
    public void goBack(int processNum){
        for (int i = 0; i < 4; i++){
            avail[i] += req[i];
            allocated[processNum][i] -= req[i];
            need[processNum][i] += req[i];
        }
    }

    public boolean checkProcess(){
        boolean finished[] = new boolean[6]; //已经分配完成的进程
        int work[] = new int[4]; //资源
        int securQueue[] = new int[6];  //安全队列
        int queueIndex = 0;
        int processIndex = 0;
        for (int i = 0; i < 4; i++){
            work[i] = avail[i];
        }
        for (int i = 0; i < 4; i++){
            finished[i] = false;
        }

        while (queueIndex < 6){
            while (processIndex < 6){
                if (finished[processIndex]){
                    processIndex++;
                }

                for (int i = 0; i < 4; i++){
                    if (need[processIndex][i] > work[i]){
                        processIndex++;
                        break;
                    }else if (i == 3){
                        securQueue[queueIndex] = processIndex;
                        queueIndex++;
                        finished[processIndex] = true;
                        processIndex = 0;
                    }
                }
            }
        }

        for (int i = 0; i < 6; i++){
            if (finished[i] == false){
                System.out.println("系统资源不安全，申请失败");
                return false;
            }
        }
        System.out.println("系统资源安全，申请成功");
        System.out.println();
        System.out.println();
        System.out.print("安全序列为：");
        for (int i = 0; i < 6; i++){
            System.out.print(securQueue[i]+" ");
        }
        return  true;
    }


    public static void main(String[] args) {
        BankerAlgorithm bankerAlgorithm = new BankerAlgorithm();
        bankerAlgorithm.initDate();
        bankerAlgorithm.action();
    }
}
