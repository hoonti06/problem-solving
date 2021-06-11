#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

#define MAX_N 1505

int N, M;

int map[MAX_N][MAX_N];
char fruit[MAX_N][MAX_N];

int s[MAX_N][MAX_N][2];
int dp[MAX_N][MAX_N][3];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			char str[4];
			scanf("%s", str);
			fruit[i][j] = str[0];
			map[i][j] = atoi(&str[1]);

			if (fruit[i][j] == 'A')
			{
				s[i][j][0] = s[i][j-1][0] + map[i][j];
				s[i][j][1] = s[i][j-1][1];
			}
			else
			{
				s[i][j][1] += s[i][j-1][1] + map[i][j];
				s[i][j][0] = s[i][j-1][0];
			}
		}
	}


	for (int k = 1; k < M; k++)
		if (fruit[0][k] == 'B')
			dp[0][0][0] += map[0][k];

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (i == 0 && j == 0)
				continue;

			if (j-1 >= 0)
			{
				dp[i][j][0] = max(dp[i][j-1][0], max(dp[i][j-1][1], dp[i][j-1][2]));
				if (fruit[i][j] == 'B')
					dp[i][j][0] -= map[i][j];
			}

			if (i-1 >= 0)
			{
				dp[i][j][1] = max(dp[i-1][j][0], max(dp[i-1][j][1], dp[i-1][j][2]));

				dp[i][j][1] += s[i][j-1][0];
				dp[i][j][1] += (s[i][M-1][1] - s[i][j][1]);
			}

			if (i-1 >= 0 && j-1 >= 0)
			{
				dp[i][j][2] = max(dp[i-1][j-1][0], max(dp[i-1][j-1][1], dp[i-1][j-1][2]));

				dp[i][j][2] += s[i][j-1][0];
				dp[i][j][2] += (s[i][M-1][1] - s[i][j][1]);
			}
		}
	}
	printf("%d", max(dp[N-1][M-1][0], max(dp[N-1][M-1][1], dp[N-1][M-1][2])));
}
