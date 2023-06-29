package D1_OpenAddressing;

public class DoubleHashing<K, V> {
    private int N = 0, M = 13;
    private K[] a = (K[]) new Object[M]; // 헤시테이블
    private V[] dt = (V[]) new Object[M]; // key관련 데이터 저장

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void put(K key, V data) {
        int initialpos = hash(key); // 초기 위치
        int i = initialpos;
        int j = 1;
        int d = (7 - (int) key % 7); // 두번째 해시 함수, d(key) = 7 - key%7

        do {
            if (a[i] == null) { // 삽입 위치 발견
                a[i] = key; // key를 해시테이블에 저장
                dt[i] = data; // key관련 데이터 저장
                return;
            }
            if (a[i].equals(key)) { // 이미 key 존재
                dt[i] = data; // 데이터만 갱신
                return;
            }
            i = (initialpos + j * d) % M;
            j++;
        } while (N < M);
    }

    public V get(K key) {
        int initialpos = hash(key); // 초기 위치
        int i = initialpos;
        int j = 1;
        int d = (7 - (int) key % 7);

        while (a[i] != null) {
            if (a[i].equals(key)) {
                return dt[i];
            } // 탐색 성공
            i = (initialpos + j * d) % M; // i = 다음위치
        }
        return null; // 탐색 실패
    }
}
