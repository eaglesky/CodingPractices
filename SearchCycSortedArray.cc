#include <iostream>
#include <vector>
using namespace std;

int search0(const vector<int>& array)
{
    int n = array.size();
    int left = 0;
    int right = n-1;
    if (array[left] < array[right])
        return left;
    
    int minId = 0;
    while (left < right) {

        int mid = left + ((right - left) >> 1);
        if (array[left] < array[mid]) {
            minId = (array[left] < array[minId]) ? left : minId;
            left = mid + 1;
        } else {
            minId = (array[mid+1] < array[minId]) ? (mid+1) : minId;
            right = mid;
        }
    }

    return (array[left] < array[minId]) ? left : minId;
}

//Improved solution
int search(const vector<int>& array)
{
    
    int n = array.size();
    int left = 0;
    int right = n-1;

    while (left < right) {
        int mid = left + ((right - left) >> 1);
        if (array[mid] > array[right])
            left = mid + 1;
        else
            right = mid;
    }
    return left;
}

//If there are duplicate elements, then for the case array[mid] ==
//array[right], both half must be searched. Hence recursive solution should be
//used. See EPI Page 136 for details.

int main(int argc, char** argv)
{
    vector<int> test = {378, 478, 550, 631, 103, 203, 220, 234, 279, 368};
//    vector<int> test = {1};
    cout << search(test) << endl;
    return 0;
}
