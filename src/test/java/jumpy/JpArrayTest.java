package jumpy;

import jumpy.array.JpArray;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
		 Assert.assertEquals("Value at index not equal to the input value in setValue()!", 2.0, v.getValue(2,2), 0.01);
		 Assert.assertEquals("Value at index not equal to the input value in setValue()!", 1.0, v.getValue(1,1), 0.01);
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
		 //v.printArrayFlat();
		 //w.printArrayFlat();
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
	 
	 @Test
	 public void printTest() {
		 JpArray v = new JpArray(0.0, new int[] {3,3});
		 int[] shape = v.getShape();
		 for(int i=0; i<shape[0]; i++) {
			 for(int j=0; j<shape[1]; j++) {
				 v.setValue(j, i, j);
			 }
		 }
		 // get the string being printed to stdout
		 /*
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 PrintStream ps = new PrintStream(baos);
			 PrintStream old = System.out; 
			 System.setOut(ps);
			 // print to the new output stream
			 v.printArray();
			 System.out.flush();
			 System.setOut(old);
			 String actual = baos.toString();
			 String expected = "[[0.0, 1.0, 2.0], \n [0.0, 1.0, 2.0], \n [0.0, 1.0, 2.0]]";			  
			 System.out.println(expected);
			 System.out.println(actual);
		 */	
		 v.printArray();
		// Assert.assertEquals("Expected output does not match the actual output of the method!", true, actual.compareTo(expected) == 0);
	 }
	 
	 @Test(expected=IllegalArgumentException.class)
	 public void addArraysTest() {
		 JpArray v = new JpArray(0.0, new int[] {3,3});
		 JpArray w = new JpArray(0.0, new int[] {3,3});
		 JpArray a = new JpArray(4.0, new int[] {5});
		 JpArray b = new JpArray(8.0, new int[] {5});
		 JpArray c = new JpArray(9.0, new int[] {3});
		 int[] shape = v.getShape();
		 for(int i=0; i<shape[0]; i++) {
			 for(int j=0; j<shape[1]; j++) {
				 v.setValue(j, i, j);
				 w.setValue(j, i, j);
			 }
		 }
		 JpArray q = JpArray.add(v, w);
		 JpArray p = JpArray.add(a, b);

		 double[] expectedFlat1 = {0.,2.,4.,0.,2.,4.,0.,2.,4.};
		 double[] expectedFlat2 = {12.,12.,12.,12.,12.};
		 
		 double[] actualFlat1 = new double[9];
		 double[] actualFlat2 = new double[5];
		 int index = 0;
		 // get actual results and store into a 1-D array representation of the Jparray
		 for(int i=0; i<q.getShape()[0]; i++) {
			 for(int j=0; j<q.getShape()[1]; j++) {
				 actualFlat1[index] = q.getValue(i,j);
				 index++;
			 }
		 }
		 for(int i=0; i<p.getSize(); i++) {
			 actualFlat2[i] = p.getValue(i);
		 }
		 
		 Assert.assertArrayEquals("Resulting array after add does not match expected!", expectedFlat1, actualFlat1, 0.01);
		 Assert.assertArrayEquals("Resulting array after add does not match expected!", expectedFlat2, actualFlat2, 0.01);
		 JpArray z = JpArray.add(c, w); // should throw exception
	 }
	 
	 @Test
	 public void subtractArraysTest() {
		 JpArray v = new JpArray(0.0, new int[] {3,3});
		 JpArray w = new JpArray(0.0, new int[] {3,3});
		 int[] shape = v.getShape();
		 for(int i=0; i<shape[0]; i++) {
			 for(int j=0; j<shape[1]; j++) {
				 v.setValue(j, i, j);
				 w.setValue(j, i, j);
			 }
		 }
		 JpArray q = JpArray.subtract(v, w);

		 double[] expected = {0.,0.,0.,0.,0.,0.,0.,0.,0.};
		 double[] actual = new double[9];
		 int index = 0;
		 // get actual results and store into a 1-D array representation of the Jparray
		 for(int i=0; i<q.getShape()[0]; i++) {
			 for(int j=0; j<q.getShape()[1]; j++) {
				 actual[index] = q.getValue(i,j);
				 index++;
			 }
		 }
		 Assert.assertArrayEquals("Resulting array after subtract does not match expected!", expected, actual, 0.01);
	 }
	 
	 @Test
	 public void dotInnerProductTest() {
		 JpArray v = new JpArray(4.0, new int[] {5});
		 JpArray w = new JpArray(4.0, new int[] {5});
		 double expected = 80;
		 double actual = (Double) JpArray.dot(v, w);
		 Assert.assertEquals("Actual inner product value does not match expected!", expected, actual, 0.01);
	 }
	 
	 @Test
	 public void dotMatrixMultiplyTest() {
		 JpArray v = new JpArray(0.0, new int[] {3,3});
		 JpArray w = new JpArray(0.0, new int[] {3,3});
		 int[] shape = v.getShape();
		 for(int i=0; i<shape[0]; i++) {
			 for(int j=0; j<shape[1]; j++) {
				 v.setValue(2, i, j);
				 w.setValue(3, i, j);
			 }
		 }
		 JpArray q = (JpArray) JpArray.dot(v, w);
		 v.printArray();
		 w.printArray();
		 double[] expected = {18.,18.,18.,18.,18.,18.,18.,18.,18.};
		 double[] actual = new double[9];
		 int index = 0;
		 // get actual results and store into a 1-D array representation of the Jparray
		 for(int i=0; i<q.getShape()[0]; i++) {
			 for(int j=0; j<q.getShape()[1]; j++) {
				 actual[index] = q.getValue(i,j);
				 index++;
			 }
		 }
		 Assert.assertArrayEquals("Resulting array after matrix multiply does not match expected!", expected, actual, 0.01);
	 }
	 
}
