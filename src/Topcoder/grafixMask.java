package Topcoder;
import java.util.*;

//http://community.topcoder.com/stat?c=problem_statement&pm=2998&rd=5857
//Single Round Match 211 Round 1 - Division I, Level Two
public class grafixMask {

	public class Node{
		int x;
		int y;
		
		Node(int i, int j){
			x=i;
			y=j;
		}
	}
	
	public boolean[][] mask = new boolean[400][600];

	public int[] sortedAreas(String[] rectangles){
		
		int l=0;
		while(l<rectangles.length){
			String[] values = rectangles[l].split(" ");
			int i = Integer.parseInt(values[0]);
			int j = Integer.parseInt(values[1]);
			int m = Integer.parseInt(values[2]);
			int n = Integer.parseInt(values[3]);

			for(int x=i;x<=m;x++)
				for(int y=j;y<=n;y++)
					mask[x][y]=true;
			l++;
		}

		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0;i<400;i++)
			for(int j=0;j<600;j++)
				if(!mask[i][j])
					result.add(dfs(i,j));


		result.sort(null);
		int[] rst = new int[result.size()];
		Iterator<Integer> it = result.iterator();
		int i=0;
		while(it.hasNext()){
			rst[i++] = it.next();
		}
		return rst;
	}
	
	public int dfs(int i, int j){
		int area = 0;
		Stack<Node> stck = new Stack<Node>();
		stck.push(new Node(i,j));

		while(!stck.isEmpty()){
			Node tmp = stck.pop();
			i = tmp.x;
			j = tmp.y;

			if(mask[i][j])
				continue;
			mask[i][j] = true;
			area++;
			
			if(j+1<600 && !mask[i][j+1])
				stck.push(new Node(i,j+1));
			if(i+1<400 && !mask[i+1][j])
				stck.push(new Node(i+1,j));
			if(j-1>=0 && !mask[i][j-1])
				stck.push(new Node(i,j-1));
			if(i-1>=0 && !mask[i-1][j])
				stck.push(new Node(i-1,j));
		}
		return area;
	}

	public static void main(String[] args) {
		grafixMask gf = new grafixMask();
		String[] arg = {"0 292 399 307"};
		System.out.println(Arrays.toString(gf.sortedAreas(arg)));
		String str = "";
		String[] s = str.split(" ");
		Queue<Integer> q = new LinkedList<Integer>();
	}

}
