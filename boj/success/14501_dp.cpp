#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 10005

int N;
int dp[20]; /*
			 * dp[i] : i번째에서 i번 가치를 포함한 최댓값
			 * 
			 * dp[i] = MAX(0 < j < i && j + t[j] <= i){ dp[j] + p[i] }
			 * j + t[j] <= i를 만족하는 i보다 작은 위치인 j에 한해서 가장 큰 값을 구한다.
			 */

int t[20], p[20];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 1; i <= N; i++)
	{
		scanf("%d%d", &t[i], &p[i]);
		dp[i] = p[i];
	}
	
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j < i; j++)
		{
			if (j + t[j] <= i)
				dp[i] = max(dp[i], dp[j] + p[i]);
		}
	}

	/*
	 * 가장 큰 값인 결과값을 찾을 때 i번의 가치를 포함하였으니 해당 가치를 
	 * 기한 내에 도달할 수 있는지 확인하기 위해 i + t[i]값이 N+1 이하 이어야 한다.
	 */
	int res = 0;
	for (int i = 1; i <= N; i++)
		if (i + t[i] <= N + 1)
			res = max(res, dp[i]);

	printf("%d", res);
}
