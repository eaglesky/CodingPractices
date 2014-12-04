#include <iostream>
#include <cstring>
#include <cassert>
//#include <cstdlib>

using namespace std;

/*
size_t myStrLen(const char* str)
{
    size_t sz = 0;
    if (!str) 
        return sz;

    const char* ptr = str;
    while (*ptr != '\0') {
        sz++;
        ptr++;
    }

    return sz;
}
*/

size_t myStrLen(const char* str)
{
    const char *ptr;
    for (ptr = str; *ptr; ++ptr) {}

  //  if (!(*ptr))
  //      cout << "NULL: " << *ptr << endl;

    return (ptr - str);
}

// Return destination string
char* myStrCpy(char* to, const char* from)
{
    assert(to != NULL && from != NULL);
    char* ptrTo = to;
    while((*ptrTo++ = *from++) != '\0');

    return to;
}

int main(int argc, char** argv)
{
    const char* tests[] = {"", "a", "allen", "allen chin!"};

    for (int i = 0; i < 4; i++)
    {
        cout << tests[i] << ": " << myStrLen(tests[i]) << ", " << strlen(tests[i]) << "(truth)" << endl;        
    }

    char test_out1[14];
    char test_out2[1];

    char* ret = myStrCpy(test_out1, tests[2]);
    cout << "Result1: " << ret << endl;

    ret = myStrCpy(test_out2, tests[2]);
    cout << "Result2: " << ret << endl;

    return 0;
}
