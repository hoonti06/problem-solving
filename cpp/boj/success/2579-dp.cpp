#include<stdio.h>

#define MAX_N 305
#define MAX(A,B) (A > B? (A) : (B))

int N;
int dp[MAX_N][2], pr[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	int i;
	scanf("%d", &N);
	for(i = 1; i <= N; i++)
		scanf("%d", &pr[i]);

	for(i = 1; i <= N; i++)
	{
		dp[i][0] = MAX(dp[i-2][0], dp[i-2][1]) + pr[i];
		dp[i][1] = dp[i-1][0] + pr[i];
	}
	printf("%d\n", MAX(dp[N][0], dp[N][1]));
}
