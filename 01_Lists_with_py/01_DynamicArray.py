class ArrayList:
    def __init__(self):
        self.arr = [None]   # 리스트의 항목들을 저장할 배열(최초로 1개의 원소를 가진 배열 생성)
        self.size = 0       # 리스트 항목의 수(배열의 크기)
    
    def isEmpty(self):
        '''list가 empty이면 True를 반환함.'''
        return self.size==0

    def peek(self, k):
        '''k번째 항목을 return'''
        try:
            return self.arr[k]
        except:
            raise ValueError
            
    def insertLast(self, newItem):
        '''배열 마지막에 newItem을 삽입함.'''
        if self.size==len(self.arr):
            self.resize(2*len(self.arr))
        self.arr[self.size] = newItem
        self.size += 1

    def insert(self, newItem, k):
        '''배열 k번째에 newItem을 삽입함.'''
        if self.size==len(self.arr):
            self.resize(2*len(self.arr))

        for i in range(self.size-1, k-1, -1):
            self.arr[i+1] = self.arr[i]

        self.arr[k] = newItem
        self.size += 1

    def resize(self, newSize):
        '''배열의 크기를 newSize로 바꿔줌.
        배열 크기와 원소의 갯수가 같아진다면 2배 증가시키고,
        원소의 갯수가 배열 크기의 1/4 된다면 배열 크기를 1/2로 줄여줌.'''
        tempArr = [None for _ in range(newSize)]

        for i in range(self.size):
            tempArr[i] = self.arr[i]
        
        self.arr = tempArr

    def delete(self, k):
        '''배열의 k번째에 위치한 원소를 삭제하고 return함.'''
        try:
            item = self.arr[k]

            for i in range(k, self.size):
                self.arr[i] = self.arr[i+1]

            self.size -= 1

            if self.size>0 and self.size==len(self.arr)/4:
                self.resize(len(self.arr)/2)

            return item
        except:
            raise ValueError
        
DA = ArrayList()
DA.insertLast("Apple")
DA.insertLast("Orange")
DA.insert("Grape", 1)
DA.insert("Lemon", 0)

for i in range(DA.size):
    print(DA.peek(i))