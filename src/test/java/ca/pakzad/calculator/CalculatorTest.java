/*************************************************************************
 * @author Y. Pakzad
 * A JUnit test class for a simple calculator class to evaluate the result
 *  of arithmetic  expressions such as: "add(5,mult(3,sub(6,4)))" and  
 * "mult(5,mult(3,sub(6,4)))"
 * 
 *************************************************************************/

package ca.pakzad.calculator;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CalculatorTest {
	private String expression;
	private int result;

	public CalculatorTest(String expression, int result) {
		super();
		this.expression = expression;
		this.result = result;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> expressions() {
		return Arrays.asList(new Object[][] { 
			{ "add(5,mult(3,sub(6,4)))", 11 }, 
			{ "mult(5,mult(3,sub(6,4)))", 30 },
			{ "sub(5,mult(3,sub(6,4)))", -1 }, 
			{ "add(5,div(3,sub(6,4)))", 6 },
			{ "mult(add(2, 2), div(9, 3))", 12 }, 
			{ "let(a,5,add(a,a)))", 10 }, 
			{ "let(a,3,sub(5,a))", 2 },
			{ "let(a,let(b,2,mult(b,3)),add(a,12))", 18 }, 
			{ "let(a,5,let(b,mult(a,10),add(b,a)))", 55 },
			{ "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))", 40 } 
			});
	}

	@Test
	public void testCalcularParameters() {
		try {
			assertEquals(Calculator.compute(expression), result);

		} catch (RuntimeException e) {

		}
	}
}
