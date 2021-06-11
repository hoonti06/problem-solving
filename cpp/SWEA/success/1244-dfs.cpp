#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
#include <cstdlib>
#include <cmath>
#include <string>

using namespace std;

#define MAX_N 1000010

int N;
int res;
int tc;
bool isVisited[MAX_N][2];
string input;

void init() {
    memset(isVisited, 0, sizeof(isVisited));
    res = 0;

    cin >> input >> N;
}

void dfs(int cnt) {
    int cur = stoi(input);
    isVisited[cur][cnt%2] = true;

    if (cnt >= N) {
        res = max(res, cur);
        return;
    }

    for (int i = 0; i < input.size(); i++) {
        for (int j = i+1; j < input.size(); j++) {
            swap(input[i], input[j]);

            int next = stoi(input);
            if (!isVisited[next][(cnt+1)%2])
                dfs(cnt+1);

            swap(input[i], input[j]);
        }
    }
}

void solution() {
    dfs(0);

    for (int i = 0; i < MAX_N; i++)
        if (isVisited[i][N%2])
            res = max(res, i);
}

void print() {
    cout << "#" << tc << " " << res << endl;
}

int main() {
    freopen("in.txt", "r", stdin);

    int testcase;
    cin >> testcase;
    for (tc = 1; tc <= testcase; tc++) {
        init();
        solution();
        print();
    }
}
