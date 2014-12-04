#include <iostream>
#include <vector>
using namespace std;

void swap(vector<int> &num, int i, int j)
{
	int temp = num[i];
	num[i] = num[j];
	num[j] = temp;
}
    
void reverse(vector<int> &num, int i, int j)
{
	while (i < j) {
	    swap(num, i, j);
	    i++;
	    j--;
	}
}

void nextPermutation(vector<int> &num) {
        int len = num.size();
        
        int i;
        for (i = len-2; i >=0; --i)
        {
            if (num[i] < num[i+1])
                break;
        }
        
        if (i == -1) {
            reverse(num, 0, len-1);
            return;
        }
        
        int j;
        for (j = len-1; j>=0; --j)
        {
            if (num[j] > num[i])
                break;
        }
        swap(num, i, j);
        reverse(num, i+1, len-1);
    }
    
    
void printVector(vector<int>& num)
{
    for (int i = 0; i < num.size(); ++i)
    {
        cout << num[i] << " ";
    }
    cout << endl;
}

int main(int argc, char** argv)
{
    vector<vector<int>> tests {
        {1, 2, 3, 4},
        {4, 3, 2, 1},
        {1, 1, 2, 2, 1, 1},
        {4}
    };

    for (int i = 0; i < tests.size(); ++i)
    {
        cout << "Original: ";
        printVector(tests[i]);
        nextPermutation(tests[i]);
        printVector(tests[i]);
        cout << endl;

    }
    return 0;
}
