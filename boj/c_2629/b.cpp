#include<stdio.h>

#define MAX_N 35
#define MAX_M 10

int N, M;
int w[MAX_N], bead[MAX_M];
int sum[MAX_N] = {0, }; 
int dp[150010] = {0, };
int sum_w = 0;


int main()
{
	freopen("in.txt", "r", stdin);
	
	int i;
	scanf("%d\n", &N);
	for(i = N; i >= 1; i--)
	{
		scanf("%d ", &w[i]);
		sum[i] = sum[i+1] + w[i];
		dp[w[i]] = 1;
		sum_w += w[i];
	}

	scanf("%d\n", &M);
	for(i = 1; i <= M; i++)
		scanf("%d ", &bead[i]);

	for(i = 1; i <= 150010; i++)
	{
		for(int j = 1; j <= N; j++)
		{
			if(dp[i] == 1 && i + w[j] <= 150010)
				dp[i + w[j]] = 1;
			//if(i > w[j])
			//	dp[i - w[j]] = 1;

		}
	}

	for(i = 150010; i >= 1; i--)
	{
	
		for(int j = 1; j <= N;j ++)
		{
			if(dp[i] == 1 && i > w[j])	
				dp[i-w[j]] = 1;	
		}
	
	}


	for(i = 1; i <= M; i++)
	{
		if(i != 1)
			printf(" ");
		if(dp[bead[i]] == 1)
		{
			printf("Y");
		}
		else
			printf("N");
	
	}
	printf("\n");
	
}
