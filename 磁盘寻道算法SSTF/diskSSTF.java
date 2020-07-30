public class diskSSTF {
    public diskSSTF(int[] findOrder, int initIndex){
        int countLong = 0;
        System.out.print("寻道序列:" + initIndex);
        int times = 0;
        boolean[] flag = new boolean[findOrder.length];
        for (int i = 0; i < flag.length; i++){
            flag[i] = false;
        }
        while (times ++ < findOrder.length){
            int min = 999999;
            int minIndex = 0;
            for (int i = 0; i < findOrder.length; i++){
                if (!flag[i]  && min > Math.abs(initIndex - findOrder[i])){
                    min = Math.abs(initIndex - findOrder[i]);
                    //System.out.println("min=" + min);
                    minIndex = i;
                }
            }
            System.out.print("->" + findOrder[minIndex]);
            countLong += min;
            initIndex = findOrder[minIndex];
            flag[minIndex] = true;
        }
        System.out.println();
        System.out.println("寻道长度:" + countLong);
        System.out.println("平均寻道长度:" + countLong/(findOrder.length + 0.0));
    }

    public static void main(String[] args) {
        int[] findOrder = {98,25,63,97,56,51,55,55,6};
        new diskSSTF(findOrder, 67);
    }
}
