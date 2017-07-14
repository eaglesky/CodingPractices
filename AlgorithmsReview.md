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
    - [Leetcode]Longest Substring Without Repeating Characters(Implementation*)
    - [Leetcode]Longest Substring with At Most K Distinct Characters(Algorithm*)
    - [Leetcode]Longest Substring with At Least K Repeating Characters(Multiple Algorithms*, one of them is two pointer**)
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
* [Leetcode]Increasing Triplet Subsequence(Algorithm**). This can also be solved using idea similar to the previous problem, but it would use O(n) extra space. The best solution can also be applied to checking the existence of increasing subsequence of any length. Useful trick and can be applied to other problems.  
When finding max/min element, we can either use reference type variable instead of primitive type so that we can use null as the initial value, or use index of the element and -1 as the initial value. The latter way may require slightly more code when update. Both ways are preferable to using primitve type and Integer.MAX_VALUE/Integer.MIN_VALUE as the initial value, since the latter way cannot return a reasonable value when there is no max/min value found. Or if the logic is simple, we can use the first element as initial value.


## Strings

### Kowledge
* JAVA: String, StringBuilder and char[]. 
  * StringBuilder is essentially a wrapper of dynamic char array , and append(string) basically copy each character from input string to the end of existing char array. When deleting, it will shift the characters after those deleted forward. To remove the last element in the StringBuilder, just use sb.setLength(sb.length() - 1). toString () method returns a copy of wrapped char array, wrapped by a newly created String. setCharAt(index, char) is also an useful method to update a certain char in the StringBuilder. Also note that StringBuilder doesn't have isEmpty() method while String does.
  * char[] array usually has a better performance than StringBuilder since it is simpler. But when dynamically adding new elements to it, usually another variable charLen is needed to record the current length(number of filled elements in the array). Creating a string from it is simply using new String(char[] value, int offset, int count). char[] can make backtracking easier since you can just override the old elements when searching a new branch. But StringBuilder is also useful and saves you from maintaining the length of the array.
  * If we have to keep inserting strings/chars to the front, there is a better way -- append the strings/chars to the end and at last call StringBuilder.reverse(). This can make the code run in O(n) time instead of O(n^2) time.
* Palindrome.
  * Definition. A string that reads the same backward as forward, e.g., madam.
  Checking if a string is a palindrome can be easily done in O(n) time. And so is checking if a linked list a palindrome, which can also be done using O(1) space if reversing the first/second half of the original list is allowed.
  * Check if concatenation of two strings is a palindrome. The reverse of the
  second string has to be the 1)the prefix of the first string, and the rest of
  the first string is a palindrome, or 2)the first string is the prefix of the reverse of second string, and the rest of the reversed string is a palindrome. [Leetcode]Palindrome Pairs.
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