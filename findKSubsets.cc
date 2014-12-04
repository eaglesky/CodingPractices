#include <cstdlib>
#include <iostream>
#include <vector>
using namespace std;

int timeCount = 0;
int timeCountGetNum = 0;

int getKSubsetsNum(int* A, int i, int k, int** memoized)
{

    if (memoized[i][k] >= 0)
        return memoized[i][k];

    if  ( i < k - 1 ) {
        return 0;
    }  

    if ( (i == k - 1 ) || ( k == 0 )) {
        return 1;
    }

    int count1 = getKSubsetsNum(A, i-1, k-1, memoized); 
    int count2 = getKSubsetsNum(A, i-1, k, memoized);
    int count = count1 + count2;
    memoized[i][k] = count;
    timeCountGetNum++;

    return count;
}


void findKSubsetsRec(int* A, int n, int k, int i, vector<vector<int> >& results, vector<int>& curResult)
{
    if (k == 0) {
        results.push_back(curResult);
        return;
    }

    if ( (n - i) < k ) {
        return;
    }

    curResult.push_back(A[i]);
    findKSubsetsRec(A, n, k-1, i+1, results, curResult);

    curResult.pop_back();
    findKSubsetsRec(A, n, k, i+1, results, curResult);
    
    timeCount++;

}

void findKSubsets(int* A, int n, int k, vector<vector<int> >& results)
{
    vector<int> cur;
    findKSubsetsRec(A, n, k, 0, results, cur);
}

int main(int argc, char** argv)
{
    if (argc < 3) {
        cout << "Usage: findKSubsets n k" << endl;
        return -1;
    }

    int n = atoi(argv[1]);
    int k = atoi(argv[2]);

    cout << "Test array: ";
    int* testArray = new int[n];
    for (int i = 0; i < n; ++i)
    {
        testArray[i] = i + 1;
        cout << testArray[i] << " ";
    }
    cout << endl;

    int** cache = new int*[n];
    for (int i = 0; i < n; ++i)
    {
        cache[i] = new int[k+1];
        for (int j = 0; j < k+1; ++j)
            cache[i][j] = -1;
    }

    
    vector<vector<int> > results;
    findKSubsets(testArray, n, k,results);
    for (int i = 0; i < results.size(); ++i)
    {
        for (int j = 0; j < results[i].size(); ++j)
            cout << results[i][j] << " ";

        cout << endl;
    }

    cout << "Time count: " << timeCount << endl;
    int totalNum = getKSubsetsNum(testArray, n-1, k, cache); 
    cout << "Total Number: " << totalNum << endl;
    cout << "Ratio: " << ((float)timeCount/totalNum) << endl;

    cout << "Time count of getting total number: " << timeCountGetNum << endl;

    for (int i = 0; i < n; ++i)
        delete[] cache[i];
    delete[] cache;
    delete[] testArray;
    return 0;
}
