package Topcoder;

import java.io.*;
import java.util.*;

public class SherlockAndQueries {
	
	Parser parser = new Parser(System.in);
	
	long[] A;
	long[] C;
	int[] B;
	HashMap<Integer,Long> prodMap;
	int[] productArray;
	static final int mod = (int)Math.pow(10, 9)+7;
	
	void solve() throws Exception{
		int N = parser.nextInt();
		int M = parser.nextInt();
		prodMap = new HashMap<Integer, Long>();
		A = new long[N+1];
		B = new int[M+1];
		C = new long[M+1];
		
		for (int i = 1; i <= N; i++) {
			A[i] = parser.nextInt();
		}
		
		for (int i = 1; i <= M; i++) {
			B[i] = parser.nextInt();
		}
		
		for (int i = 1; i <= M; i++) {
			C[i] = parser.nextInt();
		}
		
		for (int i = 1; i <= M; i++) {
			if(!prodMap.containsKey(B[i]))
				prodMap.put(B[i],1L);
			prodMap.put(B[i],(prodMap.get(B[i])*C[i])%mod);
		}
	        
	    Iterator<Integer> it = prodMap.keySet().iterator();
	    int num;
        while(it.hasNext()){
        	num = it.next();
        	int i = 1;
        	int prod = i++*num;
        	while(prod<=N){
        		A[prod] = (A[prod]*prodMap.get(num))%mod;
        		prod = i++*num;
        	}
        }
		
		for (int i = 1; i < A.length; i++) {
			System.out.print(A[i]+" ");
		}

		
	}
	
	public static void main(String[] args) throws Exception {
		SherlockAndQueries sq = new SherlockAndQueries();
		sq.solve();
	
	}

}

class Parser {
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