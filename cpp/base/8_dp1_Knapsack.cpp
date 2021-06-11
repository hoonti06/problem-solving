#include <cstdio>
#include <cstring>
#include <algorithm>
#include <cmath>
#include <queue>

using namespace std;


#define MAX_N 105
#define	MAX_K 10005

int N, K;

int dp[MAX_N][MAX_K];
int w[MAX_N], p[MAX_N];

int process()
{
	for (int n = 1; n <= N; n++)
	{
		for (int k = 0; k <= K; k++)
		{
			if (k - w[n] >= 0)
				dp[n][k] = max(dp[n-1][k], dp[n-1][k-w[n]] + p[n]);
			else
				dp[n][k] = dp[n-1][k];
		}
	}
	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &K);
	for (int i = 1; i <= N; i++)
		scanf("%d%d", &p[i], &w[i]);

	process();

	int res = 0;
	for (int i = 0; i <= K; i++)
		if (res < dp[N][i])
			res = dp[N][i];
	printf("%d\n", res);

	return 0;
}
