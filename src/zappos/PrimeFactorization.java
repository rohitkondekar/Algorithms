package zappos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PrimeFactorization {

	
	static int primeFactorization(int num){
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		while(num%2==0){
			map.put(2, map.containsKey(2)?map.get(2)+1:1);
			num /=2;
		}
		

		int sqr = (int)Math.sqrt(num);
		for (int i = 3; i<=sqr && num!=1 ; i=i+2) {
			while(num%i==0)
			{
				map.put(i, map.containsKey(i)?map.get(i)+1:1);
				num/=i;
			}
		}
		
		if(num>1)
			map.put(num, map.containsKey(num)?map.get(num)+1:1);
		
		
		Integer[] arr = map.values().toArray(new Integer[0]);
		int totalPrimes = arr.length;
		
		boolean result = true;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]%totalPrimes!=0){
				result = false;
				break;
			}
		}
		
		return result?1:0;
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(primeFactorization(25));
		System.out.println(primeFactorization(26));
		System.out.println(primeFactorization(27000000));
	}

}
