package D1_Sort;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("정렬 전 배열: " + Arrays.toString(array));

        bubbleSort(array);

        System.out.println("정렬 후 배열: " + Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // 두 원소의 위치를 바꿔줍니다.
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // 배열이 정렬되었다면 더 이상 반복할 필요가 없습니다.
            if (!swapped) {
                break;
            }
        }
    }
}
