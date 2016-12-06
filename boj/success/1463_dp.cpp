#include<stdio.h>

#define MAX_N 1000010

int N;
int dp[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	int i;
	scanf("%d", &N);

	for(i = 2; i <= N; i++)
		dp[i] = 1e9;
	for(i = 2; i <= N; i++)
	{
		if(dp[i] > dp[i-1] + 1)
			dp[i] = dp[i-1] + 1;

		if(i%2 == 0 && dp[i] > dp[i/2] + 1)
			dp[i] = dp[i/2] + 1;

		if(i%3 == 0 & dp[i] > dp[i/3] + 1)
			dp[i] = dp[i/3] + 1;
	}
	printf("%d\n", dp[N]);
}
