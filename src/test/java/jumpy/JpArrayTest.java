package jumpy;

import jumpy.array.JpArray;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class JpArrayTest {

	 @Test
	 public void constructorDoubleTest() {
		 int[] dimensions = {3,3}; 
		 JpArray v = new JpArray(0.0, dimensions);
		 Assert.assertEquals("getValue value is not equal!", 0.0, v.getValue(0,0), 0.01);
	 }

	 @Test
	 public void constructorNoParamTest() {
		 JpArray v = new JpArray();
		 Assert.assertEquals("getValue value is not equal!", 0.0, v.getValue(0), 0.01);
	 }

	 @Test(expected=IllegalArgumentException.class)
	 public void constructorInvalidParamOneTest() {
		 int[] dimensions = {3,3}; 
		 JpArray v = new JpArray("aq", dimensions);
	 }
	 
	 @Test(expected=IllegalArgumentException.class)
	 public void constructorInvalidParamTwoTest() {
		 int[] dimensions = {3,3}; 
		 JpArray v = new JpArray(9, dimensions);
	 }
	 
}
