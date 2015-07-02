package jumpy;

import jumpy.array.JpArray;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class JpArrayTest {

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
	 
	 @Test
	 public void setGetValueTest() { // should test both get and set
		 JpArray v = new JpArray(2.0, new int[] {3,3});
		 int[] shape = v.getShape();
		 for(int i=0; i<shape[0]; i++) {
			 for(int j=0; j<shape[1]; j++) {
				 v.setValue(j, i, j);
			 }
		 }
		 Assert.assertEquals("Value at index not equal to the input value in setValue()", 2.0, v.getValue(2,2), 0.01);
		 Assert.assertEquals("Value at index not equal to the input value in setValue()", 1.0, v.getValue(1,1), 0.01);
	 }

	 @Test
	 public void transposeTest() {
		 JpArray v = new JpArray(0.0, new int[] {3,3});
		 int[] shape = v.getShape();
		 for(int i=0; i<shape[0]; i++) {
			 for(int j=0; j<shape[1]; j++) {
				 v.setValue(j, i, j);
			 }
		 }
		 JpArray w = v.transpose();
		 v.printArrayFlat();
		 w.printArrayFlat();
		 Assert.assertEquals("Transposed indices do not match!", w.getValue(0,0), v.getValue(0,0), 0.01);
		 Assert.assertEquals("Transposed indices do not match!", w.getValue(0,1), v.getValue(1,0), 0.01);
		 Assert.assertEquals("Transposed indices do not match!", w.getValue(0,2), v.getValue(2,0), 0.01);
		 Assert.assertEquals("Transposed indices do not match!", w.getValue(1,0), v.getValue(0,1), 0.01);
		 Assert.assertEquals("Transposed indices do not match!", w.getValue(1,1), v.getValue(1,1), 0.01);
		 Assert.assertEquals("Transposed indices do not match!", w.getValue(1,2), v.getValue(2,1), 0.01);
		 Assert.assertEquals("Transposed indices do not match!", w.getValue(2,0), v.getValue(0,2), 0.01);
		 Assert.assertEquals("Transposed indices do not match!", w.getValue(2,1), v.getValue(1,2), 0.01);
		 Assert.assertEquals("Transposed indices do not match!", w.getValue(2,2), v.getValue(2,2), 0.01);

	 }
	 
}
