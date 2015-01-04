package Arrays;
// Coding Interviews: Questions, Analysis & Solutions
// Harry He

import java.util.*;

public class StringCombination {
	
	
	static void combination(String str,int index, Stack<Character> stck)
	{
		if(index>=str.length())
			return;
		
		if(!stck.isEmpty())
			System.out.println(stck.toString());
		int[] num = new int[10];
		
		//consider element
		stck.push(str.charAt(index));
		combination(str, index+1, stck);
		stck.pop();
		combination(str, index+1, stck);		
	}
	
	public static void main(String[] args)
	{
		Stack<Character> stck = new Stack<Character>();
		combination("abc", 0, stck);		
	}

}
    