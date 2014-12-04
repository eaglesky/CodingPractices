#include "CombinationSumSolution.h"
#include <iostream>

int main(int argc, char** argv)
{
    CombinationSumSolution cs;
    vector<int> candidates {2};
    int target = 1;

    vector<vector<int>> solutions = cs.combinationSum(candidates, target);

    for (int i = 0; i < solutions.size(); ++i)
    {
        for (int j = 0; j < solutions[i].size(); ++j)
        {
            cout << solutions[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}
