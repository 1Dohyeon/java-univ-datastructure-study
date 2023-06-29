package D1_BinaryTree;

import java.util.*;

public class BinaryTree<Key extends Comparable<Key>> {
    private Node<Key> root;

    // 트리 생성자
    public BinaryTree() {
        root = null;
    }

    public Node<Key> getRoot() {
        return root;
    }

    public void setRoot(Node<Key> newRoot) {
        root = newRoot;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // preorder(), inorder(), postorder(), levelorder(),
    // size(), height(), isEqual() 메소드 선언

    // 전위 순회
    public void preorder(Node<Key> n) {
        if (n != null) {
            System.out.print(n.getKey() + " "); // 노드 n 방문
            preorder(n.getLeft()); // n의 왼쪽 서브트리 순회
            preorder(n.getRight()); // n의 오른쪽 서브트리 순회
        }
    }

    // 중위 순회
    public void inorder(Node<Key> n) {
        // empty도 tree이기 때문에 n!=null이라는 조건을 해야 실행됨
        if (n != null) {
            inorder(n.getLeft()); // n의 왼쪽 서브트리 순회
            System.out.print(n.getKey() + " "); // 노드 n 방문
            inorder(n.getRight()); // n의 오른쪽 서브트리 순회
        }
    }

    // 후위 순회
    public void postorder(Node<Key> n) {
        if (n != null) {
            postorder(n.getLeft()); // n의 왼쪽 서브트리 순회
            postorder(n.getRight()); // n의 오른쪽 서브트리 순회
            System.out.print(n.getKey() + " "); // 노드 n 방문
        }
    }

    // 레벨순회: 각 레벨에서는 좌에수 우로 노드들을 방문, 큐를 사용해 구현
    public void leverorder(Node<Key> root) {
        // 큐 자료구조 이용(add, remove 사용)
        LinkedList<Node<Key>> q = new LinkedList<Node<Key>>();
        Node<Key> t = null;

        q.add(root); // 루트 노드를 큐에 삽입
        while (!q.isEmpty()) { // 큐가 비어있을 때까지 반복
            t = q.remove(); // 맨 앞 노드 제거
            System.out.print(t.getKey() + " "); // 제거 노드 출력(방문)

            if (t.getLeft() != null) { // 큐에 왼쪽 자식 삽입
                q.add(t.getLeft());
            }
            if (t.getRight() != null) { // 큐에 오른쪽 자식 삽입
                q.add(t.getRight());
            }
        }
    }

    // n를 루트로 하는 (서브)트리의 노드 수
    public int size(Node<Key> n) {
        if (n == null) {
            return 0;
        } else {
            return (1 + size(n.getLeft()) + size(n.getRight()));
        }
    }

    // n를 루트로하는 (서브)트리의 높이
    public int height(Node<Key> n) {
        if (n == null) {
            return 0;
        } else {
            return (1 + Math.max(height(n.getLeft()),
                    height(n.getRight())));
        }
    }

    // 두 트리의 동일성 검사
    public static boolean isEqual(Node n, Node m) {
        // 둘다 null이면 true, 아니면 false
        if (n == null || m == null) {
            return n == m;
        }

        // 둘다 null이 아니면 item 비교
        if (n.getKey().compareTo(m.getKey()) != 0) {
            return false;
        }

        return (isEqual(n.getLeft(), m.getRight()) &&
                isEqual(n.getRight(), m.getLeft()));
    }
}
