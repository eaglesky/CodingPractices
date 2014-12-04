#include <iostream>
#include <vector>

using namespace std;

int* computeNextArray(string Pattern)
{
    int m = Pattern.size();
    if (m == 0)
        return NULL;

    int* next = new int[m];

    next[0] = -1;
    int j = -1;
    for (int i = 1; i < m; ++i)
    {
        while (j > -1 && (Pattern[j+1] != Pattern[i]))
        {
           j = next[j]; 
        }
        
        if (Pattern[j+1] == Pattern[i])
            j++;

        next[i] = j;

    }

    return next;
}

// Output all the locations of pattern in the text and return the first
// position
int kmp(string text, string pattern)
{
    int n = text.size();
    int m = pattern.size();

    if ((m == 0) || (m > n))
        return -1;
   
    int* next = computeNextArray(pattern);
    int j = -1;
    bool found = false;
    int retPos = -1;


    for (int i = 0; i < n; ++i)
    {
        while (j > -1 && (text[i] != pattern[j+1]))
            j = next[j];

        if (text[i] == pattern[j+1])
            j++;

        if (j == m-1) {
            cout << "Substring found: " << i - m + 1 << endl;

            if (!found) {
                found = true;
                retPos = i - m + 1;
            }
                
            j = next[j];
        }
    }

    delete[] next;

    return retPos;
}

int main(int argc, char** argv)
{
    string textStr = "abc abcdab abcdabcdabde";
    vector<string> patternStrs = {"", "f", "abcf", "dabcdaf", "fabc",  "ab", "dabde", "abc ", "abc abcdab abcdabcdabde", "abc abcdab abcdabcdabdef"};

    cout << "Text string: " << textStr << endl;
    for (int i = 0; i < patternStrs.size(); i++)
    {
        cout << patternStrs[i] << " : " << endl;
        int firstPos = kmp(textStr, patternStrs[i]);
        cout << firstPos << endl;
        cout << endl;
    }

    return 0;
}
