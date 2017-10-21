#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 15
#define MAX_M 22 

int N, M, K;

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

int board[MAX_N][MAX_M];

int track[MAX_N];

int res;

int backtracking(int idx, int cnt)
{
	if (res < cnt)
		return 0;

	int tmp[MAX_N][MAX_M];
	memcpy(tmp, board, sizeof(tmp));

	for (int j = 0; j < N; j++)
	{
		if (track[j] == 1)
			for (int k = 0; k < M; k++)
				tmp[j][k] = 0;
		else if (track[j] == 2)
			for (int k = 0; k < M; k++)
				tmp[j][k] = 1;
	}

	bool isFin = true;
	for (int j = 0; j < M; j++)
	{
		int sCnt = 1;
		int maxScnt = 0;
		int tt = tmp[0][j];
		for (int i = 1; i < N; i++)
		{
			if (tt != tmp[i][j])
			{
				maxScnt = max(maxScnt, sCnt);
				sCnt = 1;
				tt = tmp[i][j];
			}
			else
				sCnt++;
		}
		maxScnt = max(maxScnt, sCnt);
		if (maxScnt < K)
		{
			isFin = false;
			break;
		}
	}
	if (isFin)
	{
		res = min(res, cnt);
		return 0;
	}

	for (int i = idx; i < N; i++)
	{
		track[i] = 1;
		backtracking(i+1, cnt+1);
		track[i] = 2;
		backtracking(i+1, cnt+1);
		track[i] = 0;
	}
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 1; testcase <= T; testcase++)
	{
		res = 1e9;
		memset(track, 0, sizeof(track));

		scanf("%d%d%d", &N, &M, &K);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				scanf("%d", &board[i][j]);

		backtracking(0, 0);

		printf("#%d %d\n", testcase, res);
	}
}
