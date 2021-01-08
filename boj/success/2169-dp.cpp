#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 1005

void print_dp();

int N, M;
int map[MAX_N][MAX_N];
int dp[MAX_N][MAX_N][3]; /*
						  * dp[i][j][k] : (i, j)를 k 방향에서 왔을 때의 최댓값
						  * k ==  0 : 북 -> 남, 1 : 서 -> 동, 2 : 동 -> 서
						  */
						  
/*
 * 아래로 한 칸 내려오게 되면 다시는 위로 올라갈 수 없다.
 *
 * 도착점은 (N-1, M-1), 결과값은 dp[N-1][M-1][0 ~ 2] 중 최댓값이다.
 *
 * dp[i][j][0] : dp[i-1][j][0 ~ 2] 중 최댓값 + (i, j)의 가치 
 * dp[i][j][1] : dp[i][j-1][0 ~ 1] 중 최댓값 + (i, j)의 가치, 순서를 서 -> 동 진행
 * dp[i][j][2] : dp[i][j+1][0 ~ 1] 중 최댓값 + (i, j)의 가치, 순서를 동 -> 서 진행
 *
 * i, j, k 에서 변수 j를 증가시켜 서에서 동 혹은 동에서 서의 방향으로 열을 이동시킬 때,
 * k의 값 증가로 방향을 변환해주기 전에 하나의 방향으로 j를 처음부터 끝까지 이동시켜주어야 한다.
 * 그래서 반복의 순서가 j를 먼저 반복시켜주고, k를 반복시켜준 다음 마지막으로 i를 반복시켜주는 것이다.
 */

int process()
{

	// init
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			for (int k = 0; k < 3; k++)
				dp[i][j][k] = -1e9;

	dp[0][0][1] = map[0][0];
	for (int j = 1; j < M; j++)
		dp[0][j][1] = dp[0][j-1][1] + map[0][j];


	for (int i = 1; i < N; i++)
	{
		for (int k = 0; k < 3; k++)
		{
			for (int j = 0; j < M; j++)
			{
				// 북 -> 남
				if (k == 0)
					dp[i][j][0] = max(dp[i-1][j][0], max(dp[i-1][j][1], dp[i-1][j][2])) + map[i][j];

				// 서 -> 동
				if (k == 1 && j-1 >= 0)
					dp[i][j][1] = max(dp[i][j-1][1], dp[i][j-1][0]) + map[i][j];

				// 동 -> 서
				else
				{
					int l = M-1 - j;
					if (l+1 < M)
						dp[i][l][2] = max(dp[i][l+1][0], dp[i][l+1][2]) + map[i][l];
				}
			}
		}
	}

	int res = max(dp[N-1][M-1][1], max(dp[N-1][M-1][0], dp[N-1][M-1][2]));
	printf("%d", res);
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			scanf("%d", &map[i][j]);

	process();
	return 0;
}


void print_dp()
{
	printf("dp\n");
	for (int k = 0; k < 3; k++)
	{
		printf("k : %d\n", k);
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				printf("%3d ", dp[i][j][k]);
			}
			printf("\n");
		}
		printf("\n");

	}
}
