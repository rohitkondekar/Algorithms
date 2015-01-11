package HackerRank;

import java.io.*;
import java.util.*;

public class ASuperHero_improved {
	
	private static Parser parser = new Parser(System.in);
	private static int N;
	private static int M;
	
	
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
	
	private long getKey(int i,int j, int origBullets, int lastBullets){
		long num = M*i+j;
		return (num*1000000+origBullets)*1000000+lastBullets;
	}
	
	String getSolution() throws Exception{
		
		StringBuilder result = new StringBuilder("");
		
		int T = parser.nextInt();
		while(T-->0){
			N = parser.nextInt();
			M = parser.nextInt();
			
			
			int[][] power = new int[N][M];
			for (int i = 0; i < power.length; i++) {
				for (int j = 0; j < M; j++) {
					power[i][j] = parser.nextInt();
				}				
			}
			
			int[][] bullets = new int[N][M];
			for (int i = 0; i < power.length; i++) {
				for (int j = 0; j < M; j++) {
					bullets[i][j] = parser.nextInt();
				}				
			}
			
			PriorityQueue<Node> heap = new PriorityQueue<ASuperHero_improved.Node>();
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
		return result.toString();
	}
	
	public static void main(String[] args) throws Exception {
		ASuperHero_improved as = new ASuperHero_improved();
		System.out.println(as.getSolution());
	}

}

class Parser{
	final private int BUFFER_SIZE = 1 << 17;
    final private DataInputStream din;
    final private byte[] buffer;
    private int bufferPointer, bytesRead;
    
    public Parser(InputStream in) {
        din = new DataInputStream(in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }
    
    private byte read() throws Exception {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }
    
    private void fillBuffer() throws Exception {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) {
            buffer[0] = -1;
        }
    }
    
    public String nextString() throws Exception {
        StringBuilder sb = new StringBuilder("");
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        do {
            sb.append((char) c);
            c = read();
        } while (c > ' ');
        return sb.toString();
    }
    
    public char nextChar() throws Exception {
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        return (char) c;
    }
    
    public int nextInt() throws Exception {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c > ' ');
        return ret;
    }
}
