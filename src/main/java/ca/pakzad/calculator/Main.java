/*************************************************************************
 * @author Y. Pakzad
 * A simple calculator program to evaluate the result of arithmetic 
 * expressions such as: "add(5,mult(3,sub(6,4)))" and  
 * "mult(5,mult(3,sub(6,4)))"
 * 
 * 
 *************************************************************************/

package ca.pakzad.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	static Logger logger = LoggerFactory.getLogger(Main.class);
	 
	public static void main(String[] args) {

		if (args.length < 1 || args[0].isEmpty()){
			System.out.println("Error! Missing the required argument.");
			System.out.println("Example expression arguments:" + " add(5,mult(3,sub(6,4)))" + ", " + "mult(3,4)");
			logger.info("Error! Missing the required argument.");
			System.exit(1);
		}
		String expression = args[0];
		logger.debug("Verifying commandline arguments: {}: ", expression);
		try{
			long result = Calculator.compute(expression);
			System.out.println(expression + " = " + result);
		}catch (Exception e){
			logger.error("Failed to compute expression: {}", expression );
			logger.error("",e);
			//e.printStackTrace();
			System.out.println("Failed to compute expression: " + expression );
			System.out.println("The expression might be invalid.");
			System.exit(1);
		}
		System.exit(0);
	}
}
