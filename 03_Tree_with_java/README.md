### 3.1 Binary Search Tree

**3.1.1 개념**

Binary Search Tree(이진 검색 트리) 는 [1. DynamicArray AND LinkedList](https://github.com/1Dohyeon/Study-DataStructure/tree/master/01_Lists_with_py) 의 1.2.1 에서 설명한 노드를 `next`가 아닌 `left`, `right` **두가지**로 노드들을 연결한 자료구조이다.

즉, 데이터를 이진 검색 트리에 삽입한다면 아래와 같은 모양의 자료구조를 갖는다.
![[BST 예제 이미지.png]]

또한 이진 검색 트리는 위 그림에서처럼 부모 노드보다 크다면 `right` , 부모노드보다 작으면 `left` 로 이동하여 자리를 잡는다.

**3.1.2 class Node**
``` java
public class Node<Key extends Comparable<Key>, Value> {
    private Key id;
    private Value name;
    private Node<Key, Value> left, right;
    
    // 생략
```
- `id` : 각 노드의 고유값으로 노드를 배치하기 위해서 사용된다.
- `name` : 각 노드에 담을 데이터
- `left` : 현재 노드의 왼쪽 자식 노드를 가리킨다.
- `right` : 현재 노드의 오른쪽 자식 노드를 가리킨다.

전체 코드 -> [Node Code](https://github.com/1Dohyeon/Study-DataStructure/blob/master/04_BinarySearchTree_with_java/D2_BS_Tree/Node.java)

**3.1.3 get 탐색**
``` java
public Value get(Node<Key, Value> n, Key k) {
        if (n == null) {
            return null;
        } // k를 발견 못함

        int t = n.getKey().compareTo(k);

        if (t > 0) {
            return get(n.getLeft(), k);
        } else if (t < 0) {
            return get(n.getRight(), k);
        } else { // k를 찾았을 경우 t==0
            return (Value) n.getValue();
        }
    }
```
위 코드는 `root` 노드(부모가 없는 노드(최상위 노드))부터 시작하여 `id`(key)를 비교한다. 찾으려는 노드의`id`가 탐색 중인 현재 노드보다 더 클경우 `right` , 작을 경우 `left`로 이동한다. 같을 경우는 그 노드의 `value`를 반환한다.

**3.1.4 put 트리에 노드 삽입**
``` java
// 트리에 노드 (k, v)를 삽입
public void put(Key k, Value v) {
    root = put(root, k, v);
}

// n를 루트로 하는 (서브)트리에 노드 (k, v)를 삽입
public Node<Key, Value> put(Node<Key, Value> n, Key k, Value v) {
    // root부터 탐색을 시작해야 하므로 n 파라미터 자리에 처음에는 n이 삽입된다.

    if (n == null) {
        return new Node<Key, Value>(k, v);
    } // 비었을 경우 새로운 노드를 연결시킴
    int t = n.getKey().compareTo(k);

    if (t > 0) { // n의 key가 새로운 노드의 key보다 클 경우 왼쪽으로
        n.setLeft(put(n.getLeft(), k, v));
    } else if (t < 0) { // n의 key가 새로운 노드의 key보다 작을 경우 오른쪽으로
        n.setRight(put(n.getRight(), k, v));
    } else { // 같을 경우는 key는 냅두고 value를 v로 대체한다.
        n.setValue(v);
    }

    // 마지막에 return n;을 해줌으로써, 삽입된 노드의 부모 노드에 대한 포인터를 반환하여 거슬러 올라가며 재 연결
    return n;
}
```
3.1.1 에서 설명했듯이 `root` 노드부터 내려가며 `k`(key) 를 비교하여 작으면 `left`, 크면 `right`로 이동하여 빈자리가 나오면 노드를 삽입한다.

**3.1.5 delete**
``` java
public void delete(Key k) {
        root = delete(root, k);
    }

public Node<Key, Value> delete(Node<Key, Value> n, Key k) {
    if (n == null) {
        return null;
    }

    int t = n.getKey().compareTo(k);
    if (t > 0) { // if (k < 노드 n의 id) 왼쪽 자식으로 이동
        n.setLeft(delete(n.getLeft(), k));
    } else if (t < 0) { // if (k > 노드 n의 id) 오른쪽 자식으로 이동
        n.setRight(delete(n.getRight(), k));
    } else { // 삭제할 노드를 발견했을 경우
        if (n.getRight() == null) { // 오른쪽이 없을 경우는 왼쪽, 둘 다 없는 경우에도 어차피 null을 반환하는 것이므로 양쪽 다 null인 경우도 이 조건문이 해결
            return n.getLeft();
        }
        if (n.getLeft() == null) { // 왼쪽이 없을 경우는 오른쪽을 반환. 반환 후 deleteMin처럼 수행
            return n.getRight();
        }

        
        Node<Key, Value> target = n;
        // 삭제할 노드 자리로 옮겨올 노드 찾아서 n이 가리키게 함
        n = min(target.getRight()); 
        n.setRight(deleteMin(target.getRight())); 
        n.setLeft(target.getLeft()); 
    }

    return n; // 부모 노드의 포인터를 갱신해야 하기 때문에 n을 반환
}
```
트리에서 노드를 삭제할 경우 세가지 경우를 생각해야한다. **첫번째 경우**는 자식 노드가 없는 경우는 그냥 그 노드를 삭제하면 된다.

**두번째 경우**는 왼쪽이든 오른쪽이든 자식이 하나만 있는 노드이다. 하나만 있을 경우는 삭제하려는 노드`p` 라고 했을때, `p` 의 자식은 `q` , `p` 의 부모는 `x` 라고 가정한다.

`p` 를 삭제하고 `q` 를 `x` 의 자식으로 붙이면 된다. `p` > `x` 라면 `q` 는 이진 검색 트리의 특성상 `q` > `x` 가 성립하기 때문이다.

**세번째 경우**는 삭제하려는 노드 `p`가 왼쪽, 오른쪽 자식을 전부 갖고 있을 때이다. 다음과 같이 노드를 정의한다:

`p` : 삭제하려는 노드
`s` : `p` 의 부모 노드
`x` : `p` 의 왼쪽 자식 노드
`y` : `p` 의 오른쪽 자식 노드

위와 같이 정의할 경우 각 노드의 키값은 다음과 같이 정의된다 : `x` < `p` < `y`. 이때 `y`의 최솟값 노드의 키값 `y_min` 은 `x` 의 키값보다 크다.

즉, `p`의 키값보다 바로 다음단계로 큰 노드의 키값은 `y_min`이란 소리이다. 따라서 (아래 그림처럼) `y_min` 을 `p` 의 위치로 옮기고 `y_min` 의 `left` , `right` 를 기존 `p` 의 `left` , `right` 와 연결시키면 된다.
![[이진검색트리 삭제 예제.png]]
+ 기존 `y_min` 은 삭제해야한다. 

전체 코드 -> [BST Code](https://github.com/1Dohyeon/Study-DataStructure/blob/master/04_BinarySearchTree_with_java/D2_BS_Tree/BST.java)

### 3.2 Union

**3.2.1 개념**

상호배타적 집합들의 트리 표현으로 배열에서 모든 집합의 "원소를 인덱스로 사용" 한다. 그리고 그 배열의 value값은 부모 노드를 가리킨다.

union 메소드를 통해서 나눠져 있는 트리를 통합하고, find메소드를 통해서 경로압축(Path Compression)을 한다.

**3.2.2 union**

높이가 상대적으로 낮은 트리의 루트노드가 높이가 상대적으로 높은 트리의 루트노드의 자식이 되도록 통합한다.

즉, rank(높이)가 높은 루트가 union 후에도 승자(합쳐진 트리의 루트)가 되도록 한다.

**3.2.3 find**

find(n) 메소드를 실행하면 n index에서 부모노드를 따라 올라가 그 **index들의 원소를 전부 트리의 루트노드로 지정**하여 경로압축(Path Compression)을 한다.

전체 코드 -> [Union AND Find Code](https://github.com/1Dohyeon/Study-DataStructure/blob/master/03_Tree_with_java/D2_Union/UnionFind.java)

---
### 3.3 AVL 트리

**3.3.1 개념**

3.1 에서의 이진탐색트리에 15, 13, 11, 9, 7 과 같이 계속 작은 수를 삽입하게 되면 왼쪽으로 치우쳐진 형태의 트리 모습을 갖게 된다. 큰수를 계속 삽입한다면 반대로 오른쪽으로 치우쳐지게 될 것이다.

이러한 문제점을 해결하기 위해서 한쪽으로 치우쳐지면 트리를 회전시키는 것이 AVL 알고리즘이다. 이런 알고리즘을 지닌 트리를 AVL 트리 자료구조라고 한다.

![[LLcase.png]]
15 -> 10 -> 7 순으로 채워져서 트리가 왼쪽으로 치우쳐진 모양이라고 가정해보자. 이때 루트 노드이 왼쪽 자식인 10, 즉 중간 노드를 기준으로 오른쪽으로 회전시킨다. 우회전을 했다고 하여 Right Rotation 이라고 부른다. 

하지만 이런 케이스는 왼쪽 자식의 왼쪽 자식 때문에 회전이 일어났으므로 Left Left Case, 즉 **LL case** 라고 부른다. 반대로 오른쪽으로 치우쳐져 있다면 위와 반대로 생각하면 쉽다.

![[LRcase.png]]
이번에는 15 -> 10 -> 12 순서로 들어왔다고 가정하자. 이때 왼쪽 자식의 오른쪽 자식에 의해서 트리 균형이 맞지 않음을 알 수 있다. 따라서 Left Right case, 줄여서 LR case 라고 한다. 오른쪽으로 치우쳐진 것을 회복하기 위해 왼쪽으로 먼저 회전시키고, 오른쪽으로 다시 회전시켜 균형을 맞춘다.

**3.3.2 Right Rotation**
``` java
	private Node<Key, Value> rotateRight(Node<Key, Value> n) {
        Node<Key, Value> x = n.left;
        n.left = x.right;
        x.right = n;
        
        // 높이 갱신
        n.height = tallerHeight(height(n.left), height(n.right)) + 1;
        x.height = tallerHeight(height(x.left), height(x.right)) + 1;

        return x;
    }
```
![[LLcase 코드대로 실행 예제.png]]

3.3.1 에서 예를 든 것처럼 15 -> 10 -> 7 순서로 숫자가 들어왔다고 가정. 이때 15가 n 노드가 된다. **n 노드에서 좌우 불균형을 발견**했기 때문이다. 다음은 코드대로 실행하면 된다.

n의 왼쪽 자식을 x라고 두고, n의 왼쪽 자식 위치로 x의 오른쪽 자식을 옮긴다. x의 오른쪽 자식은 없으므로 null. 그다음 x의 오른쪽 자식에 n을 위치시키면 마치 트리가 회전한 것처럼 보인다.

왼쪽으로 회전은 위와 반대로 작동한다. **tallerHeight()** 메서드는 n과 x의 위치가 변했기 때문에 높이를 갱신해준 것이다. 왼쪽 오른쪽 자식의 높이차이가 2이상 나면 회전을 해야하기 때문이다.

전체 코드 -> [AVL](https://github.com/1Dohyeon/Study-DataStructure/blob/master/03_Tree_with_java/D3_AVL/AVL.java)
