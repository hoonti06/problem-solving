#include<stdio.h>

#define MAX_N 11

int N, m;
int dp[MAX_N], num[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	int i;
	scanf("%d", &N);
	for(i = 0; i < N; i++)
	{
		scanf("%d", &num[i]);
		if(m < num[i])
			m = num[i];
	}
	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 4;

	for(i = 4; i <= m; i++)
		dp[i] = dp[i-1] + dp[i-2] + dp[i-3];	
	
	for(i = 0; i < N; i++)
		printf("%d\n", dp[num[i]]);

}
