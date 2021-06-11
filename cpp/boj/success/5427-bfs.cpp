#include <cstdio>
#include <algorithm>
#include <queue>

using namespace std;

#define MAX_N 1005

void print_test();

#define BLANK '.'
#define WALL '#'
#define USER '@'
#define FIRE '*'

int N, M;
char map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N][2];	/* [i][j][0] : 사람의 (i, j) 방문 여부
									 * [i][j][1] : 불의 (i, j) 방문(번짐) 여부
									 */
int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

// 불 번짐 위한 queue
queue <pair<int, int> > fire_q;

int fire_move()
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
			if (map[nr][nc] == WALL)
				continue;

			if (isVisited[nr][nc][1] == false)
			{
				isVisited[nr][nc][1] = true;
				fire_q.push(make_pair(nr, nc));
			}
		}
	}
}

// 탈출구 체크 함수
bool check_esc(int r, int c)
{
	if (r < 0 || r >= N || c < 0 || c >= M)
		return false;
	else if (0 < r && r < N-1 && 0 < c && c < M-1)
		return false;

	return true;
}

/*
 * 불의 번짐 및 사람의 이동을 일일이 map에 표시하는 게 아니라 isVisited에 표시하는 것이다.
 *
 * BFS에서 한 턴에서 사람의 움직일 수 있는 이동을 먼저 진행한 다음, cnt를 증가시킬 때 불을 이동시킨다.
 * 그렇게 되면 사람과 불이 동시에 같은 위치로 이동해야할 때를 캐치하지 못하기 때문에,
 * 일단 사람을 먼저 그 위치에 두고 cnt를 증가시킨다. 다음 턴이 되어 해당 위치가 current가 돼서
 * 다음 위치로 이동해야 될 차례가 되면, 다음 위치 이동 전에 현재 위치(i, j)에 불도 이동됐는지 확인한다. 
 * 즉, isVisitied[i][j][0]과 isVisitied[i][j][1]이 둘 다 true라면 더 이상 진행하지 않는다.
 *
 * 그리고 bfs의 종료 조건은 현재 자리(i, j)가 밖으로 나왔는지의 여부이다.
 * 밖에 나와있으면 cnt를 리턴하고 종료한다.
 */

int bfs(int sr, int sc)
{
	queue<pair<int, int> > q;
	q.push(make_pair(sr, sc));

	int cnt = 1;

	while(!q.empty())
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().first;
			int c = q.front().second;
			q.pop();

			// 불과 사람이 동시에 접근하는 경우
			if (isVisited[r][c][0] == isVisited[r][c][1])
				continue;

			// 현재 위치가 밖으로 나오면 종료
			if (check_esc(r, c) == true)
				return cnt;

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (isVisited[nr][nc][0] == true)
					continue;
				else if (isVisited[nr][nc][1] == true)
					continue;

				if (map[nr][nc] == WALL)
					continue;
				isVisited[nr][nc][0] = true;

				q.push(make_pair(nr, nc));
			}
		}
		fire_move();
		cnt++;
	}
	return -1;
}

int main()
{
	freopen("in3.txt", "r", stdin);
	
	int T, testcase;
	scanf("%d", &T);
	for (testcase = 0; testcase < T; testcase++)
	{
		scanf("%d%d", &M, &N);
		for (int i = 0; i < N; i++)
			scanf("%s", map[i]);

		int ur, uc;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				if (map[i][j] == FIRE)
				{
					isVisited[i][j][1] = true;
					fire_q.push(make_pair(i, j));
				}
				else if (map[i][j] == USER)
					ur = i, uc = j;
			}
		}
		isVisited[ur][uc][0] = true;

		int res = bfs(ur, uc);
		if (res == -1)
			printf("IMPOSSIBLE\n");
		else
			printf("%d\n", res);


		// 초기화
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				isVisited[i][j][0] = false;
				isVisited[i][j][1] = false;
			}
		}
		while(!fire_q.empty())
			fire_q.pop();
	}
	return 0;
}


void print_test()
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (map[i][j] != WALL)
			{
				if (isVisited[i][j][0] == true && isVisited[i][j][1] == false)
					printf("@");
				else
					printf("%d", isVisited[i][j][1]);
			}
			else
			{
				printf("%c", map[i][j]);
			}
		}
		printf("\n");
	}
	printf("\n");
}
