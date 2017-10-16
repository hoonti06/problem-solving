#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 305

int N, M;

bool board[MAX_N][MAX_N];
long long dp[MAX_N][MAX_N][2];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
	{
		int a, b;
		scanf("%d%d", &a, &b);
		board[a][b] = true;
	}

	for (int i = 0; i <= 300; i++)
	{
		for (int j = 0; j <= 300; j++)
		{
				int num = M - (i + j);
				if (num < 0) num = 0;

				if (i -1 >= 0)
					dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1]) + (board[i][j]? num : 0);

				if (j -1 >= 0)
					dp[i][j][1] = max(dp[i][j-1][0], dp[i][j-1][1]) + (board[i][j]? num : 0);
		}
	}
	printf("%lld", max(dp[300][300][0], dp[300][300][1]));
}
