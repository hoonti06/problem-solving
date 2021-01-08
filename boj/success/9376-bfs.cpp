#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <cstring>

using namespace std;

#define MAX_N 110

#define WALL '*'
#define DOOR '#'
#define PRISONER '$'
#define EMPTY '.'

void printVisited();

int N, M;
char board[MAX_N][MAX_N];
int isVisited[MAX_N][MAX_N][3];

int dx[4] = {  0, -1, 0 , 1 };
int dy[4] = { -1,  0, 1 , 0 };


struct Node
{
	int r, c, dist;
	Node(int rr, int cc, int dd)
	{
		r = rr, c = cc, dist = dd;
	}
	bool operator<(const Node& y) const
	{
		return dist > y.dist;
	}
};

int bfs(int sr, int sc, int num)
{
	priority_queue<struct Node> pq;
	struct Node initNod(sr, sc, 0);
	isVisited[sr][sc][num] = 0;
	pq.push(initNod);

	while (!pq.empty())
	{
		int r = pq.top().r;
		int c = pq.top().c;
		int curDist = pq.top().dist;
		pq.pop();

		for (int i = 0; i < 4; i++)
		{
			int nr = r + dx[i];
			int nc = c + dy[i];

			if (nr < 0 || nr > N || nc < 0 || nc > M)
				continue;

			if (isVisited[nr][nc][num] != 1e9)
				continue;

			int nDist = curDist;
			if (board[nr][nc] == WALL)
				continue;
			else if (board[nr][nc] == DOOR)
				nDist++;

			isVisited[nr][nc][num] = nDist;

			Node addNod(nr, nc, nDist);
			pq.push(addNod);
		}
	}
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for(int testcase = 0; testcase < T; testcase++)
	{
		scanf("%d%d", &N, &M);
		for (int i = 0; i <= M+1; i++)
			board[0][i] = board[N+1][i] = EMPTY;

		for (int i = 1; i <= N; i++)
		{
			scanf("%s", &board[i][1]);
			board[i][0] = EMPTY;
			board[i][M+1] = EMPTY;
		}
		N++, M++;

		int p[2][2], pCnt = 0;
		for (int i = 0; i <= N; i++)
		{
			for (int j = 0; j <= M; j++)
			{
				if (board[i][j] == PRISONER)
					p[pCnt][0] = i, p[pCnt++][1] = j;	

				for (int k = 0; k < 3; k++)
					isVisited[i][j][k] = 1e9;
			}
		}

		bfs(0, 0, 0);
		bfs(p[0][0], p[0][1], 1);
		bfs(p[1][0], p[1][1], 2);


		long long res = 1e15;
		for (int i = 0; i <= N; i++)
		{
			for (int j = 0; j <= M; j++)
			{
				long long sum = 0;
				for (int k = 0; k < 3; k++)
					sum += isVisited[i][j][k];

				if (board[i][j] == DOOR)
					sum -= 2;

				res = min(res, sum);
			}
		}
		printf("%lld\n", res);
	}
	return 0;
}
