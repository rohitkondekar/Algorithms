package FacebookHackerCup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NewYearsResolution {

	static String getResolution() throws NumberFormatException, IOException{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder("");
		
		int T = Integer.parseInt(in.readLine());
		int trials = 0;
	
		while(trials++<T){
			
			result.append("Case #"+trials+" ");
			
			String[] tmp = in.readLine().split(" ");
			int[] wanted = new int[3];
			wanted[0] = Integer.parseInt(tmp[0]);
			wanted[1] = Integer.parseInt(tmp[1]);
			wanted[2] = Integer.parseInt(tmp[2]);
			
			int numFoods = Integer.parseInt(in.readLine());
			int mask = 1<<numFoods;
			
			
			int[][] amounts = new int[numFoods][3];
			int index = 0;
			while(index<numFoods){
				tmp = in.readLine().split(" ");
				amounts[index][0] = Integer.parseInt(tmp[0]);
				amounts[index][1] = Integer.parseInt(tmp[1]);
				amounts[index][2] = Integer.parseInt(tmp[2]);
				index++;
			}
			
			boolean solved = false;
			
			for (int i = 1; i < mask; i++) {
				
				int sumP = 0,sumC = 0, sumF = 0;
				for (int j = 0; j < numFoods; j++) {
					if((i & 1<<j)!=0){
						sumP+=amounts[j][0];
						sumC+=amounts[j][1];
						sumF+=amounts[j][2];
					}					
				}
				
				if(sumP>wanted[0] || sumC>wanted[1] || sumF>wanted[2])
					continue;
				
				if(sumP==wanted[0] && sumC==wanted[1] && sumF==wanted[2])
				{
					result.append("yes"+"\n");
					solved = true;
					break;
				}
				
			}
			
			if(!solved)
				result.append("no"+"\n");
			
		}
		
		return result.toString().trim();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(getResolution());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
