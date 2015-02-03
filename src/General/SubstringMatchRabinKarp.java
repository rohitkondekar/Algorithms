package General;

import java.math.BigInteger;
import java.util.Random;


/**
 * Two hash function check
 * Double hash Checking
 * @author rohitkondekar
 *
 */
public class SubstringMatchRabinKarp {
	
	private String pattern;
	private long pHash1;
	private long pHash2;
	private int M; // pattern Length
	
	//Hash 1
	private long mB1;
	private long Q1; //prime number
	private long B1; //base
	
	//Hash 2
	private long mB2;
	private long Q2; //prime number
	private long B2; //base
	
	SubstringMatchRabinKarp(String pattern){
		this.pattern = pattern;
		M = pattern.length();

		mB1 = 1;
		Q1 = getRandomPrime();
		B1 = 256;
		
		//Precalculate B^(M-1)

		for (int i = 1; i <= M-1; i++) {
			mB1 = (B1*mB1)%Q1;
		}
		
		while((Q2=getRandomPrime())==Q1);
		mB2 = 1;
		B2 = 101;
		
		for (int i = 1; i <= M-1; i++) {
			mB2 = (B2*mB2)%Q2;
		}

		pHash1 = getHash(pattern, B1, Q1);
		pHash2 = getHash(pattern, B2, Q2);
	}
	
	private long getHash(String key, long B, long Q){
		long h = 0;
		for (int i = 0; i < M; i++) {
			h = (h*B + key.charAt(i))%Q;
		}
		return h;
	}
	
	private long getRandomPrime(){
		return BigInteger.probablePrime(31, new Random()).longValue();
	}

	/**
	 * Returns offset - where match starts
	 * @param text
	 * @return
	 */
	public int search(String text){
		
		if(text.length()<M)
			return -1;
		
		long tHash1 = getHash(text, B1, Q1);
		long tHash2 = getHash(text, B2, Q2);
		

		if(tHash1==pHash1 && tHash2==pHash2)
			return 0;
		else if(text.length()==M)
			return -1;


		for (int i = M; i < text.length(); i++) {

			tHash1 = (tHash1 + Q1 - (mB1*text.charAt(i-M) % Q1) ) %Q1; //remove first character
			tHash1 = (tHash1*B1+text.charAt(i))%Q1;
			
			tHash2 = (tHash2+Q2-mB2*text.charAt(i-M)%Q2)%Q2;
			tHash2 = (tHash2*B2+text.charAt(i))%Q2;
			
			if(tHash1==pHash1 && tHash2==pHash2)
				return i-M+1;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		SubstringMatchRabinKarp sm = new SubstringMatchRabinKarp("abracadabra");
		System.out.println(sm.search("abacadabrabracabracadabrabrabracad"));

	}

}
