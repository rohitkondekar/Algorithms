package FacebookHackerCup;

import java.util.*;

public class Autocomplete {

	private static Parser parser = new Parser(System.in);
	
	private class Node{
		Node[] alphabets = new Node[26];
		int dept;
		int words;
		
		Node(int d,int w){
			dept = d;
			words = w;
		}
	}
	
	String solve() throws Exception{
		
		int T = parser.nextInt();
		int test = 1;
		StringBuilder buf = new StringBuilder();
		
		while(test<=T){
			
			buf.append("Case #"+test+": ");
			int N = parser.nextInt();
			Node root = new Node(0, 0);
			
			int num = 0;
			
			for (int i = 0; i < N; i++) {
				String word = parser.nextString();
				Node current = root;
				
				for (int j = 0; j < word.length(); j++) {					
					if(current.alphabets[word.charAt(j)-'a']!=null){
						current = current.alphabets[word.charAt(j)-'a'];
						current.words++;
					}
					else{
						current.alphabets[word.charAt(j)-'a'] = new Node(current.dept+1, 1);
						current = current.alphabets[word.charAt(j)-'a'];
					}
					
				}			
	
				current = root;	
				int j = 0;
				for (; j < word.length(); j++) {
					if((current.words!=0 && current.words==1)){
						num += current.dept;						
						break;
					}
					current = current.alphabets[word.charAt(j)-'a'];
				}
				if(j==word.length())
					num += current.dept;
			}	
			buf.append(num+"\n");
			test++;
		}
		
		return buf.toString();
	}
	
	public static void main(String[] args) throws Exception {
		
		Autocomplete ac = new Autocomplete();
		System.out.println(ac.solve());

	}

}

