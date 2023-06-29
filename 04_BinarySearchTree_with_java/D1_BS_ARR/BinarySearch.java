package D1_BS_ARR;

public class BinarySearch {
    public static void main(String[] args) {
        int[] a = { 5, 8, 10, 15, 20, 25, 30, 40, 50, 54, 66, 69, 83, 86, 90 };

        // a 배열에서 index 0부터 a의 마지막 인덱스까지 탐색하여 66을 찾음
        int result = binarySearch(a, 0, a.length - 1, 66);
        System.out.println("binarySearch(a, 0, 14, 66) = " + result);

        result = binarySearch(a, 0, a.length - 1, 33);
        System.out.println("binarySearch(a, 0, 14, 33) = " + result);
    }

    public static int binarySearch(int[] a, int first, int last, int target) {
        if (first > last) {
            return -1;
        } // 배열 탐색 실패(target이 없음)

        int mid = (first + last) / 2; // 배열에서 탐색할 중간 원소의 인덱스 계산
        System.out.println(mid);

        if (a[mid] == target) {
            return mid;
        } else if (a[mid] > target) { // 배열의 앞부분 탐색
            return binarySearch(a, first, mid - 1, target);
        } else { // 배열의 뒷부분 탐색
            return binarySearch(a, mid + 1, last, target);
        }
    }
}
