package ca.pakzad.calculator;

public class TreeNode {

	private TreeNode parent;
	private TreeNode leftChild;
	private TreeNode rightChild;
	//NodeData data;
	private int value;
	private String operator;
	
	
	
	public TreeNode(String operator) {
		super();
		this.operator = operator;
		this.value = 0;
		parent = null;
		leftChild = null;
		rightChild = null;
	}
	
	public TreeNode(int value) {
		super();
		this.value = value;
		this.operator = null;
	}
	
	public TreeNode getParent() {
		return parent;
	}
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	public TreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
		this.leftChild.setParent(this);
	}
	public TreeNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
		this.rightChild.setParent(this);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public boolean isInternal(){
		return (leftChild  != null ||  rightChild != null);
	}
	
	public boolean isExternal(TreeNode n){
		return (leftChild  == null && rightChild == null);
		
	}
}
