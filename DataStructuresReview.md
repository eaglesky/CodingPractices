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

(How does it resize?)
```
//C++ STL vector
//The capacity will double when full, but it never shrinks unless shrink_to_fit() is called
std::vector<std::string> words4(5, "Mo");
std::vector<int> v = {0, 1, 2, 3, 4, 5};
```

Reference:
* The C++ Standard Library -- A Tutorial and Reference, Nicolai
* STL source code analysis, Hou Jie
* [http://en.cppreference.com/w/cpp/container/vector](http://en.cppreference.com/w/cpp/container/vector)

