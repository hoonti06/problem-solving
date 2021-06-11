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
int tc;
bool isVisited[MAX_N][2];
int board[MAX_N][MAX_N];
int sr, sc;

int dx[4] = { -1, 0, 1,  0};
int dy[4] = {  0, 1, 0, -1};

void init() {
	memset(board, 0, sizeof(board));
	res = -1;

	int tmp;
	scanf("%d", &tmp);
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++) {
			scanf("%d", &board[i][j]);
			if (board[i][j] == 2) {
				sr = i;
				sc = j;
			}
		}
}

void solution() {
	int dir = 0;
	int r = sr;
	int c = sc;
	while(true) {
		int ndir = dir;
		switch(dir) {
			case 0:
				if (r == 0) {
					res = c;
					return;
				}

				for (int i = 1; i < 4; i++) {
					if (i == 2) continue;

					int nr = r + dx[i];
					int nc = c + dy[i];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (board[nr][nc] == 1) {
						dir = i;
						break;
					}
				}
				break;
			case 1:
			case 3:
				int nr = r + dx[dir];
				int nc = c + dy[dir];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 0) {
					dir = 0;
				}
				break;
		}
		r += dx[dir];
		c += dy[dir];
	}
}

void print() {
	printf("#%d %d\n", tc, res);
}

int main() {
	freopen("in.txt", "r", stdin);

	int testcase = 10;
	for (tc = 1; tc <= testcase; tc++) {
		init();
		solution();
		print();
	}
}
