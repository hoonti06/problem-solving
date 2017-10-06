#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 1005
#define HALF_N 500

int N;

bool dir[MAX_N][MAX_N][4]; // 서 : 0, 북 : 1, 동 : 2, 남 : 3

bool map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

int dx[4] = { 0, -1, 0, 1 };
int dy[4] = {-1,  0, 1, 0 };

int bfs(int sr, int sc)
{
	queue<pair<int, int> > q;
	q.push(make_pair(sr, sc));

	while (!q.empty())
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().first;
			int c = q.front().second;
			q.pop();

			for (int i = 0; i < 4; i++)
			{
				if (dir[r][c][i] == false)
					continue;

				int nr = r + dx[i];
				int nc = c + dy[i];
				
				if (nr < 0 || nr >= MAX_N || nc < 0 || nc >= MAX_N)
					continue;

				if (isVisited[nr][nc] == true)
					continue;

				isVisited[nr][nc] = true;

				q.push(make_pair(nr, nc));
			}
		}
	}
}


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	
	int minX = 500, minY = 500;
	int maxX = 500, maxY = 500;
	
	for (int i = 0; i < N; i++)
	{
		int a, b, c, d;
		scanf("%d%d%d%d", &a, &b, &c ,&d);

		a += HALF_N, b += HALF_N;
		c += HALF_N, d += HALF_N;

		int x1, y1, x2, y2;
		x1 = min(a, c);
		y1 = min(b, d);
		x2 = max(a, c);
		y2 = max(b, d);

		minX = min(minX, x1);
		minY = min(minY, y1);
		maxX = max(maxX, x2);
		maxY = max(maxY, y2);
		
		map[x1][y1] = map[x1][y2] = true;
		map[x2][y1] = map[x2][y2] = true;
		
		dir[x1][y1][2] = dir[x1][y1][3] = true;
		dir[x1][y2][3] = dir[x1][y2][0] = true;
		dir[x2][y2][0] = dir[x2][y2][1] = true;
		dir[x2][y1][1] = dir[x2][y1][2] = true;
		
		for (int j = x1+1; j < x2; j++)
		{
			dir[j][y1][1] = dir[j][y1][3] = true;
			dir[j][y2][1] = dir[j][y2][3] = true;
			map[j][y1] = map[j][y2] = true;
		}

		for (int j = y1+1; j < y2; j++)
		{
			dir[x1][j][0] = dir[x1][j][2] = true;
			dir[x2][j][0] = dir[x2][j][2] = true;
			map[x1][j] = map[x2][j] = true;
		}
	}

	map[HALF_N][HALF_N] = true;

	int cnt = -1;
	for (int i = minX; i <= maxX; i++)
	{
		for (int j = minY; j <= maxY; j++)
		{
			if (isVisited[i][j] == false && map[i][j] == true)
			{
				isVisited[i][j] = true;
				cnt++;
				bfs(i, j);
			}
		}
	}
	printf("%d", cnt);
}
