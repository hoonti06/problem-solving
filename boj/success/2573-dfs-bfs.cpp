#include <cstdio>
#include <algorithm>
#include <queue>

using namespace std;

#define MAX_N 305

int N, M;

int map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

struct node 
{
	int r, c;
	node(int rr, int cc)
	{
		r = rr;
		c = cc;
	}
};

int island_cnt;		// 섬의 개수
int res;			// 결과값

int dx[4] = {  0, 0, 1, -1 };
int dy[4] = { -1, 1, 0,  0 };

// 섬의 개수를 세기 위한 dfs
void dfs_for_island_count(int r, int c)
{
	isVisited[r][c] = true;
	
	for (int i = 0; i < 4; i++)
	{
		int nr = r + dx[i];
		int nc = c + dy[i];

		if (nr < 0 || nr >= N || nc < 0 || nc >= M)
			continue;

		if (map[nr][nc] > 0 && isVisited[nr][nc] == false)
			dfs_for_island_count(nr, nc);
	}
}

// 섬의 개수를 세기 위한 bfs
int bfs_for_island_count(int r, int c)
{
	queue <struct node> q;
	
	struct node tnode(r, c);
	q.push(tnode);
	isVisited[r][c] = true;

	while (!q.empty())
	{
		int curr = q.front().r;
		int curc = q.front().c;
		q.pop();

		for (int i = 0; i < 4; i++)
		{
			int nr = curr + dx[i];
			int nc = curc + dy[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
				
			if (isVisited[nr][nc] == false && map[nr][nc] > 0)
			{
				struct node addnode(nr, nc);
				addnode.r = nr;
				addnode.c = nc;
				isVisited[nr][nc] = true;

				q.push(addnode);
			}
		}

	}
	return 0;
}


// 빙산 높이를 감소시키기 위한 함수, 함수의 인자의 대상은 아직 녹지 않은 빙산이다. (map[r][c] > 0)
void func_for_melt(int r, int c)
{
	int sea_cnt = 0;

	for (int i = 0; i < 4; i++)
	{
		int nr = r + dx[i];
		int nc = c + dy[i];

		if (nr < 0 || nr >= N || nc < 0 || nc >= M)
			continue;

		if (map[nr][nc] == 0 && isVisited[nr][nc] == false)
			sea_cnt++;

	}
	map[r][c] -= sea_cnt;
	if (map[r][c] < 0)
		map[r][c] = 0;

	isVisited[r][c] = true;
}

// 빙산 높이를 감소시키기 위한 dfs, 함수의 인자의 대상은 바다이다. (map[r][c] == 0)
void dfs_for_melt(int r, int c)
{
	isVisited[r][c] = true;

	for (int i = 0; i < 4; i++)
	{
		int nr = r + dx[i];
		int nc = c + dy[i];

		if (nr < 0 || nr >= N || nc < 0 || nc >= M)
			continue;

		if (map[nr][nc] > 0)
		{
			isVisited[nr][nc] = true;
			map[nr][nc]--;
		}
		else if (map[nr][nc] == 0 && isVisited[nr][nc] == false)
			dfs_for_melt(nr, nc);
	}
}

int main()
{
	freopen("in.txt", "r", stdin);
	
	scanf("%d%d", &N, &M);

	int i, j;
	for (i = 0; i < N; i++)
		for (j = 0; j < M; j++)
			scanf("%d", &map[i][j]);

	while (true)
	{
		island_cnt = 0;
		for (i = 0; i < N; i++)
			for (j = 0; j < M; j++)
				isVisited[i][j] = false;

		for (i = 0; i < N; i++)	// 섬의 개수 세기 위한 for문 
		{
			for (j = 0; j < M; j++)
			{
				if (map[i][j] > 0 && isVisited[i][j] == false)
				{
					island_cnt++;
					if (island_cnt > 1)
						break;

					bfs_for_island_count(i, j);
//							OR
					dfs_for_island_count(i, j);
				}
			}
		}

		for (i = 0; i < N; i++)
			for (j = 0; j < M; j++)
				isVisited[i][j] = false;

		for (i = 0; i < N; i++)	// 빙산 높이를 감소시키기 위한 for문
		{
			for (j = 0; j < M; j++)
			{
				if (map[i][j] != 0 && isVisited[i][j] == false)
					func_for_melt(i, j);
//									OR
				if (map[i][j] == 0 && isVisited[i][j] == false)
					dfs_for_melt(i, j);
			}
		}

		if (island_cnt > 1)
		{
			printf("%d", res);
			return 0;
		}
		else if (island_cnt == 0)
		{
			printf("0");
			return 0;
		}
		res++;
	}
	return 0;
}
