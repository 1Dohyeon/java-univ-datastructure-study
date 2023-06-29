package D2_BS_Tree;

public class BST<Key extends Comparable<Key>, Value> {
    public Node<Key, Value> root;

    public Node<Key, Value> getRoot() {
        return root;
    }

    public BST(Key newId, Value newName) { // BST 생성자
        root = new Node<Key, Value>(newId, newName);
    }

    // get, put, min, deleteMin, delete 메소드들 선언

    // 트리에서 k값을 갖는 노드를 탐색 & Value 리턴
    public Value get(Key k) {
        return get(root, k);
    } // root부터 내려와야 하므로 root를 argument로 담음

    public Value get(Node<Key, Value> n, Key k) {
        if (n == null) {
            return null;
        } // k를 발견 못함

        int t = n.getKey().compareTo(k);

        if (t > 0) {
            return get(n.getLeft(), k);
        } else if (t < 0) {
            return get(n.getRight(), k);
        } else { // k를 찾았을 경우 t==0
            return (Value) n.getValue();
        }
    }

    // 트리에 노드 (k, v)를 삽입
    public void put(Key k, Value v) {
        root = put(root, k, v);
    }

    // n를 루트로 하는 (서브)트리에 노드 (k, v)를 삽입
    public Node<Key, Value> put(Node<Key, Value> n, Key k, Value v) {
        // root부터 탐색을 시작해야 하므로 n 파라미터 자리에 처음에는 n이 삽입된다.

        if (n == null) {
            return new Node<Key, Value>(k, v);
        } // 비었을 경우 새로운 노드를 연결시킴
        int t = n.getKey().compareTo(k);

        if (t > 0) { // n의 key가 새로운 노드의 key보다 클 경우 왼쪽으로
            n.setLeft(put(n.getLeft(), k, v));
        } else if (t < 0) { // n의 key가 새로운 노드의 key보다 작을 경우 오른쪽으로
            n.setRight(put(n.getRight(), k, v));
        } else { // 같을 경우는 key는 냅두고 value를 v로 대체한다.
            n.setValue(v);
        }

        // 마지막에 return n;을 해줌으로써, 삽입된 노드의 부모 노드에 대한 포인터를 반환하여 거슬러 올라가며 재 연결
        return n;
    }

    // 트리에서 최소값을 갖는 노드의 key를 리턴
    public Key min() {
        if (root == null) {
            return null;
        }
        return (Key) min(root).getKey();
    }

    // n를 루트로 하는 (서브)트리에서 최소값을 갖는 노드를 리턴
    private Node<Key, Value> min(Node<Key, Value> n) {
        if (n.getLeft() == null) {
            return n;
        }
        return min(n.getLeft());
    }

    // 트리에서 최소값을 갖는 노드를 삭제
    public void deleteMin() {
        if (root == null) {
            System.out.println("empty 트리");
        }
        root = deleteMin(root);
    }

    // n를 루트로 하는 (서브)트리에서 최소값을 갖는 노드를 삭제
    public Node<Key, Value> deleteMin(Node<Key, Value> n) {
        // if(n의 왼쪽 자식==null) n의 오른쪽 자식 리턴
        if (n.getLeft() == null) {
            return n.getRight();
        }

        // if(n의 왼쪽자식!=null), n의 왼쪽 자식으로 재귀 호출
        n.setLeft(deleteMin(n.getLeft()));
        return n;
    }

    public void delete(Key k) {
        root = delete(root, k);
    }

    public Node<Key, Value> delete(Node<Key, Value> n, Key k) {
        if (n == null) {
            return null;
        }

        int t = n.getKey().compareTo(k);
        if (t > 0) { // if (k < 노드 n의 id) 왼쪽 자식으로 이동
            n.setLeft(delete(n.getLeft(), k));
        } else if (t < 0) { // if (k > 노드 n의 id) 오른쪽 자식으로 이동
            n.setRight(delete(n.getRight(), k));
        } else { // 삭제할 노드를 발견했을 경우
            if (n.getRight() == null) { // 오른쪽이 없을 경우는 왼쪽, 둘 다 없는 경우에도 어차피 null을 반환하는 것이므로 양쪽 다 null인 경우도 이 조건문이 해결
                return n.getLeft();
            }
            if (n.getLeft() == null) { // 왼쪽이 없을 경우는 오른쪽을 반환. 반환 후 deleteMin처럼 수행
                return n.getRight();
            }

            // 삭제한 노드의 양쪽에 둘 다 있을 경우(원래 n 자리에 다른 값을 넣어야하므로 target에 n을 담음)
            Node<Key, Value> target = n;
            // 삭제할 노드 자리로 옮겨올 노드 찾아서 n이 가리키게 함
            n = min(target.getRight()); // target의 오른쪽의 최솟값을 원래 n 자리에 넣고
            n.setRight(deleteMin(target.getRight())); // target의 맨 오른쪽에 있는 최솟값(예전 n=min(target.getRight()))을
                                                      // 삭제하고, deleteMin(target.getRight())는 서브트리의 root,
                                                      // 즉 target.getRight를 반환할 테니 target의 오른쪽 노드를 n의 오른쪽에 담음
            n.setLeft(target.getLeft()); // n의 left도 원래 n(target)의 left와 연결
        }

        return n; // 부모 노드의 포인터를 갱신해야 하기 때문에 n을 반환
    }

    // 트리의 높이 리턴
    public int height() {
        return height(root) + 1;
    }

    // n을 루트로 하는 (서브)트리의 높이 리턴
    public int height(Node<Key, Value> n) {
        if (n == null) {
            return -1;
        }
        return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
    }

    // 트리 출력: 세가지 순회 방식 사용
    public void print(Node<Key, Value> root) {
        System.out.printf("inorder: ");
        inorder(root);
        System.out.println();
    }

    // 전위 순회
    public void preorder(Node<Key, Value> n) {
        if (n != null) {
            System.out.print(n.getKey() + " "); // 노드 n 방문
            preorder(n.getLeft()); // n의 왼쪽 서브트리 순회
            preorder(n.getRight()); // n의 오른쪽 서브트리 순회
        }
    }

    // 중위 순회
    public void inorder(Node<Key, Value> n) {
        // empty도 tree이기 때문에 n!=null이라는 조건을 해야 실행됨
        if (n != null) {
            inorder(n.getLeft()); // n의 왼쪽 서브트리 순회
            System.out.print(n.getKey() + " "); // 노드 n 방문
            inorder(n.getRight()); // n의 오른쪽 서브트리 순회
        }
    }

    // 후위 순회
    public void postorder(Node<Key, Value> n) {
        if (n != null) {
            postorder(n.getLeft()); // n의 왼쪽 서브트리 순회
            postorder(n.getRight()); // n의 오른쪽 서브트리 순회
            System.out.print(n.getKey() + " "); // 노드 n 방문
        }
    }
}
