#include <cstdio>
#include <algorithm>
#include <queue>

using namespace std;

#define MAX_N 1005

#define BLANK '.'
#define WALL '#'
#define USER 'J'
#define FIRE 'F'

int N, M;
char board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N][2];

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

queue <pair<int, int> > fire_q;
queue <pair<int, int> > user_q;

int moveFire()
{
	int qSize = fire_q.size();
	for (int qs = 0; qs < qSize; qs++)
	{
		int r = fire_q.front().first;
		int c = fire_q.front().second;
		fire_q.pop();

		for (int i = 0; i < 4; i++)
		{
			int nr = r + dx[i];
			int nc = c + dy[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			if (board[nr][nc] == WALL)
				continue;
			if (isVisited[nr][nc][1])
				continue;

			isVisited[nr][nc][1] = true;
			fire_q.push(make_pair(nr, nc));
		}
	}
}

bool isEscape(int r, int c)
{
	if (r == 0 || c == 0 || r == N-1 || c == M-1)
		return true;
	else
		return false;
}

int bfs()
{
	int cnt = 1;
	while(!user_q.empty())
	{
		int qSize = user_q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = user_q.front().first;
			int c = user_q.front().second;
			user_q.pop();

			// user가 이동하기 전에 현재 위치가 fire와 겹치는 곳인지 체크한다.
			if (isVisited[r][c][0] && isVisited[r][c][1])
				continue;

			if (isEscape(r, c) == true)
				return cnt;

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (isVisited[nr][nc][0] || isVisited[nr][nc][1])
					continue;
				if (board[nr][nc] == WALL)
					continue;

				isVisited[nr][nc][0] = true;

				user_q.push(make_pair(nr, nc));
			}
		}
		moveFire();

		cnt++;
	}
	return -1;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
	{
		scanf("%s", board[i]);

		for (int j = 0; j < M; j++)
		{
			if (board[i][j] == FIRE)
			{
				fire_q.push(make_pair(i, j));
				isVisited[i][j][1] = true;
			}
			else if (board[i][j] == USER)
			{
				user_q.push(make_pair(i, j));
				isVisited[i][j][0] = true;
			}
		}
	}

	int res = bfs();

	if (res == -1)
		printf("IMPOSSIBLE\n");
	else
		printf("%d\n", res);

	return 0;
}
