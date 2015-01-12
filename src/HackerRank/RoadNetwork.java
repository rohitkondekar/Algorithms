package HackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://www.hackerrank.com/challenges/road-network
//All Pairs - Maximum Flow - Network flow
//Naive Approach n^2 max-flow problems - slow

//I finally found the paper from Gusfield. It has a really simple method.
//For those who are interested, here is a pseudo-c++-code:
//int parent[n]; //initialized to 0
//int answer[n][n]; //initialize this one to infinity
//for(int i=1;i<n;++i){
//	//Compute the minimum cut between i and parent[i].
//	//Let the i-side of the min cut be S, and the value of the min-cut be F
//	for (int j=i+1;j<n;++j)
//		if ((j is in S) && parent[j]==parent[i])
//			parent[j]=i;
//	answer[i][parent[i]]=answer[parent[i]][i]=F;
//	for (int j=0;j<i;++j)
//		answer[i][j]=answer[j][i]=min(F,answer[parent[i]][j]);
//}

public class RoadNetwork {
	
	private Parser parser = new Parser(System.in);
	private static int[][] adjacentMatrix;
//	private static int[][] adjacentMatrix_clone;
	private static int[][] flowMatrix;
	
	private static ArrayList<ArrayList<Integer>> adjacentList;
	private static int N,M;
	private static ArrayList<Integer> cut = new ArrayList<Integer>();
	
	long solveRoadNetwork() throws Exception{
			
		N = parser.nextInt();
		M = parser.nextInt();
		
		adjacentList = new ArrayList<ArrayList<Integer>>(N);
		for (int i = 0; i < N; i++) {
			adjacentList.add(new ArrayList<Integer>());
		}
		
		adjacentMatrix = new int[N][N];
//		adjacentMatrix_clone = new int[N][N];
		
		int x,y,z;
		for (int i = 0; i < M; i++) {
			x = parser.nextInt()-1;
			y = parser.nextInt()-1;
			z = parser.nextInt();
			
			adjacentList.get(x).add(y);
			adjacentList.get(y).add(x);
			adjacentMatrix[x][y] += z;
			adjacentMatrix[y][x] += z;
		}
		
		long result = 1;
		int[][] flows = gusfieldMaxFlowAllPairs();
		for (int i = 0; i < flows.length; i++) {
			for (int j = 0; j < i; j++) {
				result = (result*flows[i][j])%1000000007;
			}
		}
	
		return result;
	}
	

	
	private int[][] gusfieldMaxFlowAllPairs(){
		int[] parent = new int[N];
		int[][] flows = new int[N][N];
		
		for (int i = 0; i < N; i++)
			Arrays.fill(flows[i], Integer.MAX_VALUE);
		
		for (int i = 1; i < N; i++) {
			int val = getMaxFlow(i,parent[i]);
			
			for (int j = i+1; j < N; j++) {
				if(cut.contains(j) && parent[j]==parent[i])
					parent[j] = i;
			}
			
			flows[i][parent[i]] = flows[parent[i]][i] = val;
			
			for (int j = 0; j < i; j++) {
				flows[i][j] = flows[j][i] = Math.min(val, flows[parent[i]][j]);
			}
		}
		return flows;		
	}
	
	private int getMaxFlow(int source,int dest){
//		for (int i = 0; i < adjacentMatrix.length; i++) {
//			System.arraycopy(adjacentMatrix[i], 0, adjacentMatrix_clone[i], 0, N);
//		}
		flowMatrix = new int[N][N];
		
		int flow = 0;
		while(true){
			int pathCapacity = getPathBFS(source,dest);
			if(pathCapacity==-1) break;
			flow+=pathCapacity;
		}
		return flow;
	}
	
	private int getPathBFS(int source,int dest){
		cut.clear();
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];
		int[] parent = new int[N];
		Arrays.fill(parent, -1);
		
		queue.add(source);
		visited[source] = true;
		
		while(!queue.isEmpty()){
			
			int node = queue.poll();
			cut.add(node);
			
			if(node==dest)
				break;
			
			for(int adjacent : adjacentList.get(node)){
				if(adjacentMatrix[node][adjacent]-flowMatrix[node][adjacent]<=0 || visited[adjacent])continue;
//				if(adjacentMatrix_clone[node][adjacent]<=0 || visited[adjacent]) continue;
				parent[adjacent] = node;
				visited[adjacent] = true;
				queue.add(adjacent);
			}
		}
		
		int minCapacity = Integer.MAX_VALUE;
		int before = parent[dest];
		int after = dest;
		
		while(before!=-1){
			if(adjacentMatrix[before][after] - flowMatrix[before][after] < minCapacity)
				minCapacity = adjacentMatrix[before][after] - flowMatrix[before][after];
//			if(adjacentMatrix_clone[before][after]<minCapacity)
//				minCapacity=adjacentMatrix_clone[before][after];
			before = parent[before];
			after = parent[after];
		}
		
		
		before = parent[dest];
		after = dest;
		
		while(before!=-1){
//			adjacentMatrix_clone[before][after] -= minCapacity;
//			adjacentMatrix_clone[after][before] -= minCapacity;
			flowMatrix[before][after] += minCapacity;
			flowMatrix[after][before] -= minCapacity;
			before = parent[before];
			after = parent[after];
		}
		
		if(minCapacity==Integer.MAX_VALUE)
			return -1;
		
		return minCapacity;
	}
	

	public static void main(String[] args) throws Exception {
		RoadNetwork rd = new RoadNetwork();
		System.out.println(rd.solveRoadNetwork());
	}

}

