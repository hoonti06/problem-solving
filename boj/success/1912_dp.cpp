#include <stdio.h>
#include <iostream>

#define MAX_N 1000000

using namespace std;

int N;
int arr[MAX_N], dp[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	int i, res;

	scanf("%d", &N);
	for(i = 1; i <= N; i++)
		scanf("%d", &arr[i]);

	for(i = 1; i <= N; i++)
		dp[i] = max(dp[i-1] + arr[i], arr[i]);
	
	res = dp[1];	
	for(i = 2; i <= N; i++)
		if(res < dp[i])
			res = dp[i];

	printf("%d\n", res);
}
