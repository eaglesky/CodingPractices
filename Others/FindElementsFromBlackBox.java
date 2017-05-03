/*
Given a black box containing some sorted elements, it has three APIs:
pop(): randomly pop an element out, which could be either the first one or the last one.
peek(): randomly peek an element, which could be either the first one or the last one.
isEmpty(): check if the black box is empty.

Write a function that takes out all the elements out from the black box and keeps the order.
Assuming there are no duplicates.

http://www.1point3acres.com/bbs/thread-158720-1-1.html

Solution:

With peek:

After poping an element, say a, we can then call peek to see the next element b. If a < b, 
then a must be the first element, otherwise a must be the last one. So we can keep two
linked list call smaller and larger, keep poping and peeking the elements, insert the 
element to the smaller linked list if it is the first one, or to the larger linked list if 
it is the last one. And finally concatenate the two linked lists.

Without peek:

Still maintain two linked list, smaller and larger. Compare the popped element a with the last
element b in the smaller linked list, if a > b, then keep b(b must be the previous first element),
and insert a to the smaller linked list, otherwise move b to the larger linked list and then
insert a to the smaller linked list. 

This can be applied to the case when there are duplicates. We can insert a after b when a >= b,
instead of a > b. However the relative order of the duplicate elements could change. Don't know
how to keep it yet.
*/

