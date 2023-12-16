package D2_ClosedAddressing;

// hash값이 같은 경우 같은 hash에서 Node로 연결, 최근에 삽입된 데이터가 head Node가 됨

public class Chaining<K, V> {
    private int M = 13; // 테이블 크기
    private Node[] a = new Node[M]; // 해시테이블

    public static class Node { // Node class, Chaining class안에 public으로 선언
        private Object key;
        private Object data;
        private Node next;

        public Node(Object newKey, Object newData, Node ref) { // 생성자
            key = newKey;
            data = newData;
            next = ref;
        }

        public Object getKey() {
            return key;
        }

        public Object getData() {
            return data;
        }
    }

    private int hash(K key) { // 해시코드
        return (key.hashCode() & 0x7fffffff) % M; // 나눗셈 연산
    }

    public V get(K key) { // 탐색 연산
        int i = hash(key);
        for (Node x = a[i]; x != null; x = x.next) { // 연결리스트 탐색
            if (key.equals(x.key)) {
                return (V) x.data;
            }
        }
        return null; // 탐색 실패
    }

    public void put(K key, V data) { // 삽입 연산
        int i = hash(key);
        for (Node x = a[i]; x != null; x = x.next) {
            if (key.equals(x.key)) { // 이미 key 존재
                x.data = data; // 데이터만 갱신
                return;
            }
        }
        // hash값이 같은 경우 같은 hash에서 Node로 연결, 최근에 삽입된 데이터가 head Node가 되기에 원래 a[i]를 next로
        // 연결
        a[i] = new Node(key, data, a[i]); // 연결 리스트 첫 노드로 삽입
    }
}
