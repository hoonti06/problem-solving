#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 18

int M, N;

int dp[MAX_N];
int t[MAX_N];
int p[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 1; i <= N; i++)
	{
		scanf("%d%d", &t[i], &p[i]);
		dp[i] = p[i];

	}

	for (int i = 1; i <= N + 1; i++)
	{
		for (int j = 1; j < i; j++)
		{
			if ((j + t[j]) <= i)
				dp[i] = max(dp[j] + p[i], dp[i]);

		}

	}
	printf("%d\n", dp[N + 1]);
}
