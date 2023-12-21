### 6.1 Selection 선택 정렬

**6.1.1 개념**

선택정렬이란 각 루프마다 최대 원소를 찾고, 최대 원소와 맨 오른쪽 원소를 교환하는 것이다. 그 다음으로는 맨 오른쪽 원소를 제외하고 위 과정을 반복한다.

똑같은 방법이지만 반대로 최솟값을 찾고 가장 왼쪽과 교환한 후 다음 루프에서는 가장 왼쪽 원소를 제외하고 하나의 원소만 남을 때까지 루프를 반복시키는 방법도 있다.

**6.1.2 sort 코드**
``` java
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
            swap(a, i, min); // min과 a[i]의 교환
        }
    }
```
위 코드에서는 최솟값을 찾고 앞 원소와 교환하는 방법을 선택하였다. 이중 `for` 문을 돌기 때문에 시간복잡도는 O(N^2) 를 가진다.

[전체 코드](https://github.com/1Dohyeon/Study-DataStructure/blob/master/06_sort_with_java/D1_Sort/Selection.java)

---
### 6.2 Insertion 삽입 정렬

**6.2.1 개념**

배열이 정렬된 부분과 정렬되지 않은 부분으로 나뉘며, 정렬이 안된 부분의 가장 왼쪽 원소를 정렬된 부분에 삽입하는 방식의 알고리즘이다.

![[삽입정렬.png]]

정렬 안된 부분의 가장 왼쪽 i 를 정렬된 원소들과 비교를 하면서 순서에 맞게 현재 원소를 삽입한다. 삽입한 원소를 정렬하는 것으로 입력에 따라서 시간복잡도가 달라진다. 

즉, 입력이 이미 정렬된 경우는 탐색만 하면 되므로 O(N) 이라는 시간복잡도를 가지고, 입력이 역으로 정렬된 경우는 최악의 경우로 O(N^2) 이라는 시간복잡도를 가진다.

예를 들어, 10억개의 데이터 중 10개를 제외하고는 모두 정렬이 되어있을 때, 나머지 10개를 정렬하기 위해서 그 10개만 반복문을  돌기에 O(N) 이라는 짧은 시간이 걸린다.

또한 입력 순서가 랜덤인 평균 경우에도 O(N^2) 이라는 시간복잡도를 가진다.

**6.2.2 sort 코드**
``` java
	public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) { // i는 현재 원소의 인덱스
            for (int j = i; j > 0; j--) { // 현재 원소를 정렬된 앞부분에 삽입
                if (isless(a[j], a[j - 1])) {
                    swap(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
```

위 코드의 진행 과정을 예시를 통해서 설명하자면 아래 이미지와 같다 :

![[삽입정렬 진행과정.png]]

[전체 코드](https://github.com/1Dohyeon/Study-DataStructure/blob/master/06_sort_with_java/D1_Sort/Insertion.java)

---
### 6.3 Bubble 버블 정렬

**6.3.1 개념**

버블 정렬은 인접한 두 원소를 비교하면서 상대적으로 큰 원소를 뒤로 옮긴다. 이것을 계속 진행하다 보면 마지막 위치에는 가장 큰 원소가 있을 것이다. 이렇게 하나의 루프를 돌았다면 끝자리를 제외하고 다시 이 루프를 반복한다.

**6.3.2 sort 코드**

``` java
	public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // 두 원소의 위치 바꿈
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // 배열이 정렬되었다면 더 이상 반복할 필요가 없다.
            if (!swapped) {
                break;
            }
        }
    }
```

이중 `for` 문의 형태를 하고 있으므로 시간복잡도는 O(N^2) 임을 알 수 있다. 또 위 코드에서는 `swapped` 이 일어나지 않았으면 반복문을 종료시킨다.

원소들이 `j` 반복문을 한바퀴 도는 동안 교환이 하나도 일어나지 않았다면, 이미 정렬되어 있음을 뜻하기 때문이다.

[전체 코드](https://github.com/1Dohyeon/Study-DataStructure/blob/master/06_sort_with_java/D1_Sort/Bubble.java)

---
### 6.4 Heap 힙 정렬




[전체 코드](https://github.com/1Dohyeon/Study-DataStructure/blob/master/06_sort_with_java/D1_Sort/Heap.java)

---
### 6.5 Quick 퀵 정렬





[전체 코드](https://github.com/1Dohyeon/Study-DataStructure/blob/master/06_sort_with_java/D1_Sort/Quick.java)

---
### 6.6 Merge 합병(병합) 정렬





[전체 코드](https://github.com/1Dohyeon/Study-DataStructure/blob/master/06_sort_with_java/D1_Sort/Merge.java)
