package HackerRank;

/**
 * https://www.hackerrank.com/challenges/training-the-army
 * Network Flow - good one
 * Max Flow
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TrainingTheArmy {
	
	private static int N;
	private static int T;
	private static int gn;
	private static int src;
	private static int sink;
	private Parser parser = new Parser(System.in);
	private static int[][] adjMatrix;
//	private static int[][] flowMatrix;
	
	static int maxFlow(){
//		flowMatrix = new int[gn][gn];
		int flow = 0;
		int maxFlow = 0;
		while((flow = bfs())!=-1){
			maxFlow+=flow;
		}
		return maxFlow;
	}
	
	static int bfs(){
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[gn];
		int[] parent = new int[gn];
		Arrays.fill(parent, -1);
		queue.add(src);
		visited[src] = true;
		
		while(!queue.isEmpty()){
			int top = queue.poll();
			if(top==sink)
				break;
			
			for (int i = 0; i < gn; i++) {
				if(visited[i] || adjMatrix[top][i]<=0)continue;
				parent[i] = top;
				visited[i] = true;
				queue.add(i);
			}			
		}
		
		
		if(parent[sink]==-1)
			return -1;
		
		int min  = Integer.MAX_VALUE;
		int current = sink;
		int p = parent[current];
		while(p!=-1){
			if(adjMatrix[p][current]<min)
				min = adjMatrix[p][current];;
			current = p;
			p = parent[current];
		}
		
		current = sink;
		p = parent[current];
		while(p!=-1){
			adjMatrix[p][current] -= min;
			adjMatrix[current][p] += min;
			current = p;
			p = parent[current];
		}
		return min;
	}
	
	int solve() throws Exception{
		
		N = parser.nextInt();
		T = parser.nextInt();
		gn = N+T+2;
		src = N+T;
		sink = N+T+1;
		
		adjMatrix = new int[gn][gn];
		
		for (int i = 0; i < N; i++) {
			adjMatrix[src][i] = parser.nextInt();
			adjMatrix[i][sink] = 1;
		}
		
		for (int i = 0; i < T; i++) {
			int as = parser.nextInt();
			
			for (int j = 0; j < as; j++) {
				adjMatrix[parser.nextInt()-1][N+i] = 1;
			}
			
			int bs = parser.nextInt();
			for (int j = 0; j < bs; j++) {
				adjMatrix[N+i][parser.nextInt()-1] = 1;
			}
		}
//		print();
		return maxFlow();
		
	}
	
	static void print(){
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				System.out.print(adjMatrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		TrainingTheArmy ta = new TrainingTheArmy();
		System.out.println(ta.solve());

	}

}

