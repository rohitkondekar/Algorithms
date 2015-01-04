package Topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class CostOfDancing{
	
		public int minimum(int K, int[] danceCost){
		
			int N = danceCost.length;
			if(K==N){
				int sum = 0;
				for(int i=0;i<N;i++){
					sum+=danceCost[i];
				}
				return sum;
			}
			
			if(K==1){
				int min = danceCost[0];
				for(int i=1;i<N;i++){
					if(danceCost[i]<min)
						min = danceCost[i];
				}
				return min;
			}
			
			
			int[][] opt = new int[N][K];
			
			//fill diagonal
			int sum=0;
			int min=danceCost[0];
			for(int i=0;i<N;i++){
				sum+=danceCost[i];
				if(danceCost[i]<min)
					min = danceCost[i];
				if(i<K)opt[i][i] = sum;
				opt[i][0] = min; // here 0 means K=1
			}
			
			
			for(int i=2;i<N;i++){
				for(int j=1;j<i && j<K;j++){
					opt[i][j] = Math.min(danceCost[i]+opt[i-1][j-1],opt[i-1][j]);
				}
			}
			
			return opt[N-1][K-1];			
		}

	public static void main(String[] args) {
		CostOfDancing cd = new CostOfDancing();
		int[] cost = 	{973, 793, 722, 573, 521, 568, 845, 674, 595, 310, 284, 794, 913, 93, 129, 758, 108, 433, 181, 163, 96, 932,
				 703, 989, 884, 420, 615, 991, 364, 657, 421, 336, 801, 142, 908, 321, 709, 752, 346, 656, 413, 629, 801};
		System.out.println(cd.minimum(39, cost));
	}

}