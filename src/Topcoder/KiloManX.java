package Topcoder;

import java.util.PriorityQueue;


//http://community.topcoder.com/stat?c=problem_statement&pm=2288&rd=4725
//Has at max 15 weapons/bosses
//Dijkstras heap algo
//Node being the available weapons/bosses killed + shots used + visited states

//Important problem
//Concept idea : http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=graphsDataStrucs3

//Main Concept : use that node first with less number of shots.

public class KiloManX {
	
	private static boolean[] visited;
	
	public class Node implements Comparable<Node>{
		int weapons;
		Integer shots;
		
		Node(int w, int s){
			weapons = w;
			shots = s;
		}
		
		@Override
		public int compareTo(Node other) {
			return shots.compareTo(other.shots);
		}		
	}
		
	public int leastShots(String[] damageChart, int[] bossHealth){
		
		int numWeapons = bossHealth.length;
		visited = new boolean[1<<numWeapons];
		
		PriorityQueue<Node> heap = new PriorityQueue<KiloManX.Node>();
		heap.add(new Node(0,0));
		
		while(!heap.isEmpty()){
			Node node = heap.poll();
			if(visited[node.weapons])
				continue;
			visited[node.weapons] = true;
	
			if(node.weapons==((1<<numWeapons)-1))
				return node.shots;
			
			for (int i = 0; i < bossHealth.length; i++) {

				//already killed this boss - if u have his weapon
				if(((node.weapons>>i) & 1) ==1)
					continue;

				Node nw = new Node(node.weapons|1<<i,bossHealth[i]);
				
				for (int j = 0; j < numWeapons; j++) {
					if((node.weapons>>j & 1) ==1){//do u have this weapon
						int damage =  Character.getNumericValue(damageChart[j].charAt(i));
						if(damage==0)
							continue;
						int shots = (int)Math.ceil(((double)bossHealth[i])/damage);
						if(shots<nw.shots)
							nw.shots = shots;
					}
				}
				
				nw.shots += node.shots;
				heap.add(nw);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		KiloManX km = new KiloManX();
		String[] damageChart = {"198573618294842",
				 "159819849819205",
				 "698849290010992",
				 "000000000000000",
				 "139581938009384",
				 "158919111891911",
				 "182731827381787",
				 "135788359198718",
				 "187587819218927",
				 "185783759199192",
				 "857819038188122",
				 "897387187472737",
				 "159938981818247",
				 "128974182773177",
				 "135885818282838"};
		int[] bossHealth  = {157, 1984, 577, 3001, 2003, 2984, 5988, 190003,
				9000, 102930, 5938, 1000000, 1000000, 5892, 38};

		System.out.println(km.leastShots(damageChart, bossHealth));

	}

}
