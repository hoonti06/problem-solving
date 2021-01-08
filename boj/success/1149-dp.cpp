#include<stdio.h>

#define MAX_N 1010

int N, res = 1e9;
int dp[MAX_N][3], pr[MAX_N][3];

int main()
{
	freopen("in.txt", "r", stdin); 
	
	int i, j;	
	//입력
	scanf("%d\n", &N);
	for(i = 1; i <= N; i++)
		for(j = 0; j < 3; j++)
			scanf("%d ", &pr[i][j]);

	//초기화
	for(i = 2; i <= N; i++)
		for(j = 0; j < 3; j++)
			dp[i][j] = 1e9;
	for(j = 0; j < 3; j++)
		dp[1][j] = pr[1][j];
	
	//연산
	for(i = 1; i <= N; i++)
	{
		for(j = 0; j < 3; j++)
		{
			for(int k = 0; k < 3; k++)
			{
				if(k == j || dp[i-1][k] == 1e9)
					continue;
				
				if(dp[i][j] > dp[i-1][k] + pr[i][j])
					dp[i][j] = dp[i-1][k] + pr[i][j];
			}	
		}
	}

	//최솟값 검색
	for(j = 0; j < 3; j++)
		if(res > dp[N][j])
			res = dp[N][j];

	//출력
	printf("%d\n", res);
}
