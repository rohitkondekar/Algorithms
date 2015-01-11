package HackerRank;

//https://www.hackerrank.com/contests/w13/challenges/sherlock-and-anagrams

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class SherlockAndAnagrams {
	
	static String getSolution() throws NumberFormatException, IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder("");
		int T = Integer.parseInt(in.readLine());
		int trials = 0;
	
		while(trials++<T){
			String S = in.readLine();
			
			int count = 0;
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			
			for (int i = 0; i < S.length(); i++) {
				for (int j = i; j < S.length(); j++) {
					
					String subString = S.substring(i, j+1);
					int[] alphas = new int[26];
					
					
					
					for(char s:subString.toCharArray())
						alphas[s-'a']++;
					
					StringBuilder str = new StringBuilder("");
					for (int k = 0; k < alphas.length; k++) {
						for (int k2 = 0; k2 < alphas[k]; k2++) {
							str.append(Character.toString((char)(k+'a')));
						}						
					}

					if(map.containsKey(str.toString())){
						int val = map.get(str.toString());
						count+=val;
						map.put(str.toString(), val+1);
					}
					else
						map.put(str.toString(),1);
				}
			}
			result.append(count+"\n");
		}
		return result.toString().trim();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {		
		System.out.println(getSolution());
	}

}
