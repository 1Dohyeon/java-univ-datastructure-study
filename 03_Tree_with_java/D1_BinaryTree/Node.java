package D1_BinaryTree;

public class Node<Key extends Comparable<Key>> {
    private Key item;
    private Node<Key> left;
    private Node<Key> right;

    // 노드 생성자
    public Node(Key newItem, Node<Key> lt, Node<Key> rt) {
        item = newItem;
        left = lt;
        right = rt;
    }

    // 값을 리턴하는 메소드
    public Key getKey() {
        return item;
    }

    public Node<Key> getLeft() {
        return left;
    }

    public Node<Key> getRight() {
        return right;
    }

    // 값을 설정하는 메소드
    public void setKey(Key newItem) {
        item = newItem;
    }

    public void setLeft(Node<Key> lt) {
        left = lt;
    }

    public void setRight(Node<Key> rt) {
        right = rt;
    }
}
