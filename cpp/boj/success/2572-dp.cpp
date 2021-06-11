#include <cstdio>
#include <algorithm>
#include <vector>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 1005
#define MAX_M 505

int N, M, K;

char board[MAX_M][MAX_M];
int dp[MAX_N][MAX_M];

int main()
{
	freopen("in.txt", "r", stdin);

	char str[MAX_N];
	scanf("%d\n", &N);

	char tmp;
	for (int i = 1; i <= N; i++)
		scanf("%c%c", &str[i], &tmp);

	scanf("%d%d\n", &M, &K);
	for (int i = 0; i < K; i++)
	{
		int a, b;
		char c;
		scanf("%d %d %c%c", &a, &b, &c, &tmp);
		board[a-1][b-1] = c;
		board[b-1][a-1] = c;
	}
	
	int cnt = 0;
	for (int i = 0; i < M; i++)
		if (str[1] == board[0][i])
		{
			dp[1][i] = 10;
			cnt++;
		}

	if (cnt == 0)
	{
		printf("0");
		return 0;
	}

	for (int i = 2; i <= N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			for (int k = 0; k < M; k++)
			{
				if (board[k][j] == 0)
					continue;

				dp[i][j] = max(dp[i][j], dp[i-1][k] + (str[i] == board[k][j]? 10 : 0));
			}
		}
	}
	int res = 0;
	for (int i = 0; i < M; i++)
		res = max(res, dp[N][i]);

	printf("%d", res);
}
