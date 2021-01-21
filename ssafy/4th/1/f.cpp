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

#define MAX_N 105

int dp[MAX_N][MAX_N];

int N;
int board[MAX_N];

int main() {
    string str;
    cin >> str;

    freopen("in.txt", "r", stdin);
    scanf("%d", &N);
    for (int i = 0; i < N; i++)
        scanf("%d", &board[i]);

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)
            dp[i][j] = 1;
    }

    for (int j = N-1; j >= 0; j--)
        if (board[0] == board[j]) {
            dp[0][0] = 2;
            dp[0][j] = 2;
        }

    for (int i = 0; i < N; i++) {
        for (int j = N-1; j >= i; j--) {
            if (board[i] == board[j]) {
                dp[i][j]

            }

        }
    }
    

}
