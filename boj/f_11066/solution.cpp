#include <cstdio>
#include <algorithm>
#include <cstring>

using namespace std;

#define MAX_N 505

int N;
int sum[MAX_N];


/*
 * dp[i][j] : i번째에서 j번째까지 합치는데 드는 최소 비용
 * dp[i][j] = (sum[j] - sum[i-1]) + min(i <= k < j){ dp[i][k] + dp[k+1][j] } 
 * 결과값은 dp[1][N]
 */
int dp[MAX_N][MAX_N];

/*
 * Matrix Chain Multiplication, (n^3)
 * i와 j사이의 값인 k를 두어 제일 작은 값을 찾는다.
 * 이때, 변수 d를 두어 1에서 N-1 까지 1씩 증가시키면서 i와 j의 간격을 늘린다.
 * 먼저 i와 j의 간격을 고정시킨 후, i와 j를 같이 1씩 증가시키면서 반복한다.
 * j를 증가시켜 i와 j의 간격을 먼저 늘리게 되면 아직 계산되지 않은 값을 참조하게 된다.
 */
int process_1()
{
	for (int d = 1; d < N; d++)
	{
		for (int i = 1; i + d <= N; i++)
		{
			int j = i + d;
			dp[i][j] = 1e9;
			
			for (int k = i; k < j; k++)
				dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j]);

			dp[i][j] += sum[j] - sum[i-1];
		}
	}
	printf("%d\n", dp[1][N]);
}

/*
 * Matrix Chain Multiplication, (n^3)
 * 재귀적 방법.
 * dp의 값을 모두 -1로 초기화해주어야 한다.
 * s와 e사이의 값인 k를 두어 제일 작은 값을 찾는다.
 * 시작위치와 끝위치가 같은 위치가 될 때까지 쪼개나간다.
 */

int process_2(int s, int e)
{
	if (s == e)
		return 0;

	// ret 대신 그냥 dp[s][e]를 써도 무방하다.
	int& ret = dp[s][e];
	if (ret != -1)
		return ret;
	ret = 1e9;

	for (int k = s; k < e; k++)
		ret = min(ret, process_2(s, k) + process_2(k+1, e));

	ret += sum[e] - sum[s-1];
	return ret;
} 
 
/*
 * Kruth's Algorithm, O(n^2)
 * 
 * 1) 사각부등식
 *	  C[a][c] + C[b][d] <= C[a][d] + C[b][c] (a <= b <= c <= d) 
 *
 * 2) 단조증가
 * 	  C[b][c] <= C[a][d] (a <= b <= c <= d)
 *
 *
 * 실제로 최적화가 적용가능한지 살펴보기 위해, 먼저 우리의 sum[][] 배열이 
 * C[][] 꼴 인지 생각해보자.
 *
 *   1) 사각부등식에 대해, 앞의 항과 뒤의 항 모두 b~c의 input 값을 중복해서 더하므로,
 *      등호를 만족한다.
 *
 *   2) 단조증가에 대해, sum[b][c] <= sum[a][d] 임은 자명하다.
 *
 *
 * 다음으로 점화식에 대해 생각해보면, 우리의 점화식은 다음과 같았다.
 *
 * i <= k < j인 k에 대해,
 * dp[i][j] = min(i <= k < j){dp[i][k] + dp[k+1][j]} + sum[i][j] -> i+1부터 j까지의 최소 비용
 */
int process_3()
{
	int dp2[MAX_N][MAX_N]; /*
							* dp2[i][j] : i+1번째에서 j번째까지 합치는데 드는 최소 비용 
							* 결과값은 dp2[0][N]
							*/

	int num[MAX_N][MAX_N]; // i+1번째에서 j번째까지 최소 비용을 갖는 k값

	for (int d = 2; d <= N; d++) 
	{
		for (int i = 0; i + d <= N; i++) 
		{
			int j = i + d;
			dp2[i][j] = 1e9;

			for (int k = num[i][j - 1]; k <= num[i + 1][j]; k++) 
			{
				int v = dp2[i][k] + dp2[k][j] + sum[j] - sum[i];

				if (dp2[i][j] > v)
					dp2[i][j] = v, num[i][j] = k;

			}

		}
	}

	printf("%d\n", dp2[0][N]);
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T, testcase;
	scanf("%d", &T);
	for (testcase = 0; testcase < T; testcase++)
	{
		scanf("%d", &N);
		for (int i = 1; i <= N; i++)
		{
			int a;
			scanf("%d", &a);
			sum[i] = sum[i-1] + a;
		}

//		process_1();
//			OR
		memset(dp, -1, sizeof(dp));
		printf("%d\n", process_2(1, N));
	}
}
