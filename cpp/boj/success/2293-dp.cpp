#include <stdio.h>
#include <iostream>

#define MAX_N 105
#define MAX_K 10005

using namespace std;

int N, K;
int input[MAX_N];
int dp[MAX_K];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d %d", &N, &K);

	for(int i = 1; i <= N; i++)
		scanf("%d\n", &input[i]);

	dp[0] = 1;
	for(int i = 1; i <= N; i++)
		for(int j = 1; j <= K; j++)
			if (j - input[i] >= 0)
				dp[j] += dp[j-input[i]];

	printf("%d", dp[K]);
}
