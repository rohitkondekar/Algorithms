package Topcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


//STill some issue left
public class RoboCourier {
	
	
	private class Point implements Comparable<Point>{
		int x,y;
		Point parent;
		int steps; //default 0
		boolean visited; //default false
		int dist = Integer.MAX_VALUE;
		int reachDirection; //default 0;
		boolean[] inDirections = new boolean[6];
		
		Point(int a, int b){
			x=a;
			y=b;
		}
		
		@Override
		public int compareTo(Point other) {
			if(dist==other.dist)
				return 0;
			return dist>other.dist?1:-1;
		}
	}
	
	private static int getValue(int x,int y){
		return 10000*x+y;
	}
	
	private final static int[][] positions = {{-2,0},
		{-1,1},
		{1,1},
		{2,0},
		{1,-1},
		{-1,-1}};
	
	
	
	public int timeToDeliver(String[] path){
		int startx = 500;
		int starty = 500;
		int endx;
		int endy;
		
		HashMap<Integer, Point> map = new HashMap<Integer, RoboCourier.Point>();
		map.put(getValue(startx, starty), new Point(startx,starty));
		
		int x=startx,y=starty;
		int positionIndex = 0;
		
		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path[i].length(); j++) {
				
				char c = path[i].charAt(j);
				if(c=='R')
					positionIndex+=1;
				else if(c=='L')
					positionIndex-=1;
				else{
					x+= positions[positionIndex][0];
					y+= positions[positionIndex][1];
					Point p = new Point(x,y);
					p.inDirections[(positionIndex+3)%6]=true;
					if(!map.containsKey(getValue(x, y)))
						map.put(getValue(x, y), p);
				}
											
				if(positionIndex<0)
					positionIndex = 5;
				positionIndex = positionIndex%6;
			}
		}
		
//		System.out.println(Arrays.toString(map.keySet().toArray()));
		
		if(x==startx && y==starty)
			return 0;
		
		endx = x;
		endy = y;
		
		
		
		PriorityQueue<Point> heap = new PriorityQueue<RoboCourier.Point>();
		
		map.get(getValue(startx, starty)).dist=0;
		heap.add(map.get(getValue(startx, starty)));
		
		while(!heap.isEmpty()){
			Point top = heap.poll();
			
			if(top.x == endx && top.y == endy){
				Point p = top;
				while(p!=null){
					System.out.println(p.x+" "+p.y+" "+p.reachDirection);
					p = p.parent;

				}
				return top.dist;
			}
			
			top.visited = true;
			
			for (int i = 0; i < positions.length; i++) {
				x = top.x + positions[i][0];
				y = top.y + positions[i][1];
				
				int value = getValue(x, y);
				if(!map.containsKey(value) || map.get(value).visited)
					continue;
				
				Point point = map.get(getValue(x, y));
				if(!point.inDirections[(i+3)%6])
					continue;
				
				int distance,steps = 1;
				
				if(i==top.reachDirection){
					steps = top.steps+1;
					if(steps>2)
						distance = top.dist+2;
					else
						distance = top.dist+4;
				}
				else{
					int low = top.reachDirection<i?top.reachDirection:i;
					int high = top.reachDirection>i?top.reachDirection:i;
					distance = top.dist + 4 + 3*((low-high+6)<(high-low)?(low-high+6):(high-low));
				}
				
				
				if(point.dist>distance){
					point.dist = distance;
					point.parent = top;
					point.reachDirection = i;
					point.steps = steps;
					heap.remove(point);
					heap.add(point);
				}

			}
						
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		RoboCourier rc = new RoboCourier();
		String[] path = { "FRRFLLFLLFRRFLF" };
		String[] path1 = { "RFLLF" };
		String[] path2 = { "FLFRRFRFRRFLLFRRF" };
		String[] path3 = { "FFFFFFFFFRRFFFFFFRRFFFFF",
		  "FLLFFFFFFLLFFFFFFRRFFFF" };
		String[] path4 = { "RFLLFLFLFRFRRFFFRFFRFFRRFLFFRLRRFFLFFLFLLFRFLFLRFF",
				  "RFFLFLFFRFFLLFLLFRFRFLRLFLRRFLRFLFFLFFFLFLFFRLFRLF",
		  "LLFLFLRLRRFLFLFRLFRF" };
		System.out.println(rc.timeToDeliver(path));
//		System.out.println(rc.timeToDeliver(path1));
//		System.out.println(rc.timeToDeliver(path2));
		System.out.println(rc.timeToDeliver(path3));
		System.out.println(rc.timeToDeliver(path4));
	
	}

}
