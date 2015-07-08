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
```
Reference:

1. *The C++ Standard Library -- A Tutorial and Reference*, Nicolai
2. *STL source code analysis*, Hou Jie
3. [http://en.cppreference.com/w](http://en.cppreference.com/w)
4. *Head first Java*


