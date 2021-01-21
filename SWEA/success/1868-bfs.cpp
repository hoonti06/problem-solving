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

#define MAX_N 305

int tc;
int N;
bool isVisited[MAX_N][MAX_N];
string board[MAX_N];
int num[MAX_N][MAX_N];
int res;
int dx[8] = { 0, -1, 0, 1, -1, -1,  1, 1};
int dy[8] = {-1,  0, 1, 0, -1,  1, -1, 1};

void init() {
	memset(isVisited, 0, sizeof(isVisited));
	memset(num, 0, sizeof(num));
	res = 0;

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> board[i];
	}
}

void bfs(int sr, int sc) {

	queue<pair<int, int> > q;
	q.push(make_pair(sr, sc));

	while (!q.empty()) {
		int r = q.front().first;
		int c = q.front().second;
		q.pop();

		for (int i = 0; i < 8; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (isVisited[nr][nc])
				continue;
			isVisited[nr][nc] = true;

			if (num[nr][nc] == 0)
				q.push(make_pair(nr, nc));
		}
	}
}

void solution() {

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (board[i][j] == '*') {
				isVisited[i][j] = true;
				continue;
			}

			for (int k = 0; k < 8; k++) {
				int nr = i + dx[k];
				int nc = j + dy[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;

				if (board[nr][nc] == '*')
					num[i][j]++;
			}
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) { 
			if (!isVisited[i][j] && num[i][j] == 0) {
				isVisited[i][j] = true;
				bfs(i, j);
				res++;
			}
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) { 
			if (!isVisited[i][j] && num[i][j] != 0) {
				isVisited[i][j] = true;
				res++;
			}
		}
	}
}

void print() {
	cout << "#" << tc << " " << res << endl;
}

int main() {
	freopen("in.txt", "r", stdin);

	int testcase;
	scanf("%d", &testcase);
	for (tc = 1; tc <= testcase; tc++) {
		init();
		solution();
		print();
	}
}
