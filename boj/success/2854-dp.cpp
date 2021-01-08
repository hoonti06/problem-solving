#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

#define MAX_N 100005
#define DIV 1000000007

int N;
long long dp[MAX_N][3];

int input1[MAX_N];
int input2[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 1; i <= N; i++)
		scanf("%d", &input1[i]);

	for (int i = 1; i < N; i++)
		scanf("%d", &input2[i]);

	dp[1][0] = input1[1];
	dp[1][2] = input2[1];

	for (int i = 2; i <= N; i++)
	{
		dp[i][0] = input1[i] * (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % DIV;

		dp[i][1] = input2[i-1] * (dp[i-1][0] + dp[i-1][1]) % DIV + (input2[i-1] - 1) * dp[i-1][2] % DIV;

		dp[i][2] = input2[i] * (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % DIV;
	}

	long long res = 0;
	for (int i = 0; i < 3; i++)
		res += (dp[N][i] % DIV);

	printf("%lld", res % DIV);
}
