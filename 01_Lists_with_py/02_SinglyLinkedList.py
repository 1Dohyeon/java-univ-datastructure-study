class Node:
    def __init__(self, newItem, nextNode):
        self.item = newItem
        self.next = nextNode

    def getItem(self): return self.item
    def getNext(self): return self.next
    def setItem(self, newItem): self.item = newItem
    def setNext(self, nextNode): self.next = nextNode

class SList:
    def __init__(self):
        self.size = 0
        self.head = Node(None, None)

    def isEmpty(self):
        '''list가 empty이면 True를 반환함.'''
        return self.size==0

    def search(self, target):
        '''target이라는 원소를 가진 index를 return함.'''
        p = self.head

        for k in range(self.size):
            if target==p.getItem(): return k
            p = p.getNext()

        return -1
    
    def insertFront(self, newItem):
        '''head Node에 newItem을 삽입'''
        self.head = Node(newItem, self.head)
        self.size += 1

    def insertAfter(self, newItem, p=Node):     # 'Node()'라고 할 경우 class를 새롭게 지정시키게 되기에 주의!
        '''p의 다음 노드에 새로운 newItem을 가진 노드를 연결, 이때 파라미터 p의 type이 Node여야함.'''
        p.setNext(Node(newItem, p.getNext()))
        self.size += 1

    def deleteFront(self):
        '''원래 head Node에 있던 원소를 return하며, head를 head의 Next Node로 바꾸면서 기존 head Node를 삭제함.'''
        try:
            t = self.head
            self.head = self.head.getNext()
            self.size -= 1
            return t.getItem()
        except:
            raise ValueError
        
    def deleteAfter(self, p=Node):      # 'Node()'라고 할 경우 class를 새롭게 지정시키게 되기에 주의!
        '''p의 Next Node(삭제할 노드)를 return하며, p의 next Node를 t의 next Node와 연결함으로써 사이에 있는 노드를
         제거시킴. 이때 파라미터 p의 type이 Node여야함.'''
        try:
            t = p.getNext()
            p.setNext(t.getNext())
            self.size -= 1
            return t.getItem()
        except:
            raise ValueError
        
SL = SList()
SL.insertFront("Apple")
SL.insertFront("Orange")
SL.insertFront("Melon")
print(SL.search("Apple"))
print(SL.search("Orange"))
print(SL.search("Melon"))

SL.insertAfter("Mango", SL.head)
print(SL.search("Mango"))
print(SL.deleteAfter(SL.head))
print(SL.deleteFront())
