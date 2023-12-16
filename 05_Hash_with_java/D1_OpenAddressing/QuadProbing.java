package D1_OpenAddressing;

public class QuadProbing<K, V> {
    private int N = 0, M = 13;
    private K[] a = (K[]) new Object[M]; // 헤시테이블
    private V[] d = (V[]) new Object[M]; // key관련 데이터 저장

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void put(K key, V data) { // 삽입 연산
        int initialpos = hash(key); // 초기 위치(key의 기본 hash값)
        int i = initialpos, j = 1;
        do {
            if (a[i] == null) { // 삽입 위치 발견
                a[i] = key; // key를 해시테이블에 저장
                d[i] = data;
                N++; // key 관련 데이터 저장
                return;
            }
            if (a[i].equals(key)) {
                d[i] = data;
            } // 같은 key값이라면 data만 갱신
            i = (initialpos + j * j++) % M; // 선형방법과 다르게 제곱을 하여 빈자리를 찾음
        } while (N < M);
        /*
         * N은 data가 채워질 때 +되므로 size라고 보면 되고, N = M 이상일 경우
         * 해시 배열이 다 채워진 것이므로 실행하지 않음.
         */
    }

    public V get(K key) { // 탐색 연산
        int initialpos = hash(key);
        int i = initialpos, j = 1;
        while (a[i] != null) {
            if (a[i].equals(key)) {
                return d[i];
            } // 탐색 성공
            i = (initialpos + j * j++) % M; // i = 다음 위치
        }
        return null; // 탐색 실패
    }
}
