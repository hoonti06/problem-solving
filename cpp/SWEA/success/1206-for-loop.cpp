#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
#include <cstdlib>

using namespace std;

#define MAX_N 1005

int N;
int board[MAX_N];
int res;
int tc;

void init() {
	res = 0;
}

void solution() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++)
		scanf("%d", &board[i]);

	for (int i = 0; i < N; i++) {
		int maxNum = 0;
		for (int j = -2; j <= 2; j++) {
			int curIdx = i + j;
			if (curIdx < 0 || curIdx >= N || curIdx == i)
				continue;

			maxNum = max(maxNum, board[curIdx]);
		}
		int diff = board[i] - maxNum;
		if (diff > 0)
			res += diff;
	}
	printf("#%d %d\n", tc, res);
}

int main() {
	for (tc = 1; tc <= 10; tc++) {
		init();
		solution();
	}
}
