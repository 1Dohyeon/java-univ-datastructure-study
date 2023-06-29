package D4_ListQueue;

public class Node<E extends Comparable<E>> {
    private E item;
    private Node<E> next;

    // 생성자
    public Node(E newItem, Node<E> p) {
        item = newItem;
        next = p;
    }

    // get 메소드와 set 메소드
    public E getItem() {
        return item;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setItem(E newItem) {
        item = newItem;
    }

    public void setNext(Node<E> n) {
        next = n;
    }
}
