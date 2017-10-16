#include <cstdio>
#include <algorithm>
#include <vector>
 
using namespace std;
 
#define MAX_N 1005
 
int board[MAX_N][2];
int dp[MAX_N][16][16];
 
int main()
{
	freopen("in.txt", "r", stdin);

    int N = 1;
    while(true)
    {
        char tmp;
        if (scanf("%d %d%c", &board[N][0], &board[N][1], &tmp) != 3)
            break;
        N++;
    }
    N--;

	for (int i = 1; i <= N+1; i++)
	{
		for (int j = 0; j <= i && j <= 15; j++)
		{
			for (int k = 0; k <= i-j && k <= 15; k++)
			{
				dp[i][j][k] = dp[i-1][j][k];

				if (j-1 >= 0)
					dp[i][j][k] = max(dp[i][j][k], dp[i-1][j-1][k] + board[i-1][0]);

				if (k-1 >= 0)
					dp[i][j][k] = max(dp[i][j][k], dp[i-1][j][k-1] + board[i-1][1]);
			}
		}
	}
	printf("%d", dp[N+1][15][15]);
}
