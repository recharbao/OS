import java.util.Queue;
import java.util.LinkedList;

public class FIFO {
	
	private  int size ;
	Queue<Integer> queue;

	public FIFO(int size){
		queue = new LinkedList<Integer>();
		this.size = size;
	}
	
	public int Fifo(int[] parms){
		if(size <= 0 || parms == null)
            throw new IllegalArgumentException("illegal arugments");
		int count = 0;
		for(int i : parms){
			boolean flag = true;
			
			for(int j : queue){
				if(i == j){
					flag = false;
				}
			}
			
			if(flag && queue.size() < size){
				queue.offer(i);
				count ++;
			}
			else if(flag){
				
				queue.poll();
				queue.offer(i);
				count ++;
			}
			
			System.out.println("页面:");
			for(int j : queue){
				System.out.print(j+" ");
			}
			System.out.println();
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		int[] parms = {4,3,2,1,4,3,5,4,3,2,1,5};
		int size = 3;
		FIFO fifo = new FIFO(size);
		System.out.println("页面的置换次数为:" + fifo.Fifo(parms));
	}
}