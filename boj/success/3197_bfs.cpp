#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
#include <cmath>
#include <map>

using namespace std;

#define MAX_N 1505
#define WATER '.'
#define ICE 'X'
#define SWAN 'L'

int N, M;

char board[MAX_N][MAX_N];
bool swanVisited[MAX_N][MAX_N];
bool waterVisited[MAX_N][MAX_N];

queue<pair<int, int> > swanQ;
queue<pair<int, int> > waterQ;

int dx[4] = { 0, 1,  0, -1 };
int dy[4] = { 1, 0, -1,  0 };

int swan[2][2];
int sCnt = 0;

bool initBfs(int sr, int sc)
{
	int cnt = 0;
	queue<pair<int, int> > que;
	que.push(make_pair(sr, sc));

	while (!que.empty())
	{
		int qSize = que.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = que.front().first;
			int c = que.front().second;
			que.pop();

			if (board[r][c] == SWAN)
			{
				swan[sCnt][0] = r;
				swan[sCnt][1] = c;
				sCnt++;

				if (++cnt >= 2)
					return true;
			}

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if(waterVisited[nr][nc] == true)
					continue;

				waterVisited[nr][nc] = true;

				if (board[nr][nc] == ICE)
				{
					waterQ.push(make_pair(nr, nc));
					continue;
				}
				que.push(make_pair(nr, nc));
			}
		}
	}
	return false;
}

bool waterBfs()
{
	queue<pair<int, int> > que;
	swap(que, waterQ);

	while (!que.empty())
	{
		int qSize = que.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = que.front().first;
			int c = que.front().second;
			que.pop();

			waterVisited[r][c] = true;
			board[r][c] = WATER;

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if(waterVisited[nr][nc] == true)
					continue;

				waterVisited[nr][nc] = true;

				if (board[nr][nc] == ICE)
				{
					waterQ.push(make_pair(nr, nc));
					continue;
				}

				que.push(make_pair(nr, nc));
			}
		}
	}
	return false;
}

bool swanBfs()
{
	queue<pair<int, int> > que;
	swap(que, swanQ);

	while (!que.empty())
	{
		int qSize = que.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = que.front().first;
			int c = que.front().second;
			que.pop();

			swanVisited[r][c] = true;

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (nr == swan[1][0] && nc == swan[1][1])
					return true;

				if(swanVisited[nr][nc] == true)
					continue;
				swanVisited[nr][nc] = true;

				if (board[nr][nc] == ICE)
				{
					swanQ.push(make_pair(nr, nc));
					continue;
				}

				que.push(make_pair(nr, nc));
			}
		}
	}
	return false;
}

int main()
{
	freopen("in.txt", "r", stdin);
	
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		scanf("%s", board[i]);

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (waterVisited[i][j] == true || board[i][j] == ICE)
				continue;

			waterVisited[i][j] = true;

			if (initBfs(i, j))
			{
				printf("0");
				return 0;
			}
		}
	}

	swanQ.push(make_pair(swan[0][0], swan[0][1]));

	int res = 1;
	while (true)
	{
		waterBfs();
		if (swanBfs())
			break;

		res++;
	}

	printf("%d", res);
	return 0;
}
