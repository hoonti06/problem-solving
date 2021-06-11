#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 1005
#define MOD 10007

int N;
long long int dp[MAX_N];

int main()
{
	scanf("%d", &N);
	
	dp[1] = 1, dp[2] = 3;
	for (int i = 3; i <= N; i++)
		dp[i] = (dp[i-2]*2 + dp[i-1] + MOD) % MOD;

	printf("%d", dp[N] % MOD);
}
