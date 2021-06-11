#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <list>
#include <functional>
#include <cmath>
#include <cstring>
#include <string>

using namespace std;

#define MAX_N 25

int N;

int dp[MAX_N][100];

int w[MAX_N], p[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 1; i <= N; i++)
		scanf("%d", &w[i]);
	for (int i = 1; i <= N; i++)
		scanf("%d", &p[i]);

	for (int i = 1; i <= N; i++)
	{
		for (int j = 0; j < 100; j++)
		{
			dp[i][j] = dp[i-1][j];

			if (j - w[i] >= 0)
				dp[i][j] = max(dp[i][j], dp[i-1][j-w[i]] + p[i]);
		}
	}

	int res = 0;
	for (int i = 0; i < 100; i++)
		res = max(res, dp[N][i]);

	printf("%d", res);
}
