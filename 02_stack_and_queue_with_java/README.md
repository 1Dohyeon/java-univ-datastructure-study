### 2.1 Stack (Array)

**2.1.1 개념**

Stack(스택) 은 직역하면 **쌓아 올린** 것이다. 데이터들을 아날로그인 물컵에 쌓아올린다고 생각해보자. 가장 마지막에 넣은 데이터가 컵의 가장 위쪽에 있을 것이다. 또한 데이터를 빼낼 때는 안쪽부터가 아니라 컵 가장 위에 있는 데이터, 즉 마지막으로 들어온 데이터부터 쉽게 빼낼 수 있을 것이다.

스택은 이와 같은 개념으로 [1.1 Dynamic Array](https://github.com/1Dohyeon/Study-DataStructure/blob/master/01_Lists_with_py/01_DynamicArray.py)에서 배운 동적 배열을 이용하여 설명하자면 데이터를 동적배열에 하나씩 담을 때는 배열의 가장 마지막에 원소가 담기게 될 것이다. 또한 배열에서 데이터를 삭제할 때는 배열의 가장 마지막 원소가 삭제되게 된다.

**2.1.2 peek()**
``` java
public class ArrayStack<E> {
	private E s[];
	private int topIndex;
	
	public ArrayStack() {
		s = (E[]) new Object[1];	
		topIndex = -1;
	}
	
	public boolean isEmpty() {
		return (topIndex == -1);
	}
	
	public E peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return s[topIndex];
	}
}
```
배열의 인덱스는 0부터 시작하기 때문에 `topIndex` 의 초기값은 -1 로 지정하였다. `peek()` 메서드는 배열의 마지막 원소, 즉 가장 최근에 들어온 데이터를 반환한다.

**2.1.3 push()**
``` java
public int size() {
	return topIndex + 1;
}

public void push(E newItem) {
	if(size()==s.length) {
		resize(2*s.length);
	}
		
	s[size()] = newItem;
	topIndex++;
}
```
> resize() 메서드는 [1.1 Dynamic Array](https://github.com/1Dohyeon/Study-DataStructure/blob/master/01_Lists_with_py/01_DynamicArray.py) 참고

`size()` 메서드는 배열의 크기를 그대로 반환해야 하기 때문에 `topIndex` 에 +1 한 값을 반환한다. 이 `size()` 값이 배열의 길이와 같다면 배열의 길이를 증가시킨다.

그 후 `size()` 값을 배열의 인덱스로 주게 되면 가장 최근에 들어온 값보다 한층 더 위에를 가리키게 된다. 이곳에 새로운 원소를 담아주면 된다. 추가로 데이터가 늘어났으니 `topIndex`의 값을 늘려준다.

**2.1.4 pop()**
``` java
public E pop() {
	if(isEmpty()) {
		throw new EmptyStackException();
	}
	
	E item = s[topIndex];
	s[topIndex] = null;
	topIndex--;
	
	if(size()>0 && size()==s.length/4) {
		resize(s.length/2);
	}
	
	return item;
}
```
`pop()` 메서드는 가장 최근에 들어온 데이터를 삭제하고 그 값을 그대로 반환한다. 따라서 삭제하기 전에 `item` 이라는 변수에 배열의 가장 마지막 원소를 저장한다. 

`topIndex` 를 인덱스로 하는 배열의 원소를 지워주고, `topIndex`의 값을 줄인다. 이때, `size`의 길이가 배열 길이의 1/4 크기가 된다면 배열의 길이를 줄인다. 그 후 저장해둔 `item` 을 반환한다.

코드 전체 : [Code](https://github.com/1Dohyeon/Study-DataStructure/blob/master/02_stack_and_queue_with_java/D1_ArrayStack/ArrayStack.java)

### 2.2 Queue (LinkedList)

**2.2.1 개념**

Queue(큐) 는 직역과 같이 **줄**의 의미를 가지고 있다. 가장 먼저 줄을 섰던 사람이 먼저 들어갈 수 있는 것처럼 큐에 먼저 들어온 데이터가 가장 먼저 나갈 수 있다. 스택과는 반대의 개념이라고 생각하면 쉽다.

큐를 [2.1 Array Stack](https://github.com/1Dohyeon/Study-DataStructure/blob/master/02_stack_and_queue_with_java/D1_ArrayStack/ArrayStack.java) 처럼 동적 배열로 만들게 된다면 배열의 0번째 인덱스, 즉 첫번째 원소를 삭제하게 되면 나머지 뒤의 원소를 다 당겨와야 하기 때문에 비효율적이다. 물론, 삭제된 인덱스 값의 +1한 값을 가지는 변수를 통해서 앞으로 안당겨오게 할 수도 있긴 하다. 

하지만 나는 노드의 head를 삭제 후에 갱신하는 방식으로 [1.2 Singly Linked List](https://github.com/1Dohyeon/Study-DataStructure/blob/master/01_Lists_with_py/02_SinglyLinkedList.py) 를 통해서 구성하였다.

**2.2.2 생성자**
``` java
public class ListQueue<E extends Comparable<E>> {
    private Node<E> head, last;
    private int size;

    // 큐 생성자
    public ListQueue() {
        head = last = null;
        size = 0;
    }
	
	// 큐에 있는 항목의 수를 리턴
    public int size() {
        return size;
    }

    // 큐가 empty면 true를 리턴
    public boolean isEmpty() {
        return (size == 0);
    }
    // empty가 되면 front == rear
```
노드는 원소를 담을 수 있고, 다음 노드를 가리킬 수 있다. [1.2 Singly Linked List](https://github.com/1Dohyeon/Study-DataStructure/blob/master/01_Lists_with_py/02_SinglyLinkedList.py) 에서는 `head` 만 따로 설정했지만, 삽입할 때 시간복잡도를 줄이기 위하여 `last` 를 통해서 리스트의 마지막 노드도 저장할 수 있도록 하였다.

**2.2.3 add**
``` java
    // 큐 add 연산
    public void add(E newItem) {
        Node<E> newNode = new Node<E>(newItem, null);

        if (isEmpty()) {
            head = newNode;
        } else {
            last.setNext(newNode);
        }

        last = newNode;
        size++;
    }
```
우선 삽입하고 싶은 데이터를 가지는 노드를 생성한다. 큐가 비어있다면 `head` 가 그 노드가 되는 것이고, 아니라면 마지막 노드를 가리키는 `last` 의 다음 노드에 새로운 노드를 연결한다. 그 후 `last` 는 다시 마지막 노드를 가리키도록 이동한다.

**2.2.4 remove**
``` java
// 큐 remove 연산
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E frontItem = head.getItem();
        head = head.getNext();

        size--;
        if (isEmpty()) {
            last = null;
        }

        return frontItem;
    }
```
첫번째 노드의 원소를 반환하기 위해서 `frontItem` 이라는 변수에 저장해두고, `head` 는 기존 `head` 의 다음 노드를 가리키게 함으로써 기존 `head` 를 삭제한다.

코드 전체 : [Code](https://github.com/1Dohyeon/Study-DataStructure/blob/master/02_stack_and_queue_with_java/D4_ListQueue/ListQueue.java)
