/*************************************************************************
 * @author Y. Pakzad
 * A JUnit test class for a simple calculator class to evaluate the result
 *  of arithmetic  expressions such as: "add(5,mult(3,sub(6,4)))" and  
 * "mult(5,mult(3,sub(6,4)))"
 * 
 *************************************************************************/
package ca.pakzad.calculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTestInvalid {


	@Test
	public void testCalcularWrongResult() {
		try {
			assertNotEquals(Calculator.compute("add(5,4"),10);
		} catch (RuntimeException e) {

		}
	}
	
	@Test
	public void testCalcularDivideByZero() {
		try {
			Calculator.compute("div(5,0");
			fail("Expected divide by zero error.");
		} catch (RuntimeException e) {
		}
	}
	
	@Test
	public void testCalcularInvalidExpressions() {
		try {
			Calculator.compute("add(5,mult(3,sub 6,4))");
			fail("Invalid expression, expected to fail.");

		} catch (RuntimeException e) {
		}
	}

	@Test
	public void testCalcularInvalidExpressions2() {
		try {
			Calculator.compute("let(a,let(b,2,(mult(b,3))");
			fail("Invalid expression, expected to fail");

		} catch (RuntimeException e) {

		}
	}
}
