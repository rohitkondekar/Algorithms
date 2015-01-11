package FacebookHackerCup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class CookingTheBooks {

	static String solveCooking() throws NumberFormatException, IOException{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder("");
		int T = Integer.parseInt(in.readLine());
		int trials = 0;
	
		while(trials++<T){
			String[] num = in.readLine().split("");
			
			int[] highest = new int[10];
			int[] lowest = new int[10];
			
			Arrays.fill(highest, -1);
			Arrays.fill(lowest, -1);
			
			for (int i = 0; i < num.length; i++) {
				int tmp = Integer.parseInt(num[i]);
				if(highest[tmp]==-1)
					highest[tmp] = i;
				lowest[tmp] = i;
			}
			

			boolean changedLowest=false, changedHighest=false;
			String smaller="",larger="";
			result.append("Case #"+trials+" ");
			
			for (int i = 0; i < num.length-1; i++) {
				int digit = Integer.parseInt(num[i]);
				int next = -1;
				
				//lowest
				if(!changedLowest){
					
					for (int j = (i==0?1:0); j <digit ; j++) {
						if(lowest[j]!=-1 && lowest[j]>i)
						{
							next = lowest[j];
							changedLowest = true;
							break;
						}
					}
					
					if(changedLowest){
						swap(num,i,next);
						smaller = Arrays.toString(num).replaceAll("[, \\[\\]]", "")+" ";
						swap(num,i,next);
					}
				}
				
				//highest
				if(!changedHighest){
					
					for (int j = 9; j >digit; j--) {
						if(lowest[j]!=-1 && lowest[j]>i){
							next = lowest[j];
							changedHighest = true;
							break;
						}
					}
					
					if(changedHighest){
						swap(num,i,next);
						larger = Arrays.toString(num).replaceAll("[, \\[\\]]", "")+"\n";
						swap(num,i,next);
					}
				}				
			}	
			
			smaller = smaller.equals("")?Arrays.toString(num).replaceAll("[, \\[\\]]", "")+" ":smaller;
			larger = larger.equals("")?Arrays.toString(num).replaceAll("[, \\[\\]]", "")+"\n":larger;
			result.append(smaller).append(larger);
			
		}
		
		return result.toString().trim();
		
	}
	
	static void swap(String[] num,int i, int j){
		String tmp = num[j];
		num[j] = num[i];
		num[i] = tmp;
	}
	
	public static void main(String[] args) {
		
		
		try {
			System.out.println(solveCooking());
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

}
