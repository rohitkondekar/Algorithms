package Topcoder;

import java.util.Arrays;
import java.util.Stack;

//http://community.topcoder.com/tc?module=ProblemDetail&rd=4494&pm=1593
//As it is DAG - it must have vertices with out degree as zero
//Using DP - 
//We will assign each vertex a cost ->i.e. longest path that can begin at that vertex
//base case are those vertices with outdegree as 0 so there length is 0.

public class Circuits {

	int[][] costMat;
	
	public int howLong(String[] connects, String[] costs){
		
		int N = connects.length;
		costMat = new int[N][N];
		int[] longPath = new int[N];
		Arrays.fill(longPath,-1);
		//for Topo sort
		Stack<Integer> stck = new Stack<Integer>();
		
		//initialization
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				costMat[i][j]=Integer.MIN_VALUE;
		


		
		//construction of cost matrix
		for(int i=0;i<N;i++){
			if(connects[i].equals(""))
			{	
				longPath[i] = 0;
				stck.push(i);
				continue;
			}
				
			String[] nodes = connects[i].split(" ");
			String[] ct = costs[i].split(" ");
			
			for(int j=0;j<nodes.length;j++){
				costMat[i][Integer.parseInt(nodes[j])] = Integer.parseInt(ct[j]);
			}
		}
		
		
		//dfs for topological sort
		for (int i = 0; i < N; i++)
			topologicalSort(stck,i);
		
		int max = 0;
		for (int i = stck.size()-1; i >=0;i--) {
			if(longPath[i]==0)
				continue;
			
			int tmp = 0;
			for (int j = 0; j < N; j++){
				if(costMat[i][j]!=Integer.MIN_VALUE && longPath[j]!=-1){
					if(tmp<costMat[i][j]+longPath[j])
						tmp = costMat[i][j]+longPath[j];
				}
			}
			longPath[i] = tmp;
			if(tmp>max)
				max = tmp;
		}
		
		return max;				
	}

	public void topologicalSort(Stack<Integer> stck, int node){
		if(stck.contains(node))
			return;
		
		for (int j = 0; j < costMat.length; j++) {
			if(costMat[node][j]!=Integer.MIN_VALUE){
				topologicalSort(stck,j);
			}
		}
		stck.push(node);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circuits ct = new Circuits();
		String[] s = {"","2 3 5","4 5","5 6","7","7 8","8 9","10",
				 "10 11 12","11","12","12",""};
		
		String[] c = {"","3 2 9","2 4","6 9","3","1 2","1 2","5",
				 "5 6 9","2","5","3",""};
		System.out.println(ct.howLong(s,c));
		
	}

}
