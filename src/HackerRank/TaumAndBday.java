package HackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//https://www.hackerrank.com/contests/w13/challenges/taum-and-bday


public class TaumAndBday {
	
	static String solveQuestion() throws NumberFormatException, IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder("");
		
		int T = Integer.parseInt(in.readLine());
		int trials = 0;
		
		while(trials++<T){
			String[] tmp = in.readLine().split(" ");
			long B = Integer.parseInt(tmp[0]);
			long W = Integer.parseInt(tmp[1]);
			
			tmp = in.readLine().split(" ");
			long costB = Integer.parseInt(tmp[0]);
			long costW = Integer.parseInt(tmp[1]);
			long costConv = Integer.parseInt(tmp[2]);
			
			costB = costB<(costW+costConv)?costB:(costW+costConv);
			costW = costW<(costB+costConv)?costW:(costB+costConv);
			
			result.append(B*costB+W*costW+"\n");						
		}
		return result.toString();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println(solveQuestion());
	}

}
