/**
 * @author Andy Vuong
 */

public class LinearAlgebra {

	/**
	 * Take the inner product of a (m x n) and (p x q) JpArrays. In the case of vector multiplication (1-D JpArrays), a scalar is returned as a JpArray of one index.
	 *
	 * @param A 
	 * @param B
	 * @return mtx A JpArray containing the result.
	 */
	public static JpArray dot(JpArray A, JpArray B) {
		int[] ADim = A.getShape();
		int[] BDim = B.getShape();
		if(ADim.length > 2 || BDim.length > 2) {
			throw new IllegalArgumentException("This method currently cannot take the inner product of JpArrays with dimensions greater than 2!");
		}
		if(ADim.length == 1 && BDim.length == 1 && ADim[0] == BDim[0]) {
			JpArray newJp = new JpArray(0.0, ADim);
			for(int i=0; i<ADim[0]; i++) {
				newJp.setValue((Double) A.getValue(i) * (Double) B.getValue(i), i);
			}
			return newJp;
		}
		else if(ADim.length == 1 && BDim.length > 1) {
			JpArray temp = A.transpose();
			if(ADim[0] == BDim[0]) {
				int[] newDim = {1, BDim[1]};
				JpArray newJp = new JpArray(0.0, newDim);
				for(int i=0; i<newDim.length; i++) {
					for(int j=0; j<newDim[i]; j++) {
						
					}
				}
				return newJp;
			}			
		}
		else {
			throw new IllegalArgumentException("JpArray dimension mismatch!");
		}
		return null;
	}
	
	
	/**
	 * Decomposes a (m x n) dimensional JpArray into a lower and upper triangular matrix.
	 * 
	 * @param A	Matrix to decompose.
	 * @return L,U Matrices stored in a JpArray object.
	 */
	public static JpArray[] lu(JpArray A) {
		
		return null;
	}
	
	/**
	 * Decomposes a (m x n) dimensional JpArray into Q and R, orthogonal and upper triangular matrices.
	 * 
	 * @param A	Matrix to decompose.
	 * @return Q,R Matrices stored in a JpArray object.
	 */
	public static JpArray[] qr(JpArray A) {
		
		return null;
	}
	
	/**
	 * Decomposes a (m x n) dimensional JpArray into matrices containing the left singular vectors, singular values, and right singular vectors.
	 * 
	 * @param A	Matrix to decompose.
	 * @return u, sigma, vT Matrices stored in a JpArray object.
	 */
	public static JpArray[] svd(JpArray A) {
		
		return null;
	}
	
}
