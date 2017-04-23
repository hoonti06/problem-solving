#include <cstdio>
#include <algorithm>

#define MAX_N 505

using namespace std;

int N, M;

int input[MAX_N][MAX_N];
bool chk[MAX_N][MAX_N];


int ans = 0;

int dx[4] = { -1, 0, 1,  0  };
int dy[4] = {  0, 1, 0, -1  };

int bfs(int row, int col, int cnt)
{
	if (cnt == 4)
		return input[row][col];

	int r, c;
	int res = 0;

	chk[row][col] = true;
	for (int i = 0; i < 4; i++)
	{
		r = row + dx[i];
		c = col + dy[i];
		if (r < 0 || r >= N || c < 0 || c >= M || chk[r][c])
			continue;

		res = max(bfs(r, c, cnt + 1) + input[row][col], res);
	}
	chk[row][col] = false;
	return res;
}

int fuc(int row, int col)
{
	int r, c;
	int res = 0;

	for (int i = 0; i < 4; i++)
	{
		r = row;
		c = col;
		int sum = input[r][c];

		for (int j = 0; j < 2; j++)
		{
			r += dx[i];
			c += dy[i];
			if (r < 0 || r >= N || c < 0 || c >= M)
				break;

			sum += input[r][c];

			if (j == 0)
			{
				int rr = r + dx[(i + 1) % 4];
				int cc = c + dy[(i + 1) % 4];

				if (rr < 0 || rr >= N || cc < 0 || cc >= M)
					break;
				else
					sum += input[rr][cc];
			}
		}
		res = max(sum, res);
	}

	return res;
}

int main()
{
	freopen("in.txt", "r", stdin);

	int i, j;
	scanf("%d%d", &N, &M);

	for (i = 0; i < N; i++)
		for (j = 0; j < M; j++)
			scanf("%d", &input[i][j]);

	for (i = 0; i < N; i++)
	{
		for (j = 0; j < M; j++)
		{
			ans = max(bfs(i, j, 1), ans);
			ans = max(fuc(i, j), ans);
		}
	}
	printf("%d\n", ans);
}
