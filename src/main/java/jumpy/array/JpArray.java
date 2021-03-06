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
		for (int i = 0; i < dimensionLength; i++) {
			shape[i] = dim[i];
			size = size * dim[i];
		}
		elements = populateArray(options, size);
	}
	
	/** Methods */

	// returns a double[] array of length size and populated with a value based on the provided option.
	private double[] populateArray(Object option, int size) {
		double[] newArr = new double[size];
		if (option instanceof String) {
			int flag = 0;
			String arg = (String) option;
			if (arg.charAt(0) == 'r') {
				flag = 1;
			}
			else if (arg.charAt(0) == 'w') {
				flag = 2;
			}
			else {
				throw new IllegalArgumentException("Invalid option! Option not 'r' or 'w'.");				
			}
			// generate random values to populate array
			Random rand = new Random();
			double value = 0;
			for (int i = 0; i < size; i++) {
				if (flag == 1) {
					value = rand.nextDouble();
					value = (double) Math.round(value * 100000) / 100000; // default 5 decimals
				}
				else {
					value = rand.nextInt(100); // default 0 - 100
				}
				newArr[i] = value;
			}			
		}
		else if (option instanceof Double) {
			double value = ((Double) option).doubleValue();
			for(int i = 0; i < size; i++) {
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
		for (int i = 0; i < indices.length; i++) {
			int temp = 1;
			for (int j = 0; j < indices.length - (i + 1); j++) {
				temp = temp * shape[end - (j + 1)];
			}
			index += indices[i] * temp;
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
	 * Returns the transpose of the current JpArray object as a new object. Currently only supported for 1-D and 2-D arrays.
	 * @return newArr The transposed JpArray.
	 */
	public JpArray transpose() {
		if (shape.length > 1 && shape.length < 3) {
			int[] newShape = {shape[1], shape[0]};
			JpArray newArr = new JpArray(0.0, newShape);
			for (int i = 0; i < shape[1]; i++) {
				for (int j = 0; j < shape[0]; j++) {
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
	
	/**
	 * Prints the array as a flat 1-D array.
	 */
	public void printArrayFlat() {
		System.out.println(Arrays.toString(elements));
	}
	
	/**
	 * Prints the shape of the JpArray
	 */
	public void printShape() {
		System.out.println(Arrays.toString(shape));		
	}
	
	/**
	 * Prints the JpArray. Currently only supported for 1-D and 2-D arrays.
	 */
	public void printArray() {
		if (shape.length > 2) {
			System.out.println("print() currently only supports 1-D and 2-D arrays!");
		}
		else if (shape.length == 1) {
			System.out.println(Arrays.toString(elements));
		}
		else {
			String arrStr = "[";
			for (int i = 0; i < shape[0]; i++) {
				arrStr += "[";
				for (int j = 0; j < shape[1]; j++) {
					arrStr += Double.toString(this.getValue(i,j))+", "; // to string
				}
				arrStr += "]";
			}
			arrStr = arrStr.replaceAll(", ]", "], \n ");
			arrStr = arrStr.substring(0, arrStr.length()-4);
			arrStr+="]";
			System.out.println(arrStr);
		}
	}
	
	/**
	 * Adds two JpArrays of matching dimensions together and returns the resulting JpArray. Currently only supported for 1-D and 2-D arrays.
	 * @param v a JpArray
	 * @param w a JpArray
	 * @return newArr The resulting JpArray
	 */
	public static JpArray add(JpArray v, JpArray w) {
		if (!shapesMatch(v,w) || v.shape.length > 2 || w.shape.length > 2) {
			throw new IllegalArgumentException("Shape/Dimension mis-match. JpArray arguments must be of 1-D or 2-D and have the same shapes.");
		}
		JpArray newArr = new JpArray(0.0, v.getShape());
		for (int i = 0; i < v.size; i++) {
			newArr.elements[i] = v.elements[i] + w.elements[i];
		}
		return newArr;
	}
	
	/**
	 * Subtracts JpArray v from JpArray w of matching dimensions and returns the resulting JpArray. Currently only supported for 1-D and 2-D arrays.
	 * @param v a JpArray
	 * @param w a JpArray
	 * @return newArr The resulting JpArray
	 */
	public static JpArray subtract(JpArray v, JpArray w) {
		if (!shapesMatch(v,w) || v.shape.length > 2 || w.shape.length > 2) {
			throw new IllegalArgumentException("Shape/Dimension mis-match. JpArray arguments must be of 1-D or 2-D and have the same shapes.");
		}
		JpArray newArr = new JpArray(0.0, v.getShape());
		for (int i = 0; i < v.size; i++) {
			newArr.elements[i] = v.elements[i] - w.elements[i];
		}
		return newArr;
	}
	
	/**
	 * Takes the inner product if v and w are vectors, else performs a matrix multiply if v and w are 2-D and returns the resulting JpArray. Currently only supported for 1-D and 2-D arrays.
	 * The result should be casted into the appropriate return object type.
	 * @param v a JpArray
	 * @param w a JpArray
	 * @return value a Double class object or JpArray object
	 */
	public static Object dot(JpArray v, JpArray w) {
		if(v.shape.length > 2 || w.shape.length > 2) {
			throw new IllegalArgumentException("JpArray arguments must be of 1-D or 2-D");
		}
		try {
			int bit = validateDimensions(v,w);
			if (bit == 0) {
				JpArray newArr = matrixMultiply(v, w);
				return newArr;
			}
			else if (bit == 1) {
				Double value = innerProduct(v, w);
				return value;
			}
			else {
				return null;
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println("JpArray dimension mis-match!");
			return null;
		}
	}
	
	// checks whether or not the dimensions of arrays are fine
	private static int validateDimensions(JpArray v, JpArray w) throws IllegalArgumentException {
		if (v.shape.length > 1) {
			if (v.shape[1] != w.shape[0]) {
				throw new IllegalArgumentException();
			}
			return 0;
		}
		else if (v.shape.length < 2) {
			if (v.shape[0] != w.shape[0]) {
				throw new IllegalArgumentException();
			}
			return 1;
		}
		else {
			System.out.println("temp");
			return -1; // should never be this
		}
	}

	// matrix*matrix or vector*matrix or matrix*vector multiply
	// TODO add options parameter to specify the naive(default) or Strassen algorithm.
	private static JpArray matrixMultiply(JpArray v, JpArray w) {
		JpArray newArr = new JpArray(0., v.shape);
		double sum = 0;
		for  (int i = 0; i < v.shape[0]; i++) {
			for (int j = 0; j < w.shape[1]; j++) {
				for (int k = 0; k < w.shape[0]; k++) {
					sum += v.getValue(i,k) * w.getValue(k,j);
				}
				newArr.setValue(sum, i, j);
				sum = 0;
			}
		}
		return newArr;
	}
	
	// inner product
	private static double innerProduct(JpArray v, JpArray w) {
		double sum = 0;
		for (int i = 0; i < v.size; i++) {
			sum += v.getValue(i) * w.getValue(i);
		}
		return sum;
	}
	
	// true if dimensions of JpArray match by level and shape
	private static boolean shapesMatch(JpArray v, JpArray w) {
		int[] shape1 = v.shape;
		int[] shape2 = w.shape;
		if (shape1.length == shape2.length) {
			for (int i = 0; i < shape1.length; i++) {
				if (shape1[i] != shape2[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the maximum value in the JpArray.
	 * @return the maximum value in the JpArray
	 */
	public double getMax() {
		int[] elementsCopy = new int[size];
		System.arraycopy(elements, 0, elementsCopy, 0, size);
		Arrays.sort(elementsCopy);
		return elementsCopy[size-1];
	}

	/**
	 * Returns the minimum value in the JpArray.
	 * @return the minimum value in the JpArray
	 */
	public double getMin() {
		int[] elementsCopy = new int[size];
		System.arraycopy(elements, 0, elementsCopy, 0, size);
		Arrays.sort(elementsCopy);
		return elementsCopy[0];
	}
	
	

}