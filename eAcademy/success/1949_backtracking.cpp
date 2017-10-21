#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 10 

int N, K;

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

int board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

int res;
int backtracking(int r, int c, int val, bool already, int cnt)
{
	res = max(res, cnt);

	for (int i = 0; i < 4; i++)
	{
		int nr = r + dx[i];
		int nc = c + dy[i];

		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			continue;
		if (isVisited[nr][nc] == true)
			continue;

		if (val <= board[nr][nc])
		{
			if (already == true)
				continue;
			if (board[nr][nc] - val >= K)
				continue;
			if (val - 1 < 0)
				continue;

			isVisited[nr][nc] = true;
			backtracking(nr, nc, val-1, true, cnt+1);
			isVisited[nr][nc] = false;
		}
		else
		{
			isVisited[nr][nc] = true;
			backtracking(nr, nc, board[nr][nc], already, cnt+1);
			isVisited[nr][nc] = false;
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
		res = 0;

		scanf("%d%d", &N, &K);
		
		int maximum = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				scanf("%d", &board[i][j]);
				maximum = max(maximum, board[i][j]);
			}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (board[i][j] == maximum)
				{
					isVisited[i][j] = true;
					backtracking(i, j, maximum, false, 0);
					isVisited[i][j] = false;
				}
		printf("#%d %d\n", testcase, res+1);
	}
}
