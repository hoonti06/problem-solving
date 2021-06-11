#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 12

int N, M, C;

int board[MAX_N][MAX_N];
int num[MAX_N][MAX_N];

bool track[6];

int process(int r, int c)
{
	int sum = 0;
	for (int i = 0; i < M; i++)
		if (track[i] == true)
			sum += board[r][c+i]*board[r][c+i];

	num[r][c] = max(num[r][c], sum);
}

int backtracking(int r, int c, int cnt)
{
	if (cnt >= M)
	{
		int sum = 0;
		for (int i = 0; i < M; i++)
			if (track[i] == true)
				sum += board[r][c+i];

		if (sum <= C)
			process(r, c);

		return 0;
	}
	
	track[cnt] = true;
	backtracking(r, c, cnt+1);
	track[cnt] = false;
	backtracking(r, c, cnt+1);
}


int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 1; testcase <= T; testcase++)
	{
		memset(track, 0, sizeof(track));
		memset(num, 0, sizeof(num));
		
		scanf("%d%d%d", &N, &M, &C);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				scanf("%d", &board[i][j]);

		for (int i = 0; i < N; i++)
			for (int j = 0; j <= N-M; j++)
				backtracking(i, j, 0);

		int res = 0;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j <= N-M; j++)
			{
				int aa = num[i][j];
				for (int k = i; k < N; k++)
				{
					for (int l = 0; l <= N-M; l++)
					{
						if (k == i && l < j+M)
							continue;

						res = max(res, aa + num[k][l]);
					}
				}
			}
		}
		printf("#%d %d\n", testcase, res);
	}
}
