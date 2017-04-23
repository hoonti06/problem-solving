#include <stdio.h>
#include <iostream>

#define MAX_N 105
#define MAX_K 10005

using namespace std;

int N, K;
int arr[MAX_N], dp[MAX_N][MAX_K];

int main()
{
	freopen("in.txt", "r", stdin);

	int i, res;

	scanf("%d %d", &N, &K);

	for(i = 1; i <= N; i++)
	{
		scanf("%d\n", &arr[i]);
		dp[i][arr[i]] = 1;
	}

	for(i = 1; i <= N; i++)
	{
		for(j = 1; j <= K; j++)
		{
			if(j - arr[i] > 0)
				dp[i][j] = dp[i-1][j-arr[i]];
		}
	}

}
