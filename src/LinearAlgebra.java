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
	public static JpArray[] dot(JpArray A, JpArray B) {
		int[] ADim = A.getShape();
		int[] BDim = B.getShape();
		if(ADim.length == 1 || BDim.length == 1) {
			
			
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
