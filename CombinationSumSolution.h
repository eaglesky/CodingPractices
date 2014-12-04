#include <vector>
using namespace std;

class CombinationSumSolution {
public:    
    vector<vector<int> > combinationSum(vector<int> &candidates, int target) {
        vector<vector<int>> solutions;
        vector<int> oneSolution;
       
        dfsSolve(candidates, solutions, oneSolution, 0, target);

        return solutions;
    }

private:
    void dfsSolve(vector<int>& candidates, vector<vector<int>> &solutions, vector<int>& oneSolution, int curSum, int target) {
        int last ;
        if (oneSolution.size() > 0)
            last = oneSolution.back();
        else 
            last = 0;

        int rest = target - curSum;
        if (rest < 0)
            return;
        else if (rest == 0) {
            solutions.push_back(oneSolution);
            return;
        }

        for (int i = 0; i < candidates.size(); ++i)
        {
            int cur = candidates[i];
            if (cur >= last) {
                oneSolution.push_back(cur);
                int sum = curSum + cur;
                dfsSolve(candidates, solutions, oneSolution, sum, target);
                oneSolution.pop_back();
            }
        }
    }
};
