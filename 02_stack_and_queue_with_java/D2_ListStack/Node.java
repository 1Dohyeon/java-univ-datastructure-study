package D2_ListStack;

public class Node <E extends Comparable<E>>{
	private E item;
	private Node<E> next;
	
	public Node(E newItem, Node<E> p) {
		item = newItem;
		next = p;
	}

	public E getItem() {return item;}
	public Node<E> getNext() {return next;}
	public void setItem(E newItem) {item = newItem;}
	public void setNext(Node<E> n) {next = n;}
}
