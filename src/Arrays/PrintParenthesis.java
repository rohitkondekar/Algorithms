package Arrays;

public class PrintParenthesis {

	/**
	 *  Print all valid combinations of groups of parenthesis. For example, for three pairs of "()", the possible outcomes include:
((())), (()()), (())(), ()(()), ()()()
	 */
	
	
	void printParenthesis(int numLeft, int numRight, String str)
	{
		//termination
		if(numRight==0)
		{
			System.out.println(str);
			return;
		}
		
		if(numLeft>0)
		{
			printParenthesis(numLeft-1, numRight, str+"(");
			if(numLeft<numRight)
				printParenthesis(numLeft, numRight-1, str+")");
		}
		else
			printParenthesis(numLeft, numRight-1, str+")");
	}
	
	
	public static void main(String[] args) {
		
		PrintParenthesis pn = new PrintParenthesis();
		pn.printParenthesis(5,5, "");

	}

}
