#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <list>
#include <functional>
#include <cmath>
#include <cstring>
#include <string>

using namespace std;

#define MAX_N 55
#define EMPTY '.'
#define DOOR '#'
#define WALL '*'
#define MIRROR '!'

int N;

char board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N][4];

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

struct Node
{
	int r, c, dir, cnt;
	Node(int rr, int cc, int dd, int ccnt)
	{
		r = rr, c = cc, dir = dd, cnt = ccnt;
	}
	bool operator<(const Node& y) const
	{
		return cnt > y.cnt;
	}
};

int bfs(int sr, int sc, int er, int ec)
{
	priority_queue<Node> pq;

	for (int i = 0; i < 4; i++)
	{
		int nr = sr + dx[i];
		int nc = sc + dy[i];

		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			continue;
		if (board[nr][nc] == WALL)
			continue;

		isVisited[sr][sc][i] = true;
		Node initNod(sr, sc, i, 0);
		pq.push(initNod);
	}

	while (!pq.empty())
	{
		int curDir = pq.top().dir;
		int curCnt = pq.top().cnt;

		int nr = pq.top().r + dx[curDir];
		int nc = pq.top().c + dy[curDir];
		pq.pop();

		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			continue;

		if (nr == er && nc == ec)
			return curCnt;

		if (isVisited[nr][nc][curDir] == true)
			continue;
		isVisited[nr][nc][curDir] = true;

		if (board[nr][nc] == WALL)
			continue;
		else if (board[nr][nc] == MIRROR)
		{
			for (int i = 0; i < 4; i++)
			{
				if (i == curDir || i == (curDir + 2) % 4)
					continue;

				Node addNod(nr, nc, i, curCnt+1);
				pq.push(addNod);
			}
		}
			
		Node addNod(nr, nc, curDir, curCnt);
		pq.push(addNod);
	}
	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 0; i < N; i++)
		scanf("%s", board[i]);

	int d[2][2], dCnt = 0;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			if (board[i][j] == DOOR)
				d[dCnt][0] = i, d[dCnt++][1] = j;

	printf("%d\n", bfs(d[0][0], d[0][1], d[1][0], d[1][1]));
	return 0;
}
