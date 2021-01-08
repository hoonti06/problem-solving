#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <cstring>
#include <cmath>
#include <functional>

using namespace std;

#define MAX_N 20

int N, P;

int board[MAX_N][MAX_N];
int dp[MAX_N][1<<16];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			scanf("%d", &board[i][j]);

	char str[MAX_N];
	scanf("%s", str);

	unsigned short bitmask = 0;
	int cnt = 0;
	for (int i = 0; i < N; i++)
	{
		if (str[i] == 'Y')
		{
			bitmask |= 1 << i;
			cnt++;
		}
	}

	scanf("%d", &P);

	if (cnt >= P)
	{
		printf("0");
		return 0;
	}

	for (int i = 0; i <= N; i++)
		for (int j = 0; j <= 1<<16; j++)
			dp[i][j] = 1e9;

	dp[cnt][bitmask] = 0;
	for (int i = cnt; i <= P; i++)
	{
		for (int j = 0; j <= (1<<16); j++)
		{
			if (dp[i][j] == 1e9)
				continue;

			for (int k = 1; k <= N; k++)
			{
				if ((j & (1 << k-1)) == 0)
					continue;

				for (int m = 1; m <= N; m++)
				{
					if ((j & (1 << m-1)) != 0)
						continue;

					dp[i+1][j | (1 << (m-1))] = min(dp[i+1][j | (1 << (m-1))], dp[i][j] + board[k][m]);
				}
			}
		}
	}

	int res = 1e9;
	for (int i = 0; i <= (1<<16); i++)
		res = min(res, dp[P][i]);

	printf("%d", res==1e9? -1 : res);
}

