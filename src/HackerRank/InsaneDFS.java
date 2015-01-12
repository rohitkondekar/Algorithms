package HackerRank;

import java.util.Arrays;

//wrong
public class InsaneDFS {
	
	Parser parse = new Parser(System.in);

	String solveDFS() throws Exception{

		int n = parse.nextInt();
		long num = 1;
		int[] array = new int[n];
		
		char c = parse.nextChar();

		if(c!='?' && c!='0')
			return "0";
		
		int max=0,t;
		for (int i = 1; i < array.length; i++) {
			array[i] = (t=parse.nextChar())=='?'?-1:t-'0';

			if(array[i]!=-1 && array[i]<max)
				return "0";
			if(array[i]>max) max = array[i];
		}
		
//		System.out.println(Arrays.toString(array));

		
		int low = 1, high =-1;
		int blanks = 0;
		for (int i = 1; i < array.length; i++) {
			if(array[i]==-1)
				blanks++;
			else{
				high = array[i];
				num*=getArrangements(low,high,blanks);
				if(num<0)
					return "0";
				low = high;
				high = -1;
				blanks=0;
			}
		}
		
		if(high==-1 && blanks!=0)
			num*=getArrangements(low,high,blanks);
		
		
		return (num%(1000000007)+"");
		
	}
	
	long getArrangements(int low,int high,int blanks){

//		System.out.println(low+" "+high+" "+blanks);
		long t;
		if(high == -1){
			long tot = 0;
			for (int i = low; i <= low+blanks; i++) {
				tot+=(t=getArrangements(low,i,blanks-1))==-1?0:t;
			}
			return tot;			
		}
		
		
		long dif1 = high - low;
		long dif2 = blanks-dif1;
		
//		System.out.println(dif1+" "+dif2+" ");
		
		if(blanks==0 && dif2==-1)
			return 1;
		else if(dif2<0)
			return -1;
		else if(dif2==0)
			return 1;
		

		return (dif1+1)*dif2;
	}
	
	public static void main(String[] args) throws Exception {
		InsaneDFS id = new InsaneDFS();
		System.out.println(id.solveDFS());

	}

}
//
//class Parser {
//	final private int BUFFER_SIZE = 1 << 17;
//	final private DataInputStream din;
//	final private byte[] buffer;
//	private int bufferPointer, bytesRead;
//
//	public Parser(InputStream in) {
//		din = new DataInputStream(in);
//		buffer = new byte[BUFFER_SIZE];
//		bufferPointer = bytesRead = 0;
//	}
//
//	private byte read() throws Exception {
//		if (bufferPointer == bytesRead) {
//			fillBuffer();
//		}
//		return buffer[bufferPointer++];
//	}
//
//	private void fillBuffer() throws Exception {
//		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//		if (bytesRead == -1) {
//			buffer[0] = -1;
//		}
//	}
//
//	public String nextString() throws Exception {
//		StringBuilder sb = new StringBuilder("");
//		byte c = read();
//		while (c <= ' ') {
//			c = read();
//		}
//		do {
//			sb.append((char) c);
//			c = read();
//		} while (c > ' ');
//		return sb.toString();
//	}
//
//	public char nextChar() throws Exception {
//		byte c = read();
//		while (c <= ' ') {
//			c = read();
//		}
//		return (char) c;
//	}
//
//	public int nextInt() throws Exception {
//		int ret = 0;
//		byte c = read();
//		while (c <= ' ') {
//			c = read();
//		}
//		do {
//			ret = ret * 10 + c - '0';
//			c = read();
//		} while (c > ' ');
//		return ret;
//	}
//}

