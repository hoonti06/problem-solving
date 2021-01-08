#include<stdio.h>

#define MAX_N 1005
#define DIVISOR 10007

int N;
int dp[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	int i, j;
	scanf("%d", &N);

	dp[1] = 1;
	dp[2] = 2;

	for(i = 3; i <= N; i++)
		dp[i] = (dp[i-1] + dp[i-2]) % DIVISOR;

	printf("%d\n", dp[N] % DIVISOR);
}
