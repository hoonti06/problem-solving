#include <cstdio>
#include <algorithm>
#include <vector>
#include <cmath>
#include <queue>
#include <map>
#include <cstring>

using namespace std;

#define MAX_N 105

int N, M;

bool isVisited[MAX_N][MAX_N];

char board[MAX_N][MAX_N];
int stick[MAX_N];

int dx[4] = { -1,  0, 0, 1 };
int dy[4] = {  0, -1, 1, 0 };

int bfs(int sr, int sc)
{
	queue<pair<int, int> > q;
	q.push(make_pair(sr, sc));

	int cnt = 0;
	
	while (!q.empty())
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().first;
			int c = q.front().second;
			q.pop();

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				{
					if (nr >= N)
						return 1;
					continue;
				}

				if (board[nr][nc] == '.')
					continue;

				if (isVisited[nr][nc] == true)
					continue;

				isVisited[nr][nc] = true;
				
				q.push(make_pair(nr, nc));
			}
		}
	}
	return 0;
}


int main()
{
	freopen("in.txt", "r", stdin);
    
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		scanf("%s", board[i]);

	int K;
	scanf("%d", &K);

	int i, j, k, l;
	for (i = 0; i < K; i++)
	{
		int a;
		scanf("%d", &a);
		stick[i] = N - a;
	}
	for (i = 0; i < K; i++)
	{
		int r = stick[i];
		int c = -1;

		if (i % 2 == 0)
		{
			for (j = 0; j < M; j++)
			{
				if (board[r][j] == 'x')
				{
					c = j;
					break;
				}
			}
		}
		else
		{
			for (j = M-1; j >= 0; j--)
			{
				if (board[r][j] == 'x')
				{
					c = j;
					break;
				}
			}
		}
		if (c <= -1)
			continue;

		board[r][c] = '.';

		bool isInAir = false;
		for (j = 0; j < 4; j++)
		{
			memset(isVisited, 0, sizeof(isVisited));

			int nr = r + dx[j];
			int nc = c + dy[j];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			
			if(board[nr][nc] != 'x')
				continue;

			isVisited[nr][nc] = true;

			if (bfs(nr, nc) > 0)
				continue;
			else
			{
				isInAir = true;
				break;
			}
		}
		if (isInAir == false)
			continue;

		int cnt = 1;
		while (true)
		{
			bool isPossible = true;
			for (int k = 0; k < N; k++)
			{
				for (int l = 0; l < M; l++)
				{
					if (isVisited[k][l] == false)
						continue;

					if (k+cnt >= N)
					{
						isPossible = false;
						break;
					}
					if (isVisited[k+cnt][l] == false && board[k+cnt][l] == 'x')
					{
						isPossible = false;
						break;
					}
				}
				if (isPossible == false)
					break;
			}
			if (isPossible == false)
			{
				cnt--;
				break;
			}
			cnt++;
		}

		for (int k = N-1; k >= 0; k--)
		{
			for (int l = 0; l < M; l++)
			{
				if (isVisited[k][l] == false)
					continue;

				isVisited[k+cnt][l] = true;
				board[k+cnt][l] = 'x';
				board[k][l] = '.';
				isVisited[k][l] = false;
			}
		}
	}
	for (i = 0; i < N; i++)
		printf("%s\n", board[i]);
}
