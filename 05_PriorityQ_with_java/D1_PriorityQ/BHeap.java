package D1_PriorityQ;

public class BHeap<K extends Comparable<K>, V> { // 이진(최소) 힙
    private Entry<K, V>[] a; // a[0]은 사용 안함
    private int N; // 힙의 크기

    public BHeap(Entry<K, V>[] harray, int initialSize) { // 생성자
        a = harray;
        N = initialSize;
    }

    public int size() {
        return N;
    } // 힙의 크기 리턴

    private boolean greater(int i, int j) { // 키 비교
        return (a[j].getKey().compareTo(a[i].getKey()) < 0);
    }

    private void swap(int i, int j) { // a[i]와a[j]를 교환
        Entry<K, V> temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // 임의의 순서로 키가 저장된 초기 배열을 최소힙으로 만드는 메소드
    // 각각의 노드에서는 다운힙이지만, 힙을 만들때 전체적으로는 바텀업방식이다. 시간이 젤 많이 걸림
    public void createHeap() {
        for (int i = N / 2; i > 0; i--) {
            downheap(i);
        }
    }

    private void downheap(int i) {
        while (2 * i <= N) {
            int k = 2 * i; // i의 왼쪽 자식을 k
            // 오른쪽 자식이 있으면 오른쪽 자식과 비교
            if (k < N && greater(k, k + 1)) {
                k++;
            } // 더 작은 값을 k로 설정
            if (!greater(i, k)) {
                break;
            }
            swap(i, k);
            i = k;
        }
    }

    // 새 항목 삽입 메소드
    public void insert(K newKey, V newValue) {
        Entry<K, V> temp = new Entry<K, V>(newKey, newValue);
        a[++N] = temp;
        upheap(N);
    }

    private void upheap(int j) {
        while (j > 1 && greater(j / 2, j)) { // j=1이라면 root
            swap(j / 2, j);
            j = j / 2;
        }
    }

    // 최솟값 삭제 메소드
    public Entry<K, V> deleteMin() {
        Entry<K, V> min = a[1];
        swap(1, N--);
        a[N + 1] = null;
        downheap(1); // 루트에서부터 다운힙
        return min;
    }

    public void print() {
        print(1);
    }

    private void print(int i) {
        if (i > N) {
            return;
        }
        print(2 * i);
        System.out.print(a[i] + " ");
        print(2 * i + 1);
    }
}
