package HackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.hackerrank.com/contests/w13/challenges/a-super-hero


public class ASuperHero {

	private class Node implements Comparable<Node>{
		int i,j;
		int origBullets;
		int lastBullets;
		
		Node(int x,int y,int ob, int last){
			i = x;
			j = y;
			origBullets = ob;
			lastBullets = last;
		}
		
		@Override
		public int compareTo(Node other) {
			if(other.origBullets==origBullets)
				return 0;
			return origBullets<other.origBullets?-1:1;
		}
	}
	
	private Long getKey(int i,int j, int origBullets, int lastBullets){
		long num = M*i+j;
		return (num*1000000+origBullets)*1000000+lastBullets;
	}
	
	private static int N;
	private static int M;
	
	String getSolution() throws NumberFormatException, IOException{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder("");
		int T = Integer.parseInt(in.readLine());
		int trials = 0;
	
		while(trials++<T){
			String[] tmp = in.readLine().split(" ");
			N = Integer.parseInt(tmp[0]);
			M = Integer.parseInt(tmp[1]);
			
			int[][] power = new int[N][M];
			for (int i = 0; i < power.length; i++) {
				StringTokenizer token = new StringTokenizer(in.readLine()," ");
				int j = 0;
				while(token.hasMoreTokens()){
					power[i][j++] = Integer.parseInt(token.nextToken());
				}
			}
			
			int[][] bullets = new int[N][M];
			for (int i = 0; i < bullets.length; i++) {
				StringTokenizer token = new StringTokenizer(in.readLine()," ");
				int j = 0;
				while(token.hasMoreTokens()){
					bullets[i][j++] = Integer.parseInt(token.nextToken());
				}
			}
			
			
			PriorityQueue<Node> heap = new PriorityQueue<ASuperHero.Node>();
			HashSet<Long> visited = new HashSet<Long>();
			HashSet<Long> inHeap = new HashSet<Long>();
			
			for (int j = 0; j < M; j++) {
				heap.add(new Node(0,j,power[0][j],bullets[0][j]));
				inHeap.add(getKey(0, j, power[0][j], bullets[0][j]));
			}
			
			while(!heap.isEmpty()){
				Node top = heap.poll();
				
				Long topKey = getKey(top.i,top.j,top.origBullets,top.lastBullets);
				inHeap.remove(topKey);
						
				if(top.i == N-1)
				{
					result.append(top.origBullets+"\n");
					break;
				}
				
				if(visited.contains(topKey))
					continue;
				visited.add(getKey(top.i, top.j, top.origBullets, top.lastBullets));
				
				int nexti = top.i+1;
				for (int j = 0; j < M; j++) {
					int reqOrigs = top.origBullets + ((power[nexti][j]-top.lastBullets)<=0?0:(power[nexti][j]-top.lastBullets));

					int lastBul = bullets[nexti][j];
					long nextKey = getKey(nexti, j, reqOrigs, lastBul);
					if(visited.contains(nextKey))
						continue;
					
					if(inHeap.contains(nextKey))
						continue;
					heap.add(new Node(nexti,j,reqOrigs,lastBul));					
				}								
			}			
		}
		return result.toString().trim();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		ASuperHero as = new ASuperHero();
		System.out.println(as.getSolution());
	}

}
