#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 22

int N;

int dx[4] = { -1, -1, 1,  1 };
int dy[4] = { -1,  1, 1, -1 };

int board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

int track[MAX_N*MAX_N];

int sr, sc;
int res;

int backtracking(int r, int c, int cnt, int dir, int aa, int bb)
{
	track[cnt] = board[r][c];
	
	for (int i = 0; i < 2; i++)
	{
		int naa = aa;
		int nbb = bb;

		bool isCon = false;
		switch(dir)
		{
			case 0:
				if (i == 0) naa--;
				else
				{
					if (naa > 0)
					{
						isCon = true;
						break;
					}
					nbb--;
				}
				break;
			case 1:
				if (i == 0) nbb--;
				else
					isCon = true;
				break;
			case 2:
				if (i == 0) naa++;
				else nbb++;
				break;
			case 3:
				if (i == 0) nbb++;
				else naa--;
			default:
				break;
		}
		if (isCon == true)
			continue;

		if (naa < 0 || nbb < 0)
			continue;

		int ndir = (dir + i) % 4;
		int nr = r + dx[ndir];
		int nc = c + dy[ndir];

		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			continue;

		if (nr == sr && nc == sc && cnt > 1 && res < cnt)
		{
			res = cnt;
			return 0;
		}

		if (isVisited[nr][nc] == true)
			continue;

		bool isDup = false;
		for (int i = 0; i <= cnt; i++)
		{
			if (track[i] == board[nr][nc])
			{
				isDup = true;
				break;
			}
		}
		if (isDup == true)
			continue;

		isVisited[nr][nc] = true;
		backtracking(nr, nc, cnt+1, ndir, naa, nbb);
		isVisited[nr][nc] = false;
	}
	return -1;
}


int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 1; testcase <= T; testcase++)
	{
		memset(isVisited, 0, sizeof(isVisited));
		res = -2;

		scanf("%d", &N);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				scanf("%d", &board[i][j]);

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				sr = i, sc = j;
				isVisited[i][j] = true;
				backtracking(i, j, 0, 2, 0, 0);
				isVisited[i][j] = false;
			}
		}
		printf("#%d %d\n", testcase, res+1);
	}
}
