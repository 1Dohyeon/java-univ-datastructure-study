package D1_OpenAddressing;

import java.util.Random;

public class RandomProbing<K, V> {
    private int N = 0, M = 13;
    private K[] a = (K[]) new Object[M]; // 헤시테이블
    private V[] d = (V[]) new Object[M]; // key관련 데이터 저장

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V data) { // 삽입 연산
        int initialpos = hash(key); // 초기 위치
        int i = initialpos;
        Random rand = new Random();
        rand.setSeed(10);
        do {
            if (a[i] == null) { // 삽입 위치 발견
                a[i] = key; // key를 해시테이블에 저장
                d[i] = data;
                N++; // key관련 데이터 저장
                return;
            }
            if (a[i].equals(key)) { // 이미 key 존재
                d[i] = data; // 데이터만 갱신
                return;
            }
            i = (initialpos + rand.nextInt(1000)) % M; // i = 다음 위치
        } while (N < M);
    }

    public V get(K key) { // 탐색 연산
        Random rand = new Random();
        rand.setSeed(10); // 삽입때와 같은 seed값 사용
        int initialpos = hash(key); // 초기 위치
        int i = initialpos;
        while (a[i] != null) {
            if (a[i].equals(key)) {
                return d[i];
            } // 탐색 성공
            i = (initialpos + rand.nextInt(1000)) % M; // i = 다음 위치
        }
        return null; // 탐색 실패
    }
}