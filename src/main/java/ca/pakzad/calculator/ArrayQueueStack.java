package ca.pakzad.calculator;

import java.util.ArrayDeque;

//public class ArrayQueueStack<E> extends java.util.Stack<E>{
public class ArrayQueueStack<E> implements StackIf<E> {

	private ArrayDeque<E> aq = new ArrayDeque<>();

	@Override
	public void push(E e) {
		aq.push(e);
	}

	@Override
	public E pop() {

		return aq.pop();
	}

	@Override
	public int size() {

		return aq.size();
	}

}
