
#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

#define MAX_N 55


int N;
int map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];
bool flag[MAX_N];
int res;

int backtracking(int cnt, int tCnt)
{
	if (cnt >= N)
	{
		if (tCnt > 0)
			return 0;

		int aSum = 0, bSum = 0;
		for (int i = 0; i < N; i++)
		{
			for (int j = i+1; j < N; j++)
			{
				if (flag[i] && flag[j])
					aSum += map[i][j] + map[j][i];

				else if (!flag[i] && !flag[j])
					bSum += map[i][j] + map[j][i];
			}
		}
		res = min(res, abs(aSum-bSum));
		return 0;
	}

	if (tCnt > 0)
	{
		flag[cnt] = true;
		backtracking(cnt+1, tCnt-1);
	}

	flag[cnt] = false;
	backtracking(cnt+1, tCnt);

	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	int testcase;
	scanf("%d", &testcase);
	for (int tc = 1; tc <= testcase; tc++)
	{
		memset(isVisited, 0, sizeof(isVisited));
		res = 1e9;

		scanf("%d", &N);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				scanf("%d", &map[i][j]);

		backtracking(0, N/2);

		printf("#%d %d\n", tc, res);
	}
	return 0;
}
