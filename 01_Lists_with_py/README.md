### 1.1 Dynamic Array

**1.1.1 개념**

Dynamic Array(동적 배열) 은 프로그래밍이 실행되는 동안 할당된 메모리 공간을 확장 또는 축소하는 배열이다. 기본 배열은 공간을 전부 차지하면 직접 공간을 늘려줘야했다. 

하지만 동적 배열은 `resize()` 메서드에 의해서 동적으로 크기를 조절한다. 배열 크기와 원소의 갯수가 같아진다면 2배 증가시키고, 원소의 갯수가 배열 크기의 1/4 된다면 배열 크기를 1/2로 줄여준다.

동적 배열은 배열을 이용한 자료구조이기 때문에 원소에 접근할 때는 O(1)의 시간복잡도를 가진다. 하지만 배열에 원소를 삽입할 때나 삭제할 때, 중간에 있는 원소를 건들이게 될 경우 원소들의 위치를 밀거나 당겨야함으로 최악의 경우 O(N)의 시간복잡도를 가지게 된다.

또한 크기를 동적으로 바꿀 때는 O(N)의 시간이 걸리게 된다.(1.1.2 참고)

**1.1.2 resize()**
``` python
def resize(self, newSize):
        tempArr = [None for _ in range(newSize)]

        for i in range(self.size):
            tempArr[i] = self.arr[i]
        
        self.arr = tempArr
```
위 코드는 파이썬으로 작성한 예시 코드이다. 우선 `newSize` 라는 새로운 배열의 크기를 매개변수로 받는다.

위 변수의 크기를 갖는 임시 배열 `tempArr` 을 생성하여 기존 배열의 원소들을 복사해준다. 이제 기존 배열은 임시 배열의 값으로 업데이트 하면 된다.

원소를 삭제할 때 원소의 크기가 배열의 1/4 이하인지 확인하고, 이하가 맞다면 `newSize` 는 현재 `size` 의 절반 값을 갖게 된다.

또한 원소를 삽입하는 과정에서 `size` 가 배열의 크기와 같아진다면 `newSize` 의 값은 `size`의 두배가 된다.


코드 전체 : [Code](https://github.com/1Dohyeon/Study-DataStructure/blob/master/01_Lists_with_py/01_DynamicArray.py)

---
### 1.2 (Singly)Linked List

**1.2.1 개념**

(Singly)Linked List(단일 연결 리스트)는 값을 배열에 담지 않는다. 동적 메모리 할당을 받아서 노드를 저장하고, 노드는 레퍼런스를 이용하여 다음 노드를 가리키도록 만들어서 하나의 줄로 연결된 것처럼 보이게 하는 리스트이다.

즉, (아래 그림처럼)노드 객체에 데이터를 담고, 다른 데이터를 가진 노드 객체와 연결하는 방식이다.
![[linkedList노드설명이미지.png]]

**1.2.2 class Node**
``` python
class Node:
    def __init__(self, newItem, nextNode):
        self.item = newItem
        self.next = nextNode

    def getItem(self): return self.item
    def getNext(self): return self.next
    def setItem(self, newItem): self.item = newItem
    def setNext(self, nextNode): self.next = nextNode
```
원소를 담을 필드 `item` 과 다음 노드를 가리키는 `next` 필드를 가진 객체 클래스이다.

**1.2.3 Search**
``` python
def search(self, target):
    '''target이라는 원소를 가진 index를 return함.'''
    p = self.head

    for k in range(self.size):
        if target==p.getItem(): return k
        p = p.getNext()

	return -1
```
위 코드를 보면 알 수 있듯이 원소에 접근할 때, 첫번째 노드부터 `next`를 따라서 리스트를 탐색하는 것을 알 수 있다. 그렇기에 동적 배열과는 달리 시간복잡도가 O(N)이 나옴을 알 수 있다.

**1.2.4 insertFront**
``` python
def insertFront(self, newItem):
	'''head Node에 newItem을 삽입'''
	self.head = Node(newItem, self.head)
	self.size += 1
```
리스트의 맨 처음 위치에 원소를 삽입할 때는 동적배열과는 달리 원소를 모두 뒤로 밀 필요가 없다. 따라서 이 경우에 시간 복잡도가 O(1)이 나온다.


코드 전체 : [Code](https://github.com/1Dohyeon/Study-DataStructure/blob/master/01_Lists_with_py/02_SinglyLinkedList.py)
