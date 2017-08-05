#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 105

int N;
int map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

int dx[4] = { 0, 1,  0, -1 };
int dy[4] = { 1, 0, -1,  0 };

int max_high;	 // 입력의 최대 높이
int max_cnt; // 최대 안전구역 수
int wLevel;		// 물의 높이

/*
 *  물의 높이를 0부터 입력의 최대 높이까지 올리면서,
 *  모든 배열을 인자로 주어 dfs를 시도해보는데 이때,
 *  현재 물 높이보다 높으면서 아직 방문하지 않은 곳만을
 *  cnt로 1 증가하면서 dfs를 수행한다.
 *  한번의 dfs는 하나의 안전구역만을 뻗어나간다.
 *
 *  cnt가 가장 큰 값을 출력하면 된다.
 */

int dfs(int r, int c)
{
	for (int i = 0; i < 4; i++)
	{
		int nr = r + dx[i];
		int nc = c + dy[i];

		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			continue;

		if (isVisited[nr][nc] == true)
			continue;

		if (map[nr][nc] > wLevel)
		{
			isVisited[nr][nc] = true;
			dfs(nr, nc);
		}
	}
}

int main()
{
	freopen("in2.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			scanf("%d", &map[i][j]);
			max_high = max(max_high, map[i][j]);
		}
	}

	for (wLevel = 0; wLevel <= max_high; wLevel++)
	{
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isVisited[i][j] = false;

		int cnt = 0;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (isVisited[i][j] == false && map[i][j] > wLevel)
				{
					isVisited[i][j] = true;
					dfs(i, j);
					cnt++;
				}
			}
		}
		max_cnt = max(max_cnt, cnt);
	}

	printf("%d", max_cnt);
	return 0;
}
