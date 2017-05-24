/*************************************************************************
 * @author Y. Pakzad
 * A simple calculator class to evaluate the result of arithmetic 
 * expressions such as: "add(5,mult(3,sub(6,4)))" and  
 * "mult(5,mult(3,sub(6,4)))"
 * 
 *************************************************************************/


package ca.pakzad.calculator;

import java.util.ArrayDeque;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Calculator {

	static Logger logger = LoggerFactory.getLogger(Calculator.class);

	/**
	 * @param expression 
 	 *		string in the form of: mult(5,mult(3,sub(6,4)))
	 * @return 
	 * 			an integer value, the result of evaluating the arithmetic expression
	 *  @throws RuntimeException
	 *  	 
	 */
	
	public static int calculate(String expression) throws RuntimeException {
		// We use ArrayDeque as a stack
		String ss[] = expression.split("[(),]");
		logger.info("Processiong input expression: {}.", Arrays.toString(ss));
		ArrayDeque<String> st = new ArrayDeque<>();
		int result = 0;

		logger.info("Calculating " + expression);
		int variable_value = 0;
		String variable = "";
		int operand_1 = 0;
		int operand_2 = 0;
		for (int i = ss.length - 1; i >= 0; i--) {
			String token = ss[i];
			if (Calculator.isLet(token)) {
				logger.error("let not implemeted yet.");
				return 0;
			} else if (Calculator.isOperator(token)) {
				String op1 = st.pop();
				if (op1.equals(variable)) {
					operand_1 = variable_value;
				} else {
					operand_1 = new Integer(op1).intValue();
				}

				String op2 = st.pop();
				if (op2.equals(variable)) {
					operand_2 = variable_value;
				} else {
					operand_2 = new Integer(op2).intValue();
				}
				
				result = Calculator.compute(token, operand_1, operand_2);
				st.push(new Integer(result).toString());
				logger.debug("compute: {} {} {} Result: {}", token, operand_1, operand_2, result);

			} else {
				st.push(token);
			}
		}
		result = new Integer(st.pop()).intValue();
		logger.info("{} Result: {} ", expression, result);
		return result;
	}

	/**
	 * @param exp
	 * @return
	 */
	private static String[] processLet(String[] exp) {
		// String newExp = exp.split("let");
		for (int i = 0; i < exp.length - 3; i++) {
			if (exp[i].equals("let")) {
				exp[i] = "";
				String var = exp[i + 1];
				String value = exp[i + 2];
				if (isInteger(value)) {
					for (int j = i + 3; j < exp.length; j++) {
						if (exp[j].equals(var))
							exp[j] = value;
					}
					exp[i + 1] = "";
					if (!isOperator(exp[i + 2]))
						exp[i + 2] = "";
				}
			}
		}
		logger.info(Arrays.toString(exp));
		return exp;
	}

	/**
	 * @param operator
	 * @param variable
	 * @param value
	 * @return
	 */
	public static int compute(String operator, String variable, int value) {
		if (operator.equals("let"))
			return value;
		else
			return 0;
	}

	/**
	 * @param operator
	 * @param x
	 * @param y
	 * @return
	 */
	public static int compute(String operator, int x, int y) {
		int result = 0;
		switch (operator) {
		case "add":
			result = x + y;
			break;
		case "sub":
			result = x - y;
			break;
		case "mult":
			result = x * y;
			break;
		case "div":
			result = x / y;
			break;
		default:
			throw new RuntimeException("Invalid operator: + operator");
		}
		return result;
	}

	/**
	 * @param value
	 * @return
	 */
	public static boolean isOperator(String value) {
		return (value.equals("add") || value.equals("sub") || value.equals("mult") || value.equals("div")) ? true
				: false;
	}

	public static boolean isLet(String value) {
		return (value.equals("let"));
	}

	/**
	 * @param s
	 * @return
	 */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
