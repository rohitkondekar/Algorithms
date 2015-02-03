package General;

/**
 * 
 * @author rohitkondekar
 * 
 * Range API - using Interval Tree as a Data Structure.
 * - Each node consists a range starting-ending
 * - contains boolean is end is included or not.
 * - all comparisons for range is done on starting index
 * - each node also stores the maximum string below it.
 * 
 * - For search query
 * 		- if contains within the range - found
 * 		- if below the starting string - go left
 * 		- if above the starting range - compare left max with input - if greater or equal go left
 * 				- else go right (has a issue of end point inclusion - need to fix)
 * 
 * - Delete Not implemented
 *
 */


public class RangeAPI {
	
	private Node root;
	
	// Node Structure
	private class Node {
		
		String lowerLimit;
		String higherLimit;
		boolean lowerInclusive;
		boolean higherInclusive;
		String max="";
		
		Node right,left,parent;
		
		Node(String lowerLimit,String higherLimit){
			this.lowerLimit = lowerLimit;
			this.higherLimit = higherLimit;
		}
		
		void setMax(String input){
			if(compare(max, input)==1)
				max = input;				
		}
	
		void setLowerInclusive(){
			lowerInclusive = true;
		}
		
		void unSetLowerInclusive(){
			lowerInclusive = false;
		}
		
		void setHigherInclusive(){
			higherInclusive = true;
		}
		
		void unSetHigherInclusive(){
			higherInclusive = false;
		}
		
		void setParent(Node parent){
			this.parent = parent;
		}
	}
	

	/**
	 * Custom compare function - compares input to bound
	 * if input>bound return 1 - i.e. goto right
	 * if input<boung return -1 - i.e. goto left
	 * @param bound
	 * @param input
	 * @return
	 */
	private int compare(String bound, String input){
		
		if(bound.equals("") && !input.equals(""))
			return 1;
		
		int compare = 0;			
		for (int i = 0; i < bound.length() && i<input.length(); i++) {
			
			if(bound.toLowerCase().charAt(i)>input.toLowerCase().charAt(i)){
				compare = -1;
				break;
			}
			else if(bound.toLowerCase().charAt(i)<input.toLowerCase().charAt(i)){
				compare = 1;
				break;
				
			}
			else{ // equal
				if(bound.charAt(i)>input.charAt(i))
				{
					compare = -1;
					break;
				}
				else if(bound.charAt(i)<input.charAt(i))
				{
					compare = 1;
					break;
				}
			}				
		}
		
		return compare;			
	}
	
	
	/**
	 * Add a text to the interval tree
	 * @param lowerLimit
	 * @param isLowerInclusive
	 * @param higherLimit
	 * @param isHigherInclusive
	 * @throws Exception
	 */
	void AddText(String lowerLimit, boolean isLowerInclusive, String higherLimit, boolean isHigherInclusive) throws Exception{
		
		Node tmpNode = new Node(lowerLimit, higherLimit);
		if(compare(lowerLimit, higherLimit)==-1)
			throw new Exception("Invalid User Input");
		
		if(root==null){
			root = tmpNode;
		}
		
		Node current = root;
		
		while(true){
			if(compare(current.lowerLimit, tmpNode.lowerLimit)>0){
				current.setMax(tmpNode.higherLimit);
				if(current.right==null)
					break;
				else
					current = current.right;
			}
			else if(compare(current.lowerLimit,tmpNode.lowerLimit)==0){
				break;
			}
			else{
				current.setMax(tmpNode.higherLimit);
				if(current.left==null)
					break;
				else
					current = current.left;
			}
		}
		
		//if same range as encountered earlier - just modify the limit.
		if(compare(current.lowerLimit, tmpNode.lowerLimit)==0 && compare(current.higherLimit, tmpNode.higherLimit)==0){
			if(isLowerInclusive)current.setLowerInclusive();
			if(isHigherInclusive)current.setHigherInclusive();
			return;
		}
		
		//if starting index is same just change the lower index if appropriate
		if(compare(current.lowerLimit, tmpNode.lowerLimit)==0){
			
			if(isLowerInclusive)current.setLowerInclusive();
			
			if(compare(current.higherLimit, tmpNode.higherLimit)<0)
			{
				current.higherLimit = tmpNode.higherLimit;
				current.higherInclusive = isHigherInclusive;
			}
			else if(compare(current.higherLimit, tmpNode.higherLimit)==0)
				if(isHigherInclusive)current.setHigherInclusive();
			
			return;
		}
		
		if(compare(current.lowerLimit, tmpNode.lowerLimit)>0){
			current.right = new Node(lowerLimit, higherLimit);
			current.right.setParent(current);
			if(isLowerInclusive)current.right.setLowerInclusive();
			if(isHigherInclusive)current.right.setHigherInclusive();
		}
		else{//left
			current.left = new Node(lowerLimit, higherLimit);
			current.left.setParent(current);
			if(isLowerInclusive)current.left.setLowerInclusive();
			if(isHigherInclusive)current.left.setHigherInclusive();
		}
		
	}
	
	/**
	 * Searches for a text in the the tree, computation based on lower index
	 * @param text
	 * @return
	 * @throws Exception
	 */
	boolean QueryText(String text) throws Exception{
		
		if(root==null)
			throw new Exception("No Search Tree found");
		
		Node current = root;
		
		while(current!=null){
			
			if(compare(current.lowerLimit, text)==0 && !current.lowerInclusive)
				return false;
			
			
			if(compare(current.lowerLimit, text)>0 && compare(current.higherLimit, text)<=0){
				if(compare(current.higherLimit, text)==0){
					if(current.higherInclusive)
						return true;
				}
				else{
					return true;
				}
			}
			
			if(compare(current.lowerLimit, text)<0)
				current = current.left;
			else{
				
				if(current.left!=null && compare(current.left.max,text)<=0){
					current = current.left;
				}
				else
					current = current.right;	
			}
		}
		
		return false;
	}
	

	public static void main(String[] args) throws Exception {
		//Tests
		RangeAPI rng = new RangeAPI();
		rng.AddText("AaA", true, "BaB", true);
		rng.AddText("Aac", true, "CaC", true);
		rng.AddText("Aaa", true, "BB", true);
		rng.AddText("Dd", true, "Df", true);
		rng.AddText("Aad", true, "Df", true);
		System.out.println(rng.root.lowerLimit);
		System.out.println(rng.root.right.lowerLimit);
		System.out.println(rng.root.right.left.lowerLimit);
		System.out.println(rng.root.right.right.lowerLimit);
		System.out.println(rng.QueryText("Aabb"));
		System.out.println(rng.QueryText("CaC"));
		System.out.println(rng.QueryText("Cac"));
		System.out.println(rng.QueryText("Cab"));
		System.out.println(rng.QueryText("Df"));
		System.out.println(rng.QueryText("De"));
		System.out.println(rng.QueryText("Dg"));
		System.out.println(rng.QueryText("AA"));
		System.out.println(rng.QueryText("A"));
		System.out.println(rng.QueryText("aa"));
	}
	
	
	/**
	 * Delete Feature to be implemented
	 * @param args
	 * @throws Exception
	 */
	
//	private class AddValues{
//		String lowerLimit;
//		String higherLimit;
//		
//		public AddValues(String lowerLimit, String higherLimit) {
//			this.lowerLimit = lowerLimit;
//			this.higherLimit = higherLimit;
//		}
//	}

//	void deleteRange(String lowerLimit, String higherLimit) throws Exception{
//		
//		Node tmpNode = new Node(lowerLimit, higherLimit);
//		if(compare(lowerLimit, higherLimit)==-1)
//			throw new Exception("Invalid User Input");
//		
//		if(root==null)
//			throw new Exception("Tree not constructed");
//		
//		ArrayList<AddValues> list = new ArrayList<RangeAPI.AddValues>();
//		
//		//DFS
//		Queue<Node> queue = new LinkedList<RangeAPI.Node>();
//		queue.add(root);
//		
//		
//		while(!queue.isEmpty()){
//			Node top = queue.poll();
//			if(compare(top.lowerLimit,lowerLimit)<=0 && compare(top.higherLimit,higherLimit)>=0){
//				if(top.parent==null){
//					if(top.right!=null){
//						root = top.right;
//					}
//					else root = top.left;
//				}
//				else{
//					
//				}
//				top = top.right;
//			}
//			else{
//				if(compare(top.lowerLimit,lowerLimit)<=0){
//					top.lowerLimit = lowerLimit;
//					top.unSetLowerInclusive();
//				}
//				else if(compare(top.higherLimit,higherLimit)>=0){
//					top.higherLimit = higherLimit;
//					top.unSetHigherInclusive();
//				}
//				else{ // in between delete
//					top.higherLimit = lowerLimit;
//					list.add(new AddValues(higherLimit,top.higherLimit));
//				}
//			}
//			if(top!=null){
//				if(top.left!=null)
//					queue.add(top.left);
//				if(top.right!=null)
//					queue.add(top.right);
//			}
//		}
//		
//		
//		for (int i = 0; i < list.size(); i++) {
//			AddText(list.get(i).lowerLimit,false,list.get(i).higherLimit,false);
//		}
//		
//	}

}
