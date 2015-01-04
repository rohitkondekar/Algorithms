package Arrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/** 
 * @author Rohit
 * Find number of inversions in a array
 * Done using merge sort method
 * Link: http://www.geeksforgeeks.org/counting-inversions/
 */
public class NumberOfInversions {

	
	int numberOfInversions(int[] array)
	{
		return mergeSort(array,0,array.length-1);
	}
	
	int mergeSort(int[] array, int low, int high)
	{
		if (low>=high)
			return 0;
		
		int mid = (low+high)/2;
		int inversions = mergeSort(array,low,mid);
		inversions += mergeSort(array,mid+1,high);		
		inversions += merge(array,low,mid+1,high);		
		return inversions;
	}
	
	int merge(int[] array,int low, int mid, int high)
	{
		int i = low;
		int j = mid;
		int k = 0;
		int[] tmp = new int[high-low+1];
		int inv = 0;
		
		while(i<mid && j <=high)
		{
			if(array[i]<=array[j])
				tmp[k++]=array[i++];
			else
			{
				tmp[k++]=array[j++];
				inv += mid-i;  //// inportant step
			}
		}
		
		while(j<=high)
			tmp[k++]=array[j++];
		while(i<mid)
			tmp[k++]=array[i++];
		
		i=low;
		for(int z=0;z<tmp.length;z++)
			array[i++]=tmp[z];
		return inv;
	}
	
	public static void main(String[] args) {
		
		int[] array = {2, 4, 1, 3, 5};
		NumberOfInversions nv = new NumberOfInversions();
		System.out.println(nv.numberOfInversions(array));
	}

}
