#include <stdio.h>

#define MAX_N 305
#define MAX_K 10000

int N, M, K;
int arr[MAX_N][MAX_N];
int dp[MAX_N][MAX_N];


int main()
{

	freopen("in.txt", "r", stdin);

	int i, j, l, t;
	int x, y, z, w;

	scanf("%d %d", &N, &M);
	for(i = 1; i <= N; i++)
		for(j = 1; j <= M; j++)
			scanf("%d", &arr[i][j]);

	for(i = 1; i <= N; i++)
	{
		for(j = 1; j <= M; j++)
		{
			for(l = 1; l <= i; l++)
			{
				for(t = 1; t <= j; t++)
				{
					dp[i][j] += arr[l][t];
				}
			}

		}
	}


	scanf("%d", &K);

	while(K--)
	{
		scanf("%d %d %d %d", &x, &y, &z, &w);
		printf("%d\n", dp[z][w] + dp[x-1][y-1] - dp[x-1][w] - dp[z][y-1]);
	}
}
