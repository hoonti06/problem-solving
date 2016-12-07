#include<stdio.h>

#define MAX_N 95 

int N;
long long dp[MAX_N][2];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	
	dp[1][0] = dp[1][1] = 1;

	for(int i = 2; i <= N; i++)
	{
		dp[i][0] = dp[i-1][0] + dp[i-1][1];
		dp[i][1] = dp[i-1][0];
	}
	printf("%llu\n", dp[N][1]);
	
}
