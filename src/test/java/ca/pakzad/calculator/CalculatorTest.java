/*************************************************************************
 * @author Y. Pakzad
 * A JUnit test class for simple calculator class to evaluate the result
 *  of arithmetic  expressions such as: "add(5,mult(3,sub(6,4)))" and  
 * "mult(5,mult(3,sub(6,4)))"
 * 
 *************************************************************************/

package ca.pakzad.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CalculatorTest {
	private String expression;
	private int result;
	private Calculator calculator;

	public CalculatorTest(String expression, int result) {
		super();
		this.expression = expression;
		this.result = result;
	}

	@Before
	public void initialize() {
		calculator = new Calculator();
	}

	@Parameterized.Parameters
	public static Collection expressions() {
		return Arrays.asList(new Object[][] { 
			{ "add(5,mult(3,sub(6,4)))", 11 }, 
			{ "mult(5,mult(3,sub(6,4)))", 30 },
			{ "sub(5,mult(3,sub(6,4)))", -1 },
			{ "add(5,div(3,sub(6,4)))", 6 } });
	}

	@Test
	public void testCalcularParameters() {
		try {
			assertEquals(Calculator.calculate(expression), result);

		} catch (RuntimeException e) {

		}
	}

	@Test
	public void testCalcularInvalidExpressions() {
		try {
			Calculator.calculate("add(5,mult(3,sub 6,4))");
			fail("Expected to fail bucause of an invalid expression.");

		} catch (RuntimeException e) {

		}
	}

	//@Test
	public void testCalcularLet() {
		assertEquals(Calculator.calculate("let(a,5,let(b,mult(a,10),add(b,a)))"), 55);
	}
}
