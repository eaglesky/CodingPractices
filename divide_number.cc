#include <iostream>
#include <vector>
using namespace std;

int running = 0;

void printVec(vector<int>& vec)
{

    for (size_t i = 0; i < vec.size(); ++i)
    {
        cout << vec[i] << " ";
    }
    cout << endl;
}

int divideNumber(int sum, int k, vector<int>& solution)
{
    if ((k == 0) && (sum == 0)) {
//        cout << "Correct: ";
//        printVec(solution);

        running ++;
        return 1; 
    } else if ((k == 0) || (sum == 0)) {
//        cout << "Wrong: ";
//        printVec(solution);
        running++;
        return 0;
    }

    int upperBound;
    if (solution.empty()) {
        upperBound = sum;
    } else {
        upperBound = solution.back();
    }
    int count = 0;

    upperBound = (upperBound > sum) ? sum : upperBound;
    for (int i = 1; i <= upperBound; ++i)
    {
        solution.push_back(i);
        count += divideNumber(sum-i, k-1, solution);
        solution.pop_back();
    }

    return count;
}

int main(int argc, char** argv)
{
    int n, k, res;
    cin >> n >> k;
//    cout << "n = " << n << ", " << "k = " << k << endl;

    vector<int> solution;
    res = divideNumber(n, k, solution);
    cout <<  res << endl;

  //  cout << "Running: " << running << endl;
    return 0;
}
