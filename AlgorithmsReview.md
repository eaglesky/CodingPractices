# My Algorithms Review

## Reference Books
* Cracking the coding interview(outline and summary)
* An ultimate guide to coding interview, on read.amazon.com. (outline, summay and problems)
* Coding manual by Fangqin Dai(outline and problems)
* Robert Sedgewick's Algorithms(algorithms reference)
* Introduction to algorithms(algorithms reference)
* Head first Java(language reference)
* Leetcode C++ solutions by Fangqin Dai(solutions)
* Element of Programming Interviews(EPI)(solutions)
* Leetcode OJ(problems and solutions)

## Arrays and Hashtables
### Knowledge
* Hash Tables -- implementation, performance, collision.
  *  Search, insert and delete is average O(1), and we usually see them as O(1) in interview as we assume that the chance of collision is low.  
  https://discuss.leetcode.com/topic/53193/are-hash-tables-ok-here-they-re-not-really-o-1-are-they/2
  * Iterating each element in it usually requires iterating each bucket, so the iteration time is proportional to the "capacity" of the HashMap instance (the number of buckets) plus its size (the number of key-value mappings). Since the load factor is a constant, iterating n elements take O(n) time, and therefore the amortized time for iterate each element is O(1)(not strictly O(1) even if no collision. Iterator() method also needs to advance the next pointer to the next element, which could check multiple empty buckets before it). As for Java HashMap, each key-value pair is wrapped in a node, and once the number of items in a hash bucket grows beyond a certain threshold, that bucket will switch from using a linked list of entries to a balanced tree. In the case of high hash collisions, this will improve worst-case performance from O(n) to O(log n). http://openjdk.java.net/jeps/180
  * LinkedHashMap is faster at iteration and has the same time complexity for the basic operations(put, get and remove) as HashMap. Internally it uses a linked node which has before and after pointers in addition to <key, val> and next pointer used by HashMap.Node. The linked nodes are linked together in insertion order, and this doubly-linked list is used for iteration. Therefore, iteration over the collection-views of a LinkedHashMap requires time proportional to the size of the map, regardless of its capacity, and iterating each element takes O(1)(including getting the iterator and return the first element). However it doesn't support getting the last element in O(1) time. One workround could be caching the last element, like in "[Leetcode]Snake Game". We can also specifiy access order in the constructor so that merely querying the map with get can change the iteration order too. Note that TreeNode extends LinkedHashMap Node, so each bucket can also be turned into a BST, while keeping the insertion/access order through LinkedHashMap Node pointers.  
  Iteration on them often requires gettign a collection view first(like calling entrySet()/values()/keySet()), which just return internal references(they are all lazy-intialization, so initial value of entrySet, values and keySet fields are null) and do not copy any data. Then we can use for-each or iterator to do the iteration, which are essentially the same. https://stackoverflow.com/questions/2923856/is-the-order-guaranteed-for-the-return-of-keys-and-values-from-a-linkedhashmap-o/2924143#2924143
  * Chaining with linked lists, chaining with BST, Open Addressing with Linear Probing, Quadratic Probing and Double Hashing. (See CTCI)
* Resizeable array
  * Implementation: Vector(C++), ArrayList(Java)
  * Performance: get, set is O(1), amortized time of add is O(1) and why. Remove an element at some position is typically O(n), but could be O(1) by switching the last element with the one to be deleted and then remove the last element. In Java, ArrayList.remove(int id) does it, and if id is the last position, it simply decrease the size counter and doesn't copy any element.
* Basic language syntax for array(fixed and dynamic), hash map and hash set.
* In Java, it's better to use constructor to get the shallow copy of ArrayList than calling the clone method, since List interface does not have clone method.
* Implementation of equals and hashCode methods in Java(See <<Effective Java>>,
http://www.angelikalanger.com/Articles/JavaSolutions/SecretsOfEquals/Equals.html)
  * Note that if two objects are equal, their hash codes must be the same; but if two objects have the same hash codes, they don't have to be equal.
  * The hash code of Pair or unordered collection is usually implemented as sum of the hash codes of all elements.

### Problems
* [Leetcode] First Missing Positive(Algorithm and Implementation*)
