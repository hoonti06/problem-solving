#include <cstdio>
#include <algorithm>

#define MAX_N 55

int N, M, way;
int input[MAX_N][MAX_N];

int dx[4] = { 0, -1, 0, 1};
int dy[4] = {-1,  0, 1, 0};

int res = 0;

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);


	int i, j;
	int r, c;

	scanf("%d%d%d", &r, &c, &way);
	for (i = 0; i < N; i++)
		for (j = 0; j < M; j++)
			scanf("%d", &input[i][j]);

	bool flag = true;
	while (flag)
	{
		if (!input[r][c])
			res++;
		input[r][c] = 2;

		for (int i = 0; i < 5; i++)
		{
			if (i == 4)
			{
				int row = r + dx[(way + 3) % 4];
				int col = c + dy[(way + 3) % 4];

				if (input[row][col] != 1)
				{
					r = row;
					c = col;
				}
				else
					flag = false;

			}
			else
			{
				int row = r + dx[way];
				int col = c + dy[way];

				way = (way + 3) % 4;
				if (!input[row][col])
				{
					r = row;
					c = col;
					break;
				}
			}

		}

	}
	printf("%d\n", res);
}
