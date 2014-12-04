#include <iostream>
#include <deque>
#include <vector>

using namespace std;


class Point {
    public:
        Point(int ix, int iy):x(ix), y(iy) {}
        int x;
        int y;
};

void printStatus(vector<vector<bool>>& status)
{

    int rowNum = status.size();
    int colNum = status[0].size();

    cout << "Free status (1 represents free): " << endl;
    for (int i = 0; i < rowNum; ++i) 
    {
        for (int j = 0; j < colNum; ++j)
        {

            cout << status[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

//Input: status indicating whether a location is free or not
//Output: parents storing coordinates of parents
bool findShortestPath(vector<vector<bool>>& status, vector<vector<Point>>& parents)
{

    int rowNum = status.size();
    int colNum = status[0].size();

    vector<vector<bool>> visited (rowNum, vector<bool>(colNum, false));

    deque<Point> queue;
    Point c(0, 0);
    queue.push_back(c);
    visited[0][0] = true;

    bool pathFound = false;

    while (!queue.empty())
    {
        Point curPoint = queue.front();
        queue.pop_front();

        int curX = curPoint.x;
        int curY = curPoint.y;

//        cout << "(" << curX << ", " << curY << ")" << endl;

        // Examine 4 neighbors of current points
        for (int i = 0; i < 4; ++i)
        {
            int x = curX;
            int y = curY;
            if (i == 0) {
                x = curX - 1;
            } else if (i == 1) {
                x = curX + 1;
            } else if (i == 2) {
                y = curY - 1;
            } else {
                y = curY + 1;
            }

            if (x < 0 || y < 0 || x >= rowNum || y >= colNum)
                continue;

            Point neiPoint(x, y);
            if (status[x][y] && !visited[x][y]) {
                queue.push_back(neiPoint);
                visited[x][y] = true;
                parents[x][y] = curPoint;

                if ((x == rowNum - 1) && (y == colNum - 1)) {
                    pathFound = true;
                    break;
                }      
            }
        }

        if (pathFound)
            break;
    }

    return pathFound;
}

bool printPath(vector<vector<Point>>& parents, int x, int y)
{
        
    if (x < 0 || y < 0) {
        cout << "No valid path!" << endl;
        return false;
    }
    bool ret;

    Point parent = parents[x][y];
    if (x != 0 || y != 0)
        ret =  printPath(parents, parent.x, parent.y);

    cout << "(" << x << ", " << y << "), ";

    return ret;
}

int main(int argc, char** argv)
{
    vector<vector<bool>> status {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 1, 1, 1, 1},
      {1, 0, 0, 0, 1}, {1, 1, 1, 0, 1}};

    int rowNum = status.size();
    int colNum = status[0].size();
    cout << "Maze: " << rowNum << " x " << colNum << endl;

    vector<vector<Point>> parents(rowNum, vector<Point>(colNum, Point(-1, -1)));

    printStatus(status);

    bool pathIsFound = findShortestPath(status, parents);

    if (!pathIsFound)
        cout << "Path not found!" << endl;
    printPath(parents, rowNum-1, colNum-1);
    cout << endl; 
    return 0;
}
