#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
#include <cstdlib>
#include <cmath>
#include <string>
#include <set>

using namespace std;

#define MAX_N 22

int tc;
int R, C;
bool isVisited[MAX_N][MAX_N][4][16]; // [R][C][dir][num]
string board[MAX_N];
bool res;
int dx[4] = { 0, -1, 0, 1};
int dy[4] = {-1,  0, 1, 0};

void init() {
	memset(isVisited, 0, sizeof(isVisited));

	cin >> R >> C;
	for (int i = 0; i < R; i++) {
		cin >> board[i];
	}
}

struct Node {
	int r, c, d, s;
	Node(int _r, int _c, int _d, int _s) {
		r = _r;
		c = _c;
		d = _d;
		s = _s;
	}
};

void solution() {
	queue<Node> q;
	Node startNod(0, 0, 2, 0);

	q.push(startNod);
	isVisited[0][0][2][0] = true;

	int cnt = 0;
	while (!q.empty()) {
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++) {
			int r = q.front().r;
			int c = q.front().c;
			int dir = q.front().d;
			int s = q.front().s;
			q.pop();

			bool isQuestion = false;
			switch(board[r][c]) {
				case '<':
					dir = 0;
					break;
				case '^':
					dir = 1;
					break;
				case '>':
					dir = 2;
					break;
				case 'v':
					dir = 3;
					break;
				case '_':
					if (s == 0)
						dir = 2;
					else
						dir = 0;
					break;
				case '|':
					if (s == 0)
						dir = 3;
					else
						dir = 1;
					break;
				case '?':
					isQuestion = true;
					break;
				case '.':
					break;
				case '@':
					res = true;
					return;
				case '+':
					s = (s+1) % 16;
					break;
				case '-':
					s = (s == 0 ? 15: s - 1);
					break;
				default:
					s = board[r][c] - '0';
					break;
			}
			if (!isQuestion) {
				int nr = (r + dx[dir] + R) % R;
				int nc = (c + dy[dir] + C) % C;
				if (isVisited[nr][nc][dir][s])
					continue;

				isVisited[nr][nc][dir][s] = true;
				Node nod(nr, nc, dir, s);
				q.push(nod);
			}
			else {
				for (int i = 0; i < 4; i++) {
					int nr = (r + dx[i] + R) % R;
					int nc = (c + dy[i] + C) % C;

					if (isVisited[nr][nc][i][s])
						continue;

					isVisited[nr][nc][i][s] = true;
					Node nod(nr, nc, i, s);
					q.push(nod);
				}
			}
		}
		cnt++;
	}
	res = false;
	return;
}

void print() {
	cout << "#" << tc << " " << (res ? "YES" : "NO") << endl;
}

int main() {
//	freopen("in.txt", "r", stdin);

	int testcase;
	scanf("%d", &testcase);
	for (tc = 1; tc <= testcase; tc++) {
		init();
		solution();
		print();
	}
}
