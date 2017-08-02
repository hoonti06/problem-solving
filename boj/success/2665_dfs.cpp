#include <cstdio>
#include <algorithm>
#include <queue>

using namespace std;

#define MAX_N 51

int N;
int map[MAX_N][MAX_N];

int dx[4] = { 0, 1,  0, -1 };
int dy[4] = { 1, 0, -1,  0 };

bool isVisited[MAX_N][MAX_N];

// process 1
int eachcnt[MAX_N][MAX_N]; /* 방에 도달했을 때, 거쳐온 검은 방의 개수의 최솟값
							* trim 하기 위한 방법,
							* 다시 방문했을 때 현재 cnt가 이전 eachcnt 보다 높으면
							* 계속 진행할 필요가 없다.
							*/
int min_cnt = 1e9;


/* 
 * 시작점(1, 1)에서 도착점(N, N)에 도달할 때까지 함수는 끝나지 않는다.
 * 이전에 방문했었을 때의 거쳐온 검은 방의 개수의 최솟값(eachcnt[i][j])과 
 * 현재 거쳐온 검은 방의 개수(cnt)와 비교, trim한다.
 */
int dfs1(int r, int c, int cnt)
{
	if (r == N && c == N)
	{
		min_cnt = min(min_cnt, cnt);
		return 0;
	}

	if (min_cnt < cnt)
		return 0;

	for (int i = 0; i < 4; i++)
	{
		int nr = r + dx[i];
		int nc = c + dy[i];

		if (nr < 1 || nr > N || nc < 1 || nc > N)
			continue;

		if (isVisited[nr][nc] == true)
			continue;

		if (eachcnt[nr][nc] > cnt + (map[nr][nc] == 1? 0 : 1))
			eachcnt[nr][nc] = cnt + (map[nr][nc] == 1? 0 : 1);
		else
			continue;
			
		isVisited[nr][nc] = true;
		dfs1(nr, nc, cnt + (map[nr][nc] == 1? 0 : 1));
		isVisited[nr][nc] = false;
	}
}
// 1 end


// process 2
/* 
 * DFS로 최대한 갈 수 있는 곳까지 간다.
 * 검은 방(map[i][j] == 0)을 하얀 방(map[i][j] == 1)으로 바꾼 후, return한다. 
 * (DFS를 다시 수행할 시 바뀐 곳을 지나갈 수 있게 된다)
 * 
 * DFS를 다시 시작점(1, 1)부터 반복 실시하여 한번에 최대한 갈 수 있는 곳이 도착점(N, N)일 때까지 수행한다.
 */
int dfs2(int r, int c)
{
	if (r == N && c == N)
		return 0;

	for (int i = 0; i < 4; i++)
	{
		int nr = r + dx[i];
		int nc = c + dy[i];

		if (nr < 1 || nr > N || nc < 1 || nc > N)
			continue;

		if (isVisited[nr][nc] == true)
			continue;

		isVisited[nr][nc] = true;

		if (map[nr][nc] == 0)
			map[nr][nc] = 1;
		else
			dfs2(nr, nc);
	}
	return 0;
}
// 2 end

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			scanf("%1d", &map[i][j]);

	isVisited[1][1] = true;

	// process 1
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			eachcnt[i][j] = 1e9;

	dfs1(1, 1, 0);
	
	if (min_cnt == 1e9)
		min_cnt = 0;

	printf("%d", min_cnt);
	// 1 end

//			OR

	// process 2
	int cnt = 0;
	while (isVisited[N][N] == false)
	{
		cnt++;
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				isVisited[i][j] = false;

		dfs2(1, 1);
	}
	printf("%d", cnt - 1);
	// 2 end

	return 0;
}
