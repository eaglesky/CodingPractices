#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

typedef struct {
    int n;
    int color;
} State;

void printBoolMat(bool** mat, int n)
{
    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < n; ++j)
            cout << mat[i][j] << " ";
        cout << endl;
    }
}

// Return the number of solutions using iterative DFS
int dfsIterSolve(bool** adjMat, int n)
{
    vector<State> stack;
    int count = 0;
    int* history = new int[n];

    for (int i = 1; i < 5; ++i)
    {
        State iniState = {0, i};
        stack.push_back(iniState);
    }

    while (!stack.empty()) 
    {
        State curState = stack.back();
        stack.pop_back();

        int pid = curState.n;
        int c = curState.color;

        history[pid] = c;
        if (pid == n-1) {
            count++;
            continue;
        }

        for (c = 1; c < 5; ++c)
        {
            bool ok = true;
            for (int j = 0; j < pid+1; ++j)
            {
                if (adjMat[pid+1][j] && (history[j] == c)) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                State newState = {pid+1, c};
                stack.push_back(newState);
            }
        }
    }

    delete[] history;
    return count;
}

int main(int argc, char** argv)
{
    ifstream fin("four_colors_input.txt");
    int n;
    fin >> n;

    cout << "Number of points: " << n << endl;
    bool** adjMat = new bool*[n];
    for (int i = 0; i < n; ++i)
    {
        adjMat[i] = new bool[n];
        for (int j = 0; j < n; ++j)
        {
            fin >> adjMat[i][j];
        }
    }

    printBoolMat(adjMat, n);

    cout << "Number of solutions: " << dfsIterSolve(adjMat, n) << endl;

    for (int i = 0; i < n; ++i)
        delete[] adjMat[i];
    delete[] adjMat;

    fin.close();
    return 0;
}
