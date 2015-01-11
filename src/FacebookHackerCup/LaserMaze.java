package FacebookHackerCup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LaserMaze {
	
	private class Node{
		
		int i,j;
		int steps = 0;
		
		Node(int i,int j, int s){
			this.i = i;
			this.j = j;
			steps = s;
		}
	}
	
	private int[][] maze;
	ArrayList<ArrayList<Integer>> cols;
	ArrayList<ArrayList<Integer>> rows;
	
	private double getKey(int i,int j, int steps){
		
		int sum = 0;
		int index =0;
		for(ArrayList<Integer> collist:cols){
			for(int r:collist){
				sum += ((maze[r][index]+steps)%4)+1;
			}
			index++;
		}
		
		index =0;
		for(ArrayList<Integer> rowlist:rows){
			for(int c:rowlist){
				sum += ((maze[index][c]+steps)%4)+1;
			}
			index++;
		}

		return ((i+1)*1000+(j+1))*10000+sum;
		
	}
	
	String solveLaserMaze() throws NumberFormatException, IOException{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder("");
		
		int T = Integer.parseInt(in.readLine());
		int trials = 0;
	
		while(trials++<T){
			result.append("Case #"+trials+" ");
			
			String[] tmp = in.readLine().split(" ");
			int m = Integer.parseInt(tmp[0]);
			int n = Integer.parseInt(tmp[1]);
			
			int startx=0,starty=0;
			int endx=0,endy=0;
			
			cols = new ArrayList<ArrayList<Integer>>();
			rows = new ArrayList<ArrayList<Integer>>();
			
			maze = new int[m][n];
			for (int i = 0; i < maze.length; i++) {
				rows.add(new ArrayList<Integer>());
				tmp = in.readLine().split("");
				for (int j = 0; j < maze[0].length; j++) {
					if(i==0)
						cols.add(new ArrayList<Integer>());
					
					if(tmp[j].equals("#"))
						maze[i][j] = -1;
					else if(tmp[j].equals("."))
						maze[i][j] = 5;
					else if(tmp[j].equals("^")){
						maze[i][j] = 0;
						rows.get(i).add(j);
						cols.get(j).add(i);
					}
					else if(tmp[j].equals(">")){
						maze[i][j] = 1;
						rows.get(i).add(j);
						cols.get(j).add(i);
					}
					else if(tmp[j].equals("v")){
						maze[i][j] = 2;
						rows.get(i).add(j);
						cols.get(j).add(i);
					}
					else if(tmp[j].equals("<")){
						maze[i][j] = 3;
						rows.get(i).add(j);
						cols.get(j).add(i);
					}
					else if(tmp[j].equals("S")){
						startx = i;
						starty = j;
						maze[i][j] = 5;
					}
					else if(tmp[j].equals("G")){
						maze[i][j] = 5;
						endx = i;
						endy = j;
					}
				}
			}

			
			Queue<Node> queue = new LinkedList<LaserMaze.Node>();
			queue.add(new Node(startx,starty,0));
			
			HashSet<Double> set = new HashSet<Double>();
			
			boolean solved = false;
			while(!queue.isEmpty() && !solved){
				Node top = queue.poll();

				
				for(int[] move:moves){
					int x = top.i+move[0];
					int y = top.j+move[1];
					int steps = top.steps+1;
					
					if(x<0 || x>=m || y<0 || y>=n || maze[x][y]!=5 )
						continue;
					
					if(set.contains(getKey(x, y, steps)))
						continue;					

					boolean valid = true;
					ArrayList<Integer> rowlist = rows.get(x);
					for(int col:rowlist){
						if(col<y && ((maze[x][col]+steps)%4==1))
						{
							valid = false;
							break;
						}
						else if(col>y && ((maze[x][col]+steps)%4==3)){
							valid = false;
							break;
						}
					}
					
					if(!valid)
						continue;
					
					ArrayList<Integer> collist = cols.get(y);
					for(int row:collist){
						if(row<x && ((maze[row][y]+steps)%4==2))
						{
							valid = false;
							break;
						}
						else if(row>x && ((maze[row][y]+steps)%4==0)){
							valid = false;
							break;
						}
					}
					
					if(!valid)
						continue;
					
					Node state = new Node(x,y,steps);
					queue.add(state);
					set.add(getKey(state.i, state.j,state.steps));
				
					if(x==endx && y==endy){
						result.append(steps+"\n");
						solved = true;
					}					
				}
			}
			
			if(!solved)
				result.append("impossible"+"\n");			

		}
		return result.toString().trim();
	}

	private static int[][] moves = {
		{-1,0},
		{0,1},
		{1,0},
		{0,-1}
	};
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		LaserMaze lm = new LaserMaze();
		System.out.println(lm.solveLaserMaze());

	}

}
