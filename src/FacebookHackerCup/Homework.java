package FacebookHackerCup;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class Homework {
	
	private static Parser parser = new Parser(System.in);
	private static ArrayList<Integer> primes = new ArrayList<Integer>();
	
	
	void getSieve(){
		boolean[] sieve = new boolean[3163];
		Arrays.fill(sieve, true);
		for (int i = 0; i < sieve.length; i=i+2) {
			sieve[i] = false;
		}
		sieve[1] = false;
		sieve[2] = true;
		
		for (int i = 3; i < sieve.length; i=i+2) {			
			if(sieve[i]){
				for (int j = 3; j*i < sieve.length; j=j+2) {					
					sieve[j*i] = false;
				}
			}
		}
		for (int i = 0; i < sieve.length; i++) {
			if(sieve[i])primes.add(i);
		}
		
	}
	
	String solve() throws Exception{
		
		int T = parser.nextInt();
		int test = 1;
		StringBuilder buf = new StringBuilder();
		getSieve();
		
		int[] mat = new int[10000001];
		
		
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 2; i < mat.length; i++) {	
			set.clear();
			if(primes.contains(i)){
				set.add(i);
				mat[i] = 1;
				continue;
			}
			
			int limit = (int) Math.ceil(Math.sqrt(i));
			int num = i;
			for (int j = 0; j < primes.size() && primes.get(j)<=limit; j++) {					
				while(num%primes.get(j)==0){
					set.add(primes.get(j));
					num = num/primes.get(j);
				}					
			}
			if(num>2)
				set.add(num);
			
			mat[i] = set.size();
		}	

		
		int A,B,K;
		while(test<=T){
			A = parser.nextInt();
			B = parser.nextInt();
			K = parser.nextInt();
			
			int count = 0;			
			for (int i = A; i <= B; i++) {
				if(mat[i]==K)
					count++;
			}	

			buf.append("Case #"+test+": "+count+"\n");
			test++;
		}
		return buf.toString();	
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Homework hm = new Homework();
		System.out.println(hm.solve());
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