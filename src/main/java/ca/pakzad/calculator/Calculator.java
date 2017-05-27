/*************************************************************************
 * @author Y. Pakzad
 * A new implementation, still under development and debugging
 * A simple calculator class to evaluate the result of arithmetic 
 * expressions such as: "add(5,mult(3,sub(6,4)))",  
 * "mult(5,mult(3,sub(6,4)))" and "let(a,5,add(a,a)))"
 * 
 *************************************************************************/
package ca.pakzad.calculator;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
	static Logger logger = LoggerFactory.getLogger(Calculator.class);
	private static Pattern PATTERN_VARIABLE = Pattern.compile("[a-zA-z]");
	private static final String LET = "let";
	private static final String ADD = "add";
	private static final String SUB = "sub";
	private static final String DIV = "div";
	private static final String MULT = "mult";

	public static long compute(String expression) {
		
		String tempArray[] = expression.trim().split("[(),]");
		String[] expressionArray = new String[tempArray.length];
		for(int i=0;i<tempArray.length;i++){
			expressionArray[i]=tempArray[i].trim(); // remove white spaces
		}
		logger.debug("Processing expression: {} started.", expression);
		
		List<String> list = new ArrayList<String>(Arrays.asList(expressionArray));
		list.removeAll(Arrays.asList("", null));
		Object obj[] = list.toArray();
		String[] exp = Arrays.copyOf(obj, obj.length, String[].class);
		long result = compute(exp);
		
		logger.info("{} Result: {} ", expression, result);
		return result;
	}
	public static long compute(String[] expression) {
		HashMap<String, Long> variablesHashMap = new HashMap<>();
		return compute(expression, variablesHashMap);
	}

	/************************************************************************
	 * Computes an arithmetic expression
	 * 
	 * Recursively computes the value of an arithmetic expression such as:
	 * mult(add(2,2),div(9,3)) and let(a,let(b,10,add(b,b)),let(b,20,add(a,b))
	 * 
	 * @param expression
	 *            string in the form of: mult(5,mult(3,sub(6,4)))
	 * @param VariablesHashMap
	 * 		A HashMap to store variables and their values
	 * @return an integer value, the result of evaluating the arithmetic
	 *         
	 * @throws RuntimeException
	 * 
	 *************************************************************************/
	public static long compute(String[] expression, HashMap<String, Long> VariablesHashMap) {
		logger.debug("Entering compute(): {}", Arrays.toString(expression));
		
		String token = expression[0];
		if (token.equals(LET)) {
			return processLet(expression,VariablesHashMap);
		}
		String[] newExp = expression;
		long result = 0;
		long op1 = 0;
		long op2 = 0;
		int op2Index = 0;
		if (isInteger(expression[1])) {
			op1 = new Long(expression[1]);
			op2Index = 2;
		} else if (isOperator(expression[1])) {
			newExp = Arrays.copyOfRange(expression, 1, 4);
			op1 = compute(newExp); 
			op2Index = 4;
		} else if (expression[op2Index].equals(LET)) {
		newExp = Arrays.copyOfRange(expression, 1, 4);
		op1 = processLet(newExp, VariablesHashMap);
		op2Index = 4;
	}		else {
			op1 = VariablesHashMap.get(expression[1]);
			op2Index = 2;
			//logger.debug("get value for variable {} = {} ", expression[1],op1);
		}
		if (isInteger(expression[op2Index])) {
			op2 = new Long(expression[op2Index]);
		} else if (isOperator(expression[op2Index])) {
			newExp = Arrays.copyOfRange(expression, op2Index, expression.length);
			op2 = compute(newExp);
		} else if (expression[op2Index].equals(LET)) {
			newExp = Arrays.copyOfRange(expression, 2, expression.length);
			op2 = processLet(newExp, VariablesHashMap); 
		}
		else {
			op2 = VariablesHashMap.get(expression[op2Index]); // TODO catch exception
			logger.debug("get value for variable {} = {} ", expression[2],op2);
		}
		switch (token) {
		case ADD:
			result = op1 + op2;
			break;
		case SUB:
			result = op1 - op2;
			break;
		case MULT:
			result = op1 * op2;
			break;
		case DIV:
			result = op1 / op2;
			break;
		default:
			String msg = expression[0] + " is not a valid operation. Valid operations are: add, mult, sub, and div.";
			logger.debug(msg);
			throw new InvalidParameterException(msg);
		}
		logger.info("{} result: {} ",newExp , result);
		return result;
	}

	/************************************************************
	 * @param expression
	 * @return
	 ************************************************************/
	private static long processLet(String[] expression, HashMap<String, Long> VariablesHashMap) {

		if (!expression[0].equals(LET)) {
			return 0;
		}
		logger.debug("Entering processLet(): {}", Arrays.toString(expression));
		String variable;
		long value = 0; 
		variable = expression[1];
		if (!PATTERN_VARIABLE.matcher(variable).matches()) {
			String msg = " is not a valid variable. Variable names can contain lowercase and uppercase letters only.";
			logger.debug(msg);
			throw new InvalidParameterException(variable + msg);
		}
		if (expression[2].equals(LET)) {
			String[] newExp = Arrays.copyOfRange(expression, 2, expression.length);
			value = processLet(newExp,VariablesHashMap);
			logger.debug("put <{},{}> in VariablesHashMap", variable, value) ;
			VariablesHashMap.put(variable, value);
			if (isOperator(expression[8])) {
				newExp = Arrays.copyOfRange(expression, 8, expression.length);
				return compute(newExp, VariablesHashMap);
			} if(expression[8].equals(LET)){
				newExp = Arrays.copyOfRange(expression, 8, expression.length);
				value = processLet(newExp,VariablesHashMap);
				return value;
			}
			else {
				String msg = "Invalid third argument for let";
				logger.error(msg + " {}", expression[3]);
				throw new InvalidParameterException(msg);
			}
		} else {
			if (isInteger(expression[2])) {
				value = new Integer(expression[2]);
				logger.debug("put <{},{}> in VariablesHashMap", variable, value) ;
				VariablesHashMap.put(variable, value);
				if (isOperator(expression[3])) {
					String[] newExp = Arrays.copyOfRange(expression, 3, expression.length);
					return compute(newExp, VariablesHashMap);
				} else {
					String msg = "Invalid third argument for let";
					logger.error(msg + " {}", expression[3]);
					throw new InvalidParameterException(msg);
				}
			} else {
				// evaluate arithmetic expression (add,mult.div.dub)
				String[] newExp = Arrays.copyOfRange(expression, 2, expression.length);
				value = compute(newExp, VariablesHashMap);
				logger.debug("put value: {}  for key {} 2", value, variable);
				VariablesHashMap.put(variable, value);

			}
		}
		logger.debug(" processLet() {} with result: {} ", expression, value);
		return value;
	}

	private static boolean isInteger(String value) {
		try {
			new Integer(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static boolean isOperator(String value) {
		return (value.equals("add") || value.equals("sub") || value.equals("mult") || value.equals("div")) ? true
				: false;
	}
}