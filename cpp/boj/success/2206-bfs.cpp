#include <cstdio>
#include <algorithm>
#include <queue>

using namespace std;

#define MAX_N 1005

int N, M;
int map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N][2]; /* 
								  * [i][j][0] : 이전까지 벽을 부시지 않고 (i, j) 방문 여부
								  * [i][j][1] : 이전까지 벽을 한번 부수고 (i, j) 방문 여부
								  */

int dx[4] = { 0, -1,  0, 1 };
int dy[4] = { 1,  0, -1, 0 };

struct Node
{
	int r, c;
	bool isCrashed;	// 이전까지 벽을 한번 부쉈는지
	Node(int rr, int cc, bool flag)
	{
		r = rr, c = cc;
		isCrashed = flag;
	}
};

int bfs(int sr, int sc)
{
	queue<struct Node> q;

	struct Node tmp_nod(sr, sc, false);
	isVisited[sr][sc][0] = true;

	q.push(tmp_nod);

	int cnt = 1;

	while(!q.empty())
	{
		/*
		 * q의 size를 상수로 저장하고, for문을 돌린다.
		 * -> for문을 한번 돌때마다 count(level)가 1 증가한다.
		 */
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)	
		{
			struct Node cur = q.front();
			q.pop();

			// 도착점에 도달한 경우 종료
			if (cur.r == N-1 && cur.c == M-1)
				return cnt;

			for (int i = 0; i < 4; i++)
			{
				int nr = cur.r + dx[i];
				int nc = cur.c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (isVisited[nr][nc][cur.isCrashed] == true)
					continue;

				// 벽인 경우, 이전에 한번 벽을 부순 경우 스킵한다.
				// 벽을 부술 수 있는 경우에는 부수고(isVisited[nr][nc][1] = true), 이동
				if (map[nr][nc] == 1)
				{
					if (cur.isCrashed == true)
						continue;
					isVisited[nr][nc][1] = true;
				
					struct Node add_nod(nr, nc, true);
					q.push(add_nod);
				}
				// 벽이 아닌 경우, 그냥 이동
				else
				{
					isVisited[nr][nc][cur.isCrashed] = true;

					struct Node add_nod(nr, nc, cur.isCrashed);
					q.push(add_nod);
				}
			}
		}
		cnt++;
	}
	// 도착점에 도달 못하고 q가 비어 종료
	return -1;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			scanf("%1d", &map[i][j]);

	printf("%d", bfs(0, 0));
	return 0;
}
