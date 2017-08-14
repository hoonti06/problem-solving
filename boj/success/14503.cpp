#include <cstdio>
#include <algorithm>

#define MAX_N 55

int map[MAX_N][MAX_N];

int dx[4] = { 0, -1, 0, 1};
int dy[4] = {-1,  0, 1, 0};

int res;

int main()
{
	freopen("in.txt", "r", stdin);

	int N, M;
	scanf("%d%d", &N, &M);

	int r, c, dir;
	scanf("%d%d%d", &r, &c, &dir);

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			scanf("%d", &map[i][j]);

	bool isFinish = false;
	while(!isFinish)
	{
		if (map[r][c] == 0)
			res++;
		map[r][c] = 2;
			
		for (int i = 0; i < 5; i++)
		{
			int nr, nc;

			if (i != 4)
			{
				nr = r + dx[dir];
				nc = c + dy[dir];

				dir = (dir + 3) % 4;

				if (map[nr][nc] == 0)
				{
					r = nr, c = nc;
					break;
				}
			}
			else
			{
				nr = r + dx[(dir + 3) % 4];
				nc = c + dy[(dir + 3) % 4];

				if (map[nr][nc] == 1)
					isFinish = true;
				else
					r = nr, c = nc;
			}
		}

	}
	printf("%d", res);

}
