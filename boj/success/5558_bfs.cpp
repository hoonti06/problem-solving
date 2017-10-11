#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>
#include <cmath>
#include <functional>
#include <queue>

using namespace std;

#define MAX_N 1005

int N, M, K;

char board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

int dx[4] = {  0, -1, 0, 1  };
int dy[4] = { -1,  0, 1, 0  };

int num[10][2];

int bfs(int sr, int sc, int er, int ec)
{
	queue < pair<int, int> > q;
	q.push(make_pair(sr, sc));

	int cnt = 0;
	while (!q.empty())
	{
		int qSize = q.size();
		cnt++;
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
					continue;

				if (nr == er && nc == ec)
					return cnt;

				if (isVisited[nr][nc] == true)
					continue;
				isVisited[nr][nc] = true;

				if (board[nr][nc] == 'X')
					continue;

				q.push(make_pair(nr, nc));
			}
		}
	}
	return -1;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d%d", &N, &M, &K);

	for (int i = 0; i < N; i++)
		scanf("%s", board[i]);

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (board[i][j] == 'S')
				num[0][0] = i, num[0][1] = j;

			else if ('1' <= board[i][j] && board[i][j] <= '9')
			{
				num[board[i][j] - '0'][0] = i;
				num[board[i][j] - '0'][1] = j;
			}
		}
	}

	int sum = 0;
	for (int i = 0; i < K; i++)
	{
		memset(isVisited, 0, sizeof(isVisited));
		sum += bfs(num[i][0], num[i][1], num[i+1][0], num[i+1][1]);
	}
	printf("%d", sum);
}
