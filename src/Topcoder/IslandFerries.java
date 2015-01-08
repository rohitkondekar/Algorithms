package Topcoder;

import java.util.*;


//http://community.topcoder.com/stat?c=problem_statement&pm=2437&rd=5069
//Single Round Match 194 Round 1 - Division I, Level Three

public class IslandFerries {

	public static boolean[][][] ferries;
	public static int[][] ferryPrices;
	public static int numIslands;
	public static int numFerries;
	
	public static int[][] cost;
	
	int[] costs(String[] legs, String[] prices){
		
		numFerries = legs.length;
		numIslands = prices.length;
		
		ferries = new boolean[numFerries][numIslands][numIslands];
		ferryPrices = new int[numIslands][numFerries];
		
		int mask = 1<<numFerries; //represent tickets
		cost = new int[numIslands][mask];
		
		for (int i = 0; i < numIslands; i++) {
			for (int j = 0; j < mask; j++) {
				cost[i][j] = -1;
			}
		}
		
		//Parse Input
		for (int i = 0; i < numFerries; i++) {
			String[] trips = legs[i].split(" ");
			for (int j = 0; j < trips.length; j++) {
				String[] places = trips[j].split("-");
				ferries[i][Integer.parseInt(places[0])][Integer.parseInt(places[1])] = true;
			}
		}
		
		for (int i = 0; i < numIslands; i++) {
			String[] costs = prices[i].split(" ");
			for (int j = 0; j < costs.length; j++) {
				ferryPrices[i][j] = Integer.parseInt(costs[j]);
			}
		}
		
		
		cost[0][0] = 0;
		boolean modify = true;
		
		while(modify){
			modify = false;
			for (int i = 0; i < numIslands; i++) {
				if(cost[i][0]==-1)continue;//if not yet reached on island i
				for (int j = 0; j < mask; j++) {
					
					int have = Integer.bitCount(j);
					if(have>3) continue;
					
					//buy edge
					if(have<3){
						for (int k = 0; k < numFerries; k++) {
							if((j & (1<<k))!=0) continue; //already have the ticket
							int tmp = cost[i][j] + ferryPrices[i][k];
							int next = j | (1<<k);
							if(cost[i][next]>tmp || cost[i][next]<0)
							{
								cost[i][next] = tmp;
								modify = true;
							}							
						}
					}
					
					//use edge
					for (int k = 0; k < numFerries; k++) {
						if((j & (1<<k))==0) continue; //donot have that ticket to use
						
						for (int m = 0; m < numIslands; m++) {
							if(!ferries[k][i][m]) continue;
							
							int next = j & ~(1<<k);
							if(cost[m][next]>cost[i][j] || cost[m][next]<0)
							{
								cost[m][next] = cost[i][j];
								modify = true;
							}									
						}						
					}														
				}				
			}
		}
		
		int[] result = new int[numIslands-1];
		for (int i = 1; i < numIslands; i++) 
			result[i-1] = cost[i][0];
		
		return result;		
	}
	
	public static void main(String[] args) {
		IslandFerries isf = new IslandFerries();
		String[] legs = { "0-1 0-3", "0-2" };
		

		String[] prices = { "5 7", "1000 1000", "1000 1000", "1000 1000" };
		System.out.println(Arrays.toString(isf.costs(legs, prices)));

	}
}