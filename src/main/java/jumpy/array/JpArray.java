/**
 * @author Andy Vuong
 */

package jumpy.array;

import java.util.*;

public class JpArray {
	
	/** Fields */
	private int[] shape;
	private double[] elements; // flat 1-d array for multi-dimensional arrays
	private int size;
	
	/** Constructors */
	/**
	 * Zero argument constructor creates a JpArray of dimensions {10} and filled with 0.0.
	 */
	public JpArray() {
		this(0.0, new int[] {10});
	}
	
	/**
	 * Creates a new JpArray with shape dim and values based on options.
	 * @param options 	Can be of: 
	 * 					"r" <- random doubles in range 0-1
	 * 					"w" <- random whole numbers in range 0-999
	 * 					a double <- fills array with a value of the user's choice.
	 * @param dim
	 */
	public JpArray(Object options, int ...dim) {
		int dimensionLength = dim.length;
		size = 1;
		shape = new int[dimensionLength];
		for(int i=0; i<dimensionLength; i++) {
			shape[i] = dim[i];
			size = size * dim[i];
		}
		elements = populateArray(options, size);
	}
	
	/** Methods */

	// returns a double[] array of length size and populated with a value based on the provided option.
	private double[] populateArray(Object option, int size) {
		double[] newArr = new double[size];
		if(option instanceof String) {
			int flag = 0;
			String arg = (String) option;
			if(arg.charAt(0) == 'r') {
				flag = 1;
			}
			else if(arg.charAt(0) == 'w') {
				flag = 2;
			}
			else {
				throw new IllegalArgumentException("Invalid option! Option not 'r' or 'w'.");				
			}
			// generate random values to populate array
			Random rand = new Random();
			double value = 0;
			for(int i=0; i<size; i++) {
				if(flag == 1) {
					value = rand.nextDouble();
					value = (double) Math.round(value * 100000) / 100000; // default 5 decimals
				}
				else {
					value = rand.nextInt(100); // default 0 - 100
				}
				newArr[i] = value;
			}			
		}
		else if(option instanceof Double) {
			double value = ((Double) option).doubleValue();
			for(int i=0; i<size; i++) {
				newArr[i] = value;
			}	
		}
		else {
			throw new IllegalArgumentException("Invalid option! Must be a double or string.");
		}
		return newArr;
	}
	
	// returns the internal index of the underlying primitive array for JpArray for the provided indices.
	private int getIndex(int...indices) {
		int index = 0;
		int end = shape.length;
		for(int i=0; i<indices.length; i++) {
			int temp = 1;
			for(int j=0; j<indices.length-(i+1); j++) {
				temp = temp * shape[end - (j+1)];
			}
			index += indices[i]*temp;
		}
		return index;
	}
	
	/**
	 * Gets the value at the specified index or indices.
	 * @param indices
	 * @return a double
	 */
	public double getValue(int...indices) {
		return elements[getIndex(indices)];
	}
	
	public void setValue(double value, int...indices) {
		elements[getIndex(indices)] = value;
	}
	
	public int[] getShape() {
		return shape;
	}
	
	/**
	 * @return the total number of elements in the JpArray.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Prints the array as a flat 1-D array.
	 */
	public void printArrayFlat() {
		System.out.println(Arrays.toString(elements));
	}
	
	/**
	 * Returns the transpose of the current JpArray object as a new object. Currently only supported for 1-D and 2-D arrays.
	 * @return newArr The transposed JpArray.
	 */
	public JpArray transpose() {
		if(shape.length > 1 && shape.length < 3) {
			int[] newShape = {shape[1], shape[0]};
			JpArray newArr = new JpArray(0.0, newShape);
			for(int i=0; i<shape[1]; i++) {
				for(int j=0; j<shape[0]; j++) {
					int[] oldInd = {i, j};
					int[] newInd = {j, i};
					newArr.setValue(this.getValue(oldInd), newInd);
				}
			}
			return newArr;
		}
		else {
			throw new IllegalArgumentException("transpose() is currently only supported for JpArrays of 1-D and 2-D.");
		}
	}
	
	
	
}