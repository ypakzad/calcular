/*************************************************************************
 * @author Y. Pakzad
 * A JUnit test class for a simple calculator class to evaluate the result
 *  of arithmetic  expressions such as: "add(5,mult(3,sub(6,4)))" and  
 * "mult(5,mult(3,sub(6,4)))"
 * 
 *************************************************************************/
package ca.pakzad.calculator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculatorTest.class,CalculatorTestInvalid.class })
public class AllTests {

}
