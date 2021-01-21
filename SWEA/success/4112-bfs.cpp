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

#define MAX_N 10005

int tc;
int a, b;
int res;
int depth[MAX_N];
bool isVisited[MAX_N];

void init() {
	memset(isVisited, 0, sizeof(isVisited));

	scanf("%d%d", &a, &b);
}

void preSolution() {
	int i = 1;
	int curDepth = 1;
	while (i < MAX_N) {
		for (int j = 0; j < curDepth; i++, j++) {
			if (i >= MAX_N) break;
			depth[i] = curDepth;
		}
		curDepth++;
	}
}


int bfs() {
	queue<int> q;
	q.push(a);
	isVisited[a] = true;

	int cnt = 0;
	while (!q.empty()) {

		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++) {
			int cur = q.front();
			q.pop();
			if (cur == b)
				return cnt;

			int next;

			// left-top
			next = cur - depth[cur];
			if (!isVisited[next] && depth[next] == depth[cur] - 1) {
				isVisited[next] = true;
				q.push(next);
			}

			// right-top
			next = cur - depth[cur] + 1;
			if (!isVisited[next] && depth[next] == depth[cur] - 1) {
				isVisited[next] = true;
				q.push(next);
			}

			// left
			next = cur - 1;
			if (!isVisited[next] && depth[next] == depth[cur]) {
				isVisited[next] = true;
				q.push(next);
			}

			// right
			next = cur + 1;
			if (!isVisited[next] && depth[next] == depth[cur]) {
				isVisited[next] = true;
				q.push(next);
			}

			// left-bottom
			next = cur + depth[cur];
			if (!isVisited[next] && depth[next] == depth[cur] + 1) {
				isVisited[next] = true;
				q.push(next);
			}

			// right-bottom
			next = cur + depth[cur] + 1;
			if (!isVisited[next] && depth[next] == depth[cur] + 1) {
				isVisited[next] = true;
				q.push(next);
			}
		}
		cnt++;
	}
	return 0;
}

void solution() {
	res = bfs();
}

void print() {
	printf("#%d %d\n", tc, res);
}

int main() {
	freopen("in.txt", "r", stdin);

	preSolution();

	int testcase;
	scanf("%d", &testcase);
	for (tc = 1; tc <= testcase; tc++) {
		init();
		solution();
		print();
	}
}
