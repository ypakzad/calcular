/*************************************************************************
 * @author Y. Pakzad
 * A simple calculator program to evaluate the result of arithmetic 
 * expressions such as: "add(5,mult(3,sub(6,4)))" and  
 * "mult(5,mult(3,sub(6,4)))"
 * 
 * 
 *************************************************************************/

package ca.pakzad.calculator;

import java.util.ArrayDeque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	static Logger logger = LoggerFactory.getLogger(Main.class);
	 
	public static void main(String[] args) {
		logger.info("Verifying commandline arguments." + args[0]);
		if (args.length <1 ){
			System.out.println("Missing expression arguments.");
			System.out.print("Example expression arguments:" + " add(5,mult(3,sub(6,4)))" + ", " + "mult(3,4)");
			logger.info("Missing expression arguments");
			System.exit(1);
		}
	
		logger.info(args[0]);
		
		try{
			Calculator.calculate(args[0]);
		}catch (Exception e){
			logger.error("Invalid expression: " + args[0] );
			System.exit(1);
		}
		System.exit(0);
		
		
		String ss[] = args[0].split("[(),]");

		
	}

}
