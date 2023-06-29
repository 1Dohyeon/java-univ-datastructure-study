package D1_ArrayStack;

import java.util.EmptyStackException;
public class ArrayStack<E> {
	private E s[];		
	private int topIndex;	
	
	public ArrayStack() {
		s = (E[]) new Object[1];	
		topIndex = -1;
	}
	
	public boolean isEmpty() {
		return (topIndex == -1);
	}
		
	public int size() {
		return topIndex + 1;
	}
	
	private void resize(int newSize) { // O(N)
		Object[] t = new Object[newSize];
		
		for(int i=0;i<topIndex+1;i++) {
			t[i] = s[i];
		}
			
		s = (E[]) t;
	}
	
	public E peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return s[topIndex];
	}
	
	public void push(E newItem) {
		if(size()==s.length) {
			resize(2*s.length);
		}
		
		s[size()] = newItem;
		topIndex++;
	}
	
	public E pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		E item = s[topIndex];
		s[topIndex] = null;
		topIndex--;
		if(size()>0 && size()==s.length/4) {
			resize(s.length/2);
		}
		return item;
	}
}
