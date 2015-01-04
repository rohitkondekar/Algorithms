package Arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 
 * @author Rohit
 * Count number of occurrences in a sorted array
 * 
 * Done using binary search by alternating the condition when an element is found
 * so that boundries are found.
 * 
 * Another method is using recursion : but order is klgn : http://dl.dropboxusercontent.com/u/14935294/ProgrammingVideos/3-Count_Occurance_in_Sorted_Array/NumberOccurrence.java
 * 
 */

public class NumberOfOccurrences {

	
	int getLeft(int[] array, int low, int high, int x)
	{
		if(low>high)
			return -1;
		
		int mid = (low+high)/2;
		if (mid==0 && array[mid]!= x)
			return -1;
		if(mid==0 || (array[mid-1]<x && array[mid]==x))
			return mid;
		else if (array[mid]>=x)
			return getLeft(array,low,mid-1,x);
		else
			return getLeft(array,mid+1,high,x);
	}
	
	int getRight(int[] array, int low, int high, int x)
	{
		if(low>high)
			return -1;
		
		int mid = (low+high)/2;
		
		if(mid == array.length-1 || (array[mid+1]>x && array[mid]==x))
			return mid;
		else if (array[mid]>x)
			return getRight(array,low,mid-1,x);
		else
			return getRight(array,mid+1,high,x);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int[] array = {1, 1, 2, 2, 2, 2, 3,};
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		
		String st;
		while((st=buf.readLine())!="")
		{
			int x = Integer.parseInt(st);
			
			NumberOfOccurrences num = new NumberOfOccurrences();
			int left = num.getLeft(array, 0, array.length-1, x);
			
			if(left==-1)
				System.out.println("Element not present");
			else
			{
				int right = num.getRight(array, left, array.length-1, x);
				System.out.println(left+" "+right);
				System.out.println(right-left+1);
			}
		}
	}
} 
