class Node:
    def __init__(self, newItem, prevNode, nextNode):
        self.item = newItem
        self.prev = prevNode
        self.next = nextNode

    def getItem(self): return self.item
    def getPrev(self): return self.prev
    def getNext(self): return self.next
    def setItem(self, newItem): self.item = newItem
    def setPrev(self, prevNode): self.prev = prevNode
    def setNext(self, nextNode): self.next = nextNode

class DList:
    def __init__(self): # 생성자
        self.size = 0
        self.head = Node(None, None, None)
        self.tail = Node(None, self.head, None) # tail의 이전 노드를 head로
        self.head.setNext(self.tail)            # head의 다음 노드를 tail로

    def search(self, target):
        '''target이라는 원소를 가진 index를 return함.'''
        p = self.head.getNext()   # head는 비어있으므로 head의 다음 노드부터 출발

        for k in range(self.size):
            if target==p.getItem(): return k
            p = p.getNext()

        return -1

    def insertBefore(self, newItem, p=Node):
        '''p가 가리키는 노드 앞에 삽입'''
        t = p.getPrev() 
        newNode = Node(newItem, t, p)
        t.setNext(newNode)
        p.setPrev(newNode)
        self.size += 1

    def insertAfter(self, newItem, p=Node):
        '''p가 가리키는 노드 뒤에 삽입'''
        t = p.getNext()
        newNode = Node(newItem, p, t)
        t.setPrev(newNode)
        p.setNext(newNode)
        self.size += 1 

    def delete(self, x=Node):
        '''x 노드를 삭제하는 함수'''
        try:
            target = x
            p = x.getPrev()
            n = x.getNext()
            p.setNext(n)
            n.setPrev(p)
            self.size -= 1
            return target
        except:
            raise ValueError

DL = DList()
DL.insertAfter("Apple", DL.head)
DL.insertBefore("Mango", DL.tail)
DL.insertAfter("Melon", DL.head)
print(DL.search("Melon"))
print(DL.search("Apple"))
print(DL.search("Mango"))
DL.delete(DL.tail.getPrev())
print(DL.search("Melon"))
print(DL.search("Apple"))
print(DL.search("Mango"))