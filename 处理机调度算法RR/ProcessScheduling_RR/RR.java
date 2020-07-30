package ProcessScheduling_RR;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class RR {

    private int processNumber;
    private ArrayList<Process> processList;

    public RR() {
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
            processList.get(i).process_num = i;
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
        Process actionProcess =  processList.get(0);
        boolean back = false;
        int timeStamp = 0;
        Queue<Process> queue = new LinkedList<Process>();
        boolean flag = true;
        boolean[] finished = new boolean[processNumber];
        for (int i = 0; i < processNumber; i++){
            finished[i] = false;
        }
        while (flag){
            flag = false;

            for (int i = 0; i < processNumber; i++) {
                //加入执行队列
                if (!finished[i]) {
                    flag = true;
                    if(processList.get(i).arriveTime == timeStamp){
                        queue.offer(processList.get(i));
                    }
                }
            }
            if (!flag){
                break;
            }

            if (back){
                queue.offer(actionProcess);
            }

            //执行
            actionProcess = queue.poll();
            //如果未执行过，则标明时间戳
            if (actionProcess.beginTime < 0){
                actionProcess.beginTime = timeStamp;
            }

            //已经服务时间 +1
            actionProcess.haveServiceTime ++;

            //如果服务已经完成
            if (actionProcess.haveServiceTime >= actionProcess.serviceTime){
                back = false;
                finished[actionProcess.process_num] = true;
                actionProcess.finishedTime = timeStamp + 1;
                actionProcess.turnArroundTime = actionProcess.finishedTime - actionProcess.arriveTime;
                actionProcess.weightedTurnArroundTime = actionProcess.turnArroundTime / actionProcess.serviceTime;
            }
            else {
                back = true;
            }

            timeStamp ++;
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
        new RR();
    }
}
