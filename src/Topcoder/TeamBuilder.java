package Topcoder;

import java.util.Arrays;

public class TeamBuilder {

	private static int[][] distance;
	
	public int[] specialLocations(String[] paths){
		
		distance = new int[paths.length][paths.length];
		for (int i = 0; i < paths.length; i++) {
			for (int j = 0; j < paths[i].length(); j++) {
				distance[i][j] = paths[i].charAt(j)=='0'?0:1;
			}
		}
		
		for (int i = 0; i < distance.length; i++) {
			for (int j = 0; j < distance.length; j++) {
					if(i==j)continue;					
					
					if(distance[i][j]==0)
						distance[i][j]=paths.length+1;
			}
		}		

		for (int k = 0; k < distance.length; k++) {
			for (int i = 0; i < distance.length; i++) {
				for (int j = 0; j < distance.length; j++) {
					distance[i][j] = Math.min(distance[i][j], distance[i][k]+distance[k][j]);
				}
			}
		}
		
		int[] result = new int[2];
		for (int i = 0; i < distance.length; i++) {
			int tmp = 0;
			for (int j = 0; j < distance.length; j++) {
				if(distance[i][j]!=paths.length+1)tmp++;
			}
			if(tmp==paths.length)
				result[0]++;
		}
		
		
		for (int j = 0; j < distance.length; j++) {
			int tmp = 0;
			for (int i = 0; i < distance.length; i++) {
				if(distance[i][j]!=paths.length+1)tmp++;
			}
			if(tmp==paths.length)
				result[1]++;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		TeamBuilder tb = new TeamBuilder();
		String[] paths = {"01000","00100","00010","00001","10000"};
		System.out.println(Arrays.toString(tb.specialLocations(paths)));

	}

}
