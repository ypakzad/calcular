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
		String expression = args[0];
		logger.debug("Verifying commandline arguments: " + expression);
		if (args.length <1 ){
			System.out.println("Missing expression arguments.");
			System.out.print("Example expression arguments:" + " add(5,mult(3,sub(6,4)))" + ", " + "mult(3,4)");
			logger.info("Missing expression arguments");
			System.exit(1);
		}
		try{
			long result = Calculator.compute(expression);
		}catch (Exception e){
			logger.error("Failed to compute expression: " + expression );
			logger.error("",e);
			//e.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}
}
