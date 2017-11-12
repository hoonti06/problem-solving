#include <cstdio>
#include <algorithm>
#include <vector>
#include <map>
#include <list>
#include <queue>
#include <cstring>

using namespace std;

char board[5][5];
bool isVisited[5][5];

int dx[4] = {-1, 1,  0, 0};
int dy[4] = { 0, 0, -1, 1};

bool check[1 << 25];
int res = 0;

int backtracking(int cnt, int sCnt, int status)
{
	check[status] = true;

	if (cnt >= 7)
	{
		if (sCnt >= 4)
			res++;
		return 0;
	}

	for (int i = 0; i < 25; i++)
	{
		if (!(status & (1 << i)))
			continue;

		int r = i / 5;
		int c = i % 5;
		for (int j = 0; j < 4; j++)
		{
			int nr = r + dx[j];
			int nc = c + dy[j];

			if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5)
				continue;
			if ( status & (1 << (nr*5 + nc)) || check[status | (1 << (nr*5 + nc))] )
				continue;
			if (sCnt + (7-cnt) < 4)
				continue;

			backtracking(cnt+1, sCnt + (board[nr][nc] == 'S'), status | (1 << (nr*5 + nc)));
		}
	}
}

int main()
{
	freopen("in.txt", "r", stdin);

	for (int i = 0; i < 5; i++)
		scanf("%s", board[i]);

	
	for (int i = 0; i < 5; i++)
		for (int j = 0; j < 5; j++)
			backtracking(1, board[i][j] == 'S', 1 << (i*5 + j));

	printf("%d", res);
}
