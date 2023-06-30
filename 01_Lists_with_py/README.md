# 01. Dynamic Array
동적 배열은 프로그램이 실행되는 동안 할당된 메모리 공간을 확장 또는 축소합니다.
빈자리가 없어 새 항목을 삽입할 수 없는 상황인 "Overflow"가 발생한다면 배열의 크기를 2배로 확장하고, 원소의 갯수가 배열의 크기의 1/4이라면 배열의 크기를 1/2로 축소합니다.

A dynamic array expands or reduces the allocated memory space while a program is running.
If "Overflow" occurs that is a situation in which no new items are inserted because there are no spaces, double the size of the array, and if the number of elements is 1/4 of the size of the array, reduce the size of the array by 1/2.
### resize
"resize"가 바로 위에서 설명한 배열의 크기를 확대 및 축소하는 메소드입니다.
새로운 배열의 크기로 "newSize"를 파라미터로 받고, "newSize"만큼의 메모리 공간을 가진 임시 배열을 생성하고 기존 배열의 원소를 복사합니다. 복사를 할 때 반복문을 이용하여 원소 하나하나를 복사하기에 "resize"의 시간복잡도는 "O(N)"임을 알 수 있습니다.

"resize" is the method of enlarging and shrinking the size of the array described above.
Accept "newSize" as a parameter for the size of the new array, create a temporary array with memory space as much as "newSize", and copy elements from the existing array. When copying, you can see that the time complexity of "resize" is "O(N)" because you copy each element using a repetition statement.
### insert, delete
이 메소드들은 배열 중간에 원소를 삽입하거나 중간에 있는 원소를 삭제할 경우에 원소들을 한 칸씩 뒤로 밀거나 앞으로 댕깁니다. 그렇기에 "insert"와 "delete"의 시간복잡도는 "O(N)"임을 알 수 있습니다.

These methods push back or pull back the elements one by one when you insert elements in the middle of the array or delete elements in the middle. Therefore, You can see that the time complexity of "insert" and "delete" is "O(N)."

# 02. Singly Linked List
동적 메모리 할당을 받아 "노드(Node)"를 저장하고, 노드는 레퍼런스를 이용하여 다음 노드를 가리키도록 만들어서 각 노드들은 한 줄로 연결됩니다.
"Dynamic Array"와 달리 삽입/삭제 시에 각 원소들을 이동시킬 필요도 없고, 빈 공간이 존재하지 않습니다.
하지만 항목을 탐색하기 위해서 항상 첫 노드부터 원하는 노드를 찾을 때까지 차례로 방문하는 "순차탐색(Sequential Search)"를 해야함으로 시간이 걸릴 수 있습니다.
### search
이 메소드는 반복문을 이용하여 첫 노드부터 다음 노드까지 계속 이동하여 "target"을 탐색합니다. 
그렇기에 최악의 경우 시간복잡도는 "O(N)"임을 알 수 있습니다.
### insertFront, deleteFront
"Linked List"의 앞부분에 노드를 삽입/삭제하는 메소드로 "head"와의 연결만 주의하면 되기에 시간복잡도는 "O(1)"임을 알 수 있습니다.
### insertAfter, deleteAfter
주어진 노드 p가 가리키는 다음 노드에 삽입하거나 다음 노드를 삭제하는 메소드로, "insertAfter"는 p의 다음 노드에 새로운 노드를 만들고 그 노드를 p의 원래 다음 노드와 연결시키면 됩니다.
"deleteAfter"는 p의 다음 노드를 t라고 하고, p의 다음 노드를 t의 다음 노드와 연결한다면, 그 사이에 있는 원래 p의 다음 노드는 삭제가 됩니다.
위의 과정을 보면 따로 List를 탐색하지 않기에 시간복잡도는 "O(1)"임을 알 수 있습니다.

# 03. Doubly Linked List
next로만 연결된 단일연결리스트와 달리 이중연결리스트는 이전 노드를 가리킵니다.

### Node clss
Node class에서 next 뿐만 아니라 previous(이전 노드를 가리킴)도 생성해줍니다.

### tail
head뿐만 아니라, 이중연결리스트의 마지막 노드인 tail도 생성해줍니다.
head의 다음 노드는 tail, tail의 이전 노드를 head와 연결시키는 것이 기본값입니다.
두 항목은 실제로 값을 저장하지는 않고, 그 사이에 노드를 연결함으로써 사이에 항목들을 저장합니다.

### insertBefore, insertAfter
이중연결리스트는 previous와도 연결되어 있기때문에 노드 뒤 뿐만 아니라 앞도 연결할 수 있습니다.
각각 상수 개의 레퍼런스 만을 갱신하므로 시간복잡도는 O(1)입니다. 

### delete(x)
x의 뒤 노드를 삭제하는 것이 아닌 양쪽으로 연결되어있기에 x를 바로 삭제할 수 있습니다.
x의 이전 노드와 x의 다음 노드를 서로 연결함으로써 사이에 있는 x를 제거합니다.
각각 상수 개의 레퍼런스 만을 갱신하므로 시간복잡도는 O(1)입니다. 

# 04. Circular Linked List
원형연결리스트는 단순연결리스트와 달리 첫노드 뿐만 아니라 마지막 노드까지 O(1)의 시간에 탐색할 수 있습니다.
무한 루프가 발생할 수 있으므로 주의해서 사용해야합니다.

### Node class
head가 아닌 last(마지막 노드)를 생성합니다.

### insert
새로운 항목을 리스트의 첫 노드로 삽입하는 함수입니다.
last가 비어있지 않은 경우 원래 연결리스트 삽입처럼 하면 되고,
if(last==null)조건을 보면 알 수 있듯이 노드가 없을 경우 새로운 항목은 자기 자신을 연결합니다.
즉, last의 다음 노드는 last입니다.

### delete 
리스트의 첫 노드를 삭제합니다.
last의 다음 노드를 삭제하는 것이며, last 한개만 있을 경우 last = null을 해줍니다.