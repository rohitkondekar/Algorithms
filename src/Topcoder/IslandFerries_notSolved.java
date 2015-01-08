package Topcoder;

import java.util.*;


public class IslandFerries_notSolved {
	
	public static boolean[][][] ferries;
	public static int[][] ferryPrices;
	public static int numIslands;
	public static int numFerries;
	
	public static int[] bestPrices;
	public static int numVisitedIslands;

	private class Node implements Comparable<Node>{
		Node parent;
		int islandNumber;		
		int costUpto = Integer.MAX_VALUE;
		ArrayList<Integer> tickets = new ArrayList<Integer>();
		
		Node(int n){
			islandNumber = n; 
		}
		
		void copyTickets(ArrayList<Integer> list, int notToCopy){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i)!=notToCopy)
					tickets.add(list.get(i));
			}
		}
		
		void copyTickets(Stack<Integer> list, int notToCopy){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i)!=notToCopy)
					tickets.add(list.get(i));
			}
		}
		
		@Override
		public int compareTo(Node other) {
			if(costUpto == other.costUpto)
				return 0;
			
			return costUpto<other.costUpto?-1:1;
		}
	}
	
	
	int getKey(int islandNumber,ArrayList<Integer> tickets){
		
		int[] indices = new int[3];
		Arrays.fill(indices, -1);
		
		for (int i = 0; i < tickets.size(); i++) {
			indices[i]=tickets.get(i)+2;
		}
		
		Arrays.sort(indices);
		
		int key = islandNumber+2;
		for (int i = 0; i < indices.length; i++) {
			key *= 100;
			key += indices[i]==-1?1:indices[i];
		}
		return key;
	}
	
	
	int[] costs(String[] legs, String[] prices){
		
		numFerries = legs.length;
		numIslands = prices.length;
		ferries = new boolean[numFerries][numIslands][numIslands];
		ferryPrices = new int[numIslands][numFerries];
		
		bestPrices = new int[numIslands-1];		
		Arrays.fill(bestPrices, -1);
		
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
		
		
		PriorityQueue<Node> heap = new PriorityQueue<IslandFerries_notSolved.Node>();
		HashMap<Integer, Node> map = new HashMap<Integer, IslandFerries_notSolved.Node>();
		heap.add(new Node(0));
		heap.peek().costUpto = 0;
		
		
		while(!heap.isEmpty()){
			Node top = heap.poll();
			
			if(bestPrices[top.islandNumber]==-1)
			{
				System.out.println(top.islandNumber+" "+(top.parent==null?"":top.parent.islandNumber)+" - "+top.costUpto+" "+top.tickets);
				bestPrices[top.islandNumber] = top.costUpto;
				numVisitedIslands++;
			}
			else{
				if(bestPrices[top.islandNumber]>top.costUpto)
				{
					System.out.println("Best Price Different");
					bestPrices[top.islandNumber] = top.costUpto;
				}
			}
			
			if(numVisitedIslands==numIslands-1)
				return bestPrices;
			
			
			Stack<Integer> stck = new Stack<Integer>();
			dijktraTicket(top, stck, map, heap);
			
//			System.out.println("----"+ heap.peek().islandNumber+" "+heap.peek().costUpto+" "+heap.peek().tickets);
//			System.out.println(numVisitedIslands);
			System.out.println(heap.size());


			System.out.println("--");
		}
		
		return null;
	}
	
	void print(HashMap<Integer, Node> map){
		for(int key:map.keySet()){
			Node tmp = map.get(key);
			System.out.println(key+" "+map.get(key).islandNumber+" "+map.get(key).costUpto+" "+tmp.tickets);
		}
		System.out.println("--");
	}
	
	private void dijktraTicket(Node top, Stack<Integer> stck, HashMap<Integer, Node> map,PriorityQueue<Node> heap  ){
		
		if(stck.size()>3)
			return;
		
		if(stck.isEmpty()){
			for(int ticket:top.tickets){
				
				for (int i = 0; i < numIslands; i++) {
					//is there a ferry from m to n of boat service ticket
					if(!ferries[ticket][top.islandNumber][i] || bestPrices[i]!=-1)
						continue;
					
					//free edge
					Node child = new Node(i);
					child.copyTickets(top.tickets, ticket);
					
					int key = getKey(child.islandNumber, child.tickets);
					child.costUpto = top.costUpto;
					child.parent = top;
					
					if(!map.containsKey(key)){
						map.put(key, child);
						heap.add(child);
					}
					else{
						Node tmp = map.get(key);
						if(tmp.costUpto>child.costUpto){
							heap.remove(tmp);
							heap.add(child);
							map.put(key, child);
						}	
					}
				
				}
			}
		}
		
		
		//add buy edge		
		for (int i = 0; i < numFerries; i++) {
			if(top.tickets.contains(i) || stck.contains(i))
				continue;
			stck.push(i);

			Node child = new Node(i);
			child.copyTickets(top.tickets, -1);
			child.copyTickets(stck, -1);
			
			int key = getKey(child.islandNumber, child.tickets);
			ArrayList<Integer> ar = new ArrayList<Integer>();
			ar.add(1);
			child.costUpto = top.costUpto;
			child.parent = top;
			
			for(int ticket:stck){
				child.costUpto+=ferryPrices[top.islandNumber][ticket];
			}

			if(!map.containsKey(key)){
				map.put(key, child);
				heap.add(child);
			}			
			else{

				Node tmp = map.get(key);
				if(tmp.costUpto>child.costUpto){
					heap.remove(tmp);
					heap.add(child);
					map.put(key, child);
				}	
			}
						
			dijktraTicket(top,stck,map,heap);
			stck.pop();
		}
		
	}
	
	

	public static void main(String[] args) {
//		IslandFerries_Old isf = new IslandFerries_Old();
//		String[] legs = { "0-1 1-2 2-3", "0-1 2-3" };
//		String[] prices = { "1 10", "20 25", "50 50", "1000 1000", "1000 1000" };
//		System.out.println(Arrays.toString(isf.costs(legs, prices)));

	}

}
