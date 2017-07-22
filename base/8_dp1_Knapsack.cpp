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
int v[MAX_N], w[MAX_N];

int process()
{
	for (int n = 1; n <= N; n++)
	{
		for (int k = 0; k <= K; k++)
		{
			if (w[n] - k > 0)
				dp[n][k] = dp[n - 1][k];
			else
				dp[n][k] = max(dp[n - 1][k - w[n]] + v[n], dp[n - 1][k]);

		}

	}

	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &K);
	for (int i = 1; i <= N; i++)
		scanf("%d%d", &v[i], &w[i]);

	process();

	int res = 0;
	for (int i = 0; i <= K; i++)
		if (res < dp[N][i])
			res = dp[N][i];
	printf("%d\n", res);

	return 0;
}
