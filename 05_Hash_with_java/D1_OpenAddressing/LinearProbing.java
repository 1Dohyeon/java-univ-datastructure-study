package D1_OpenAddressing;

// 선형조사: (h(key)+j)%M, j=0,1,2,3,...
public class LinearProbing<K, V> {
    private int M = 13;
    private K[] a = (K[]) new Object[M]; // 헤시테이블
    private V[] d = (V[]) new Object[M]; // key관련 데이터 저장

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void put(K key, V data) { // 삽입 연산
        int initialpos = hash(key); // 초기 위치(key의 기본 hash값)
        int i = initialpos, j = 1;
        do {
            if (a[i] == null) { // 삽입 위치 발견했을 경우
                a[i] = key; // key를 해시테이블에 저장
                d[i] = data; // key 관련 데이터를 동일한 인덱스하에 저장
                return;
            }
            if (a[i].equals(key)) { // 이미 같은 key가 존재할 경우
                d[i] = data; // 데이터만 갱신
                return;
            }
            i = (initialpos + j++) % M; // i = 다음위치
        } while (i != initialpos); // 현재 i가 초기위치와 같게되면 루프 종료
        /*
         * while이 아닌 do while을 쓰는 경우 i=initialpos일 때 반복문을 나가야하지만,
         * 초기 i는 initialpos이므로 while문을 쓰면 실행이 되지 않음.
         * i=initialpos가 돼서 반복문을 실행시키지 못하는 경우는 한바퀴를 다 돌았는데 자리가
         * 없다는 뜻이 된다.
         */
    }

    /*
     * 탐색을 할 때 key의 hash값을 찾는데 그곳에 key가 있다면 그 key의 data를 return하지만 충돌이 났어서
     * key값이 옮겨진 것이라면, 위 put 메소드의 i 값을 바꾸는 것처럼 i값을 선형방법(+1)으로 바꿔가며 탐색
     */
    public V get(K key) { // 탐색 연산
        int initialpos = hash(key);
        int i = initialpos, j = 1;
        while (a[i] != null) { // a[i]가 empty가 아니면
            if (a[i].equals(key)) {
                return d[i];
            } // 탐색성공
            i = (initialpos + j++) % M; // i = 다음위치
        }
        return null; // 탐색 실패
    }
}