#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <algorithm>
#include <string>
#include <cstring>
#include <map>
#include <vector>
#include <queue>

using namespace std;

int main() {
    string str;
    cin >> str;

    vector<char> srcVec, nextVec;
    for (int i = 0; i < str.length(); i++) {
        srcVec.push_back(str[i]);
        nextVec.push_back(str[i]);
    }
    sort(nextVec.begin(), nextVec.end());

    int res = 0;
    do {
        int sum = 0;
        for (int i = 0; i < srcVec.size(); i++) {
            if (srcVec[i] < nextVec[i])
                sum++;
        }
        res = max(res, sum);
    } while (next_permutation(nextVec.begin(), nextVec.end()));

    cout << res << endl;
}
