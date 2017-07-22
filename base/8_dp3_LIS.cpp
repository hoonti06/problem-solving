#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 1005

int N;

int input[MAX_N];
int dp[MAX_N];

int process()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j < i; j++)
		{
			if (input[j] < input[i])
				dp[i] = max(dp[i], dp[j]);

		}
		dp[i]++;
	}
}


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 1; i <= N; i++)
		scanf("%d", &input[i]);

	process();

	int res = -1;
	for (int i = 1; i <= N; i++)
		res = max(res, dp[i]);

	printf("%d\n", res);

	return 0;
}
