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
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator_2 {
	static Logger logger = LoggerFactory.getLogger(Calculator.class);
	private static Pattern PATTERN_VARIABLE = Pattern.compile("[a-zA-z]");
	public static final String LET = "let";
	public static final String ADD = "add";
	public static final String SUB = "sub";
	public static final String DIV = "div";
	public static final String MULT = "mult";
	
	int x = 0;
	int y = 0;
	
	
	public static void main(String[] args) {

		//String[] arguments = {"let(a,let(b,2,(mult(b,3)),add(1,6)"}; // 7
		//String[] arguments = {"mult(2,add(2,3))"}; // 7
		//String[] arguments = {"add(5,mult(3,sub(6,4)))"}; // 11
		//String[] arguments = "mult(5,mult(3,sub(6,4)))";
		//String[] arguments = {"sub(5,mult(3,sub(6,4)))"};
		//String[] arguments = "add(5,div(3,sub(6,4)))";
		String[] arguments = {"let(a,3,sub(5,a))"};
		
		String ss[] = arguments[0].trim().split("[(),]");
		String ss2[];
		//ArrayList<String> a
		for (int i=0; i< ss.length-1; i++){
			if (ss[i].isEmpty() || ss[i].equals(""))
				ss[i]=ss[i+1];
		}
		for (String s: ss){
			System.out.print(s);
		}
		System.out.println("");
		logger.info("Processiong input expression: {}.", Arrays.toString(ss));
		int result = compute(ss);
		logger.info("Result of {} is {}.", Arrays.toString(ss), result);

	}

	public static int processLet(String[] exp) {
		logger.debug("Entering processLet(): {}",  Arrays.toString(exp));
		if (!exp[0].equals(LET)) {
			return 0;
		}
		String variable ;
		int value = Integer.MIN_VALUE;
		int result = Integer.MIN_VALUE;
		HashMap<String, Integer> hMap = new HashMap<>();
		//public final static String OPERAND_1 = "op_1";
		//public final static String OPERAND_2 = "op_2";
		
		// Find the index of the next occurrence of LET
		// for (int i = 1; i < exp.length; i++) {
		variable = exp[1];
		if ( ! PATTERN_VARIABLE.matcher(variable).matches()){
			String msg = " is not a valid variable. Variable names can contain lowercase and uppercase letters only.";
			logger.debug(msg);
			throw new InvalidParameterException(variable + msg);
		} 
		if (exp[3].equals(LET)) {
			String[] newExp = Arrays.copyOfRange(exp, 3, exp.length);
			logger.debug("original array: " + Arrays.asList(exp) + " New array:  " + Arrays.asList(newExp)) ;
			value = processLet(newExp);
		} else {
			if (isInteger(exp[2])) {
				value = new Integer(exp[2]);
				logger.debug("put value: {}  for key {} 1",value, variable  ) ;
				hMap.put(variable, value);
			} else { 
				//evaluate arithmetic expression (add,mult.div.dub) 
				String[] newExp = Arrays.copyOfRange(exp, 2, exp.length);
				logger.debug("original array: " + Arrays.asList(exp) + " New array:  " + Arrays.asList(newExp)) ;
				//result = compute(newExp,hMap);
				value = compute(newExp,hMap);
				logger.debug("put value: {}  for key {} 2", value, variable ) ;
				hMap.put(variable, value);
			}
		}
		logger.debug("Exiting processLet() with result: {} ",  result);
		// TODO call compute exp[3]
		return value;
	}

	/**
	 * @param value
	 * @return
	 */

	public static boolean isInteger(String value) {
		try {
			new Integer(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	public static int compute(String[] exp) {
		HashMap<String, Integer> hMap = new HashMap<>();
		return compute(exp, hMap);
		
	}
	public static int compute(String[] exp, HashMap<String,Integer> hMap) {
		logger.debug("Entering compute(): {}", Arrays.toString(exp));
		String token = exp[0];
		if(token.equals(LET)){
			return processLet(exp);
		}
		int result =Integer.MIN_VALUE;
		int op1 = Integer.MIN_VALUE;
		int op2 = Integer.MIN_VALUE;
		if ( isInteger(exp[1])) {
			op1 = new Integer(exp[1]); 
		} else{
			op1 = hMap.get(exp[1]);
		}
		if ( isInteger(exp[2])) {
			op2 = new Integer(exp[2]); 
		} else if(isOperator(exp[2])){
			String[] newExp = Arrays.copyOfRange(exp, 2, exp.length);
			logger.debug("original array: " + Arrays.asList(exp) + " New array:  " + Arrays.asList(newExp)) ;
			op2 = compute(newExp);
		}else
		{
			logger.debug("get value for key {} " + exp[2]) ;
			op2 = hMap.get(exp[2]); // TODO catch exception
		}
		switch (token) {
		// TODO
		case ADD:
			result =  op1 + op2;
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
			String msg = exp[0] + " is not a valid operation. Valid operations are: add, mult, sub, and div.";
			logger.debug(msg);
			throw new InvalidParameterException(msg);
		}
		logger.debug("Exiting compute() with result: {} ",  result);
		return result;
	}
	
	public static boolean isOperator(String value) {
		return (value.equals("add") || value.equals("sub") || value.equals("mult") || value.equals("div")) ? true
				: false;
	}
}