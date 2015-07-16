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

### 4. Set and Map

```
// C++ STL set and map
// Internal data structure: red-black trees
// Search, removal, and insertion operations have logarithmic complexity(log n)
#include <map>
#include <set>

int main() {
	std::set<int> set;
	std::map<std::string, char> gradeList;
	...
}
```

```
// Java sets: The common implementation is TreeSet, and the other two implementations
// are HashSet and LinkedHashSet
// The internal structure of TreeSet is also Red-Black tree [8].
import java.util.*;

public class TestSet {
	public static void main (String[] args) {
		Set<Integer> testSet = new TreeSet<Integer>();
		//Set<Integer> testSet = new HashSet<Integer>();
		//Set<Integer> testSet = new LinkedHashSet<Integer>();

		testSet.add(1);
		testSet.add(3);
		testSet.add(5);
		testSet.add(7);
		boolean checkDuplicate = testSet.add(5);
		if (!checkDuplicate)
			System.out.println("No adding!");

		System.out.println("Size now = " + testSet.size());

		if (testSet.contains(7)) {
			System.out.println("7 is in the set!");
		} else {
			System.out.println("7 is not in the set!");
		}

		testSet.remove(5);
		if (testSet.contains(5)) {
			System.out.println("5 is in the set!");
		} else {
			System.out.println("5 is not in the set!");
		}

		for (int i : testSet)
			System.out.println(i);
	}
}
```

```
// Java map. The internal structure and performance are similar to those of the corresponding Sets.
import java.util.*;

public class TestMap {
	public static void main (String[] args) {
		Map<String, Integer> testMap = new TreeMap<String, Integer>();
		//Map<String, Integer> testMap = new HashMap<String, Integer>();
		//Map<String, Integer> testMap = new LinkedHashMap<String, Integer>();
		//Map<String, Integer> testMap = new Hashtable<String, Integer>();

		testMap.put("Allen", 1);
		testMap.put("Jack", 3);
		testMap.put("Tom", 5);
		testMap.put("Tom", 7);
		int curVal = testMap.get("Tom");
		System.out.println("Tom = " + curVal);
		System.out.println("Current Size = " + testMap.size());
		testMap.remove("Tom");
		boolean checkContainKey = testMap.containsKey("Tom");
		System.out.println("Contains Tom ? " + checkContainKey);
		System.out.println("Tom value = " + testMap.get("Tom"));

		for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

	}
}
```

### 5. HashSet and HashMap

```
// C++ STL unordered_set and unordered_map
// Internal data structure: hash table, each bucket of which is associated with a linked list [1]
// Amortized constant time for insertions, deletions, and element search
// Iteration takes longer time, linear to the number of buckets and elements
#include <unordered_set>
#include <unordered_map>
int main() {
	std::unordered_set<std::string> coll;
	std::unordered_map<std::string, char> grade_list;
}
```

The internal structure of Java HashMap and HashSet is almost the same as STL unordered_map and unordered_set [9]. 

The internal structure of Java LinkedHashMap and LinkedHashSet can be found at [10].  According to the docs of LinkedHashMap:
*Like HashMap, it provides constant-time performance for the basic operations (add, contains and remove), assuming the hash function disperses elements properly among the buckets. Performance is likely to be just slightly below that of HashMap, due to the added expense of maintaining the linked list, with one exception: Iteration over the collection-views of a LinkedHashMap requires time proportional to the size of the map, regardless of its capacity. Iteration over a HashMap is likely to be more expensive, requiring time proportional to its capacity*

Difference between Java HashMap and Hashtable can be found at [11].

Java set collections are all based on corresponding map collections, according to the constructor of HashSet [12]. 

### Reference

1. *The C++ Standard Library -- A Tutorial and Reference*, Nicolai
2. *STL source code analysis*, Hou Jie
3. [http://en.cppreference.com/w](http://en.cppreference.com/w)
4. *Head first Java*
5. [http://beginnersbook.com/2013/12/difference-between-arraylist-and-vector-in-java/](http://beginnersbook.com/2013/12/difference-between-arraylist-and-vector-in-java/)
6. [https://docs.oracle.com/javase/8/docs/api/](https://docs.oracle.com/javase/8/docs/api/)
7. [http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/ArrayDeque.java](http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/ArrayDeque.java)
8. [http://javahungry.blogspot.com/2014/06/how-treemap-works-ten-treemap-java-interview-questions.html](http://javahungry.blogspot.com/2014/06/how-treemap-works-ten-treemap-java-interview-questions.html)
9. [http://javahungry.blogspot.com/2013/08/hashing-how-hash-map-works-in-java-or.html](http://javahungry.blogspot.com/2013/08/hashing-how-hash-map-works-in-java-or.html)
10. [http://geekrai.blogspot.com/2013/06/linkedhashmap-implementation-in-java.html](http://geekrai.blogspot.com/2013/06/linkedhashmap-implementation-in-java.html)
11. [http://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable](http://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable)
12. [http://zhouyunan2010.iteye.com/blog/1236220](http://zhouyunan2010.iteye.com/blog/1236220)
