#include <cstdio>
#include <cstring>
#include <algorithm>

using namespace std;


#define	MAX_L 1005

int LA, LB;

char A[MAX_L], B[MAX_L];
int dp[MAX_L][MAX_L];

int process()
{
	for (int i = 1; i <= LA; i++)
	{
		for (int j = 1; j <= LB; j++)
		{
			if (A[i] == B[j])
				dp[i][j] = dp[i - 1][j - 1] + 1;
			else
				dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);

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
