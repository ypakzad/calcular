package ca.pakzad.calculator;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayDeque;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainBT {
	
	private static final String LOG_NAME = "ca.pakzad.calculator";
	private static final Logger logger = Logger.getLogger(LOG_NAME);
	
	public static void main(String[] args) {
		
		if(System.getProperty("java.util.logging.config.class")  == null 
			&& System.getProperty("ava.util.logging.config.file") == null){
			try {
				Logger.getLogger(LOG_NAME).setLevel(Level.ALL);
				final int LOG_ROTATION_COUNT = 10;
				Handler handler = new FileHandler("%h/cakculator.log",0,LOG_ROTATION_COUNT);
				Logger.getLogger(LOG_NAME).addHandler(handler);
				
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Can't create log file handler",e);
			}
		}
		logger.info("Starting");
		
		
		System.out.println(args[0]);
		String ss[] = args[0].split("[(),]");
		/*for (String s : ss){
			System.out.println(s);
		}
		System.out.println("-----" + ss.length);*/
		

		TreeNode root = new TreeNode("sum");
		TreeNode lc = new TreeNode(3);
		TreeNode rc = new TreeNode(5);
		
		root.setLeftChild(lc);
		root.setRightChild(rc);
		
		BinaryTree bt = new BinaryTree(root);
	
		//StackIf<String> st = new LinkedListStack<>();

		ArrayDeque<String> st = new ArrayDeque<>();
		
		for (int i= ss.length-1; i >= 0; i--){
			String current = ss[i];
			if (! isOPerator(current)) {
				st.push(current);
				System.out.println("push " + current );
			} else {
				int operand1 = new Integer(st.pop()).intValue();
				System.out.println("pop " +  operand1);
				int operand2 = new Integer(st.pop()).intValue();
				System.out.println("pop " +  operand2);
				logger.info("compute reslut: " + current );
				int result = bt.compute(current, operand1,  operand2);	
				st.push(new Integer(result).toString());
				System.out.println("push result: " + new Integer(result).toString());
			}
		}
		
		System.out.println("Result: " + st.pop());
		
		
	//	bt.inOrder(bt.getRoot());
		//System.exit(0);
		
	//	System.out.println(bt.evaluate(bt.getRoot()));
		//bt.inOrder(bt.getRoot());
		
		

	}

	private static boolean isOPerator(String value) {
		//return  (value == "sum" || value == "sub" || value == "mult") ?  true : false;
		if (value.equals("add") || value.equals("sub") || value.equals("mult"))  return true;
		else
			return false;
			
	}

}
