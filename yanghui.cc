#include <iostream>
#include <deque>
#include <cstdlib>

using namespace std;

bool printYangHui(int degree)
{
    int degreeCount = 0;
    if (degree < 0) {
        cout << "Degree must not be negative! " << endl;
        return false;
    }

    deque<int> queue{1, 0};
    cout << "Yang Hui Triangle of degree " << degree << ": " << endl;
    int prev = 0;
    while(1) {
        int cur = queue.front();
        queue.pop_front();
        queue.push_back(cur+prev);

        if (cur == 0) {
            degreeCount++;
            if (degreeCount > degree)
                break;
            queue.push_back(0);

            cout << endl;
        } else {
            cout << cur << " ";
        }
        prev = cur;

        
    }
    cout << endl;

    return true;
}

int main(int argc, char** argv)
{
    if (argc < 2) {
        cout << "Usage: yanghui d" << endl;
        return 1;
    }
    int degree = atoi(argv[1]);
    printYangHui(degree);

    return 0;
}
