package Topcoder;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Escape {
		
	private static final byte HARMFUL = 1;
	private static final byte DEADLY = -1;
	private static int[][] fill = new int[501][501];
	
	
	private class Point implements Comparable<Point>{
		int x;
		int y;
		Integer dist = Integer.MAX_VALUE;
		
		Point(int a,int b){
			x=a;
			y=b;
		}
		
		@Override
		public int compareTo(Point other) {
			return this.dist.compareTo(other.dist);
		}
	}
	
	
	public int lowest(String[] harmful, String[] deadly){
		fill(harmful, HARMFUL);
		fill(deadly, DEADLY);
		
		if(fill[500][500]==-1)
			return -1;
		
		return dijkstras();
	}
	
	private static final int[][] MOVES = {
		{1,0},
		{-1,0},
		{0,1},
		{0,-1}
	};
	
	private int dijkstras(){
		
		PriorityQueue<Point> heap = new PriorityQueue<Escape.Point>();
		HashMap<Integer, Point> map = new HashMap<Integer, Escape.Point>();
		
		for (int i = 0; i < fill.length; i++) {
			for (int j = 0; j < fill.length; j++) {
				map.put(i*501+j, new Point(i,j));
			}
		}
		
		Point p = map.get(0);
		p.dist = 0;
		heap.add(p);
		
		while(!heap.isEmpty()){
			p = heap.poll();
			
			if(p.x==500 && p.y==500)
				return p.dist;
			
			for(int[] move:MOVES){
				int x = p.x+move[0];
				int y = p.y+move[1];
				
				if(x<0 || x>500 || y<0 || y>500)
					continue;
				
				if(fill[x][y]==-1)
					continue;
				
				Point tmp = map.get(x*501+y);				
				if(tmp.dist>p.dist+fill[x][y]){
					tmp.dist = p.dist+fill[x][y];
					heap.remove(tmp);
					heap.add(tmp);
				}				
			}
		}
		
		return -1;
	}
	
	
	
	private void fill(String[] array, byte type){
		
		for (int i = 0; i < array.length; i++) {	
			String[] tmp = array[i].split(" ");
			
			int row1,row2,col1,col2;
			int x1,x2,y1,y2;
			
			x1 = Integer.parseInt(tmp[0]);
			y1 = Integer.parseInt(tmp[1]);
			x2 = Integer.parseInt(tmp[2]);
			y2 = Integer.parseInt(tmp[3]);
			
			row1 = x1<x2?x1:x2;
			row2 = x1>x2?x1:x2;
			col1 = y1<y2?y1:y2;
			col2 = y1>y2?y1:y2;
			
			for (int j = row1; j <= row2; j++) {
				for (int j2 = col1; j2 <=col2; j2++) {
					fill[j][j2] = type;
				}
			}					
		}
	}
	

	public static void main(String[] args) { 
		Escape esc = new Escape();
		
		String[] harmful = {"500 0 0 500"};
		String[] deadly = {"0 0 0 0"};
		System.out.println( esc.lowest(harmful, deadly));


		String[] harmful1 = {"0 0 250 250", "250 250 500 500"};
		String[] deadly1 = {"0 250 250 500", "250 0 500 250"};
		System.out.println( esc.lowest(harmful1, deadly1));				
	}

}
