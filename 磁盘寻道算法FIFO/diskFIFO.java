
public class diskFIFO {
    public diskFIFO(int [] findOrder, int initIndex){
        int countLong = 0;
        System.out.print("寻道序列:" + initIndex + "->");
        for (int i = 0; i < findOrder.length - 1; i++){
            System.out.print(findOrder[i] + "->");
            countLong += Math.abs(initIndex - findOrder[i]);
            initIndex = findOrder[i];
        }
        System.out.println(findOrder[findOrder.length - 1]);
        countLong += Math.abs(initIndex - findOrder[findOrder.length - 1]);
        System.out.println("寻道长度:" + countLong);
    }

    public static void main(String[] args) {
        int[] findOrder = {98,25,63,97,56,51,55,55,6};
        new diskFIFO(findOrder, 67);
    }
}
