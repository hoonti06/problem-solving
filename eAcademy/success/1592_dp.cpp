#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <list>
#include <functional>
#include <cstring>

using namespace std;

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 0; testcase < T; testcase++)
	{
		int don[4];
		int dal[13];
		int dp[13][3];

		for (int i = 0; i < 13; i++)
			for (int j = 0; j < 3; j++)
				dp[i][j] = 1e9;
		dp[0][0] = 0;

		for (int i = 0; i < 4; i++)
			scanf("%d", &don[i]);
		for (int i = 1; i <= 12; i++)
			scanf("%d", &dal[i]);

		for (int i = 1; i <= 12; i++)
		{
			dp[i][0] = min(dp[i-1][0], min(dp[i-1][1], dp[i-1][2])) + don[0]*dal[i];
			dp[i][1] = min(dp[i-1][0], min(dp[i-1][1], dp[i-1][2])) + don[1]*((dal[i] > 0)? 1 : 0);

			if (i-3 >= 0)
			{
				for (int j = 1; j <= 3; j++)
					for (int k = 0; k < 3; k++)
						dp[i][2] = min(dp[i][2], dp[i-j][k]);

				dp[i][2] += don[2];
			}
		}
		int res = 1e9;
		for (int i = 0; i < 3; i++)
			res = min(res, dp[12][i]);

		printf("#%d %d\n",testcase+1, min(res, don[3]));
	}
}
