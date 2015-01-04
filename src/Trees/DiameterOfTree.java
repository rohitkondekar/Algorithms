package Trees;


/**
 * 
 * @author Rohit
 * Determine diameter of a tree :- longest path between two leaves
 * 
 */
public class DiameterOfTree {
	
	public class Result
	{
		int diameter;
		int height;
		
		Result(int d,int h)
		{
			diameter=d;
			height=h;
		}
	}
	
	public class Node
	{
		int data;
		Node right;
		Node left;
		
		Node(int data)
		{
			this.data = data;			
		}
	}
	
	int getDiameter(Node root)
	{
		return getDiameterHelper(root).diameter;
	}
	
	Result getDiameterHelper(Node root)
	{
		if (root == null)
			return new Result(0, 0);
		
		Result rstl = getDiameterHelper(root.left);
		Result rstr = getDiameterHelper(root.right);
		
		int height = rstl.height+rstr.height+1;
		int diameter = Math.max(height,Math.max(rstl.diameter,rstr.diameter));
		
		return new Result(diameter,height);
	}
	
	public static void main(String[] args) {
		
		

	}

}
