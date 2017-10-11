#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cmath>
#include <functional>
#include <cstring>

#define MAX_N 3005
#define MOD 1000000007

int S, N;

long long dp[MAX_N][MAX_N];

int main()
{

	freopen("in.txt", "r", stdin);

	char str[MAX_N];
	scanf("%d", &S);
	scanf("%s", str);

	N = strlen(str);

//	dp[1][0] = 1;
//	for (int i = 0; i <= 2; i++)
//		for (int j = 0; j <= S; j++)
//			dp[i][j] = 1;

	for (int i = 0; i <= S; i++)
		dp[1][i] = 1;
	
	for (int i = 2; i <= N; i++)
	{
		for (int j = 0; j <= S; j++)
		{
			for (int k = 0; k <= 25 && k <= j; k++)
			{
				dp[i][j] = (dp[i][j] + dp[i-1][j]) % MOD;


				printf("%d %d, %lld\n", j, j-k, dp[i][j]);
			}
		}

	}

	
	printf("%lld", dp[N][S] % MOD);
	






}
