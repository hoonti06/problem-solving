#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 1005

int LA, LB;
char A[MAX_N], B[MAX_N];

int dp[MAX_N][MAX_N];

int process()
{
	for (int i = 1; i <= LA; i++)
		dp[i][0] = i;
	for (int j = 1; j <= LB; j++)
		dp[0][j] = j;

	for (int i = 1; i <= LA; i++)
	{
		for (int j = 1; j <= LB; j++)
		{
			if (A[i] == B[j])
				dp[i][j] = dp[i - 1][j - 1];
			else
				dp[i][j] = min(dp[i - 1][j], min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;

		}

	}
	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &LA, &LB);
	scanf("%s", &A[1]);
	scanf("%s", &B[1]);

	process();

	printf("%d\n", dp[LA][LB]);

	return 0;

}
