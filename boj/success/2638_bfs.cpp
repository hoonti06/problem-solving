#include <cstdio>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

#define MAX_N 102

int N, M;
int map[MAX_N][MAX_N];
int isVisited[MAX_N][MAX_N];

int dx[4] = {  0, 0, -1, 1 };
int dy[4] = { -1, 1,  0, 0 };

/*
 * 이 문제의 핵심은 외부 공기와 접촉하지 않는 치즈의 구별이다.
 * 이를 위해서는 모눈종이의 가장자리에는 치즈가 놓여져 있지 않는다는 것을 이용해야한다.
 *
 * 가장자리중 하나인 (0, 0)에서 BFS를 한번 호출하면 무조건 외부 공기만을 지나게 된다.
 * 따라서 외부 공기와 접촉하지 않는 치즈에는 도달하지 않는다.
 * (0, 0)에서 시작하는 BFS를 반복 호출하게 되면 외부 공기만을 지나면서 
 * 그 곳과 맞닿아 있는 치즈들만 계속 감소시킬 수 있게 된다.
 *
 * 그래서 map의 값이 모두 0이 될 때까지 bfs(0, 0)을 몇번 호출하느냐가 이 문제의 답이다.
 *
 *
 * isVisited 2차원 배열을 int형으로 하여 치즈(map[i][j] == 1)를 몇 번 방문하는지 카운트하여
 * 2번 이상 방문하게 되면 해당 map의 값을 0으로 바꿔준다.
 * 이때, 해당 map의 값이 0으로 바뀌었기 때문에 이번 턴의 BFS에서 이 위치를 
 * 원래부터 0이었던 것으로 잘못 알고 방문하게 될 수 있음을 고려하여 이에 대해 조치를 취해주어야 한다.
 */

int bfs(int sr, int sc)
{
	queue<pair<int, int> > q;
	isVisited[sr][sc] = 1;
	q.push(make_pair(sr, sc));
	
	int cnt = 0;
	while(!q.empty())
	{
		int r = q.front().first;
		int c = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++)
		{
			int nr = r + dx[i];
			int nc = c + dy[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;

			if (map[nr][nc] == 1)
			{
				isVisited[nr][nc]++;
				if (isVisited[nr][nc] >= 2)
					map[nr][nc] = 0;

				continue;
			}
			if (isVisited[nr][nc] > 0)
				continue;

			isVisited[nr][nc] = 1;
			q.push(make_pair(nr, nc));
		}
	}
	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			scanf("%d", &map[i][j]);

	for (int i = 1; i <= N; i++)
	{
		memset(isVisited, 0, sizeof(isVisited));
		bfs(0, 0);

		bool isFinish = true;
		// map이 모두 0인지 확인
		for (int j = 0; j < N; j++)
		{
			for (int k = 0; k < M; k++)
			{
				if (map[j][k] == 1)
				{
					isFinish = false;
					break;
				}
			}
			if (isFinish == false)
				break;
		}

		if (isFinish)
		{
			printf("%d", i);
			return 0;
		}
	}
	return 0;
}
