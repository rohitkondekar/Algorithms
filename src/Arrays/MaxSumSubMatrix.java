package Arrays;

public class MaxSumSubMatrix {

	/**
	 * Find the maximum sum of a sub-matrix in a 2-D Array (matrix) 
	 * 
	 */
	
	
	int maxSumSubMat(int[][] mat)
	{
		if(mat==null)
			return 0;
		
		int maxSum=0;
		for(int top=0;top<mat.length;top++) //top row
		{
			for(int bottom=top;bottom<mat.length;bottom++) //bottom row
			{
				int[] colomWise = new int[mat[0].length];
				
				for(int i=0;i<mat[0].length;i++)
				{
					int sum=0;
					for(int j=top;j<=bottom;j++)
						sum+=mat[i][j];
					colomWise[i] = sum;
				}
				
				int tmpSum = maxSumArray(colomWise);
				if(tmpSum>maxSum)
					maxSum=tmpSum;				
			}
		}
		
		return maxSum;
	}
	
	int maxSumArray(int[] a)
	{
		int max=0;
		int sum=0;
		
		for(int i=0;i<a.length;i++)
		{
			sum+=a[i];
			if(sum>0)
			{
				if(sum>max)
					max=sum;
			}
			else
				sum=0;
		}
		
		return max;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
