#include <iostream>
#include <cstdlib>
using namespace std;

//Solve equation: ax + by = gcd(a, b)
//Input: a , b
//Output: x, y
int extGCDIter(int a, int b, int& x, int& y)
{
    int x0 = 1;
    int y0 = 0;
    int x1 = 0;
    int y1 = 1;

    while(b!=0) {
        int r = a % b;
        int qua = a / b;
        a = b;
        b = r;
        int x1Next = x0 - qua*x1;
        int y1Next = y0 - qua*y1;
        x0 = x1;
        x1 = x1Next;
        y0 = y1;
        y1 = y1Next;
    }
    x = x0;
    y = y0;
    return a;
}


//Solve equation: ax + by = gcd(a, b)
//Input: a , b
//Output: x, y
int extGCDRec(int a, int b, int& x, int& y)
{
    if (b == 0) {
        x = 1;
        y = 0;
        return a;
    }
    int gcd = extGCDRec(b, a % b, y, x);
    y -= (a/b) * x;
    return gcd;
}

int main(int argc, char** argv)
{
    if (argc < 3) {
        cout << "Usage: ext_gcd a b" << endl;
        return 1;
    }

    int a = atoi(argv[1]);
    int b = atoi(argv[2]);
    cout << "a = " << a << ", b = " << b << endl;

    int x = 0;
    int y = 0;
    int gcd = extGCDRec(a, b, x, y);
    cout << "(a, b) = " << gcd << endl;
    cout << "x = " << x << ", y = " << y << endl;
    return 0;
}
