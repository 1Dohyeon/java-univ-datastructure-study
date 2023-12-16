package D1_Sort;

public class Heap {
    public static void sort(Comparable[] a) {
        int heapSize = a.length - 1;
        for (int i = heapSize / 2; i > 0; i--) { // 힙 만들기
            downHeap(a, i, heapSize);
        }
        while (heapSize > 1) { // 힙 정렬
            swap(a, 1, heapSize--);
            downHeap(a, 1, heapSize);
        }
    }

    private static void downHeap(Comparable[] a, int p, int heapSize) {
        while (2 * p <= heapSize) {
            int s = 2 * p; // s = 왼쪽 자식의 인덱스
            if (s < heapSize && isless(a[s], a[s + 1])) {
                s++;
            } // 오른쪽 자식이 큰 경우
            if (!isless(a[p], a[s])) {
                break;
            } // 부모가 자식 승자보다 크면 힙 속성 만족
            swap(a, p, s); // 힙 속성 만족 안하면 부모와 자식 승자 교환
            p = s; // 이제 자식 승자의 자리에 부모가 있게 됨
        }
    }

    private static boolean isless(Comparable i, Comparable j) { // 키 비교
        return (i.compareTo(j) < 0);
    }

    private static void swap(Comparable[] a, int i, int j) { // 원소 교환
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
