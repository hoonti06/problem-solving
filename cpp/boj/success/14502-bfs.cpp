#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 10

int N, M;
int map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];
vector<pair<int, int> > virus;

int dx[4] = { 0, 0, -1, 1 };
int dy[4] = {-1, 1,  0, 0 };

/*
 * N, M의 최댓값이 8이기 때문에 3개의 벽을 세울 수 있는 모든 경우를 시도해도 시간 제한에 걸리지 않는다.
 * 따라서, 3중 for문으로 새로 세우는 벽의 좌표를 이미 방문한 상태(isVisited[r][c] = true)로 만든 후, BFS를 수행한다.
 */

int bfs()
{
	queue<pair<int, int> > q;
	for (int i = 0; i < virus.size(); i++)
		q.push(virus[i]);
	int cnt = 0;

	while(!q.empty())
	{
		int r = q.front().first;
		int c = q.front().second;
		q.pop();

		cnt++;
		
		for (int i = 0; i < 4; i++)
		{
			int nr = r + dx[i];
			int nc = c + dy[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M || isVisited[nr][nc])
				continue;

			if (map[nr][nc] != 0)
				continue;

			isVisited[nr][nc] = true;
			q.push(make_pair(nr, nc));
		}
	}
	return cnt;
}

int main()
{
	freopen("in.txt", "r", stdin);

	int wallCnt = 0;
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
		{
			scanf("%d", &map[i][j]);
			if (map[i][j] == 2)
				virus.push_back(make_pair(i, j));
			else if (map[i][j] == 1)
				wallCnt++;
		}
	wallCnt += 3;	// 새로 세우는 벽 3개에 대한 추가

	int res = 0;
	for (int i = 0; i < N*M; i++)
	{
		int r1 = i / M, c1 = i % M;
		if (map[r1][c1] != 0)
			continue;
			
		for (int j = i+1; j < N*M; j++)
		{
			int r2 = j / M, c2 = j % M;
			if (map[r2][c2] != 0)
				continue;

			for (int k = j+1; k < N*M; k++)
			{
				int r3 = k / M, c3 = k % M;
				if (map[r3][c3] != 0)
					continue;

				memset(isVisited, 0, sizeof(isVisited));
				isVisited[r1][c1] = true;
				isVisited[r2][c2] = true;
				isVisited[r3][c3] = true;

				res = max(res, N*M - wallCnt - bfs());
			}
		}

	}
	printf("%d", res);
}
