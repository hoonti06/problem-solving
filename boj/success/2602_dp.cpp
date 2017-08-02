#include <cstdio>
#include <cstring>
#include <algorithm>

using namespace std;

int len, wLen;

char w[22];	// 문자열
char input[2][102];

int dp[2][102][20];	/* 
					 * dp[i][j][k] : (i, j) 위치에서 
					 * 문자열(w)의 [k-1]번째 문자일 때의 방법의 수
					 */

int process()
{
	for (int i = 0; i < len; i++)	// 문자열(w)의 첫번째 문자에 해당하는 위치에 dp값을 1로 저장
	{
		for (int j = 0; j < 2; j++)
		{
			if (input[j][i] == w[0])
				dp[j][i][0] = 1;
		}
	}

	for (int i = 0; i < len - 1; i++)	
	{
		for (int j = 0; j < wLen - 1; j++)
		{
			if (input[0][i] == w[j])	// 문자열(w)에서의 j번째 문자인지 확인
			{
				for (int k = i+1; k < len; k++)	// i번보다 더 뒤인 k번에 대해
				{
					if (input[1][k] != w[j+1])
						continue;

					/* 
					 * i번보다 더 뒤인 k의 위치에서 w의 j번째 문자 다음인 
					 * w의 [j+1]번째 문자가 존재하면 j번째 문자까지의 방법의 수를
					 * [j+1]번째 문자에 더해준다.
					 *
					 * 0 -> 1, 1 -> 0
					 */
					dp[1][k][j+1] += dp[0][i][j];
				}
			}

			if (input[1][i] == w[j])
			{
				for (int k = i+1; k < len; k++)
				{
					if (input[0][k] != w[j+1])
						continue;

					dp[0][k][j+1] += dp[1][i][j];
				}
			}
		}
	}

	// 문자열(w) 마지막 문자일 때의 방법의 수를 모두 더한다.
	int sum = 0;
	for (int i = 0; i < 2; i++)
		for (int j = 0; j < len; j++)
			sum += dp[i][j][wLen-1];
	return sum;
}

int main()
{
	freopen("in2.txt", "r", stdin);
	scanf("%s", w);
	scanf("%s", input[0]);
	scanf("%s", input[1]);

	wLen = strlen(w);
	len = strlen(input[0]);

	printf("%d", process());
	return 0;
}
