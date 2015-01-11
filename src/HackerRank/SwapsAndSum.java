package HackerRank;


public class SwapsAndSum {
	
	Parser parser = new Parser(System.in);
	
	String solveSwapandSum() throws Exception{
		
		StringBuilder result = new StringBuilder("");

		int n = parser.nextInt();
		int q = parser.nextInt();
		
		long[] nums = new long[n];
		
		for (int i = 0; i < n; i++) 
			nums[i] = parser.nextInt();
		
		int[] vals = new int[n];
		int maxMovement = 0;
		int l,r,tmp,start,end;
		long sum;
		
		for (int i = 0; i < q; i++) {
			switch(parser.nextInt()){
			case 1: 
				l = parser.nextInt()-1;
				r = parser.nextInt()-1;
//				start = (l-maxMovement)>=0?l-maxMovement:0;
//				end = (r+maxMovement)<vals.length?(r+maxMovement):vals.length-1;
				
				for (int j = 0 ; j < vals.length ; j++) {
					tmp = vals[j]+j;
					if(tmp>=l && tmp<=r){
						tmp -= l;
						
						vals[j] += ((tmp&1)==0)?+1:-1;		
//						maxMovement = maxMovement>Math.abs(vals[j])?maxMovement:Math.abs(vals[j]);
					}
				}
				break;
				
			case 2:
				l = parser.nextInt()-1;
				r = parser.nextInt()-1;
//				start = (l-maxMovement)>=0?l-maxMovement:0;
//				end = (r+maxMovement)<vals.length?(r+maxMovement):vals.length-1;
				sum=0;
				for (int j = 0; j < vals.length; j++) {
					tmp = vals[j]+j;
					if(tmp>=l && tmp<=r)
						sum += nums[j];
				}
				result.append(sum+"\n");
				break;
			}
			
		}
		
		
		return result.toString();
	}
	
	
	public static void main(String[] args) throws Exception {
		SwapsAndSum sw = new SwapsAndSum();
		System.out.println(sw.solveSwapandSum());
	}

}