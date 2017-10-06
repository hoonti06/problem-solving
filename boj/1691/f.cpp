#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

#define MAX_N 605

int N, M, K;

int dp[MAX_N][MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d%d", &M, &N, &K);
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= M; j++)
			dp[i][j] = i * j;

	for (int i = 0; i < K; i++)
	{
		int r, c;
		scanf("%d%d", &c, &r);
		dp[r][c] = 0;
	}

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= M; j++)
		{
			for (int k = 0; k < i; k++)
				dp[i][j] = min(dp[i][j], dp[k][j] + dp[i - k][j]);

			for (int k = 0; k < j; k++)
				dp[i][j] = min(dp[i][j], dp[i][k] + dp[i][j - k]);
		}
	}
	printf("%d", dp[N][M]);

}
