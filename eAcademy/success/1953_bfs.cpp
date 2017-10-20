#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 52

int N, M;

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

int board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

int bfs(int sr, int sc, int time)
{
	queue<pair<int, int> > q;
	q.push(make_pair(sr, sc));
	isVisited[sr][sc] = true;

	int cnt = 0;
	while (!q.empty() && cnt < time)
	{
		cnt++;
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().first;
			int c = q.front().second;
			q.pop();

			isVisited[r][c] = true;

			for (int i = 0; i < 4; i++)
			{
				if ((board[r][c] & (1 << i)) == false)
					continue;

				int nr = r + dx[i];
				int nc = c + dy[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (isVisited[nr][nc] == true)
					continue;
				if ((board[nr][nc] & (1 << (i+2)%4)) == false)
					continue;

				q.push(make_pair(nr, nc));
			}
		}
	}
	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 1; testcase <= T; testcase++)
	{
		memset(isVisited, 0, sizeof(isVisited));

		int R, C, L;
		scanf("%d%d%d%d%d", &N, &M, &R, &C, &L);

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				int a;
				scanf("%d", &a);

				switch(a)
				{
					case 0:
						board[i][j] = 0;
						break;
					case 1:
						board[i][j] = 15;
						break;
					case 2:
						board[i][j] = 10;
						break;
					case 3:
						board[i][j] = 5;
						break;
					case 4:
						board[i][j] = 6;
						break;
					case 5:
						board[i][j] = 12;
						break;
					case 6:
						board[i][j] = 9;
						break;
					case 7:
						board[i][j] = 3;
					default:
						break;
				}
			}
		}
		
		bfs(R, C, L);

		int sum = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				sum += isVisited[i][j];

		printf("#%d %d\n", testcase, sum);
	}
}
