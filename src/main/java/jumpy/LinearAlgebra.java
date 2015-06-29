/**
 * @author Andy Vuong
 */

package jumpy;

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
		return matrixMultiply(A, B, ADim, BDim);
	}
	
	private static JpArray matrixMultiply(JpArray A, JpArray B, int[] adim, int[] bdim) {
		int[] newDim = {adim[0], bdim[1]};
		JpArray newJp = new JpArray();
		double sum = 0;
		for(int i=0; i<adim[0]; i++) {
			System.out.println(i);
			for(int j=0; j<bdim[1]; j++) {
				for(int k=0; k<bdim[0]; k++) {
					sum = sum + (Double)A.getValue(i,k)*(Double)B.getValue(k,j);
				}	
				System.out.println(i+1 + " " + j+1);
				newJp.setValue(sum, i, j);
				sum = 0;
			}	
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
