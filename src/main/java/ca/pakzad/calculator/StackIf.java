package ca.pakzad.calculator;

public interface StackIf<E> {

	
	void push(E e);

	E pop();

	int size();

}