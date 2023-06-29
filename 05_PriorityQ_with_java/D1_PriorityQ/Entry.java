package D1_PriorityQ;

public class Entry<K extends Comparable<K>, V> {
    private K key;
    private V val;

    public Entry(K newKey, V newValue) { // 생성자
        key = newKey;
        val = newValue;
    }

    // get, set 메소드들
    public K getKey() {
        return key;
    }

    public V getValue() {
        return val;
    }

    public void setKey(K newKey) {
        key = newKey;
    }

    public void setValue(V newValue) {
        val = newValue;
    }
}
