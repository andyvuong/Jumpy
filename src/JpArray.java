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
	 * Constructs a n-dimensional JpArray of zeros.
	 * @param dimensions
	 */
	JpArray(int... dimensions) {
		this.dimensions = dimensions;
		// Create initial ArrayList
		List<Object> temp = new ArrayList<Object>(dimensions[0]);
		if(dimensions.length > 1) { // nd-array
			JpArrayHelper(temp, 1);
			elements = temp;
		}
		else { // 1d-array
			elements = temp;
			for(int i=0; i<dimensions[0]; i++) {
				elements.add(i, 0.);
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
		
		
		return new Double(0);
	}
	
	// This helper function recursively creates the N-dimensional array and fills it with 0.0s.
	private void JpArrayHelper(List<Object> arr, int curDimInd) {
		if(curDimInd == dimensions.length) {
			for(int i=0; i<dimensions[curDimInd-1]; i++) {
				arr.add(i, 0.);
			}
			return;
		}
		else {
			for(int i=0; i<dimensions[curDimInd-1]; i++) {
				arr.add(i, new ArrayList<Object>(dimensions[curDimInd]));
				JpArrayHelper((List<Object>) arr.get(i), curDimInd+1);
			}
		}
	}
	
	// Helper function, follows the trails...
	private Object JpArrayGetHelper(List<Object> arr, int curDimInd) {
		if(curDimInd == dimensions.length) {
			
		}
		
		
		return null;
	}
	
	
}
