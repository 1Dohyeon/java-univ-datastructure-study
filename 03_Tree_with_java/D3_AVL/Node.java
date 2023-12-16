package D3_AVL;

public class Node<Key extends Comparable<Key>, Value> {
    Key id; // 비교가 되는 것은 Key이지 Value가 아님
    Value name;
    Node<Key, Value> left, right;
    int height;

    public Node(Key newId, Value newName, int newHt) { // 생성자
        id = newId;
        name = newName;
        left = right = null;
        height = newHt;
    }
}
