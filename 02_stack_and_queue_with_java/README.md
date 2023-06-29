# 01. Array Stack
처음에는 크기가 1인 배열과 top = -1 을 가진 객체를 생성하고, Dynamic Array처럼 overflow가 발생하면 resize()메소드를 이용하여 배열의 크기를 2배 확장해줍니다. 
이때 시간복잡도는 O(N) 이고, 삽입/삭제 시에는 O(1)이라는 시간복잡도를 가집니다.
### peek() and top
Stack은 마지막에 위치한 원소가 중요하므로 top이 마지막 원소만을 가리키게 하고 peek 메소드를 이용하여 배열의 마지막 원소를 return할 수 있습니다.
# 02. List Stack
처음 삽입된 원소가 head고 삽입된 원소들은 계속해서 head의 next가 된다고 생각할 수 있지만, Linked List특성상 head에 접근하기 더 쉬우므로 stack의 top원소를 head로 둡니다. 
모두 탐색하는 것이 아닌 top에만 접근하면 되므로 삽입/삭제 시에 시간복잡도는 O(1)임을 알 수 있습니다.
### push()
SinglyLinkedList를 구현했을 때의 "insertFront" 메소드처럼 "push"메소드를 이용하여 head에 새로운 원소를 담고 원래 head를 next Node로 연결합니다.
# 03. ArrayQueue
큐를 Array로 나타내기 위해서는 원형배열 방식을 이용합니다.
N이 배열의 크기, front는 가장 먼저 들어온 원소, rear는 가장 마지막에 들어온 원소라고 할 때, rear = (rear+1) % N 이라는 값을 가지게 됩니다.
그리고 마지막 item을 삭제해도 rear는 삭제된 item을 가리키기 때문에, 원소들이 삭제될 때마다 큐가 empty가 되는지 검사하고, empty가 되면 front(가장 앞 원소)=rear=0으로 만들어야합니다.
마지막으로 배열로 구현한 큐이기 때문에 resize()메소드를 이용하여 overflow가 발생하면 배열의 크기를 2배로 확장합니다.
### add()
삽입을 할 경우 마지막에 들어온 원소 rear에 1을 더해야하므로, rear = "(rear+1) % q.length"값으로 갱신시켜야합니다.
"% q.length"를 하는 이유는 원형배열이기에 다시 0 index부터 채워야할 경우도 있기 때문입니다.
### remove()
add()와 마찬가지지만 remove는 front값을 % q.length를 통해 갱신시켜야합니다.
front = "(front+1) % q.length"로 갱신되고 q[front]에 null을 적용시키며 값을 삭제합니다.
# 04. ListQueue
List Stack과는 반대로 head가 곧 front가 됩니다. 
먼저 들어온 원소가 front노드가 되고 그 뒤로 계속 들어온 원소들은 next 노드가 되며 연결됩니다.
### remove()
head를 삭제하는 것이므로 SinglyLinkedList의 deleteFront처럼 head가 head의 next 노드 값을 갖게 하므로써 기존 head값을 삭제 후 return 합니다.

