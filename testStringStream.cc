#include <iostream>
#include <sstream>
using namespace std;

int main(int argc, char** argv)
{
    int a = 10;
    double b = 4.556;
    string s = "Allen";
    string s2 = "Chin";

    stringstream result;
    result << a << " " << b << " " << s << " " << s2;
    cout << result.str() << endl;
    cout << s + s2 + to_string(b) << endl;
    return 0;
}
