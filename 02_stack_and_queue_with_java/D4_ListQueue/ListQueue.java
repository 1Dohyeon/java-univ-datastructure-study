package D4_ListQueue;

import java.util.NoSuchElementException;

public class ListQueue<E extends Comparable<E>> {
    private Node<E> front, rear;
    private int size;

    // 큐 생성자
    public ListQueue() {
        front = rear = null;
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
            front = newNode;
        } else {
            rear.setNext(newNode);
        }

        rear = newNode;
        size++;
    }

    // 큐 remove 연산
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E frontItem = front.getItem();
        front = front.getNext();

        size--;
        if (isEmpty()) {
            rear = null;
        }

        return frontItem;
    }
}
