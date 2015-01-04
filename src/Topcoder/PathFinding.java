package Topcoder;

import java.util.LinkedList;
import java.util.Queue;

//http://community.topcoder.com/stat?c=problem_statement&pm=1110
//Single Round Match 156 Round 1 - Division I, Level Three

public class PathFinding {

	class Node{
		int Ax,Ay,Bx,By;
		int numSteps;
		
		Node(int ax,int ay,int bx, int by, int num){
			Ax = ax;
			Ay = ay;
			Bx = bx;
			By = by;
			numSteps = num;
		}
	}
	
	public int minTurns(String[] board){
		
		int N = board.length;
		int M = board[0].length();
		
		int ax=0,ay=0,bx=0,by=0;
		boolean[][][][] positions = new boolean[N][M][N][M];
		
		boolean[][] mat = new boolean[N][M];
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {				
				char c = board[i].charAt(j);
				if(c=='.'){
					mat[i][j] = true;
				}
				else if(c=='A')
				{
					mat[i][j] = true;
					ax=i;
					ay=j;
				}
				else if(c=='B'){
					mat[i][j] = true;
					bx=i;
					by=j;
				}				
			}
		}
		
		positions[ax][ay][bx][by] = true;
		Node start = new Node(ax,ay,bx,by,0);
		Queue<Node> queue = new LinkedList<PathFinding.Node>();
		queue.add(start);
		
		
		while(!queue.isEmpty()){
			Node tmp = queue.remove();
			
			if(tmp.Ax==start.Bx && tmp.Ay==start.By && tmp.Bx==start.Ax && tmp.By == start.Ay)
				return tmp.numSteps;
			
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					for (int j2 = -1; j2 <= 1; j2++) {
						for (int k = -1; k <= 1; k++) {
							
							ax = tmp.Ax+i;
							ay = tmp.Ay+j;
							bx = tmp.Bx+j2;
							by = tmp.By+k;
							
							if(!(ax>=0 && ax<N && ay>=0 && ay<M && bx>=0 && bx<N && by>=0 && by<M))
								continue;
							
							if(!mat[ax][ay] || !mat[bx][by] || positions[ax][ay][bx][by])
								continue;
							
							if(bx==tmp.Ax && by==tmp.Ay && ax==tmp.Bx && ay==tmp.By)
								continue;
							
							if(ax==bx && ay==by)
								continue;
							
							queue.add(new Node(ax,ay,bx,by,tmp.numSteps+1));
							positions[ax][ay][bx][by] = true;
						}
					}
				}
			}
		}
		return -1;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PathFinding pf = new PathFinding();
		String[] board ={"AB.................X",
				 "XXXXXXXXXXXXXXXXXXX.",
				 "X..................X",
				 ".XXXXXXXXXXXXXXXXXXX",
				 "X..................X",
				 "XXXXXXXXXXXXXXXXXXX.",
				 "X..................X",
				 ".XXXXXXXXXXXXXXXXXXX",
				 "X..................X",
				 "XXXXXXXXXXXXXXXXXXX.",
				 "X..................X",
				 ".XXXXXXXXXXXXXXXXXXX",
				 "X..................X",
				 "XXXXXXXXXXXXXXXXXXX.",
				 "X..................X",
				 ".XXXXXXXXXXXXXXXXXXX",
				 "X..................X",
				 "XXXXXXXXXXXXXXXXXXX.",
				 "...................X",
				 ".XXXXXXXXXXXXXXXXXXX"};
		System.out.println(pf.minTurns(board));
	}

}
