package D2_BS_Tree;

public class Node<Key extends Comparable<Key>, Value> {
    private Key id; // 비교가 되는 것은 Key이지 Value가 아님
    private Value name;
    private Node<Key, Value> left, right;

    public Node(Key newId, Value newName) { // 노드 생성자
        id = newId;
        name = newName;
        left = right = null;
    }

    // get과 set메소드들
    public Key getKey() {
        return id;
    }

    public Value getValue() {
        return name;
    }

    public Node<Key, Value> getLeft() {
        return left;
    }

    public Node<Key, Value> getRight() {
        return right;
    }

    public void setKey(Key newId) {
        id = newId;
    }

    public void setValue(Value newName) {
        name = newName;
    }

    public void setLeft(Node<Key, Value> newLeft) {
        left = newLeft;
    }

    public void setRight(Node<Key, Value> newRight) {
        right = newRight;
    }
}
