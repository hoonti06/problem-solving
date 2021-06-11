#include <cstdio>
#include <algorithm>
#include <queue>

using namespace std;

#define MAX_N 52

#define WATER '*'
#define END 'D'
#define START 'S'
#define WALL 'X'

//void print_test();

int N, M;
char map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N][2];	/* [i][j][0] : 고슴도치의 (i, j) 방문 여부
									 * [i][j][1] : 물의 (i, j) 방문(이동) 여부
									 */

int dx[4] = { 0, -1, 0, 1};
int dy[4] = {-1,  0, 1, 0};

// 물의 이동을 위한 queue
queue<pair<int, int> > wQ;

int water_move()
{
	int qSize = wQ.size();

	for (int qs = 0; qs < qSize; qs++)
	{
		int r = wQ.front().first;
		int c = wQ.front().second;
		wQ.pop();
		
		for (int i = 0; i < 4; i++)
		{
			int nr = r + dx[i];
			int nc = c + dy[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M || isVisited[nr][nc][1])
				continue;
			else if (map[nr][nc] == WALL || map[nr][nc] == END)
				continue;

			isVisited[nr][nc][1] = true;
			wQ.push(make_pair(nr, nc));
		}
	}
}

/*
 * 물의 이동 및 고슴도치의 이동을 일일이 map에 표시하는 게 아니라 isVisited에 표시하는 것이다.
 *
 * BFS에서 한 턴에서 고슴도치의 움직일 수 있는 이동을 먼저 진행한 다음, cnt를 증가시킬 때 물을 이동시킨다.
 * 그렇게 되면 사람과 물이 동시에 같은 위치로 이동해야할 때를 캐치하지 못하기 때문에,
 * 그런 상황이 오면 일단 사람을 먼저 그 위치에 두고 cnt를 증가시킨다. 
 * 다음 턴이 되어 해당 위치가 current가 돼서 다음 위치로 이동해야 될 차례가 되면, 
 * 다음 위치로의 이동 전에 현재 위치(i, j)에 물도 이동됐는지 확인한다. 
 * 즉, isVisitied[i][j][0]과 isVisitied[i][j][1]이 둘 다 true라면 더 이상 진행하지 않는다.
 *
 * 그리고 BFS의 종료 조건은 현재 자리(i, j)가 도착지점에 도달하는 것이다.
 * 도달하게 되면 cnt를 리턴하고 종료한다.
 */

int bfs(int sr, int sc)
{
	queue<pair<int, int> > q;
	q.push(make_pair(sr, sc));
	int cnt = 0;

	while(!q.empty())
	{
		int qSize = q.size();

		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().first;
			int c = q.front().second;
			q.pop();

			if (isVisited[r][c][0] && isVisited[r][c][1])
				continue;

			if (map[r][c] == END)
				return cnt;

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || isVisited[nr][nc][0])
					continue;
				else if (isVisited[nr][nc][1])
					continue;

				if (map[nr][nc] == WALL)
					continue;

				isVisited[nr][nc][0] = true;
				q.push(make_pair(nr, nc));
			}
		}
		water_move();
		cnt++;
	}
	return -1;
}

int main()
{
	freopen("in4.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		scanf("%s", map[i]);

	int sr, sc; 
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (map[i][j] == WATER)
			{
				isVisited[i][j][1] = true;
				wQ.push(make_pair(i, j));
			}
			else if (map[i][j] == START)
			{
				isVisited[i][j][0] = true;
				sr = i, sc = j;
			}
		}
	}

	int res = bfs(sr, sc);
	if (res == -1)
		printf("KAKTUS");
	else
		printf("%d", res);

	return 0;
}

//void print_test()
//{
//	for (int i = 0; i < N; i++)
//	{
//		for (int j = 0; j < N; j++)
//		{
//			if (isVisited[i][j][1] == true)
//				printf("1");
//			else if (isVisited[i][j][0] == true)
//				printf("S");
//			else 
//				printf("%c", map[i][j]);
//		}
//		printf("\n");
//	}
//	printf("\n");
//}
