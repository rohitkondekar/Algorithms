package General;

public class BitwiseOperationsAddMulSubDiv {

	/**
	 * And : left shift gives us carry
	 * XOR : gives us non carry part
	 * @param x
	 * @param y
	 * @return
	 */
	int add(int x,int y){
		int a,b;
		do{
			a = x&y;
			b = x^y;
			x = a<<1;
			y = b;
		}while(x!=0);
		
		return y;
	}
	
	/**
	 * Takes 2's compliment of y
	 * @param x
	 * @param y
	 * @return
	 */
	int sub(int x,int y){
		y = negate(y);
		return add(x,y);				
	}
	
	int negate(int x){
		return add(~x,1);
	}
	
	/**
	 * Keep adding y for every 1 bit of x
	 * Keep doubling y for every left move
	 * x should be >0:: therefore negate both
	 * @param x
	 * @param y
	 * @return
	 */
	int mul(int x, int y){
		
		int m=1,z=0;
		
		if(x<0){
			x = negate(x);
			y = negate(y);
		}
		
		while(x>=m){
			if((x&m)!=0) z = add(y,z);
			y<<=1;
			m<<=1;
		}
		
		return z;
	}
	
	/**
	 * Keep subtracting y from x
	 * x and y should be positive so maintain a sign variable
	 * @param x
	 * @param y
	 * @return
	 */
	int div(int x,int y){
		int c = 0;
		boolean sign = true;
		
		if(x<0){
			x = negate(x);
			sign = !sign;
		}
		
		if(y<0){
			y = negate(y);
			sign = !sign;
		}
		
		if(y!=0){
			while(x>=y){
				x = sub(x,y);
				c++;
			}
		}
		
		if(!sign)
			c = negate(c);
		
		return c;
	}
	
	public static void main(String[] args) {
		
		BitwiseOperationsAddMulSubDiv bt = new BitwiseOperationsAddMulSubDiv();
		
		//add testing
		System.out.println("add testing");
		assert bt.add(-1, 100) == 99;
		assert bt.add(-1, 0) == -1;
		assert bt.add(1, 11) == 12;
		
		//sub testing
		System.out.println("sub testing");
		assert bt.sub(7, 2) == 5;
		assert bt.sub(2, 7) ==-5;
		assert bt.sub(10, 10) == 0;
		assert bt.sub(10, -10) == 20;
		
		//mul testing
		System.out.println("mul testing");
		assert bt.mul(7, 2) == 14;
		assert bt.mul(-2, 5) == -10;
		assert bt.mul(10, 0) == 0;
		
		//Div Testing
		System.out.println("Div testing");
		assert bt.div(6, 3) == 2;
		assert bt.div(9, -3) == -3;
		assert bt.div(0, 8) == 0;
		
	}

}
