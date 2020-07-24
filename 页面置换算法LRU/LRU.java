public class LRU {

	private  int size ;
	private int[][] s;
	
	private LRU(int size){
		this.size = size;
		s = new int[size][2];
		
	}
	
	private int Lru(int[] parms){
		if(size <= 0 || parms == null)
            throw new IllegalArgumentException("illegal arugments");
		int count = 0;
		for(int i : parms){
			boolean flag = true;
			for(int j = 0; j < s.length; j++){
				if(s[j][0] == i){
					flag = false;
					s[j][1] = 0;
				}
			}
			
			
			if(flag && count < size){
				s[count ++][0] = i;
			}else if(flag){
				int MAX = s[0][1];
				int MAX_k = 0;
				for(int k = 0; k < s.length; k++){
					if(s[k][1] > MAX){
						MAX = s[k][1];
						MAX_k = k;
					}
				}
				s[MAX_k][0] = i;
				s[MAX_k][1] = 0;
				count ++;
			}
			
			for(int l = 0; l < s.length; l++){
				if(s[l][0] != 0){
					s[l][1] ++;
				}
			}
			
			System.out.println("页面:");
			for(int l = 0; l < s.length; l++){
				System.out.print(s[l][0]+" ");
			}
			System.out.println();
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		int[] parms = {4,3,2,1,4,3,5,4,3,2,1,5};
		int size = 3;
		LRU lru = new LRU(size);
		System.out.println("页面的置换次数为:" + lru.Lru(parms));
	}
}
