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

**6.4.1 개념**

힙 정렬을 알기 위해서는 Heap(힙)에 대해서 알아야한다. 
- Heap : [Heap with Priority Queue](https://github.com/1Dohyeon/Study-DataStructure/tree/master/04_PriorityQ_with_java) 

최대 힙을 구성하게 되면 루트 원소에는 가장 큰 값이 저장될 것이다. 이제 그 루트 값과 마지막 인덱스 값을 교환하면 된다.

![[Pasted image 20231222112233.png]]

위와 같이 진행한다면 마지막 인덱스에는 가장 큰 값이 담기게 된다. 따라서 이 이후에는 바뀐 마지막 인덱스를 제외하고 위 과정을 다시 반복하면 된다.

**6.4.2 sort 코드**
``` java
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
```

우선 `for` 문을 통해서 힙을 만들어 준 후에 힙정렬을 실행해준다. 6.4.1 에서 설명 했듯이 마지막 원소와 마지막 원소를 `swap` 후에 다시 `downheap` 을 수행하는 것을 알 수 있다.

bottom up 방식으로 힙을 구성하는 것은 `O(N)` 의 시간복잡도를 가지고 루트와 힙의 마지막 노드를 교환하고, `downheap()` 을 수행하는 과정은 트리의 높이와 비례하므로 `O(logN)` 임을 알 수 있다.

따라서 힙 정렬은 `O(NlogN)` 시간이 걸린다.

[전체 코드](https://github.com/1Dohyeon/Study-DataStructure/blob/master/06_sort_with_java/D1_Sort/Heap.java)

---
### 6.5 Quick 퀵 정렬

**6.5.1 개념**

퀵 정렬은 맨 왼쪽이나 오른쪽 원소를 택해서 피벗(Pivot)으로 둔다. 피벗보다 작은 원소들은 왼쪽 큰 원소들은 오른쪽에 둔다. 이렇게 되면 피벗의 왼쪽과 오른쪽 집합들로 나뉘게 될 것이다. 이 집합들에서도 따로 피벗을 구하여 위 과정을 반복하면 된다.

**6.5.2 sort 코드**

``` java
	private static void sort(Comparable[] a, int low, int high) {
        if (high <= low) {
            return;
        } // 정렬할게 없음
        int j = partition(a, low, high);
        sort(a, low, j - 1); // 피벗보다 작은 부분을 재귀호출
        sort(a, j + 1, high); // 피벗보다 큰 부분을 재귀호출
    }
```

6.5.1 에서 설명한 것처럼 `partition` 메서드를 이용하여 크기에 따라 왼쪽과 오른쪽 집합을 나눈 후 `sort` 를 왼쪽 오른쪽 불러온다. 따라서 `partiton` 메서드는 어떻게 작동하는지 알 필요가 있다.

**6.5.3 partition 코드**

``` java
	private static int partition(Comparable[] a, int pivot, int high) {
        int i = pivot + 1;
        int j = high;
        Comparable p = a[pivot];
        while (true) {
            while (i <= high && !isless(p, a[i])) {
                i++;
            } // 피벗보다 같거나 작으면
            while (j >= pivot && isless(p, a[j])) {
                j--;
            } // 피벗보다 크면
            if (i >= j) {
                break;
            } // i와 j가 교차되면 루프 나가기
            swap(a, i, j);
        }
        swap(a, pivot, j); // 피벗과 a[j]교한
        return j; // a[j]의 피벗이 자리 잡은 곳
    }
	
	private static boolean isless(Comparable i, Comparable j) { // 키 비교
        return (i.compareTo(j) < 0);
    }
```

`partition` 메서드는 매개변수로 비교할 배열과 피벗, 그리고 배열의 마지막 원소 인덱스를 받는다. 따라서 6.5.2에서 처음 `partition` 을 불러왔을 때는 `low` 를 피벗으로 매개변수로 둔 것을 알 수 있다.(`low`: 맨 왼쪽 원소, `high`: 맨 오른쪽 원소)

`i`는 피벗의 다음 원소의 인덱스, `j`는 가장 오른쪽 원소의 인덱스로 지정한다. `p`는 `pivot` 으로 받은 인덱스의 원소이다. 

우선 `i <= high && !isless(p, a[i])` 조건의 반복문을 통해서 `a[i]`가 `p`와 같거나 작은지 비교한다. `i`가 `high`(마지막 원소)보다 작고(앞으로 나아갈 곳이 있다는 뜻), `a[i] < p` 라면 현재 원소는 비교할 필요가 없다. 따라서 `i++` 를 함으로써 다음 원소와 비교한다.

그다음은 거꾸로 `j`(끝에서부터 이동) 가 피벗보다 크고, 앞으로 이동할 곳이 있다면 `j--`를 함으로써 다음 원소와 비교한다.

만약 `a[i] > a[j]` 라면 둘의 위치를 바꾸어 피벗보다 큰 값을 뒤로가게 한다. 이렇게 `i`와 `j`를 이동하게 되면 겹치는 자리가 있을 것이다. 그 자리에 기준이 되었던 피벗을 옮기게 되면 피벗보다 작은 원소는 왼쪽, 큰 원소는 오른쪽에 위치하게 된다.

만약 피벗이 가장 작거나, 가장 큰값이라고 가정한다면 원소의 이동이 많아질 것이다. 따라서 최악의 경우에는 모든 원소를 옮겨야하므로 `O(N^2)` 의 시간복잡도를 가진다. 하지만 평균의 경우는 `O(NlogN)` 의 시간복잡도를 가진다.

[전체 코드](https://github.com/1Dohyeon/Study-DataStructure/blob/master/06_sort_with_java/D1_Sort/Quick.java)

---
### 6.6 Merge 합병(병합) 정렬

**6.6.1 개념**

합병(병합) 정렬은 크기가 `N` 인 배열을 `1/2N` 크기를 갖는 배열로 분할하고, 각각에 대해서 재귀적으로 합병정렬을 또 실행한다. 그 후에 2개의 정렬된 부분을 합병하여 배열을 다시 완성시킨다. 따라서 합병 정렬은 배열을 다시 만드는 것이기 때문에 추가 공간으로 `N` 만큼을 요구한다. 

**6.6.2 sort & merge 코드**

``` java
	private static void sort(Comparable[] a, Comparable[] b, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(a, b, low, mid); // 앞부분 재귀호출
        sort(a, b, mid + 1, high); // 뒷부분 재귀호출
        merge(a, b, low, mid, high); // 합병 수행
    }

    public static void sort(Comparable[] a) {
        Comparable[] b = new Comparable[a.length];
        sort(a, b, 0, a.length - 1);
    }
	
	private static void merge(
		Comparable[] a, 
		Comparable[] b, 
		int low, int mid, int high
	) {
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) { // 배열 a의 앞부분과 뒷부분을 합병하여 보조배열 b에 저장
            if (i > mid) {
                b[k] = a[j++];
            } // 앞부분이 먼저 소진된 경우
            else if (j > high) {
                b[k] = a[i++];
            } // 뒷부분이 먼저 소진된 경우
            else if (isless(a[j], a[i])) {
                b[k] = a[i++];
            } // a[j]가 승자
            else {
                b[k] = a[i++];
            } // a[i]가 승자
        }
        for (int k = low; k <= high; k++) {
            a[k] = b[k];
        } // 보조 배열 b를 a에 복사
    }
```

위 코드를 보면 알 수 있듯 `sort(Comparable[] a)` 를 호출하면 이 함수 내에서 `private` 로 선언된 `sort` 가 호출되고, 그 `sort` 안에서는 재귀적으로 앞과 뒷부분을 호출하고 합병을 수행하는 것을 알 수 있다. 위 과정을 이미지로 설명하자면 아래와 같다 :

![[Pasted image 20231222120342.png]]

나눌 수 있을 때까지 나누고 `merge` 를 호출하여 새로운 배열 `b[]` 에 순서에 맞게 원소를 집어넣어 합병한다.

[전체 코드](https://github.com/1Dohyeon/Study-DataStructure/blob/master/06_sort_with_java/D1_Sort/Merge.java)
