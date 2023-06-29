package D1_Sort;

public class Selection {

    public static void main(String args[]) {
        Comparable[] arr = new Comparable[6];
        arr[0] = 40;
        arr[1] = 70;
        arr[2] = 60;
        arr[3] = 30;
        arr[4] = 10;
        arr[5] = 50;
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        System.out.println(N);
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) { // min 찾기
                if (isless(a[j], a[min])) {
                    min = j;
                }
            }
            // i = N-1일때, j = N이라서 j for문은 false가 되기에 에러가 아니고 그냥 break가 된다.
            swap(a, i, min); // min과 a[i]의 교환
        }
    }

    private static boolean isless(Comparable i, Comparable j) { // 키 비교
        return (i.compareTo(j) < 0); // i가 j보다 작다면 True
    }

    private static void swap(Comparable[] a, int i, int j) { // a배열의 i와 j번째 원소 교환
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}