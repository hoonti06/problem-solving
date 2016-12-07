#include<stdio.h>

#define MAX_N 505
int N, res;
int dp[MAX_N][MAX_N], board[MAX_N][MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	int i, j;
	scanf("%d", &N);
	for(i = 1; i <= N; i++)
		for(j = 1; j <= i; j++)
			scanf("%d ", &board[i][j]);

	dp[1][1] = board[1][1];
	for(i = 1; i < N; i++)
	{
		for(j = 1; j <= N; j++)
		{
			if(dp[i+1][j] < dp[i][j] + board[i+1][j])
				dp[i+1][j] = dp[i][j] + board[i+1][j];
		
			if(dp[i+1][j+1] < dp[i][j] + board[i+1][j+1])
				dp[i+1][j+1] = dp[i][j] + board[i+1][j+1];
		}
	}
	for(int j = 1; j <= N; j++)
		if(res < dp[i][j])
			res = dp[i][j];

	printf("%d\n", res);
}
