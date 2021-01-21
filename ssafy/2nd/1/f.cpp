#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <algorithm>
#include <string>
#include <cstring>
#include <map>

using namespace std;

int main() {
    string dest, src;
    cin >> dest >> src;

    map<char, int> srcMap;
    for (int i = 0; i < src.length(); i++)
        srcMap[src[i]]++;

    int res = 0;
    for (int i = 0; i < dest.length(); i++) {
        map<char, int> destMap;
        for (int j = i; j < i + src.length() && j < dest.length(); j++)
            destMap[dest[j]]++;

        map<char, int>::iterator it;
        for (it = srcMap.begin(); it != srcMap.end(); it++) {
            if (destMap[it->first] != it->second) {
                res--;
                break;
            }
        }
        res++;
    }
    cout << res << endl;
}
