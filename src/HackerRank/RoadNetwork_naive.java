package HackerRank;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
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


public class RoadNetwork_naive {
	
	private Parser parser = new Parser(System.in);
	
	private static int[][] adjacentMatrix;
	private static int[][] adjacentMatrix_clone;
	private static ArrayList<ArrayList<Integer>> adjacentList;
	private static int N,M;
	
	long solveRoadNetwork() throws Exception{
		
		N = parser.nextInt();
		M = parser.nextInt();
		
		adjacentList = new ArrayList<ArrayList<Integer>>(N);
		for (int i = 0; i < N; i++) {
			adjacentList.add(new ArrayList<Integer>());
		}
		
		adjacentMatrix = new int[N][N];
		adjacentMatrix_clone = new int[N][N];
		
		int x,y,z;
		for (int i = 0; i < M; i++) {
			x = parser.nextInt()-1;
			y = parser.nextInt()-1;
			z = parser.nextInt();
			
			adjacentList.get(x).add(y);
			adjacentList.get(y).add(x);
			adjacentMatrix[x][y] = z;
			adjacentMatrix[y][x] = z;
		}
		
//		long result = 1;
//		for (int i = 0; i < N; i++) {
//			for (int j = i+1; j < N; j++) {
//				result= (result%(1000000007))*(getMaxFlow(i,j)%(1000000007))%1000000007;
//			}
//		}		
//		return result;
		
		long result = 1;
		int[][] flows = gusfieldMaxFlowAllPairs();
		for (int i = 0; i < flows.length; i++) {
			for (int j = i+1; j < flows.length; j++) {
				result = result*flows[i][j] % 1000000007;
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
			ArrayList<Integer> cut = getCut(i);
			
			for (int j = i+1; j < N; j++) {
				if(cut.contains(j) && parent[j]==parent[i])
					parent[j] = i;
			}
			
			flows[i][parent[i]] = flows[parent[i]][i] = val;
			
			for (int j = 0; j < flows.length; j++) {
				flows[i][j] = flows[j][i] = Math.min(val, flows[parent[i]][j]);
			}
		}
		return flows;		
	}
	
	
	//Call After MaxFlow
	private ArrayList<Integer> getCut(int start){
		
		boolean[] visited = new boolean[N];
		Queue<Integer> queue = new LinkedList<Integer>();
		ArrayList<Integer> cut = new ArrayList<Integer>();
		queue.add(start);
		
		while(!queue.isEmpty()){
			int top = queue.poll();
			cut.add(top);
			
			for(int adjacent : adjacentList.get(top)){
				if(adjacentMatrix_clone[top][adjacent]<=0 || visited[adjacent]) continue;
				visited[adjacent] = true;
				queue.add(adjacent);
			}
		}
		
		return cut;
	}
	
	
	
	private int getMaxFlow(int source,int dest){
		
		for (int i = 0; i < adjacentMatrix.length; i++) {
			for (int j = 0; j < adjacentMatrix[0].length; j++) {
				adjacentMatrix_clone[i][j] = adjacentMatrix[i][j];
			}
		}
		
		int flow = 0;
		while(true){
			int pathCapacity = getPathBFS(source,dest);
			if(pathCapacity==-1) break;
			flow+=pathCapacity;
		}
		return flow;
	}
	
	class Node implements Comparable<Node>{
		int vertex;
		int minFlow;
		Node parent;
		
		Node(int v,int f,Node p){
			vertex = v;
			minFlow = f;
			parent = p;
		}
		
		@Override
		public int compareTo(Node o) {
			if(o.minFlow == minFlow)
				return 0;
			return minFlow <o.minFlow?1:-1;
		}
	}
	private int getPathPFS(int source,int dest){
		
		PriorityQueue<Node> heap = new PriorityQueue<Node>();
		boolean[] visited = new boolean[N];
		heap.add(new Node(0,Integer.MAX_VALUE,null));
		
		Node top=null,end=null;
		while(!heap.isEmpty()){
			top = heap.poll();
			if(visited[top.vertex])continue;
			visited[top.vertex]=true;
			
			if(top.vertex==dest)
			{
				end = top;
				break;
			}
			
			for(int adjacent:adjacentList.get(top.vertex)){
				if(adjacentMatrix_clone[top.vertex][adjacent]<=0 || visited[adjacent]) continue;
				heap.add(new Node(adjacent,Math.min(top.minFlow,adjacentMatrix_clone[top.vertex][adjacent]),top));
			}			
		}
		
		if(end==null)
			return -1;		
		
		int minCapacity = end.minFlow;
		Node current = end;
		while(current.parent!=null){
			adjacentMatrix_clone[current.parent.vertex][current.vertex] -= minCapacity;
			adjacentMatrix_clone[current.vertex][current.parent.vertex] -= minCapacity;
			current = current.parent;
		}
		return minCapacity;
	}
	
	
	private int getPathBFS(int source,int dest){
		
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];
		int[] parent = new int[N];
		Arrays.fill(parent, -1);
		
		queue.add(source);
		visited[source] = true;
		
		while(!queue.isEmpty()){
			
			int node = queue.poll();
			if(node==dest)
				break;
			
			for(int adjacent : adjacentList.get(node)){
				if(adjacentMatrix_clone[node][adjacent]<=0 || visited[adjacent]) continue;
				parent[adjacent] = node;
				visited[adjacent] = true;
				queue.add(adjacent);
			}
		}
		
		int minCapacity = Integer.MAX_VALUE;
		int before = parent[dest];
		int after = dest;
		
		while(before!=-1){
			if(adjacentMatrix_clone[before][after]<minCapacity)
				minCapacity=adjacentMatrix_clone[before][after];
			before = parent[before];
			after = parent[after];
		}
		
		
		before = parent[dest];
		after = dest;
		
		while(before!=-1){
			adjacentMatrix_clone[before][after] -= minCapacity;
			adjacentMatrix_clone[after][before] -= minCapacity;
			before = parent[before];
			after = parent[after];
		}
		
		if(minCapacity==Integer.MAX_VALUE)
			return -1;
		
		return minCapacity;
	}

	public static void main(String[] args) throws Exception {
		RoadNetwork_naive rn = new RoadNetwork_naive();
		System.out.println(rn.solveRoadNetwork());

	}

}
