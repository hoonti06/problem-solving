#include<stdio.h>

#define MAX_N 45

int N, m;
int dp[2][MAX_N], num[MAX_N];

int main()
{

	freopen("in.txt", "r", stdin);
	scanf("%d\n", &N);
	for(int i = 0; i < N; i++)
	{
	
		scanf("%d\n", &num[i]);
		if(num[i] > m)
			m = num[i];
	}
	dp[0][0] = 1;
	dp[0][1] = 0;
	dp[1][0] = 0;
	dp[1][1] = 1;

	for(int i = 2; i <= m; i++)
	{
		dp[0][i] = dp[0][i-1] + dp[0][i-2];
		dp[1][i] = dp[1][i-1] + dp[1][i-2];
	}

	for(int i = 0; i < N; i++)
		printf("%d %d\n", dp[0][num[i]], dp[1][num[i]]);

}
