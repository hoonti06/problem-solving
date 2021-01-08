#include<stdio.h>

#define MAX_N 35
#define MAX_M 10

#define MAX_W 15010

int N, M, sum, bead;

//MAX_W에 2를 곱한 이유는 음수를 나타내기 위함으로, 음수는 0 이상 MAX_W 미만의 수로 표현된다. 
//배열의 첫번째 괄호는 몇번째 추를 의미하는지를 나타낸다.
int dp[MAX_N][MAX_W*2] = {0, };	
int w[MAX_N];

int main()
{
	int i, j;

	scanf("%d\n", &N);
	for(i = N; i >= 1; i--)
	{
		scanf("%d ", &w[i]);
		sum += w[i];
	}

	dp[0][MAX_W] = 1;

	for(i = 1; i <= N; i++)
	{
		for(int j = 0; j <= MAX_W + sum; j++)
		{
			dp[i][j] = dp[i-1][j];
			
			if(dp[i][j] == 0 && j - w[i] >= 0)
				dp[i][j] = dp[i-1][j - w[i]];
		
			if(dp[i][j] == 0 && j + w[i] <= MAX_W + sum)
				dp[i][j] = dp[i-1][j + w[i]];	
		
		}
	}

	int isFirst = 1;
	scanf("%d\n", &M);
	while(M--)
	{
		scanf("%d ", &bead);
		if(isFirst == 1)
			isFirst = 0;
		else
			printf(" ");
	
		if(dp[N][MAX_W + bead] == 1)
			printf("Y");
		else
			printf("N");
	}
	printf("\n");
}
