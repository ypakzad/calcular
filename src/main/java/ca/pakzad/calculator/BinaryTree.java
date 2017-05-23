package ca.pakzad.calculator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryTree {

	 private static final Logger logger =
		        Logger.getLogger(BinaryTree.class.getName());
	 
	private TreeNode root = null;
	
	private TreeNode lastPosition = null;
	
	
	
	public TreeNode getRoot() {
		return root;
	}
	
	public void addNode(TreeNode n){
		if( lastPosition.getLeftChild() == null ){
			lastPosition.setLeftChild(n);
			return ;
		}else if( lastPosition.getRightChild() == null ){
			lastPosition.setRightChild(n);
			//position = position.getLeftChild();
			return ;
		} else{
			if(size(lastPosition.getLeftChild()) <= size(lastPosition.getRightChild())){
			lastPosition = lastPosition.getLeftChild();
			} else {
				lastPosition = lastPosition.getRightChild();
			}
			addNode(n);
		}
	}
	
	public void setRoot(TreeNode root) {
		this.root = this.lastPosition = root;
	}
	public BinaryTree(TreeNode root) {
		super();
		this.root = root;
	}
	public void inOrder(TreeNode n){
		if (n.isInternal()){
			inOrder(n.getLeftChild());
		}
		//evaluate(n);
		if(n.getOperator() == null){
			System.out.println(n.getValue());
		}else {
			System.out.println(n.getOperator());
		}
		
		if (n.isInternal()){
			inOrder(n.getRightChild());
		}
	}
	public void postrder(TreeNode n){
		
	}
	public void preOrder(TreeNode n){
	
	}
	public int size(TreeNode n){
		if ( n == null){
			return 0;
		}else{
			return 1+ size(n.getLeftChild())  + size(n.getRightChild());
		}
	}
	
	public int evaluate(TreeNode n){
		logger.log(Level.INFO, "entering evaluate");
		if (n.isInternal() && n.getOperator() != null){
			int x = evaluate(n.getLeftChild());
			int y = evaluate(n.getRightChild());
			return x+y; //TODO
		}
		else{
			return n.getValue();
		}
		
	}
	public int compute(String operator, int x, int y){
		int result = 0;
		switch(operator ){
		case "add":
			 result = x + y;
			break;
		case "sub":
			 result = x - y;
			break;
		case "mult":
			 result = x * y;
			break;
		default:
			result = 0; // TODO Throw an exception
		
		}
		return result;
	}
}
