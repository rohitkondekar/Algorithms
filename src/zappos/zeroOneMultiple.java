package zappos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class zeroOneMultiple {
	
	static HashSet<BigInteger> set = new HashSet<BigInteger>();
	
	public static BigInteger zeroOneMultipleHelper(Queue<BigInteger> parent,BigInteger num){
		
		Queue<BigInteger> children = new LinkedList<BigInteger>();
		
		BigInteger tmp;
		while(!parent.isEmpty()){
			tmp = parent.poll();
			
			String rem = tmp.mod(num).toString();
			if(rem.equals("0"))
				return tmp;
			
			if(!set.contains(new BigInteger(rem))){
				set.add(new BigInteger(rem));
				tmp = tmp.multiply(new BigInteger("10"));
				children.add(tmp);
				children.add(tmp.add(new BigInteger("1")));
			}							
		
		}
		
		return zeroOneMultipleHelper(children,num);
		
	}
	
	public static BigInteger zeroOneMultiple(long num){
		Queue<BigInteger> q  = new LinkedList<BigInteger>();
		q.add(new BigInteger("1"));
		return zeroOneMultipleHelper(q,new BigInteger(num+""));
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(zeroOneMultiple(Long.parseLong(in.readLine())));
		
	}
}
	
//	
//	static long zeroOneMultiple(long n){
//		
//		HashMap<Long, Long> map = new HashMap<Long, Long>();
//		
//		long val = 1;
//		while(val<=max){
//			long rem = val%n;
//			System.out.println(val+"  "+rem);
//			if(!map.containsKey(rem))
//				map.put(rem, val);
//			else{
//				return val - map.get(rem);
//			}
//			
//			val  = val*10 + 1;
//		}
//		return -1;
//	}
//
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		// TODO Auto-generated method stub
//		
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println(zeroOneMultiple(Long.parseLong(in.readLine())));
//	}
//	
//}
