/**
 * @author Andy Vuong
 */

import java.util.*;

public class JpArray {
	/** Fields */
	private int[] dimensions;
	private List<Object> elements;
	
	/** Constructors */
	JpArray() {
		dimensions = new int[]{0};
		elements = new ArrayList<Object>(0);
	}
	
	/**
	 * Constructs a n-dimensional JpArray of doubles.
	 * @param dimensions
	 * @param option : Can be of "random","random whole","zeros", A double 
	 */
	JpArray(Object arg, int... dimensions) {
		this.dimensions = dimensions;
		Random rand = new Random();
		double value = 0;
		int flag = 0;
		// Determine the option
		if(arg instanceof String && ((String) arg).compareTo("random") == 0) {
			flag = 1;
		}
		else if(arg instanceof String && ((String) arg).compareTo("random whole") == 0) {
			flag = 2;
		}
		else if(arg instanceof String && ((String) arg).compareTo("zeros") == 0) {
			value = 0;
		}
		else if(arg instanceof Integer || arg instanceof Double) {
			value = (double) arg;
		}
		else {
			value = 0;
		}
		
		// Create array
		List<Object> temp = new ArrayList<Object>(dimensions[0]);
		if(dimensions.length > 1) { // nd-array
			JpArrayHelper(temp, 1, flag, value);
			elements = temp;
		}
		else { // 1d-array
			elements = temp;
			for(int i=0; i<dimensions[0]; i++) {
				if(flag == 0) {
					elements.add(i, value);
				}
				else if (flag == 1) { 
					value = rand.nextDouble();
					elements.add(i, value);
				}
				else {
					value = rand.nextInt(100);
					elements.add(i, value);
				}
			}
		}
	}
	
	/** Methods */
	
	/**
	 * Print the JpArray's shape to console.
	 * @return dimensions
	 */
	public String printShape() {
		return Arrays.toString(dimensions);
	}
	
	public int[] getShape() {
		return dimensions;
	}
	
	/**
	 * Print the JpArray's elements to console.
	 * @return string array
	 */
	public String printArray() {
		int i = dimensions.length;
		String matrix = elements.toString();
		matrix = matrix.replaceAll("],", "],\n");
		
		return matrix;
	}
	
	/**
	 * Returns the value at the specified index of the JpArray
	 * @param indices
	 * @return a double or N-dimensional JpArray.
	 */
	public Object getValue(int... indices) {
		for(int i=0; i<indices.length; i++) { // Catch Error
			if(i == dimensions.length || indices[i] > dimensions[i]) {
				return null;
			}
		}
		if(indices.length == 1) {
			return elements.get(indices[0]);
		}
		else {
			return JpArrayGetHelper((List<Object>) elements.get(indices[0]), 1, indices);
		}
	}
	
	/** Helpers */
	// This helper function recursively creates the N-dimensional array and fills it with 0.0s.
	private void JpArrayHelper(List<Object> arr, int curDimInd, int flag, double value) {
		if(curDimInd == dimensions.length) {
			Random rand = new Random();
			for(int i=0; i<dimensions[curDimInd-1]; i++) {
				if(flag == 0) {
					arr.add(i, value);
				}
				else if (flag == 1) { 
					value = rand.nextDouble();
					arr.add(i, value);
				}
				else {
					value = rand.nextInt(100);
					arr.add(i, value);
				}
			}
			return;
		}
		else {
			for(int i=0; i<dimensions[curDimInd-1]; i++) {
				arr.add(i, new ArrayList<Object>(dimensions[curDimInd]));
				JpArrayHelper((List<Object>) arr.get(i), curDimInd+1, flag, value);
			}
		}
	}
	
	// Helper: Recursively finds the correct index
	private Object JpArrayGetHelper(List<Object> arr, int curDimInd, int[] indices) {
		if(curDimInd == dimensions.length-1) {
			return arr.get(indices[curDimInd]);
		}
		return JpArrayGetHelper((List<Object>) arr.get(indices[curDimInd]), curDimInd+1, indices);
	}
	
}
