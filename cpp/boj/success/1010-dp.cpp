#include<stdio.h>

#define MAX_M 32

int N, M, T, res;
int dp[MAX_M][MAX_M];

int main()
{
	freopen("in.txt", "r", stdin);

	int i, j, k;
	scanf("%d", &T);
	
	for(j = 1; j < MAX_M; j++)
		dp[1][j] = 1;

	while(T--)
	{
		res = 0;
		scanf("%d %d", &N, &M);
   
		for(i = 2; i <= N; i++)	
			for(j = 1; j <= M; j++)
				for(k = 1; k < j; k++)
					dp[i][j] += dp[i-1][k];
	
		for(j = 1; j <= M; j++)
			res += dp[N][j];
	
		printf("%d\n", res);	
	
		for(i = 2; i <= N; i++)
			for(j = 1; j <= M; j++)
				dp[i][j] = 0;
	}
}
