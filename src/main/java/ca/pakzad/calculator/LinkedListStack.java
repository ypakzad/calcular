package ca.pakzad.calculator;

import java.util.LinkedList;

public class LinkedListStack<E> implements StackIf<E> {
	
	private LinkedList<E> q = new LinkedList<>();

		@Override
		public void push(E e){
			q.addFirst(e);
		}
		
		@Override
		public E pop(){
			return q.removeFirst();
		}
		
		@Override
		public int size(){
			return q.size();
		}
}
