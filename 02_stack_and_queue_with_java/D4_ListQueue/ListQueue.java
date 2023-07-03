package D4_ListQueue;

import java.util.NoSuchElementException;

public class ListQueue<E extends Comparable<E>> {
    private Node<E> head, last;
    private int size;

    // 큐 생성자
    public ListQueue() {
        head = last = null;
        size = 0;
    }

    // 큐에 있는 항목의 수를 리턴
    public int size() {
        return size;
    }

    // 큐가 empty면 true를 리턴
    public boolean isEmpty() {
        return (size == 0);
    }
    // empty가 되면 front == rear

    // 큐 add 연산
    public void add(E newItem) {
        Node<E> newNode = new Node<E>(newItem, null);

        if (isEmpty()) {
            head = newNode;
        } else {
            last.setNext(newNode);
        }

        last = newNode;
        size++;
    }

    // 큐 remove 연산
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E frontItem = head.getItem();
        head = head.getNext();

        size--;
        if (isEmpty()) {
            last = null;
        }

        return frontItem;
    }
}
