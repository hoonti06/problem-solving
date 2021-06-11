#include <stdio.h>

#define max(a, b) ( ((a) > (b)) ? (a) : (b) )
#define MAX_N 10005

int N;
int dp[MAX_N][3];
int arr[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);
	
	int i;
	
	scanf("%d", &N);

	for(i = 1; i <= N; i++)
		scanf("%d", &arr[i]);

	dp[1][1] = arr[1];
	for(i = 2; i <= N; i++)
	{
		dp[i][0] = max(dp[i-1][0], max(dp[i-1][1], dp[i-1][2]));
		dp[i][1] = dp[i-1][0] + arr[i];
		dp[i][2] = dp[i-1][1] + arr[i]; 
	}

	printf("%d\n", max(dp[N][0], max(dp[N][1], dp[N][2])));
}

