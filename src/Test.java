import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andy Vuong
 */



public class Test {
	
	public int num;
	public static void main(String[] args) {
		System.out.println("Welcome to Jumpy, a Java library for numerical linear algebra.");
		
		// System.out.println(a[0] + " | " + b[0]);
		// System.out.println(c);
		// Test obj = new Test();
		// int[] x, y;
		// int q[], z;
		//String x = "yo";
		//String y = x;
		//x = "boo";
		//System.out.println(x);
		//System.out.println(y);
		
		int[] dim = {3,3};
		JpArray v = new JpArray("random", dim);
		System.out.println(Arrays.toString(v.getShape()));
		System.out.println(v.printArray());
		
	}

}
