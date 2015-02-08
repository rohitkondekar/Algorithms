package General;

public class FastModularExponentiation {

	long fastModularExponentiation(long num, long pow,long mod){
		
		long finalProduct = 1;
		long product = num;
		short numBits = (short) (Math.floor(Math.log(pow)/Math.log(2))+1);
		
		
		if((pow & 1)==1){
			finalProduct *= num;
			finalProduct %= mod;
		}
		
		for (int i = 1; i < numBits; i++) {
			product *= product;
			product %= mod;
			if((pow>>i & 1)==1){
				finalProduct *= product;
				finalProduct %= mod;
			}
		}
		
		return finalProduct;
	}
	
	public static void main(String[] args) {
		
		FastModularExponentiation fe = new FastModularExponentiation();
		System.out.println(fe.fastModularExponentiation(5,117,19));
		System.out.println(fe.fastModularExponentiation(3,1993,17));
	}

}
