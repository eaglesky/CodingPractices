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
  * LinkedHashMap is faster at iteration and has the same time complexity for the basic operations(put, get and remove) as HashMap. Internally it uses a linked node which has before and after pointers in addition to `<key, val>` and next pointer used by HashMap.Node. The linked nodes are linked together in insertion order, and this doubly-linked list is used for iteration. Therefore, iteration over the collection-views of a LinkedHashMap requires time proportional to the size of the map, regardless of its capacity, and iterating each element takes O(1)(including getting the iterator and return the first element). However it doesn't support getting the last element in O(1) time. One workround could be caching the last element, like in "[Leetcode]Snake Game". We can also specifiy access order in the constructor so that merely querying the map with get can change the iteration order too. Note that TreeNode extends LinkedHashMap Node, so each bucket can also be turned into a BST, while keeping the insertion/access order through LinkedHashMap Node pointers.  
  Iteration on them often requires gettign a collection view first(like calling entrySet()/values()/keySet()), which just return internal references(they are all lazy-intialization, so initial value of entrySet, values and keySet fields are null) and do not copy any data. Then we can use for-each or iterator to do the iteration, which are essentially the same. https://stackoverflow.com/questions/2923856/is-the-order-guaranteed-for-the-return-of-keys-and-values-from-a-linkedhashmap-o/2924143#2924143
  * Chaining with linked lists, chaining with BST, Open Addressing with Linear Probing, Quadratic Probing and Double Hashing. (See CTCI)
* Resizeable array
  * Implementation: Vector(C++), ArrayList(Java)
  * Performance: get, set is O(1), amortized time of add is O(1) and why. Remove an element at some position is typically O(n), but could be O(1) by switching the last element with the one to be deleted and then remove the last element. In Java, ArrayList.remove(int id) does it, and if id is the last position, it simply decrease the size counter and doesn't copy any element.
* Basic language syntax for array(fixed and dynamic), hash map and hash set.
* In Java, it's better to use constructor to get the shallow copy of ArrayList than calling the clone method, since List interface does not have clone method.
* Implementation of equals and hashCode methods in Java(See *Effective Java*,
http://www.angelikalanger.com/Articles/JavaSolutions/SecretsOfEquals/Equals.html)
  * Note that if two objects are equal, their hash codes must be the same; but if two objects have the same hash codes, they don't have to be equal.
  * The hash code of Pair or unordered collection is usually implemented as sum of the hash codes of all elements.

### Problems
* [Leetcode] First Missing Positive(Algorithm and Implementation**). Think about the O(n) space solution first and then the O(1) space solution.
* K Sum problems. Can be solved using two approaches:
  * Sort the input array first. Then using two pointers starting from the beginning and the end of the array, moving one of them towards the other each time based on the comparison of the sum of the two elements and target, until they meet. The two pointers process takes O(n) time. No extra space needed, assuming sort is in place. For K > 2, always move the leftmost/rightmost pointer, and do it recursively, until the innermost two pointers which use the two pointer approach instead. Using this approach, it is easy to return unique combinations, easy but not effienct to return one solution, and hard to return complete combinations.
  * Using a hash set of combinations to record the results and remove duplicates. Sort the input array first if K > 2(this is optional and can make de-duple faster and easier to implement). Divide K into two parts first. Iterate the second part and solve the subproblem for the first part. Cache the sums and their corresponding elements at the end of each iteration. Using this approach, it is easy to return complete combinations, efficient to return one solution,  and also not very hard to return unique combinations, with additional space.  
  
  Time complexity of k Sum problems: omega(n^ceil(k/2)), O(n^(k-1)).
  http://www.sigmainfy.com/blogk-sum-problem-analysis-recursive-implementation-lower-bound.html
  - Two sum
    - Input array is random, find out one solution.
      + [Leetcode] Two sum(Algorithm).
      + Input array is random, find out all unique solutions(Algorithm*).  
      Unique: if a + b = sum, then (a, b) and (b, a) are duplicate pairs.  
      Remember the clever way of removing duplicate pairs!
      + [Leetcode] Two sum II -- Input array is sorted.
        + find out all unique solutions using no additional space.
        + find out all id pairs in which the first id is smaller than the second one? The hard part is when a + b = sum, we don't know how to move the two pointers. We can move both pointers until there are no duplicates, say ia --> ia', ib --> ib', and put all pairs formed by elements between num[ia] and num[ia'] and elments between num[ib] and num[ib'] into result list.
      + [Leetcode] Two Sum III -- Data structure design.
  - Three sum
    - Input array is random, find out all unique solutions.
      + [Leetcode] Three sum(Algorithm).
      + [Leetcode] Three sum closest(Algorithm).
    - Find out the count of all triplets whose sum is less than the target:
      + [Leetcode] Three Sum Smaller(Algorithm**) Using two pointers to find out the count efficiently in linear time. Finding number of distinct triplets -- e.g. (1, 2, 3) and (1, 3, 2) are not distinct, would be harder and the runtime would be O(n^3).  
      https://discuss.leetcode.com/topic/27075/what-if-the-question-is-to-find-the-number-of-triplets-instead-of-index
  - Four sum(Random input array, find out all unique solutions)
    + [Leetcode] Four Sum(Algorithms*).
    + [Leetcode] Four Sum II(Algorithm)
* Two pointers:  
  The two pointer schemes in LinkedList chapter may be used, if they run in the
  same direction. Need to think carefully about the end condition in order to know which one to use!
  * Pointers moving in the opposite direction.  
    This can often reduce runtime from O(n^2) to O(n). It basically tries to check the two elements pointed by the pointers against some condition and moves left pointer to the right or right pointer to the left accordingly, to skip some unnecessary comparisons and narrow down the search range. Optionally we can skip duplicate elements at the beginning of each iteration.
    - [Leetcode] Previous three sum problems.
    - [Leetcode] Container With Most Water(Algorithm)
    - [Leetcode] Trapping Rain Water(Multiple Algorithms** and implementation of best solution*)
  * Pointers moving in the same direction, on different lists.  
    Same as previous, this essentially skips unnecessary comparisons and narrow down the range of comparison. 
    - [Leetcode] Intersection of Two Arrays I and II(Algorithm when the arrays are sorted*). Remember the proof.
    - [Leetcode] Shortest Word Distance II(Algorithm*). O(1) get solution will TLE due to the lone pre-processing time. Remember the acceptable two pointer solution, and its proof.
  * Pointers moving in the same direction, on the same list.  
    Works as if the faster pointer is copying data to another list. Need to know why it is not affected by overriding.
    - Remove duplicates(Maximum allowed duplicates == K). Two ways of checking duplicates!
      * [Leetcode] Remove Duplicates from Sorted Array(Best Algorithm*).
      * [Leetcode] Remove Duplicates from Sorted Array2(Best Algorithm**). Remember the prooves for solutions to both problems.
    - [Leetcode] Move Zeros(Algorithms*). Remember both algorithms and how to prove them!
  * Pointers moving in the same direction on the same list, all elements in between are considered.  
    The main loop moves the faster pointer and check each meaningful substrings ending with the faster pointer, and for each iteration, keep moving the slower pointer until some condition is met. Usually needs to maintain a max/min value and return it.
    This essentially skips some unnecessary checks of elements between certain pairs of pointers, which is mainly done by the inner loop -- subarrays/substrings starting with the slower pointer is skipped, and we only need to compare the subarrays/substrings between the slower pointer and the faster pointer, not all elements before the faster pointer. So the loop invarient at the beginning of each iteration is usually the subarrays/substrings ending with elements before current fast pointer are all checked, and subsequent checks do not need to compare subarrays/substrings starting with elements before slow pointer -- (slow pointer, fast pointer) is the first subarray/substring that is valid ending with fast pointer.
    - [Leetcode] Minimum Size Subarray Sum(Algorithms* and implementation of two pointer solution*). Sometimes the max/min value should be initialized failure value, not Integer.MAX_VALUE or Integer.MIN_VALUE, because it is likely those values are confused with some valid max/min values. The code is not that complicated for the update part -- one line is enough.  
    Note that the assuption of positive nums are important! Otherwise this two-pointer solution must be modified, and the runing time will has to be O(n^2) at best(proof?). And because of this assumption, the question makes sense asking the "minimum" size, not the "maximum" size. And similar questions like "finding maximum size subarray whose size is no larger than k" can also be solved similarly.
    - [Leetcode] Longest Substring Without Repeating Characters(Implementation*)
    - [Leetcode] Longest Substring with At Most K Distinct Characters(Algorithm*)
    - [Leetcode] Longest Substring with At Least K Repeating Characters(Multiple Algorithms*, one of them is two pointer**)
    - [Leetcode] Minimum Window Substring(Algorithm* and best implementation*). Remember the one map implementation. Also recording the indices is better than recording the substring.
    - [Leetcode] Substring with Concatenation of All Words(Algorithm* and Implementation*, very hard). In this question, word list can contain duplicate word, and the target substring should contain exactly same number as appeared in the word list. Might be easier to implement if before each scan, do a pre-processing and get list of words.
* [Leetcode] Shortest Word Distance(Algorithm*). It looks similar to another problem, but I forgot which one.
* [Leetcode] Shortest Word Distance III(Algorithm*). A follow-up of above. Easy to make mistake here!
* Subarray sum problems. Three approaches:
  * DP. If the problem asks for max/min sum and there is no more constraint on it.
  * Two pointers. 
    - [Leetcode] Minimum Size Subarray Sum(See previous).
  * Get accumulative sums first. This could be sorted in some cases. But usually we need to iterate on it while maintaining some kinds of maps. If order won't be changed, accumulative sums can be calculated on the fly instead of using additional array to store them.
    - [Leetcode] Maximum Size Subarray Sum Equals k(Algorithm*). Question could ask for minium size instead, which can also be handled similarly.
    - [Lintcode] Subarray Sum Closest(Algorithms**). Remember both solutions. Also note how useful a treemap is when getting the closest key instead of an exact key. If the questions asks for the minium length of subarray that has sum closest to k, it can still be solved with a few modifications.
    - [Lintcode] Submatrix Sum(Algorithms**). Remember how to enumerate all the sub rectangles in a 2D matrix. Remember the trick to add padding zeros. Hard to come up with the trick to reduce the 2D space usage to 1D!
    - [Leetcode] Max Sum of Rectangle No Larger Than K(Algorithm*). Extension of above.
* [Others] Task schedule with cool down(Algorithm and implementation**).
* [Leetcode] Product of Array Except Self(Algorithm**). Divide, compute one side and store it, then compute another side. Can be applied to many other problems.
* [Leetcode] Increasing Triplet Subsequence(Algorithm**). This can also be solved using idea similar to the previous problem, but it would use O(n) extra space. The best solution can also be applied to checking the existence of increasing subsequence of any length. Useful trick and can be applied to other problems.  
Trick: When finding max/min element, we can either use reference type variable instead of primitive type so that we can use null as the initial value, or use index of the element and -1 as the initial value(or any special value indicating invalid for the element). The latter ways may require slightly more code when update. Both ways are preferable to using primitve type and Integer.MAX_VALUE/Integer.MIN_VALUE as the initial value, since the latter way cannot return a reasonable value when there is no max/min value found(latter way works well only when the max/min value is guaranteed to exist). Or if the logic is simple, we can use the first element as initial value.
* [Leetcode] Longest Consecutive Sequence(Algorithms**). Remember the trick that processes each streak in an inner loop at the beginning of each streak, which still takes linear time overall.
* [Leetcode] Bomb Enemy(Best algorithm**). Remember the implementation of dealing with streaks/chunks, which is similar to previous problem. An alternative is to process each chunk and moves on to the next chunk immediately, which is less flexible and cannot be used as caching.

## Strings

### Knowledge
* JAVA: String, StringBuilder and char[]. 
  * StringBuilder is essentially a wrapper of dynamic char array , and append(string) basically copy each character from input string to the end of existing char array. When deleting, it will shift the characters after those deleted forward. To remove the last element in the StringBuilder, just use sb.setLength(sb.length() - 1). toString () method returns a copy of wrapped char array, wrapped by a newly created String. setCharAt(index, char) is also an useful method to update a certain char in the StringBuilder. Also note that StringBuilder doesn't have isEmpty() method while String does.
  * char[] array usually has a better performance than StringBuilder since it is simpler. But when dynamically adding new elements to it, usually another variable charLen is needed to record the current length(number of filled elements in the array). Creating a string from it is simply using new String(char[] value, int offset, int count). char[] can make backtracking easier since you can just override the old elements when searching a new branch. But StringBuilder is also useful and saves you from maintaining the length of the array.
  * If we have to keep inserting strings/chars to the front, there is a better way -- append the strings/chars to the end and at last call StringBuilder.reverse(). This can make the code run in O(n) time instead of O(n^2) time.
* Palindrome.
  * Definition. A string that reads the same backward as forward, e.g., madam.
  Checking if a string is a palindrome can be easily done in O(n) time. And so is checking if a linked list a palindrome, which can also be done using O(1) space if reversing the first/second half of the original list is allowed.
  * Check if concatenation of two strings is a palindrome.
    - The reverse of the second string has to be the 1)the prefix of the first string, and the rest of the first string is a palindrome, or 2)the first string is the prefix of the reverse of second string, and the rest of the reversed string is a palindrome. [Leetcode]Palindrome Pairs. Trie approach.
    - Choose the longer(or equal) one s1, the other is s2. If s2_reverse is the prefix of s1, and the rest of s1 is a palindrome, then s1s2 is a palindrome. Otherwise if s2_reverse is the suffix of s1, and the rest of s1 is a palindrome, then s2s1 is a palindrome. When s1.length == s2.length, the concatenation is a palindrome when and only when s2_reverse == s1. [Leetcode]Palindrome Pairs. Hashmap approach.

  * Find out all the palindrome substrings. There are two ways to do this, which are very useful when solving more complex palindrome related problems. Both ways take O(n^2) time, n is the length of string s.
    * Search all the substrings and check if each of them is palindrome. Has to use addition space.  
    Let f[i][j] indicate whether substring s[i...j] is a palindrome or
    not. Iterate the string from right to left, for each character s[i], check
    all substrings s[i..j], i <= j < s_length. `f[i][j] = (s[i] == s[j]) && (j - i < 2 || f[i+1][j-1]))`. f[i][j] can be reduced to 1D if j starts from s_length - 1. Remember!
    * Search the substrings with each possible center. Doesn't check all substrings but just those that are likely to be a palindrome. If we only want to iterate all palindrome substrings but not store them, this approach uses O(1) space. So this is more efficient in both time and space.   
    Iterate the string from left to right. For each character s[i], check
    the substrings that center at it of odd and even lengths.
    ```java
      for (int i = 0; i < s.length(); ++i) {
          //Odd length.
          for (int j = 0; i-j >= 0 && i+j < len && s.charAt(i-j) == s.charAt(i+j); ++j) {
              f[i-j][i+j] = true;
          }
          //Even length.
          for (int j = 0; i-1-j >= 0 && i+j < len && s.charAt(i-1-j) == s.charAt(i+j); ++j) {
              f[i-1-j][i+j] = true;
          }
      }
    ```
    Example: [Leetcode] Palindrome Partitioning II.
  * Finding the longest palindrome starting from a certain letter in a string
  can be solved in O(n) time and O(n) space using KMP pre-processing algorithm(after appending a non-existent character and the reverse of original string to it). No need to know the implementation details, but need to know the time and space complexities of KMP. The actual string matching part of KMP takes O(m) time, where m is the length of the string being matched. So the totol time is O(m + n). --- [Leetcode]Shortest Palindrome.
    * KMP algorithm has two parts --- pre-computing part and actual string matching part. Inputs are two strings, text string(T of length n) and pattern string(P of length m). The pre-computing part takes only the pattern string and output an array &pi; of same size.
    &pi;[i] is the length of longest prefix of P that is also the suffix of P[0..i].  
    No need to know how to implement KMP and other advanced algorithms. I think it's enough to know the time complexities of them. See more in *Introduction to Algorithms*
* Anagram: a word, phrase, or name formed by rearranging the letters of another, such as cinema, formed from iceman. To check if two strings are anagrams, either use a hashmap to count the number of occurences for each character and compare them, or just sort the two strings and see if they are the same.
* Don't forget the edge case when the string is empty! If this happens, for loop on the string won't happen!
* Note that the whitespace in a string may not be ' ', could be '\t' or even '\n'.
* Terminology: '{' --- brace, '[' --- bracket, '(' --- parenthesis.

### Problems
* Numbers related  
  Pay attention to how to handle substrings starting with 0, like '0001'.
  - [Leetcode] Integer To Roman(Algorithm*)
  - [Leetcode] Roman To Integer(Algorithm*)
  - [Leetcode] Integer to English Words(Algorithm* and Implementation)
  - [Leetcode] Basic Calculator II(Best Algorithm* and Implementation). Remember the best algorithm, which can be applied to many other problems. Also remember the way how to get integersformed by a substring without using Integer.parseInt(string).
  - [Leetcode] Valid Number(Algorithm* and Implementation*). The decription is vague, so refer to the comments in my Java solution for detailed requirements.
* Palindrome related:
  - [Leetcode] Shortest Palindrome(Algorithm*, very hard!)
  - [Leetcode] Palindrome Pairs(Multiple Algorithms** and Implementations**).(Distinct indices (i, j) just means that i != j) One way to improve the speed of not optimal algorithm is consider caching some values when building the trie. Remember the edge cases for the second approach.
* Anagram related.
  - [Leetcode] Group Anagrams(Algorithms*). The key is to find out how to uniquely represent the anagrams. The robust way is to use the sorted string, but in some cases the counting array, or even the product of prime numbers could work too.
* Parsing.
  Processing substring using an inner loop is usually a robust way. If this is done for certain characters only, consider doing it when only that condition is satisfied. An alternative way is do this for any case and break out when the condition is not satisfied and then continue handling other cases in the same iteration. The latter way might be simpler but requires more thought and could be less robust if different cases require different handling. So try the former way to be on the safe side.
  - [InterviewBit] Pretty Json(Best Algorithm** and Best Implementation**). Remember the best implementation logic. Also the edge case when '}' or ']' is followed by ','. For this recursive-like parsing problems, stack is often needed if the previous context value cannot be restored. But for this problem the previous value can easily be restored so using just a variable to store the current context value is enough.  
  The Json value could be a string, number, null, another json object, or an array object. The Json object is comprised of key-value pairs, while the array object is comprised of values. This problem just involves four cases as is shown in the code.  
  - [Leetcode] Text Justification(Implementation**). We can assume the words are non-empty since they are usually the output of split on the original text string. Pay attention to the case when there is only one word in the line, and the last line.  
  To deal with each group of elements in an array, one good implementation is to use a inner loop to iterate the elements of each group. This makes it cleaner to do pre-processing and post-processing of each group.

## Linked List

### Knowledge
* Java: LinkedList class -- doubly linked list, performance
* Two pointers technique is frequently used! The diff between any two given linked list can always be found in O(m+n) time. Also we can use two pointers to find the middle node, or the last node of first half, by using one slow and one faster pointer, without dummy head. Remember the implementation.  
Two pointers iteration on two linked lists(one on each of them):  
  ```java
  while (p != null || q != null) {
    if (p == null) {
        operation on q;
        q = q.next;
    } else if (q == null) {
        operation on p;
        p = p.next;
    } else {
        operation on both p and q(might have more branches here)
        p = p.next;
        q = q.next;
    }
  }
  ```
  ```java
    while (p != null && q != null) {
        operation on both p and q.(might have more branches here)
        p = p.next;
        q = q.next;
    }
    /*while (p != null) {
        operation on p.
        p = p.next;
    }
    while (q != null) {
        operation on q.
        q = q.next;
    }*/
    //another way
    v = p != null ? p : q;
    while (v != null) {
        ...
    }
  ```
This approach is preferable if we can skip iterating the non-null list after the loop breaks. E.g. merge two sorted linked lists.
(probably best):
  ```
  while(p != null || q != null) {
      if (p != null && (q == null || condition_of_iterate_p)) {
          operation on p.
          p = p.next;
      } else {
          operation on q.
          q = q.next
      }
  }
  ```
The above implementations can also be appied to two pointers iteration on arrays or strings. E.g. The merge function of Merge Sort. 
* Sometimes we can use the input pointers to reduce the number of pointers created. It is possible to delete a node only by using that pointer if that node is not the head or tail --- by shifting the contents of the following nodes toward it and remove the last one.
* Adding dummy node to the front a linked list can be convenient for iterating over the list using two pointers, especially if we want to remove the first node, or insert in front of the first node. It also helps when there is cycle in the linked list and the cycle starting point if the head of linked list. New head is always dummy.next, not neccessary the original head! Think carefully about edge cases involving the head node if we do not want to add the dummy node(for node finding problems, think about the case when there is only one node in a list).
* Edge cases: (think normal case first), insert/remove the first/last node, one node linked list, null list.
* Three approaches for reversing linked list and their implementation(see below). Use previous node pointer instead of the current node pointer if we want to move the current node around or remove a node. 

### Problems
* Node finding.
  - [Leetcode] Intersection Of Two Linked Lists(Best Algorithm* and Best Implementation*). Remember this algorithm(including the first trial) and the edge case when one linked list is null.
  - [Leetcode] Linked List Cycle II(Algorithm** and Implementation*). Remember the test cases too.
* Remove nodes: 
  - [Leetcode] Remove Nth Node From End of List(Implementation, practice dummy node, two pointers moving and edge case thinking)
  - [Leetcode] Remove duplicates from sorted list I(easy) and II(Implementation*).
  - Generalization of I to allow K duplicates -- Remove duplicates from sorted list III.(Best Implementation**). Not found in any OJ. I implemented the test cases.
* Reverse nodes:
  * Basic head insertion(Iterative and recursive) -- creating a new linked list, original head pointer will become tail pointer, and the new head pointer keeps updating. No need for the dummy node.
    - [Leetcode] Reverse Linked List(Implementation, recursive algorithm*)
    - [Leetcode] Palindrome Linked List(Algorithm). 
  * Head insertion for reversing sublists -- This process includes node removing and node insertion. Must add dummy node. The predecessor of the head of the original sublist now becomes the predecessor of the tail of the reversed sublist. So the predecessor of each sublist is enough to reverse the sublist and get the new head and tail of the reversed sublist. Note that we can either insert each node to the original list, or insert them into a temporary sublist and then insert it back. The former way requires more than 1 node in each sublist, but the latter way doesn't require that. See solution for 'Reverse Linked List II'.
    - [Leetcode] Swap Nodes in Pairs(Implementation)
    - [Leetcode] Reverse Nodes in K-Group(Algorithm* and Implementation*)
    - [Leetcode] Reverse Linked List II(Implementation*)
  * Tail insertion(can be skipped). Inserting each node right after the original tail node. Need to first aquire pointer to the tail node and then start from the first node. Original tail node will become new head node. Pointer to the sucessor of the original tail node need to be stored or it will be lost.
    - [Leetcode] Reverse Nodes in K-Group(Algorithm* and Implementation*)
* Re-arrange nodes:  
  Removing nodes first, and then either insert them into the original list or a new list. The latter way requires creating a new dummy node and tail node for appending, more nodes than the former way but could be simpler. The former way need to deal with some special cases, like removing and inserting into the old place, which could affect the next position of the pointer.
  - [Leetcode] Rotate List(Algorithm**, practice two pointers moving--two ways)
  - [Leetcode] Partition List(Best algorithm*)
* Copy linked list:
  - [Leetcode] Copy List with Random Pointer(Algorithm*, two approaches)


## Stack and Queue

### Knowledge
* A stack can be implemented with a resizable array or a linked list; A queue can be implemented with a cyclic array, double resizeable arrays or a linked list.
* Java implemenation:  
Interfact Deque<E>, push(E item), pop(), peek().   
Interface Queue<E>, offer(e), poll(), peek().   
Iteration using iterator and Deque.descendingItarator -- same usage, but opposite iterating order.  
Both of them can be implemented as class ArrayDeque<E>, which is essentially a cyclic array implemented with a resizable array. According to java doc, "This class is likely to be faster than Stack when used as a stack, and faster than LinkedList when used as a queue". The Deque interface usually doesn't allow null element, as peek() returns null when the deque is empty, not throwing any exceptions. However LinkedList does allow null element. Stack interface permits null element too. However try not to put null element in any case.  
Java ArrayDeque can be used to implement monotonic queue(see [Leetcode]Sliding Window Maximum). Note that peek(), poll(), push() and pop() are all performed on the head of the queue. offer(), peekLast(), pollLast() are performed on the tail.
* In some design problems, sometimes it is good to call peek() first in pop()/poll().
* Queue can be used to implement cyclic iteration with conditional removal. -- [Leetcode] Zigzag Iterator. If there is no removal, this can be done simply by using a index, or a iterator.

### Problems
* [Leetcode] Simplify path(Implementation*)
* [Leetcode] Evaluate reverse polish notation(Implementation)
* [Leetcode] Min stack(Multiple algorithms*, one stack solution is not really good).
* [Leetcode] Implement queue using stacks(Algorithm)
* [Leetcode] Implement stack using queues(Best Algorithm**)
* [Leetcode] Largest Rectangle in Histogram(Algorithm** and implementation*). Remember the progression and think of loop invarient to prove the correctness.
* [Leetcode] Sliding Window Maximum(Algorithm** and Implementation).


## Tree
### Knowledge
* Definition of tree, binary tree and binary search tree, and the differences between them. For BST, definition can vary slightly with respect to equality, if there could be duplicate values.
* Balanced vs Unbalanced. 
  * Definition of balanced binary tree:  
    An empty tree is height-balanced. A non-empty binary tree T is balanced if:
      * Left subtree of T is balanced
      * Right subtree of T is balanced
      * The difference between heights of left subtree and right subtree is not more than 1.
    E.g. [Leetcode]Balanced Binary Tree.
  * Two common types of balanced (binary search) tree are red-black tree and AVL tree. Usually the height of a balanced BST is O(log(N)). Search, insertion and deletion all take O(logN) time. Java implementation: TreeMap, based on red-black tree.
* Definition of complete, full and perfect binary trees.
* Number of nodes in perfect binary tree: n = 2^k - 1 (k is the number of levels)
* Binary tree inorder, preorder and postorder traversal and their three implementations (see related problems below, remember the implementation of them except Morris). --N-ary trees?  
Also finding the predecessor and successor is easy when the tree node has parent pointer. If not, then we have to use traversal and save the current visited node. Make sure you know how to do it both iteratively(easier) and recursively(know how to pass previous visited node to the recursive function and how to return correctly).  
Example: [Others]InorderSuccessorBinaryTreeWithParent and without.
* About Morris traversal: the basic idea is to use null right child of some nodes to store the succedents beforehand and delete them afterhand. Preorder and inorder are similar, since the right children are always traversed at last, and therefore after traversing back through the thread pointer there is no need to visit the left children again. However for postorder traversal, the parent node is traversed at last. So after traversing back through the thread pointer, the left children have to be visited reversely(only those nodes from the direct left child all the way down to the right). It is also necessary to create a dummy node and(7) have its left child be the root.
* If the problem is about the height(distance from the furthest leaf to the current node), consider to compute the height for each node recursively, based on that of its children(this is essentially bottom-up recursion). Consider treating null as height -1. E.g. [Leetcode]Find leaves of binary tree. This solution can be applied to finding height of all the nodes.  
For depth(distance from the root to the current node) related problems, if the problem asks for depth of each node, then we have to use top-down recursion. If it asks for max/min depth of a binary tree(max/min distance from the root node to a leaf), then the problem can be converted into finding max/min height of root node, and it's better to use bottom-up recursion than top-down recursion since the latter would require passing a global variable to the recursive function). However if using iterative solution, top-down is easier to implement than bottom-up.  
Note that the bottom-up recursion code of finding the minimum height/depth is more complex than finding the maximum(technically they should not be called at height/depth)! For the minimum, usually if one of them is null, we take the height/depth of the other, otherwise we use the minium one plus 1. E.g. [Leetcode]Minimum Depth of Binary Tree  
Besides traditional resursive approach, max-depth problem can also be solved by removing the leaf nodes in each iteration until the tree is empty. E.g. [Others]Find Depth.
* Binary search tree:
  * Definition: A binary search tree (BST) is a binary tree where each node has a Comparable key (and an associated value) and satisfies the restriction that the key in any node is larger than the keys in all nodes in that node’s left subtree and smaller than the keys in all nodes in that node’s right subtree.
  * Ascending order when doing inorder traversal on it.
  * Search, Minimum, Maximum, (inorder)Successor, (inorder)Predecessor  operations all run in O(h) time. h is the height of the BST. See the Leetcode practices for Successor implementation.
  * Insertion(easy) and Deletion(harder, see one example problem) can also run in O(h) time.
  * Selection and Rank. See Leetcode problem below.
  * Range query -- finding all elements in a BST that have values within a given range. See Robert's Algorithms. O(n) time and O(h) space. Can be solved both iteratively or recursively. Recursive solution that has target range as one parameter of the recursive function can avoid iterating out-of-range nodes.
  * Implementation. Java: TreeSet/TreeMap, balanced BST(red-black tree). It provides guaranteed log(n) time cost for the containsKey, get(give key or first/last key), put and remove operations. NOTE: BST usually doesn't allow duplicates. Workarounds for it are either store map of node to counts, or use more complex keys.
* Trie(Prefix tree/radix tree/digital tree):
  * Refer to Robert's Algorithms. Think of the chars on the link instead of on the node.
  * The map in each node can be implemented with hashmap or array.
  * See the first two leetcode problems for complete implementation of trie methods.
  * The value of each node can be boolean, integer, or even a String -- representation of the word from the root to the node. And this value can change and be used for de-duplication. E.g. [Leetcode] Word Search II.

### Problems
* Traversal  
  Make sure to understand the process of three basic traversals.  
  Code usually can be proved inductively -- properties of each subtree are maintained and passed to the parent tree. See solutions of postorder traversal.
  Tree problems can often be solved by recursion. DP rarely works. The recursion could be either top-down or bottom-up, or both.
  - Basic Traversals.
    + [Leetcode] Binary tree inorder traversal(Algorithms* and Implementation*). Recursive solution, typical iterative solution using a stack, Morris Traversal(rarely used).
    + [Leetcode] Binary tree preorder traversal(Algorithms* and implementation*). Recursive solution, typical iterative solution using a stack, iterative solution applicable to n-ary trees, Morris Traversal(rarely used).
    + [Leetcode] Binary tree postorder traversal(Algorithms** and implementation**), it can be seen as a reverse of preorder traversal. Hardest among the three. Recursive solution, typical iterative solution using a stack, iterative solution applicable to n-ary trees, Morris Traversal(rarely used).  
    The recursive solutions to many other problems can be converted into iterative one by storing the computed result for left and right child of the current node, usually in a map. After storing the computed result for the current node, the results for its children can be discarded.
    + [Others] Find in-order successor of node in binary tree using parent pointer(Algorithm and implementation).
    + [Others] Find in-order successor of node in binary tree without using parent pointer.(Implementation of recursive solution)
    + [Leetcode] Flatten binary tree to linked list(Multiple Algorithms**)
    + [Leetcode] Path Sum I(Implementation). Problems with target sum that requires recursion can often pass remaining sum as a parameter instead of target sum and current sum. This trick can be applied to many other problems with target sum!
    + [Leetcode] Path Sum II.
    + [Leetcode] Path Sum III(Algorithm**). Remember the top-down way of enumerating all the path with target sum! Passing the total count as a parameter may be easier to think of, which can be improved to change to the return value of the recursive function, if the count is used in post-order way.
    + [Leetcode] Lowest Common Ancestor of a Binary Tree(Algorithms*). If the question asks to return special value when not both of the input nodes exist in the tree, then the recursive function should return not only a node, but also the number of found nodes.   
    Building a map of node to its parent node while traversing the tree is a great trick that can be applied to some other tree problems.
    + [Others] Lowest commmon ancestor of deepest nodes(Best algorithm** and best implementation**). Remember the recursive thinking of LCA algorithms.
    + [Leetcode] House Robber III(Algorithm*)
    + [Leetcode] Binary Tree Paths(Implementation). Remember the worst case and time complexity analysis of this kind of problem.
    + [Leetcode] Boundary of Binary Tree(Best Algorithm** and best implementation*). Clarification: Root node can be counted as left or right boundary node. When root.left != null, the path from the root to the left-most node is the left boundary, otherwise there is no other left boundary node. Similar for the right boundary nodes, which only have more than one node when root.right != null.  
    For this kind of reverse traversal problem, remember try some post-order approach. The best implementation may not be easily come up with at the beginning. It's okay to write two functions first and then consolidate them later.  
    The iterative solution for the two functions version is easy to implement since they are all pre-order traversals. But need to add a map of node to the flag.
  - Level order traversal related.
    + [Leetcode] Binary tree level order traversal I(Multiple algorithms*), II is just adding reverse to the end of the solution of I. The recursive algorithm can be applied to some other problems.
    + [Leetcode] Zigzag level order traversal. Reverse the array for that level every two levels(or assign the values reversely when creating the array for that level).
    + [Leetcode] Binary Tree right side view -- small variation of level order traversal.
    + [Leetcode] Symmetric Tree(Algorithm and Multiple Implementation*). Iterative solution using queue or stack can add the elements in any order.
    + [Leetcode] Populating next right pointers in each node I and II(Implementation**). Remember the branching logic.
    + [Leetcode] Binary Tree Vertical Order Traversal(Algorithm*). When implementing level order traversal, sometimes it is easier to handle the current node directly instead of handle the left and right node respectively. Also remember the trick to iterate keys in sorted order without using sorted map/set in linear time.
  - Depth/height related.
    + [Leetcode] Find leaves of binary tree(Algorithm** and Implementation). When requiring return a list of nodes that does not follow the order of basic traversal, consider storing the element directly to the corresponding location in the output array.
    + [Leetcode] Minimum Depth of Binary Tree(Algorithms and Implementation)
    + [Leetcode] Balanced Binary Tree(Best Algorithm*)
* Recontruction of binary tree:
  - [Leetcode] Construct Binary Tree from Inorder and Postorder Traversal (Algorithm*), and Construct Binary Tree from Preorder and Inorder Traversal (same). Cannot construct the binary tree from Preorder and Postorder(why? When coming up with exceptions, try starting with the simplest examples) Note that if duplicates exist in the input array, there may not be unique tree!
  - [Leetcode] Serialize and Deserialize Binary Tree(Algorithms** and implementation*)
* Binary Search Tree:
  - [Leetcode] Validate Binary Search Tree(Multiple algorithms* and Implementations*) -- this reveals an important attribute of BST when traversing it!
  - [Leetcode] Recover Binary Search Tree(Algorithm and Implementation*). Note that this question assumes there are no duplicates in BST! What would be the solution if there could be duplicates?
  - [Leetcode] Inorder Successor in BST(Algorithm*). This algorithm uses the ascending order attribute of BST so that it can run in O(h) time not in O(n) time. Therefore this algorithm works only when the target is INORDER successor and there are no duplicates in the tree. If duplicates are allowed, this algorithm can not gurantee that the node returned is INORDER successor. Example:
      ```
            13 (actual returned node)
          10
        9
          10 (look-up node)
            10 (expected returned node)
      ```
    This algorithm can be easily extended to implement Ceiling and Floor methods. Remember the inductive proof from the root.
  - [Leetcode] Convert Sorted Array to Binary Search Tree(Algorithms and Iterative Implementation*)
  - [Leetcode] Convert Sorted List to Binary Search Tree(Best Algorithm* and Implementations*), constructing tree from array can also be solved by bottom-up approach!
  - [Leetcode] Delete Node in a BST(Best Algorithm* and Implementation)
  - [Leetcode] Kth Smallest Element in a BST(Multiple Algorithms*)
  - [Leetcode] Lowest Common Ancestor of a Binary Search Tree(Algorithm*)
  - [Leetcode] Serialize and Deserialize BST(Algorithm** and implementation*). Remember the algorithm and implementation. Be careful about the integer pointer!
  - [Leetcode] Skyline Problem(Best Algorithm** and implementations**). Remember the two ways of implementing BST multi-map/set. They are equivelent in logic, so don't hesitate to use them exchangely.
* Trie
  - [Leetcode] Implement Trie (Prefix Tree)(Full Implementation*, recursive solution of delete?)
  - [Leetcode] Add and Search Word - Data structure design(Implementation)
* Others
  - [Others] Find Depth. (Algorithms* and implementations*).

## Graph
### Knowledge(to be completed)
* Representations(Refer to CTCI)
  * Adjacency list. V(number of Vertices) + E(number of edges) space for directed graph, or V + 2E for undirected graph. This is usually the preferred representation. Works well when accessing the neighbors is frequent. For fast edge lookup, just use hashset of integers(instead of list) as the element of arrays in the Graph class(Refer to the implementation of adjacency list in Robert's Algorithms). The list can be implemented with a HashMap or ArrayList. The former works better if the graph is sparse and  it doesn't require initializing each element. However
  need to know that when creating the map, if the graph is directed, the node without outbounding edges will not be inserted. So when getting the adjacent elements of a node, first check if the corresponding adjacency list in the map is null!
  * Adjacency matrix. V*V space. Fast for edge lookup, easier to represent weights, but usually takes more space than adjacency list. And has no way to represent parallel edges.
* Traversal. Don't forget null input node and loops! Also note that the following pseudocode only implements traversing from one node. If the graph is not connected, then DFS and BFS below must be called for each node!  
The following template is just used for implementation. Think about the problem itself when considering the algorithm -- like what parameters to use in the recursive functions and what they do in each recursion.
  * DFS. Can be used for counting the number of connected components in a graph, check if two vertices are connected, etc. Implemented recursively. Iterative solution usually uses a stack(of actual element or iterator of lists of elements. Often used as step-by-step backtracking and implementing iterators. E.g. [Leetcode]Flatten Nested List Iterator). And a map of node to its parent can be created while traversing to retrieve the paths. 
    - Search all nodes in a graph. DFS will search all the nodes and only once for each one, because in each iteration/recursion all the possible neighbors are checked and we only go to the unvisited node in the next iteration/recursion.
      + Implementation 1:
        ```
        DFS_Recursive_Main(node):
          if node is null, return;
          visit(node);
          visited[node] = true;
          DFS(node, visited);
          Done;

        DFS(node, visited):
          for (adjNode : node.adjNodes) {
              if (visited(adjNode) == false) {
                  visited[adjNode] = true;
                  visit(adjNode);
                  DFS(adjNode, visited);
              }
          }
          Done;
        ```
      + More popular implementation, very suitable for grid related problems:
        ```
        DFS_Recursive_Main(node):
          DFS(node, visited);
          Done;

        DFS(node, visited):
          if (node is visited or not valid) {
              return;
          }
          visited[node] = true;
          for (adjNode : node.adjNodes) {
              DFS(adjNode, visited);
          }
          Done;
        ```
    - Finding all the paths between two states(backtracking).
      + Basic Implementation
        ```
          main:
            //Initialize the capacity of curPath if possible!
            DFS(node, 0, curPath, paths);
            return paths;
          
          //curPath store previous visited nodes
          DFS(node, startId, curPath, paths):
            if end state {
                paths.add(curPath.shallow_copy);
                return;
            }
            //Check validity after checking end node because usually
            //the condition here is more complex than the end node 
            //condition
            if current state is not valid {
                return;
            }
            visited[node] = true;
            //Can do some pruning here to remove unnecessary adjNode 
            //to be iterated below
            for (adjNode : node.adjNodes) {
                curPath.add(adjNode); //To be corrected
                //Be careful about newStartId!
                DFS(adjNode, newStartId, curPath, paths);
                //If using Java StringBuilder, try sb.setLength(originalLen)
                //or sb.setCharAt(id, newChar) to change directly
                curPath.removeLast; 
            }
            visited[node] = false;
            Done;
        ```
  * BFS. Besides traversal, it is also often used to find the shorted path(s) and its length between two vertices. It cannot find all the path between two states as DFS, since BFS proceeds level by level and many paths are not able to searched in this way. Implemented iteratively using queue, O(V + E) time.
    * Standard implementation:
      ```
        BFS(node):
          If node is null, return; //queue usually doesn't allow null value
          Create queue q;
          q.enqueue(node);

          //Usually a hashmap. Flexible, can vary a bit depending on the
          //problem. This must be set true before enqueuing each element
          visited[node] = true; 

          while (!q.isEmpty) {
              curNode = q.dequeue();
              visit(curNode);
              for (adjNode : curNode.adjNodes) {
                  if (visited(adjNode) == false) {
                      // This must be set true before enqueuing each element.
                      // visited == true when the node is already in the queue
                      // or visited.
                      visited(adjNode) = true; 

                      //Record the predecessor, used when you want the 
                      //shortest path
                      prev[adjNode] = curNode;
                      q.enqueue(adjNode);
                  }
              }
          }
          print(prev) // Print out the shortest path recursively, optional
          done;
      ```
    * Algorithm of level BFS(node), which can give the distance from the node being traversed to the source node.
      ```
        BFS(node):
          If node is null, return; //queue usually doesn't allow null value
          Create queue q;
          q.enqueue(node);
          visited[node] = true; //Usually a hashmap. Flexible, can vary a bit depending
                                //on the problem
          for (level = 0; !q.isEmpty; ++level) {
              size = q.size();
              for (i = 0; i < size; ++i) {
                  curNode = q.dequeue();
                  visit(curNode);
                  for (adjNode : curNode.adjNodes) {
                      if (visited(adjNode) == false) {
                          visited[adjNode] = true;
                          prev[adjNode] = curNode;  //optional
                          q.enqueue(adjNode);
                      }
                  }          
              }
          }
          print(prev)  //optional
          done;
      ```
  * Try visualizing the recursive stack when analyzing the problem.

### Problems
* [Leetcode] Clone Graph(Implementations*). Shows a special implementation of BFS and DFS. This kinds of problem need to maintain a lot of variables in each recursion/iteration, which is easier by thinking the traversal and cloning processes separately.
* [Leetcode] Surrounded Regions. (Algorithms and Implementations*). For DFS, sometimes we need to add some restrictions to prevent stack overflow. Remember BFS implementation to this kind of problems.
* [Leetcode] Restore IP Addresses(Implementation**). '012' is invalid while '0' is valid.
* [Leetcode] Combination Sum(Algorithm*).
* [Leetcode] Combination Sum II(Algorithm*).
* [Leetcode] Combination Sum III(Algorithm*).



## Permutations, Combinations and Subsets.
### Knowledge
* Factorial representation of Permutation: `P(n,k) = n! / (n-k)!` 
* Factorial representation of Combination: `C(n,k) = n! / (k! * (n-k)!)`
* `(1 + X)^n = Sum(C(n,k) * X^k), 0 <= k <= n`
* `C(n,k) = C(n-1, k-1) + C(n-1, k)`
* Permutations, Combinations and Subsets can all be solved by standard DFS or iterative solution based on induction. To remove duplicates, we can either use a hashtable or sort the original array first and then compare the current number with the previous one. Combinations and Subsets problems can also be solved using BitSet, but not recommended as first trial.
* Proof -- think about in the result why there is no duplicate element, and then why there is no missing element.

### Problems
* [Leetcode] Permutations I and II (Multiple Algorithms**). Need to be careful of the recursive solution for II.
* [Leetcode] Permutation Sequence(Best Algorithm and Implementation**)
* [Leetcode] Combinations(Algorithms*)
* [Leetcode] Subsets I and II(Multiple Algorithms**). The best iterative algorithm is different from the one in Combinations!
* [Leetcode] Letter combinations of a phone number(Iterative Algorithms*). Remember the iterative algorithms!


## Heap
### Knowledge(Refer to Robert's Algorithms)
* Priority queue is a abstract data type, like an interface in Java, that can be implemented with different concrete data structures -- unordered array, ordered array, linked list or heap. The performance of the basic operations like max and insert varies in all those implmentations(See Robert's Algorithms for the comparison chart).
* Heap is a concrete data structure that is typically used to implement priority queue. Usually heap refers to binary heap, but could also refer to d-ary heaps. In concept, a binary heap is a complete binary tree. Each node in the tree is larger than or equal to the keys in that node's two children if it is a max heap. The heap operations require traversing not only down but also up, so in practice, heap is usually implemented as an array.
* Different from BST, heap allows duplicates. 
* Properties of Heap(max-root)
  1. The largest key in a heap-ordered binary tree is found at the root.
  2. The height of a complete binary tree of size N is ⎣lg N⎦ .
* Operations on heap:
  1. Parent, leftChild, rightChild. Simple O(1) operations on the indices.
  2. Size of array and size of heap is different!
  3. Exchange two nodes.
  4. Bottom-up reheapify (swim/sift-up). O(lg N)
  5. Top-down reheapify (sink/sift-down). O(lg N)
  6. Insert. Add a new key, increase heap size, call 4. O(lg N)
  7. Remove the maximum/minimum(root), and return the new root key. Exchange last key with the root key, decrease heap size, call 5 to sink the root key. O(lgN).
  8. Build heap. Call insert(6) one by one. O(nlogn), but if we do it from right to left and call sink(5) from the halfway of the array to the left, it runs in O(2n) = O(n) time.The proof is in Introduction to Algorithms. This can convert an unordered array to a heap. 
  9. Heap sort(ascending order): (http://algs4.cs.princeton.edu/24pq/Heap.java.html)
    ```
      Build max heap(8)
      while (N > 1) {
          swap(root, last element of the heap);
          N--  // Decrease the heap size by 1.
          sink(root);
      }
    ```
* Indexed priority queue(Robert's Algorithms). Puting indices into a PQ insead of the keys directly, and compare the nodes based on the key(not the index!). What is swapped during reheapfiying is the content of the array(the indices). Therefore when implementing it using a heap, besides the heap array, we also need a map of index to the key, which is often implemented with another array since the index is usually an integer. The operation 7 for this case usually returns the index. And besides the typical PQ operations, there are change(int k, Item item) method that change the key associated with index k to a new key, which requires a third array storing the actual array index for each index of the key(another map). For typical heap PQ, it usually takes the array index directly so no mapping is needed. An useful applciation is also provided in the book: merging multiple sorted streams. Source code: http://algs4.cs.princeton.edu/24pq/IndexMinPQ.java.html Also note that if the input are known elements instead of streams and it is fine to load all elements into the memory, we can also use divide and conquer to merge them, and the time complexity is similar but the space complexity could be much lower. E.g. [Leetcode]merge k sorted lists.
* Java Implementation: class java.util.PriorityQueue. Doesn't permit null values. It is essentially a min heap, implementing Queue<E> interface. The queue retrieval operations poll, remove, peek, and element access the element at the head of the queue, which is the least one by default.
Example usage:  
```java
//Two arguments passed are capacity and comparator. In the following case,
//the priority queue stores the elements of ListNode type, and one node is
//larger than the other when its value is larger than that of the other 
//node.
//The following implementation of compare method is preferable to just 
//return node1.val - node2.val since the latter could have overflow problem
PriorityQueue<ListNode> pq = new PriorityQueue<>(
  lists.length, new Comparator<ListNode>() {
    public int compare(ListNode node1, ListNode node2) {
        return Integer.compare(node1.val, node2.val);
    }
});

//Use the following to create max heap:
PriorityQueue<Integer> queue = new PriorityQueue<>(10,
  Collections.reverseOrder());
//Collection.reverseOrder() returns a ReverseComparator that has a 
//compare() method that returns the negation of the compareTo method 
//result of the object.
```

### Problems
* [Leetcode] Merge K sorted lists(Algorithms*).
* [Leetcode] Kth largest element in an array. (Algorithms of heap solutions*). Remember the proof of best heap solution using loop invarient. This can be simulated using an array of variables, when k is small and known, which is a very useful subroutine in many problems(like [Leetcode]Paint House II)? Remember the general idea of Quick Select algorithm and its time complexity.
* Given n 2D points, find k closest points to the origin. k << n. 
Solution: Using similar algorithms to (2) -- max heap(O(nlogk)) or quick select. Usually the former way is enough. The possible follow-up is, how to speed up the computation of distances? We can certainly store them along with the point coordinates in the heap, and further more, we can compute the distance(d) of the corner point of the smallest square that wraps the furthest circle, and compare the Manhanttan distance of each point d_m with d(Manhanttan distance between (p1,p2) and (q1, q2) is `|p1 - q1| + |p2 - q2|`), instead of using Euclidean distances. If d_m < d, we then compute and compare the Euclidean distances(x^2 + y^2). Several things should be noted:
  * For any point p, d_m(p, q) >= d_e(p, q).
  * If d_m(p1, q) < d_m(p2, q), than d_e(p1, q) < d_e(p2, q).
  * For points having the same d_m, their d_e could differ greatly.
  * If the coordinates are Integers, we need to use Long to store the Euclidean distances, which is big enough even if x and y equal to Integer.MAX_VALUE.
  * References: http://www.weiming.info/zhuti/JobHunting/32078455/


## Sorting and Searching
### Knowledge(Refer to Coding Manual and Robert's Algorithms)
* Source code for sortings is in Sortings.java.
* Insertion sort, bubble sort and selection sort. Although all of them run in O(n^2) time using O(1) space, insertion sort is usually the most efficient(fastest, even faster than other O(nlogn) sorting algorithms when the array is small), while bubble sort is the least efficient. This can be seen from the source code, the number of basic operations in each loop of bubble sort is simly larger than the other two. When the input array is mostly or fully sorted, insertion sort can perform much less operations, while selection sort still performs almost as many as usual. Selection sort generally performs more comparisons than insertion sort, but less writes. Also note that the first two algorithms are stable, while the selection sort is not. Example: for [9, 3, 2, 9, 1], the first 9 will be swapped with 1, leading to a change of relative order of the two 9s. Swapping two elements that are not necessarily adjacent
is usually the cause of unstability.
* Merge sort. O(nlogn) time. Needs auxiliary space. Can be implemented using top-down or bottom-up approaches. The space usage can be reduced a bit by adding more array copy operations and do in-place changes instead of returning a new array. The speed can be further improved by using insertion sort instead of merge sort when the subarray is small.
* Quick sort. Worst case O(n^2) time and on average O(nlogn) time. To make sure it runs in O(nlogn), either the pivot is choosen randomly or the array is shuffled before partitioning. Two partition schemes exist -- Lomuto(used in Introduction to Algorithms) and Hoare(similiar to my implementation, which seems to be more popular, but not easy to understand.??).  
As per Robert's Algorithms, the average performance of quick sort is usually better than the other O(nlogn) sorting algorithms, due to fewer data movements and comparisons. The speed can be further improved by using insertion sort instead of quick sort when the subarray is small.
* Heap sort. The algorithm is in Heap chapter. O(nlogn) time and O(1) space, unstable. The number of comparisons is simliar to quick sort, but the cache misses are much larger than the other sorting algorithms, because the array entries are rarely compared with nearby array entries(locality of reference is lower). Cache is always loaded in blocks and each block contains data that are adjacent to each other. Thus quick sort still outperforms heap sort most of the time.
* Linear time sorts.
  * Counting sort. It tries to put each input element directly to the destination according to the number of elements that are no greater than it, which is computed beforehand. *Introduction to Algorithms*
    - Counting sort is efficient if the range of input data is not significantly greater than the number of objects to be sorted. Consider the situation where the input sequence is between range 1 to 10K and the data is 10, 5, 10K, 5K.
    - It works as long as the input elements can be mapped to a range of integers. So even if the input elements are negative or characters, it could still work.
    - Counting sort is stable. Thus it can be used as a subroutine in radix sort.
  * Radix sort. This is useful when each of the input elements consists of several(preferably known and fixed) columns. In such case, this algorithm uses stable sort to sort from the least significant to the most significant column. E.g, if the input elements are d-digit integers:
  ```
  Radix-sort(A, d):
      for i = 1 to d {
          stable_sort(like counting sort) A on digit i;
      }
  ```
  If we do stable sort for each digit using linear sorts like counting sort, the radix sort runs in O(d(n+k)) time, or O(dn) since k is less than 10. However in practice radix can be easily outperformed by other sorting algorithms when d is larger than logn. Also radix sort requires more space and less flexible than the other in-place sorting algorithms. 
  https://www.quora.com/If-Radix-sort-has-a-better-time-complexity-why-is-quick-sort-preferred-both-in-APIs-and-in-terms-of-interviews
  Overall, radix sort is a stable sort.
* External sort to deal with big data and small memory. Divide the original data into several parts, sort each part in memory and save it back to a file, and finally use k-way merge sort to merge k files.  
https://en.wikipedia.org/wiki/External_sorting  
http://faculty.simpson.edu/lydia.sinapova/www/cmsc250/LN250_Weiss/L17-ExternalSortEX2.htm
* Binary search. 
  * The goal is to find a certain element(or the largest element that is smaller than it, or the smallest element that is larger than it, etc)in a sorted array.
  * Cases to consider:
    - Valid case, including when the target element is on the edge.
    - Target element is within the range of the array but not contained in the array
    - Target element is out of the range of the array, either smaller than the minimum element in the array or greater than the maximum element in the array.
    - For above cases, don't forget the case when the input array has no or only one element.
  * Type of search problems and its implementations. General idea is to start
    with initial range of candidates [low, high], compare target with a[mid](or a[low], a[high] if necessary), then narrow down the candidates by half and search in the new range. Pay attention to how mid value is calculated since we want the range to keep shrinking to 1. For problems that the range can not be cut down to half(time is more than O(logn)), low or high can be increased or decreased gradually.
    1. Traditional problem -- return the index of the element if found, otherwise return -1. [low, high] keeps shrinking and the size will always become 2 or 1 eventually, which is the range of possible ids. Each iteration makes sure that any id that lies out side this range is not possible to be the result(or has been considered). Therefore if target exists, it will eventually lies in a window of size 1 and target == a[mid] will always happen.
    ```java
      int binarySearch(int[] a, int target) {
          int low = 0;
          int high = a.length - 1;
          int mid;
          while (low <= high) {
              //Avoid potential overflow, can also written as:
              //low + ((high - low) >> 1)
              //The parenthesis must be there!
              mid = low + (high - low)/ 2; 

              if (target > a[mid]) {
                  low = mid + 1;
              } else if (target < a[mid]) {
                  high = mid - 1;
              } else {
                  return mid;
              }
          }
          return -1; // Error
      }

      int binarySearchRecursive(int[] a, int target, int low, int high) {
          if (low > high) return -1;// Error
          int mid = low + (high - low) / 2;
          if (target > a[mid]) {
              return binarySearchRecursive(a, target, mid+ 1, high);
          } else if (target < a[mid]) {
              return binarySearchRecursive(a, target, low, mid - 1);
          } else {
              return mid;
          }
      }
    ```
    2. Returns the index of first element that is greater than or equal to the target element. Returns n if all elements in the array of size n are less than the target element. Loop invarient is that the result id is always in
    [low, high], which is always shrinking, and low <= high always holds. mid is always between low and high and smaller than high, so mid is always valid. In the case when target is greater than any element in nums, the returned value must be n. In other cases, there must be a valid id and it is always eaqual to the returned value.
    ```java
      int firstGreaterEqual(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target > nums[mid]) {
                low = mid + 1;
            } else {
                //When target == nums[mid], we need to search the left part
                //to make sure the result is the FIRST element that is greater
                //than or eaual to the target
                high = mid;
            }
        }
        return low;
      }
    ```
    3. Returns the index of last element that is less than or equal to the
    target element. Returns -1 if all elements in the array are greater than the target element. Proof is similar to the previous case.
    ```java
      int lastLessEqual(int[] nums, int target) {
          int low = -1;
          int high = nums.length - 1;
          while (low < high) {
              int mid = high - (high - low) / 2;
              if (target < nums[mid]) {
                  high = mid - 1;
              } else {
                  low = mid;
              }
          }
          return high;
      }
    ```
    4. Another way to deal with the above two problems is to intialize a variable at the begining to record the index of last found candidate. The rest is similar to the basic binary search and after the loop finishes, that variable should contain the final result. See EPI for the solutions to above problems.
    5. Search a certain element that satisfies some condition. No explicit
    target value. The algorithm is usually to first check the middle value for
    a certain condition and use that condition to determine whether the next step is to go to the left half or the right half. The key is to determine what condition to use.
* When implementing customized comparator, make sure it satisfies three properties listed here: [https://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html#compare(T,%20T)]

### Problems
* Binary Search Problems.
  - [Leetcode] Search Insert Position(Algorithms*). Type 2.
  - [Leetcode] Search for a Range(Algorithms*). Type 4.
  - [Leetcode] Sqrt(x). (Algorithm**).  Type 3.
  - [Leetcode] Search in rotated sorted array I and II(Algorithm**). Type 1 with changes. Remember the algorithm!
  - [Leetcode] Find Minimum in Rotated Sorted Array I and II(Algorithm**). These problems return the min value rather than the id. Similar to http://practice.geeksforgeeks.org/problems/maximum-value-in-a-bitonic-array/0
  - [EPI] Search local minimum in partially sorted array(Algorithm). Type 6. Description is in CodingPractices/EPI/SearchLocalMinimum.java
* Sorting Problems.
  - [Leetcode]Kth largest element in an array(Algorithm* and implementation* of quick select?)
  - [Leetcode]Sort Colors(Algorithm**). Two-passes constanct-space solution is actually better than the one-pass solution.


## Dynamic Programming and Memoization
### Knowledge
* DP and greedy algorithm. In greedy algorithm, each step tries to achieve the local optimum, while in DP, each step is not necessarily the local optimum, but can definitely achieve global optimum. To prove if a greedy algortithm works or not, think like this: assuming there is a correct solution that achieves global optimum, for each step in the greedy solution, can it replace the first step in the correct solution? If it can, then the greedy solution can achieve the global optimum, otherwise, it cannot.
* Comparison of DP and Memoization. (http://stackoverflow.com/questions/6184869/what-is-difference-between-memoization-and-dynamic-programming)
  - If the problem want to find out the max/min value, or counting how many ways to solve the problem, or checking the exisitence, it is often a sign of optimization problem which can potentially be tackled using DP/Memoization. Search algorithms like DFS/BFS can also be used, but sometimes the search space could be extremely large, making them not preferable approaches. Greedy algorithm can solve them too, which is often hard to come up with, error-prone, and should be used as a last resort. For some problems, divide and conquer can also be used to solve them. -- [Leetcode]Maximum Subarray.
  - DP is usually bottom-up while memoization is top-down. 
  - Memoization can run a bit faster than DP when not all of the sub problems need to be computed. However as a sacrifice, it has higher memory usage due to the recursion stack, and the size of cache cannot be reduced like that of DP, which could be reduced to one dimension sometimes(like in the following Longest Common Subsequence problem). ---[Leetcode]Scramble String, Interleaving String. For the problems that requires returning a value, DP is preferable as the advantage of Memoization over DP in running time is often negligible. However for the problems that require returning all the valid paths, Memoization is preferable as DP cannot do it easily.  
  When using memoization, first think carefully about what variables determine a sub-problem, before deciding how to implement the cache. Usually they are the same as the parameters of the recursive function(not including the cache itself). And the cache usually stores the same type of data as the return value of the recursive function. ---[Leetcode]Target Sum.
* Thinking steps.
  1. Always think about brute force way first, unless you know how to solve it using DP at first glance. It may be helpful to coming up with DP solution. 
  2. Break the original problem into subproblems and then create the recursive formulas. DP/memoization works well when the subproblems overlap, otherwise simple recursion works better. Sometimes instead of breaking the original problem, we can break a variation of it whose solution can easily lead to the solution of the original problem. Think about the order of indices first before writing down the formulas. Also make sure use indices symbols that are different from the input parameters. Don't forget to write down the range of valid indices of the recursive formulas (and the target!). Double check the formula before moving on(don't forget things like plus 1!).
  Ways of breaking the problem:
      - For sequences(usually multiple), consider s[l-1] when solving the problem for s[l], which stores the result of sequence with length l. (It is often preferable to use length as index of cache rather than the index of sequences, since the starting elements in the cache ususally have length zero, and negative index of sequences. The index of cache should be non-negative. And the initial state for zero index cache value is usally harder to determine than zero length cache value) This way is commonly used when the input has multiple sequences(the cache is multi-dimentional), or the current value depends on multiple previous values(really?) --[Leetcode]Longest Common Subsequence, [Leetcode]Edit Distance. However if both iteration directions(left to right and right to left) exist in the DP loop, it is better not to use length representation. --[Leetcode]Maximal Rectangle. And for some simple grid problems, the boundary value is very easy to determine and using index rather than length is okay too.
      - For single sequence problems, consider s[i-1] when solving the problem for s[i], which contains the result of sequence starting or ending with element at index i. It might be surprising that this can also be used as one dimention when the subproblem is multi-dimentional. Similar to previous way, length representation can also be used here, especially when the cache is multi-dimentional. -- [Leetcode]Maximum Subarray.
      - Sometimes it is good to have a final process to conquer the results of all the subproblems, which could help design subproblems that are easier to solve in less time. -- [Leetcode]Best Time to Buy And Sell Stock, generaliazation DP solution.
      - If the cache is multi-dimensional, like d[m][l], when coming up with the recursive formula, try considering its relation with all possible subproblems, like d[m-1][l], d[m][l-1], or a combination of them. -- [Lintcode]Maximum Subarray III.
      - Sometimes multi-dimensional cache in the recursive formula can be reduced to one-dimentional by fixing the other dimention(usually that requrires another iteration to get the final result). -- [Leetcode]Palindrome Partitioning II, Maximum Subarray III.
      - Sometimes need to maintain multiple db arrays, e.g. Let d[i][1] denote select the current element and d[i][0] denote not select. Or d[i][k] denote including the current element, and d[i][t] denote not including. This is often used for problems similar to 0-1 knapsack problem. This kind of problem can also be visualized, like in [Leetcode]Paint House II. [Leetcode]Maximum product subarray, house robber I, Target Sum, Paint House.
      - The recursive relation may not necessarily involve adjacent indices. Sometimes we need to come up with it by observing the pattern/attribute from the problem. -- [GeeksForGeeks]Longest Arithmetic Progression, sorted array.
  3. Implement the solution.
    * Draw a graph to visualize dimentions, boundaries, and DP formula first.
    * If using DP and the path needs not to be returned, consider reducing the size of cache. For many problems the size can be reduced to one or two 1D arrays or even a few variables. Sometimes we need to swap the indices in the recursive formula to make it easier to optimize space usage. A swap of array pointers is often used at the end of each iteration if two 1D arrays are used, or use the way in Minimum Adjustment Cost. For more number of arrays, maintain a pointer array and rotate the pointers to the left. But we can save more space using following ways:
      - Previous visited element of the current array can be stored lazily to reduce two arrays to only one.-- [Leetcode]Edit Distance. 
      ```
               a_i
                |
      b_i-1 <- b_i
      ```
      if b_i is computed from b_i-1 and a_i, then one array is enough to store them, if iterating from left to right.
      - If only elements in previous row are used to compute the one in the current row, consider doing the iteration from right to left:
      ```
       a_i-1 a_i
          \   |
             b_i
      ```
      if b_i is computed from a_i-1 and a_i, then one array is enough to store them, if iterating from right to left.
      - If the computing the current element requires elements from the current row and previous row, more variables need to be mainained to store the previous elements in the previous row:
      ```
      a_i-k, a_i-k+1 .. a_i-1     a_i
           \     \        \        |
                        b_i-1 <-  b_i
      ```
      If k is a constant, then we need k variables p_i-k, p_i-k+1, ... p_i-1 to store a_i-k ... a_i-1. Use variable temp to store a_i, and after computing and storing the new values(b_i) to the array, shift the values in p to the left : p_i-k = p_i-k+1, p_i-k+1 = p_i-k+2 ... p_i-1 = temp. -- [Leetcode]Maximal Square.
    * Think about the starting values carefully, especially those out of the range of valid cache elements. Edges like when any of the indices is 0 often need to be treated separatively. Note that the recursive formulas assume that the previous state is valid. So when coding be sure to check the cache values that are next to the starting values(using the dependency graph is helpful to see what elements depend on the edge elements). Be careful when using a seperate for-loop to handle the edge elements, which could repeat some logic and is error-prone since some repeated logic(like update of certain variable) could be missing.
    * The max/min in the recursive formulas can often be computed on the fly instead of using a separate loop. This may be more obvious by switching the dimention variables sometimes. This trick can often reduce the degree of time complexity, but is hard to come up with and should be used after there is a working DP formula.
    * Prefer using boolean expression to if-else statements to implement the recursive formulas.--[Leetcode] Interleaving String.
  4. When the path needs to be returned, we have following options:
      - Cache the optimal path for each subproblem and return the result in O(1) time. However the downside is it is very costly in space.
      - If the cache for the optimal results is in 2D, we can reconstruct the path from the final problem by iterating the cache, which can be done in linear time.
      - If the cache for the optimal results is already reduced to 1D, then another 2D array should be created to store the index/direction of subproblems. Iterating this new array like above can reconstruct the path in linear time too.
      The above options also apply to memoization.

### Problems
* Grid problems.  
  Usually the boundary values are easy to determine, and using index representation is simpler than length representation. In some cases when the boundary values require complex logic to determine, using length representation might be preferable.
  Remember the thought of recursive relation.
  - [Leetcode] Unique Paths I and II(Algorithm). Boundary value is very easy to deal with.
  - [Leetcode] Minimum Path Sum(Algorithm and Implementation*). 
  - [Leetcode] Maximal Square(Best algorithm*).
  - [Leetcode] Maximal Rectangle (DP Algorithm** and implementation of DP solution*). Prefer the histogram solution. Remember the dp algorithm also, and be careful about the dp relation(very error prone). 
* Single sequence problems.  
  Typically using index representation is enough since the initial value is often easy to determine. However if input is string, or the current value depends on multiple previous values, or current state has multiple dimensions, length representation is often preferable.
  - [Leetcode] Maximum Subarray(Algorithm*). Note two edge cases: 
    + The input array is null or zero length. Expected 0.
    + Otherwise, the contiguous subarray must not be empty!
  - [Leetcode] Maximum Product Subarray(Algorithm*).
  - [Leetcode] Paint House(Algorithm and Implementation).
  - [Leetcode] Paint House II(Algorithms** and implementations*). Remember the efficient way of finding minimum elements except a certain element. Remember the best algorithm with least space usage.
  - [Leetcode] House Robber I(Algorithm).
  - [Leetcode] House Robber II(Algorithm*).
  - [Leetcode] Decode ways(Algorithm). Clarification: '02' can not be decoded using 2 -> 'B' since there is a preceding 0.
  - [Leetcode] Decode ways II(Algorithm and Implementation**). Need to do mod for every element.
  - [Lintcode] Longest Increasing Continuous Subsequence(Algorithm).
  - [Leetcode] Longest Increasing Subsequence(Best Algorithm**).
  - [Leetcode] Russian Doll Envelopes (Best Algorithm**). Similar to above, but requires another trick to deal with envelopes of equal widths. If the question allows the envelope to rotate, then we can sort the width and height of each envelope first so that (w <= h) always holds, and then sort the entire array. 
  - [Lintcode] Minimum Adjustment Cost(Algorithm*). 
* Multiple sequence problems. Usually need to use length representation.
  - [Lintcode] Longest Common Subsequence(Algorithms* and Implementations). Very good introductory problem. Remember the algroithm to get paths for DP and Memoization solutions.
  - [Leetcode] Interleaving String(Algorithm*).
  - [Leetcode] Edit Distance(Algorithm**)
  - [Leetcode] K Edit Distance(Best Algorithm** and implementation*).
  - [Leetcode] One Edit Distance(Algorithm*). Remember the algorithm and its proof
  - [Leetcode] Distinct Subsequences(Algorithm)
  - [Leetcode] Regular Expression Matching(Algorithm* and Implementation*). Initial values are a bit hard to determine.
  - [Leetcode] Wildcard Matching(Best Algorithm** and its implementation**). Using lp as the second dimension is good for saving space for this and the previous problem, since pattern string is usually much shorter than text string.
* Partitioning problems. The problem usually asks to partition a sequence of n elements, either into k parts, or without this constraint. Each part should satisfy a certain property. The state is usually defined as d[i][j] representing the value of first j elements with i parts satisfying the given property. If no constaint of number of parts, then d[j] is usually enough, and iterate each possible last part satisfying given property. Note that length representation is usually the preferable choice.
  - [Leetcode] Coin Change(Algorithm*). Be careful about the value when there is no valid combination with target sum. Also a good example of getting min/max of multiple elements in each iteration.
  - [Leetcode] Perfect Squares(Algorithm).
  - [Leetcode] Palindrome Partitioning II(Best Algorithm** and Best Implementation**).
  - [Lintcode] Copy Books(Algorithms**). What is the O(nk) solution?
* Bit manipulation problems.
  - [Leetcode] Counting Bits(Algorithm)
* Game problems. Think of the final states first and then think reversely to find out the underlying pattern.
  - [Lintcode] Coins In Line(Algorithm*).
* Backpack problems. Usually use d[i][w] representing the first i items adding up to weight w(or with limit of weight w). Check if the last item is included or which one. Length representation is preferred here.
  - [Lintcode] Backpack I (Algorithms*). Greedy algroithm won't work.
  - [Lintcode] Backpack V (Algorithm). Be careful about the initial values.
  - [Leetcode] Combination Sum IV(Best Algorithm*)(same as [Lintcode] Backpack VI). And follow-up. Try walking through examples is a great way of finding out the problem and solution to it.
  - [Lintcode] Backpack II (Algorithm). 0-1 knapsack problem. Understand why greedy doesn't work here.
  - [Lintcode] Backpack III (Algorithm*). Unbounded knapsack problem. Greedy doesn't work for it either.
  - [Leetcode] Ones and Zeroes (Algorithm). Note that greedy algorithm that chooses shortest string first doesn't work. E.g., s = {1, 11, 001, 0001}, m = 5, n = 2.
  - [Lintcode] k sum. (Algorithm**). If the input elements and target could be negative, it can also be solved using DP or Memoization.
* Interval problems. Usually use d[i][j] representing interval between [i..j]. Usually use index representation. This way is better than using l(interval length) as a dimension in terms of optimizing space usage. 
  - [Leetcode] Longest Palindromic Subsequence(Algorithm* and implementation**). Remember the best implementation. It is better than iterating l and i since the latter is hard to optimize space usage from O(n^2) to O(n).
  - [Lintcode] Coins In Line III(Algorithms**).
  - [Lintcode] Scramble String(Algorithm*). If there are multiple sequences, using l as a dimension might help reduce the total number of dimensions.
  - [Leetcode] Burst Balloons(Algorithm**). If iterating l in the outer loop, ususally it is very hard to optimize the space usage.
* [Lintcode] Maximum Subarray II(Algorithm*). Assuming that none of the subarray can be empty. Need to master the two-passes solution.
* [Lintcode] Maximum Subarray III(Algorithm**). 
* Find the number of subsets that have sum equals to s(elements and s are positive):
  - [Leetcode] Target Sum(Best Algorithm**).
 


## Other Concepts and special problems, including greedy problems.
* Best Time to Buy and Sell Stock problems
  - [Leetcode] Best Time to Buy and Sell Stock I and II(Algorithms).
  - [Leetcode] Best Time to Buy and Sell Stock III(Multiple algorithms**)
  - [Leetcode] Best Time to Buy and Sell Stock IV(Multiple algorithms**). Remember the state transition DP and interval DP(not practiced yet). The former one seems better as it can be applied to III using minum extra space.
* Use graphs to see the essence!
  - [Leetcode] Jump Game(Best Algorithm**).
  - [Leetcode] Frog Jump(Algorithm**).
* Parentheses related problems.  
  Two ways of thinking: 
  1. Using a stack and iterate a string, whenever you encounter a ')', check if the top of the stack is '(', if so, pop it, otherwise push ')'. For any substring representing valid parenthese, if you scan from left to right, each time a ')' comes in, there must be a '(' matching it, which is always the closest unmatched '(' to it. Similarly case if you scan from right to left.
  2. Compare the numbers of '(' and ')' encountered so far and compare them. For any valid parentheses, those number should be equal. Using counters of parentheses can easily calculate the length of formed parentheses. 
  - [Leetcode] Valid Parentheses(Algorithm)
  - [Leetcode] Longest Valid Parentheses(Algorithms** and implementation* of constanct space solution). 
* Arithmetic expression evaluation.
  - [Leetcode] Basic Calculator II(See previous).
  - [Leetcode] Basic Calculator I(Algorithms* and Implementations*). Might be better to deal with the digits first in each iteration. How to solve it when the operators have * and / ??
  - [Leetcode] Different Ways to Add Parentheses(Algorithm**).Remember the way of enumerating different order of calculating an arithmetic expression.
  - [Leetcode] Expression Add Operators(Algorithm** and Implementation*). Remember the best backtracking logic and implementation. Note that "00" is not valid number. Also the overflowed values should not be counted.