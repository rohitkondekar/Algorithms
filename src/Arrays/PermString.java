package Arrays;

import java.util.Arrays;
import java.util.HashMap;

public class PermString {

	/**
	 * print all permutations of a given string
	 * for duplicates :- hash table
	 * 
	 */
	private HashMap<String,Integer> map;

	public PermString() {
		map = new HashMap<String,Integer>();
		map.keySet().iterator();
	}
	
	StringBuffer swap(StringBuffer str,int i,int j)
	{
		char s = str.charAt(i);
		str.setCharAt(i, str.charAt(j));
		str.setCharAt(j, s);
		return str;
	}
	
	void permString(String s)
	{
		permString(new StringBuffer(s),0);
	}
	
	void permString(StringBuffer str,int start)
	{
		if(start==str.length())
		{
			if(!map.containsKey(str.toString()))
			{
				map.put(str.toString(),0);
				System.out.println(str.toString());
			}
			return;
		}
		
		for(int j=start;j<str.length();j++)
		{
			str = swap(str, start, j);
			permString(str, start+1);
			str = swap(str, start, j); //backtrack
		}		
	}
	
	String[] f(){
		
		
		String[] str = {"aa","aa"};
		return str;
	}
	
	
	public static void main(String[] args) {
	
//		String s = "abc";
//		System.out.println(s.length());
		int n = 1;
		int[] ar = {n,2,3};

//		PermString ps = new PermString();
//		ps.permString("abcc");
	}

}
