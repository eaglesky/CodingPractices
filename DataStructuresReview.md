# Review of data structures

### 1. Array

#### Fixed size:

```
// C/C++
int arr[5];
int* arr2 = new int[5];
```

```
//Java
int[] arr = {3, 5, 7};
```

#### Dynamic size:

(How does it resize? Proof of constant amortized time can be found [here](http://stackoverflow.com/questions/6550509/amortized-analysis-of-stdvector-insertion))
```
//C++ STL vector
//The capacity will double when full, but it never shrinks unless shrink_to_fit() is called
std::vector<std::string> words4(5, "Mo");
std::vector<int> v = {0, 1, 2, 3, 4, 5};
```

```
//Java
//Growth policy is generally similar to C++ STL vector. However how it shrinks still remains unclear
java.util.ArrayList<Integer> arr = new java.util.ArrayList<Integer>();
```

### 2. Stack

```
// C++ STL stack
// The internal container is deque by default [1] 
// No random access or iterator
#include <stack>
using namespace std;
int main() {
    stack<int> st;
    // push three elements into the stack
    st.push(1);
    st.push(2);
    st.push(3);
    // pop and print two elements from the stack
    cout << st.top() << ' ';
    st.pop();
    cout << st.top() << ' ';
    st.pop();
    // modify top element
    st.top() = 77;
    // push two new elements
    st.push(4);
    st.push(5);
    // pop one element without processing it
    st.pop();
    // pop and print remaining elements
    while (!st.empty()) {
        cout << st.top() << ' ';
        st.pop();
    }
    cout << endl;
}
```
[Comparison of deque and vector](http://www.gotw.ca/gotw/054.htm)

```
// Java Stack
// Extends Vector, which is similar to ArrayList,
// but they still differ in some ways [5].
import java.util.Stack;
public class TestStack {
	public static void main (String[] args) {
		Stack<Integer> st = new Stack<Integer>();
		st.push(1);
		st.push(3);
		st.push(5);
		st.push(7);

		while (!st.empty()) {
			System.out.println(st.peek());
			st.pop();
		}
	}
}
```
However, according to Java docs [6]: *A more complete and consistent set of LIFO stack operations is provided by the Deque interface and its implementations, which should be used in preference to this class*. 
```
import java.util.*;
public class TestDequeStack {
	public static void main (String[] args) {
		Deque<Integer> stack = new ArrayDeque<Integer>();
		stack.push(1);
		stack.push(3);
		stack.push(5);
		stack.push(7);
	    while (stack.peek() != null) {
	    	int curElement = stack.pop();
	    	System.out.println(curElement);
	    }
	}
}
```

### 3. Queue

```
// C++ STL queue
#include <iostream>
#include <queue>
#include <string>
using namespace std;
int main() {
	queue<string> q;
	// insert three elements into the queue
	q.push("These ");
	q.push("are ");
	q.push("more than ");
	// read and print two elements from the queue
	cout << q.front();
	q.pop();
	cout << q.front();
	q.pop();
	// insert two new elements
	q.push("four ");
	q.push("words!");
	// skip one element
	q.pop();
	// read and print two elements
	cout << q.front();
	q.pop();
	cout << q.front() << endl;
	q.pop();
	// print number of elements in the queue
	cout << "number of elements in the queue: " << q.size()
		 << endl;
}
```

```
// Java Queue interface
// Result: 2 4 6 8
import java.util.*;
public class TestQueue {
	public static void main (String[] args) {
		Queue<Integer> q = new ArrayDeque<Integer>();

		// Can also use LinkedList as the implementation, but slower, 
		// according to Java docs.
		//Queue<Integer> q = new LinkedList<Integer>();
		q.offer(2);
		q.offer(4);
		q.offer(6);
		q.offer(8);
		while (q.peek() != null) {
			int curInteger = q.poll();
			System.out.println(curInteger);
		}
	}
}
```

ArrayDeque is implemented as a circular array, according to the source code [7].


Reference:

1. *The C++ Standard Library -- A Tutorial and Reference*, Nicolai
2. *STL source code analysis*, Hou Jie
3. [http://en.cppreference.com/w](http://en.cppreference.com/w)
4. *Head first Java*
5. [http://beginnersbook.com/2013/12/difference-between-arraylist-and-vector-in-java/](http://beginnersbook.com/2013/12/difference-between-arraylist-and-vector-in-java/)
6. [https://docs.oracle.com/javase/8/docs/api/](https://docs.oracle.com/javase/8/docs/api/)
7. [http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/ArrayDeque.java](http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/ArrayDeque.java)

