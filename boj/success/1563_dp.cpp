#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

#define MAX_N 1005
#define DIV 1000000

int N;

/* 
 * dp[i][j][k][l] : i번째에서 j(출석 : 0, 지각 : 1, 결석 : 2)로 왔을 때, 
 * k는 지각을 한번 했는지 여부, l은 결석을 연속적으로 했는지
 */
long long dp[MAX_N][3][2][2];	

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);

	dp[1][0][0][0] = 1;
	dp[1][1][1][0] = 1;
	dp[1][2][0][0] = 1;

	for (int i = 2; i <= N; i++)
	{
		dp[i][0][0][0] = (dp[i-1][0][0][0] + dp[i-1][2][0][0] + dp[i-1][2][0][1]) % DIV;

		dp[i][0][1][0] = (dp[i-1][0][1][0] + dp[i-1][1][1][0] + dp[i-1][2][1][0] + dp[i-1][2][1][1]) % DIV;

		dp[i][1][1][0] = (dp[i-1][0][0][0] + dp[i-1][2][0][0] + dp[i-1][2][0][1]) % DIV;

		dp[i][2][0][0] = dp[i-1][0][0][0] % DIV;

		dp[i][2][0][1] = dp[i-1][2][0][0] % DIV;

		dp[i][2][1][0] = (dp[i-1][1][1][0] + dp[i-1][0][1][0]) % DIV;

		dp[i][2][1][1] = dp[i-1][2][1][0] % DIV;
	}

	long long res = 0;
	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 2; j++)
			res += ( dp[N][i][j][0] + dp[N][i][j][1] ) % DIV;

	printf("%lld", res % DIV);
}
