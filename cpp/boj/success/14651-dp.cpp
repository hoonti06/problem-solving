#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 33335
#define MOD 1000000009

int N;

long long int dp[MAX_N][3];

int main()
{
	scanf("%d", &N);

	dp[0][0] = 0;
	dp[0][1] = 1;
	dp[0][2] = 1;

	for (int i = 1; i < N; i++)
	{
		for (int k = 0; k < 3; k++)
			for (int j = 0; j < 3; j++)
				dp[i][k] = (dp[i][k] + dp[i - 1][j] + MOD) % MOD;

//		for (int j = 0; j < 3; j++)
//			dp[i][1] = (dp[i][1] + dp[i - 1][j] + MOD) % MOD;
//
//
//		for (int j = 0; j < 3; j++)
//			dp[i][2] = (dp[i][2] + dp[i - 1][j] + MOD) % MOD;
//		dp[i][2] = dp[i][0] % MOD;


	}
	printf("%lld", dp[N - 1][0] % MOD);

	return 0;

}
