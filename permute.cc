#include <iostream>
#include <cstring>
#include <unordered_set>

using namespace std;

void swap(char* str, int i, int j)
{
    char temp = str[i];
    str[i] = str[j];
    str[j] = temp;
}

// Find all the permutation of a given string
// Repetition is taken into account
int permute(char* str, int start, int end)
{
    int ret = 1;
    if (start == end) {
        cout << str << endl;
    } else if (start < end) {
        ret = 0;
        unordered_set<char> hset;
        
        for (int i = start; i <= end; ++i)
        {
            char curChar = str[i];
            if (hset.find(curChar) != hset.end()) {
                continue;    
            } 
            hset.insert(curChar);
            swap(str, start, i);
            ret += permute(str, start + 1, end);
            swap(str, start, i);
        }
    }

    return ret;
}

int permuteIter(char* str)
{
    int len = strlen(str);    
}

int main(int argc, char** argv)
{
    char testStrs[][20] = {"ABC", "AAA", "AABBCCC", "ABCDEFG"};
    for (int i = 0; i < 4; i++)
    {
        cout << "Test String: " << testStrs[i] << endl;
        int count = permute(testStrs[i], 0, strlen(testStrs[i])-1);
        cout << "Total: " << count << endl;
        cout << endl;
    }
    return 0;
}
