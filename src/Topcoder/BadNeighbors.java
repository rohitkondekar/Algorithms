package Topcoder;

//http://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009
public class BadNeighbors {
	
	public int maxDonations(int[] donations){
		
		int N = donations.length;
		
		if(N==2)
			return Math.max(donations[0],donations[1]);
		
		//Using Element 0
		int[] opt = new int[N];
		opt[0] = donations[0];
		opt[1] =  Integer.max(opt[0],donations[1]);
		for(int i=2;i<N-1;i++){
			opt[i] = Integer.max(opt[i-1],donations[i]+opt[i-2]);
		}
		
		int val = opt[N-2];
		
		//Using Last Element
		opt = new int[N];
		opt[1] = donations[1];
		opt[2] =  Integer.max(opt[1],donations[2]);
		for(int i=2;i<N;i++){
			opt[i] = Integer.max(opt[i-1],donations[i]+opt[i-2]);
		}
		
		val = Math.max(val,opt[N-1]);
		return val;
	}

	public static void main(String[] args) {
		
		BadNeighbors bd = new BadNeighbors();
		int[] d = { 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 };
		System.out.println(bd.maxDonations(d));
	}

}
