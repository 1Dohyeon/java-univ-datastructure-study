class Node:
    def __init__(self, newItem, nextNode):
        self.item = newItem
        self.next = nextNode

    def getItem(self): return self.item
    def getNext(self): return self.next
    def setItem(self, newItem): self.item = newItem
    def setNext(self, nextNode): self.next = nextNode

class CList:
    def __init__(self):
        self.last = None
        self.size = 0

    def search(self, target):
        '''target이라는 원소를 가진 index를 return함.'''
        p = self.last.getNext()   # head는 비어있으므로 head의 다음 노드부터 출발

        for k in range(self.size):
            if target==p.getItem(): return k
            p = p.getNext()

        return -1

    def insert(self, newItem):
        '''last 다음 노드에 새 노드 삽입'''
        newNode = Node(newItem, None)
        
        if(self.last is None): # last가 empty일 경우
            newNode.setNext(newNode)
            self.last = newNode
        else:
            newNode.setNext(self.last.getNext())
            self.last.setNext(newNode)

        self.size += 1

    def delete(self):
        try:
            x = self.last.getNext()
            if x is self.last:      # last 노드 하나만 있을 경우
                self.last = None
            else:
                self.last.setNext(x.getNext())
            self.size -= 1
        except:
            raise ValueError
        
CL = CList()
CL.insert("Apple")
CL.insert("Mango")
CL.insert("Melon")
print(CL.search("Apple"))
print(CL.search("Mango"))
print(CL.search("Melon"))
CL.delete()
print(CL.search("Apple"))
print(CL.search("Mango"))
print(CL.search("Melon"))
CL.delete()
CL.delete()
CL.delete()