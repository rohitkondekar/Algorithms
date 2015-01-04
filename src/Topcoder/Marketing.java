package Topcoder;

import java.util.*;

//http://community.topcoder.com/tc?module=ProblemDetail&rd=4472&pm=1524
//TCCC '03 Round 2
public class Marketing {

	public long howMany(String[] compete){
		
		int N = compete.length;
		boolean[][] adjMatrix = new boolean[N][N];
		
		for(int i=0;i<N;i++){
			if(compete[i].equals(""))
				continue;
				
			String[] arr = compete[i].split(" ");
			for(int j=0;j<arr.length;j++){
				adjMatrix[i][Integer.parseInt(arr[j])] = true;
				adjMatrix[Integer.parseInt(arr[j])][i] = true;
			}
		}
		
		int[] nodes = new int[N];
		Arrays.fill(nodes,-1);
		
		int connectedComponents = 0;
		for(int i=0;i<nodes.length;i++){
			int currentColor = 0;
			if(nodes[i]==-1){
				connectedComponents++;
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.add(i);
				nodes[i] = currentColor;
				
				while(!queue.isEmpty()){
					int m = queue.remove();
					currentColor = (nodes[m]+1)%2;
					for(int j=0;j<N;j++){
						
						if(m!=j && adjMatrix[m][j]){	
							 if(nodes[j]==-1){
								queue.add(j);
								nodes[j] = currentColor;
							 }
							 else if(nodes[j]!=currentColor)
									return -1;
						}
					}
				}
				
			}
		}
		
		return 1<<connectedComponents;
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Marketing mk = new Marketing();
//		String[] str = {"1 4","2","3","0",""};
//		String[] str = {"1","2","0"};
		String[] str = {"","","","","","","","","","",
				 "","","","","","","","","","",
				 "","","","","","","","","",""};
		System.out.println(mk.howMany(str));
	}

}
