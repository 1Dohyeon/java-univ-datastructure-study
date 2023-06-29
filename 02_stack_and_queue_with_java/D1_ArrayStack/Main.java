package D1_ArrayStack;

public class Main {
	
	public static void main(String[] args) {
		ArrayStack<String> s = new ArrayStack<String>();
		
		s.push("apple");
		s.push("banana");
		s.push("pear");
		System.out.println(s.peek());
		s.pop();
		System.out.println(s.peek());
		s.pop();
		System.out.println(s.peek());
		s.pop();
		System.out.println(s.peek());
	}

}
