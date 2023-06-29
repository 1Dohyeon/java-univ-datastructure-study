package D1_Sort;

public class Shell {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 4; // 3x+1 간격: 1, 4, 13, 40, 121, ... 중에서 4와 1만 사용
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && isless(a[j], a[j - h]); j -= h) {
                    // h- 정렬 수행, h만큼 빼주는 이유는 교환 후 다시 -h 위치의 원소와 비교해야 하기 때문,
                    // 원소가 없다면 j>=h조건이 아니라는 뜻이므로 for문 탈출, j가 j-h보다 크다면 굳이 j-=h 해가면서 계속 비교할 필요 없으므로
                    // 탈출
                    swap(a, j, j - h);
                }
            }
            h /= 3;
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
