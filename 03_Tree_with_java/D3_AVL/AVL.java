package D3_AVL;

public class AVL<Key extends Comparable<Key>, Value> {
    public Node<Key, Value> root;

    public Node<Key, Value> getRoot() {
        return root;
    }

    public AVL(Key newId, Value newName, int newHt) { // BST 생성자
        root = new Node<Key, Value>(newId, newName, newHt);
    }

    private int bf(Node<Key, Value> n) {
        return height(n.left) - height(n.right);
    }

    private int height(Node<Key, Value> n) {
        if (n == null) {
            return 0;
        } else {
            return n.height;
        }
    }

    private int tallerHeight(int x, int y) {
        if (x > y) {
            return x;
        } else {
            return y;
        }
    }

    // n은 현재노드, 바꾼 후 중위순회를 해도 기존 중위순회랑 같도록 해야한다.
    private Node<Key, Value> rotateRight(Node<Key, Value> n) {
        Node<Key, Value> x = n.left;
        n.left = x.right;
        x.right = n;
        // 높이 갱신
        n.height = tallerHeight(height(n.left), height(n.right)) + 1;
        x.height = tallerHeight(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node<Key, Value> rotateLeft(Node<Key, Value> n) {
        Node<Key, Value> x = n.right;
        n.right = x.left;
        x.left = n;

        // 높이 갱신
        n.height = tallerHeight(height(n.left), height(n.right)) + 1;
        x.height = tallerHeight(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 삽입 연산
    public void put(Key k, Value v) {
        root = put(root, k, v);
    }

    public Node<Key, Value> put(Node<Key, Value> n, Key k, Value v) {
        if (n == null) {
            return new Node<Key, Value>(k, v, 1);
        } // n이 없을 경우 추가해야하므로 노드 return

        int t = k.compareTo((Key) n.id);
        if (t < 0) {
            n.left = put(n.left, k, v);
        } // k의 id가 n의 id보다 작은 경우 왼쪽으로
        else if (t > 0) {
            n.right = put(n.right, k, v);
        } // k의 id가 n의 id보다 큰 경우 오른쪽으로
        else {
            n.name = v; // k가 이미 트리에 있으므로 Value v만 갱신
            return n; // n을 반환함으로써 재연결
        }

        n.height = tallerHeight(height(n.left), height(n.right)) + 1;

        return balance(n);
    }

    // 불균형 처리
    private Node<Key, Value> balance(Node<Key, Value> n) {
        // 노드 n의 왼쪽 서브트리가 높아서 불균형 발생
        if (bf(n) > 1) {
            if (bf(n.left) < 0) { // 노드 n의 왼쪽자식노드의 오른쪽 서브트리가 높은 경우
                n.left = rotateLeft(n.left); // LR회전
            }
            n = rotateRight(n); // LL회전
        }
        // 노드 n의 오른쪽 서브트리가 높아서 불균형 발생
        else if (bf(n) < -1) {
            if (bf(n.right) > 0) { // 노드 n의 오른쪽 자식노드의 왼쪽 서브트리가 높은 경우
                n.right = rotateRight(n.right); // RL회전
            }
            n = rotateLeft(n); // RR회전
        }

        return n;
    }

    // 최소값 key를 리턴
    public Key min() {
        if (root == null) {
            return null;
        }
        return (Key) min(root).id;
    }

    private Node<Key, Value> min(Node<Key, Value> n) {
        if (n.left == null) {
            return n;
        }
        return min(n.left);
    }

    // 삭제 연산
    public void delete(Key k) {
        root = delete(root, k);
    }

    private Node<Key, Value> delete(Node<Key, Value> n, Key k) {
        if (n == null) {
            return null;
        }
        int t = k.compareTo((Key) n.id);

        if (t < 0) {
            n.left = delete(n.left, k);
        } else if (t > 0) {
            n.right = delete(n.right, k);
        } else {
            Node<Key, Value> target = n;
            n = min(target.right);
            n.right = deleteMin(target.right);
            n.left = target.left;
        }

        n.height = tallerHeight(height(n.left), height(n.right)) + 1;
        return balance(n);
    }

    // 최소값 삭제
    public void deleteMin() {
        if (root == null) {
            System.out.println("empty 트리");
        }
        root = deleteMin(root);
    }

    private Node<Key, Value> deleteMin(Node<Key, Value> n) {
        if (n.left == null) {
            return n.right;
        }

        n.left = deleteMin(n.left); // 맞는지 모르겠음
        n.height = tallerHeight(height(n.left), height(n.right)) + 1;

        return balance(n);
    }
}