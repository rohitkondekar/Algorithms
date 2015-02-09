package General;

import java.io.*;

public class TrianglePathSum {
	
	static Parser parser = new Parser(System.in);

	int solve() throws Exception{
		
		int N = parser.nextInt();
		int[][] mat = new int[N][N];
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < i+1; j++) {
				mat[i][j] = parser.nextInt();
			}
		}
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < i+1; j++) {
				int max1 = 0;
				
				if(i-1>=0)
					max1 = mat[i-1][j];
				
				if(i-1>=0 && j-1>=0)
					max1 = max1>mat[i-1][j-1]?max1:mat[i-1][j-1];
				
				mat[i][j] = mat[i][j]+max1;
			}
		}
		
		int max = 0;
		for (int j = 0; j < mat.length; j++) {
			if(mat[N-1][j]>max)
				max = mat[N-1][j];
		}
		return max;
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		TrianglePathSum ts = new TrianglePathSum();
		System.out.println(ts.solve());

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