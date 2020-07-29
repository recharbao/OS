package ProcessScheduling_FIFO;

import java.util.ArrayList;
import java.util.Scanner;

public class FIFO {

    private int processNumber;
    private ArrayList<Process> processList;

    public FIFO() {
        init();
        caculate();
        result();
    }
    public void init(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入进程数:");
        processNumber = sc.nextInt();

        processList = new ArrayList<Process>();

        for (int i = 0; i < processNumber; i++){
            processList.add(new Process());
        }

        for (int i = 0; i < processNumber; i++){
            System.out.println("请输入进程" + i + "的到达时间:");
            processList.get(i).arriveTime = sc.nextInt();
        }
        for (int i = 0; i < processNumber; i++){
            System.out.println("请输入进程" + i + "的服务时间:");
            processList.get(i).serviceTime = sc.nextInt();
        }
    }
    public void caculate(){
        int waitTime = 0;
        boolean[] visited = new boolean[processNumber];

        for (int i = 0; i < processNumber; i++){
            visited[i] = false;
        }
        boolean flag = true;

        while (flag) {
            double min = 999999;
            int minIndex = -1;
            flag = false;
            //找到先到达的进程
            for (int i = 0; i < processNumber; i++) {
                if (!visited[i]) {
                    flag = true;
                    if (min > processList.get(i).arriveTime) {
                        min = processList.get(i).arriveTime;
                        minIndex = i;
                    }
                }
            }
            if (flag) {
                visited[minIndex] = true;
                processList.get(minIndex).beginTime = waitTime;
                waitTime += processList.get(minIndex).serviceTime;
                processList.get(minIndex).finishedTime = waitTime;
                processList.get(minIndex).turnArroundTime = processList.get(minIndex).finishedTime - processList.get(minIndex).arriveTime;
                processList.get(minIndex).weightedTurnArroundTime = (processList.get(minIndex).finishedTime - processList.get(minIndex).arriveTime) / processList.get(minIndex).serviceTime;
            }
        }
    }
    public void result(){
        for (int i = 0; i < processNumber; i++) {
            System.out.println("进程" + i + " -- " + "到达时间: " + processList.get(i).arriveTime);
            System.out.println("进程" + i + " -- " + "服务时间: " + processList.get(i).serviceTime);
            System.out.println("进程" + i + " -- " + "开始时间: " + processList.get(i).beginTime);
            System.out.println("进程" + i + " -- " + "完成时间: " + processList.get(i).finishedTime);
            System.out.println("进程" + i + " -- " + "周转时间: " + processList.get(i).turnArroundTime);
            System.out.println("进程" + i + " -- " + "带权周转时间: " + processList.get(i).weightedTurnArroundTime);
            System.out.println();
        }

            double sumTurn = 0;
            double sumWeightedTurn = 0;
            for (int i = 0; i < processNumber; i++){
                sumTurn += processList.get(i).turnArroundTime;
                sumWeightedTurn += processList.get(i).weightedTurnArroundTime;
            }

            System.out.print("平均周转时间:");
            System.out.println(sumTurn / processNumber);

            System.out.print("平均带权周转时间:");
            System.out.println(sumWeightedTurn / processNumber);
    }

    public static void main(String[] args) {
        new FIFO();
    }
}
