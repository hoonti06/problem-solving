#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 5005

int N;
int dp[MAX_N];

int main()
{
//	freopen("in.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 0; i <= N; i++)
		dp[i] = 1e9;
	dp[0] = 0;

	for (int i = 0; i <= N; i++)
	{
		if (i >= 3)
			dp[i] = min(dp[i], dp[i-3] + 1);
		if (i >= 5)
			dp[i] = min(dp[i], dp[i-5] + 1);

	}
	if (dp[N] == 1e9)
		printf("-1");
	else
		printf("%d\n", dp[N]);
	return 0;
}
