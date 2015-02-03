package zappos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static HashSet<BigInteger> set = new HashSet<BigInteger>();
	
	public static BigInteger getSolution(long num){
		Queue<BigInteger> queue = new LinkedList<BigInteger>();
		queue.add(new BigInteger("1"));
		
		BigInteger n = new BigInteger(num+"");
		BigInteger top;
		while(true){
			top = queue.poll();
			String remender = top.mod(n).toString();
			
			if(remender.equals("0"))
				return top;
			
			if(!set.contains(new BigInteger(remender))){
				set.add(new BigInteger(remender));
				top = top.multiply(new BigInteger("10"));
				queue.add(top);
				queue.add(top.add(new BigInteger("1")));
			}		
		}	
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(getSolution(Long.parseLong(in.readLine())));

	}

}
