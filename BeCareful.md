# Things should be noted

## Good workflow when tackling a interview question
* Ask clarification questions, especially about the case which could give unsure result. Make sure to understand all the input cases before solving the problem. Think about a naive way to solve it and then think of better way only when the interviewer says it exist(good thing about this, you can know the time complexity of worst approach, and guess the possible time complexity of the best approach, which can be a very useful hint). Or to deal with the naive case first, like when there are no dulicates in a list. Go with the best algorithm only if you have seen it before. Usually the algorithm may not be correct at first, and this needs to be found out by trying more test cases(sometimes may need to walk through an example). For some problems you need to draw some graphs to visualize it, and you need to make sure that they can cover enough cases, and you may need to extend it to see more cases. You may also need to show a few test cases you need! Make sure your algorithm works for all normal cases and edge cases and ask clarification questions if you are not sure about the expected output in some cases.
* After you and your interviewer agree that this algorithm works, and the interviewer agrees that you can move on to write the code, start it immediately! Pay attention to your handwriting as best as you can. Don't spend too much time thinking about how to best implement it, just choose a robust way first and then refactor it later. And don't think too much about handling edge cases first. That can be done later too.  
Also don't think too much about whether using a separate helper function. Just do it in one place first. Only use helper function when it is obvious that some part of code is repeated.
When get stuck in implementation, explain why you are stuck -- like why do you think certain ways of implementation is not good and trying to think of a better way.
* After you are done writing the code, explain the code while checking it. Bugs are often introduced due to failing to consider some use cases or the code behaving differently from what you think(both could result in failure in part of the test cases!). Check the code line by line so that you can find out some typos or minor bugs. While doing this, it is better to also walk through a normal test case to see if there is anything missing. Make sure the test case is short and representative to save time. Also think about all posible cases at each part of the code, and how the code behaves under the hood. After changing the code, this process might need to be repeated until you are sure that it can handle ALL test cases correctly. Understanding the exact expected output for all major use cases is important here especially for some parsing problems where output could be very long and easy to miss something. Also don't forget to check the return value, and if there are fields added to the class, check if they are initialized and updated correctly.
* Review the AlgorithmsReview about possible corner cases for each type of problem!
	1. When the string has numbers, consider how numbers starting with '0' like '001' should be treated. 
	2. For math related problems(as long as there is some arithmetic operations involved), remember to check overflow!
	3. For heap/stack/queue or any collection related usage, remember the case when the structure is empty, especially when peek is used!
	4. For sequence related problems, there could be duplicates. This matters if the solution uses hashmap.
  5. When iterating over a collection and the end condition involves multiple variables, put the id check at first! Also make sure there is no out-of-boundary or null-pointer exception in the end condition! When checking using a specific use case, be sure to complete the iteration so that you can cover the end condition check!
  6. For recursive functions, make sure to check if the parameters are out-of-range(and if they really are what you expect, e.g., id!). Check the end condition and related edge cases!
* For implementation problems with a lot of corner cases, pay more attention to writing robust code with good style than performance! Also focus on implementing one most common case correctly and than adjust the code to handle other cases. Refactor the code to improve the performance after you have a working solution!
* Refactor the code if the code is correct. This could be done earlier before checking the correctness of the code if the code needs obvious refactoring.

## Online coding interview preparation
* Be sure to get used to the code pad beforehand, including how to run the code and see the results in the console(scrolling and reset).
* Get familar with adding a main function to the Solution class and other nitty-gritty on Java. This will save the compling time.
* Prepare pens and paper if it is a phone interview.
* Check the headphone and microphone if it is phone interview.
* Before debugging, first do a visual check. Don't let any unsure places go -- correct it even if you are unsure why it leads to the incorrect result. Be careful about the API calls as they often perferms differently from what you expect if you don't read the doc. (e.g., String.split(String regex))
* After running the code and got the wrong anser, do first compare the actual and expected result and guess where could be wrong first, and then debug by adding logs.


1. Be careful about the end value of iterator after the for loop!!

2. When adding/multiply two numbers digits by digits, consider the following cases:

(1) Different lengths 

(2) Last carry

(3) Null number(when number is represented as a list)

Leetcode: Add Two Numbers, Add Binary

3. Don't forget pushing the iterator forward in a loop!

4. When dealing with pointers to the linked list nodes, be careful to consider
whether they are NULL or not first.

5. For all functions that take a linked list pointer as an parameter, consider
the case when the list is NULL first;

6. Change the iterator first before "continue"

7. Be careful about the boundary check in the loop especially when it is
embedded in other loops!

8. For string related problems, always consider the case when the string is
empty!

9. When treating a digit character as a digit, make sure to convert it to an
integer first! (int d = 'c' - '0')

10. When dealing with integers, make sure to check the overflow cases(INT_MAX
and INT_MIN). And the method is to check whether the base is greater than
INT_MAX/10(and there is more digigts comming)
or not first, and then check the next digit to see if it is greater
than 7. Also be careful about the fact that |INT_MIN| = |INT_MAX| + 1, and
therefore it is possible for overflow to happen when negating an negative
number(this can be solved using unsigned int). Also for increasing number,
always be careful using the time operation on it. Try to avoid using that 
and use division instead. Another way of checking overflows is to use unsigned
int or long long type to hold the number first and then check it.

Leetcode: String to Integer , Pow(x, n), Palidrome number, Divide two integers

11. Carefully consider test cases outside your own algorithm!

12. Don't forget the returned value! (Write it first in a function!)

13. Anagrams refer to multiple strings(more than 1)! Leetcode : Anagrams

14. When use string.find method, remember to check the case when the target
string is not found.

15. Always remember to check the case when the vector is empty!

16. Find swapped nodes in BST(no duplicates), pay attention to the case when
the swapped nodes are neighbors.

17. Pay attention to the definition of BST:

(1)The left subtree of a node contains only nodes with keys less than the node's
key.

(2)The right subtree of a node contains only nodes with keys greater than the
node's key.

(3)Both the left and right subtrees must also be binary search trees.

18. Pay attention to the recursive relation of finding the depth of BST.

19. When doing recursion on binary trees, think carefully about what will
happen on the NULL child nodes.

20. DFS, pay attention to the starting position of the next recursive function
and don't forget checking visited status.

21. Be careful that string.size() is unsigned int type, so try avoid using it
to do operations with other integer and using the result as a terminal
condition.

22. When computing double number from integer, pay attention to the conversion.
Leetcode : Max Points on a line

23. For questions related to string, consider the case when the string pointer
is NULL(if it is c-string) and when the strings are empty(or part of them are
empty) (Leetcode: strstr)

24. Be careful when put the embeded "if" out when there is an "else" statement
associated with the outer "if"!
Leetcode: Simplify Path

25. When thinking about possible values of function paramters, be sure to
consider all the places where the function gets called!

26. When considering numberic string and do convertion of string to number, be
careful about the case when there are leading zeros!

27. When changing the structure of a binary tree, be careful about the child
pointers in the node, make sure they are NULL when they are not pointing
anywhere in the new tree.
Leetcode: Binary Tree Upside Down.

28. Be careful about the possible overflow cases: Negation, shifting, and all
other basic operations.

29. When iterating two array/strings at the same time, don't use the same
iterator!
Leetcode: One Edit Distance.

30. Think if a parameter is really necessary when writting a recursive function. Sometimes
we make the function return special value. 

31. Consider using hash map when searching for an element in an array.

32. When implementing an algorithm, think carefully about each parameter of a function
-- whether it is necessary or not, if it can be simplified. Improper paramter could
complicate the implementation!

33. Multiple solutions are usually:
(1)Implemented with multiple data structures;
(2)Recursive / iterative
(3)Same data structure, but different algorithms.

34. Always think about if the divisor could be zero or not!

35. Use iterator(ListIterator if you want to modify the list on the fly, or just
for-each loop) to iterate the List interface instead of the accessor, because list.get
(i) could take linear time if it is implemented with linked list. When using the
iterator, avoid breaking the loop in the middle and then resume the iteration, becasue
the position of iterator after breaking the loop is usually not easy to handle.
Try putting all the logic in one loop instead. 

36. If an iteration can be executed repeatedly in a for loop, be careful when modifing
the value of a variable based on its original value, cause it might be called multiple
times. --[Leetcode]Maximal Rectangle.

37. When iterating a array of elements interval by interval, don't forget to handle
the last one! --[Leetcode]  Longest Substring with At Least K Repeating Characters

38. When comparing adjacent elements in a for loop, if we store prev element in a
separate variable, don't forget to update it at the end of each iteration.

39. Always think about if an object could be null before calling a method of it, especially
in chaining calls.