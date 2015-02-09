package General;

import java.util.*;

/**
 * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * @author rohitkondekar
 * 
 * Theory- Important.
 * For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle.
 * For that we need first smaller bar before x and first smaller bar after x.
 * After x - is current one
 * Before x - is the element before it in stack
 */

public class FindMaximumRectangleHistrogram {

	int solve(int[] array){
		
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> indexStack = new Stack<Integer>();
		
		stack.push(array[0]);
		indexStack.push(0);
		int maxArea = 0;
		
		for (int i = 1; i < array.length; i++) {
			if(array[i]==stack.peek())
				continue;
			
			while(!stack.isEmpty() && array[i]<=stack.peek())
			{
				int num = stack.pop();
				int index = indexStack.pop();
				
				int lastIndex = indexStack.isEmpty()?0:indexStack.peek();

				if(i==lastIndex+1)
					maxArea = (maxArea>num)?maxArea:num;
				else
					maxArea = (maxArea>(i-lastIndex-1)*num)?maxArea:(i-lastIndex-1)*num;
			}
			stack.push(array[i]);
			indexStack.push(i);
		}
		
		int currentIndex = array.length;
		while(!stack.isEmpty()){
			int num = stack.pop();
			int index = indexStack.pop();
			int lastIndex = indexStack.isEmpty()?0:indexStack.peek();
			
			if(currentIndex==lastIndex+1)
				maxArea = (maxArea>num)?maxArea:num;
			else
				maxArea = (maxArea>(currentIndex-lastIndex-1)*num)?maxArea:(currentIndex-lastIndex-1)*num;
		}
		
		return maxArea;
	}
	
	public static void main(String[] args) {
		FindMaximumRectangleHistrogram fm = new FindMaximumRectangleHistrogram();
		int[] ar = {6,2,5,4,5,4,6};
		System.out.println(fm.solve(ar));

	}

}
