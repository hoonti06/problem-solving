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

#define MAX_N 105

int N = 100;
int res;
int minimum;
int tc;
bool isVisited[MAX_N][2];
int board[MAX_N][MAX_N];
vector<int> startc;
int sr, sc;

int dx[4] = { -1, 0, 1,  0};
int dy[4] = {  0, 1, 0, -1};

void init() {
	memset(board, 0, sizeof(board));
	minimum = 1e9;
	res = -1;
	startc.clear();

	int tmp;
	scanf("%d", &tmp);
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			scanf("%d", &board[i][j]);

	for (int i = 0; i < N; i++)
		if (board[0][i] == 1)
			startc.push_back(i);
}

void subProcess(int sc) {
	int r = 0;
	int c = sc;
	int dir = 2;

	int cnt = 0;
	while(true) {
		cnt++;

		switch(dir) {
			case 2:
				if (r >= N-1) {
					if (minimum > cnt) {
						minimum = cnt;
						res = sc;
					}
					return;
				}
				int sideDir[2];
				sideDir[0] = 1, sideDir[1] = 3;

				for (int i = 0; i < 2; i++) {
					int curDir = sideDir[i];
					int nr = r + dx[curDir];
					int nc = c + dy[curDir];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (board[nr][nc] == 1) {
						dir = curDir;
						break;
					}
				}
				break;
			case 1:
			case 3:
				int nr = r + dx[dir];
				int nc = c + dy[dir];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 0)
					dir = 2;
				break;
		}
		r += dx[dir];
		c += dy[dir];
	}

}

void solution() {
	for (int idx = 0; idx < startc.size(); idx++) {
		subProcess(startc[idx]);
	}
}

void print() {
	printf("#%d %d\n", tc, res);
}

int main() {
	int testcase = 10;
	for (tc = 1; tc <= testcase; tc++) {
		init();
		solution();
		print();
	}
}
