#include <iostream>
#include <vector>
using namespace std;

int findLocalMinimum(const vector<int>& array)
{
    int n = array.size();
    if (n <= 2)
        return -1;
    int left = 0;
    int right = n-1;
    
    while (left < right - 1) {
        int mid = left + ((right - left) >> 1);
        if (mid == left)
            return -1;

        if ((array[mid] <= array[mid-1]) && (array[mid] <= array[mid+1]))
            return mid;
        else if (array[mid] > array[mid-1])
            right = mid;
        else
            left = mid;
    }

    return -1;
}

int main(int argc, char** argv)
{
//    vector<int> test = {3,2,1,0,-1,8,11};
    vector<int> test = {3,2, 1, 0, -1, -2, -3, -4, 5};
    cout << findLocalMinimum(test) << endl;
    return 0;
}
