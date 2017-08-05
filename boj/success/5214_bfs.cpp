#include <cstdio>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

#define MAX_N 205

int N, M, K;
int map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N][31]; /* 
								   * [i][j][k] : (i, j)에서 knight 이동을 k번째 해서 방문했는지 여부
								   */

// 0 ~ 3 : 상하좌우 한칸, 4 ~ 11 : Knight 이동
int dx[12] = { 0, -1, 0, 1, -1, 1, 2,  2,  1, -1, -2, -2 };
int dy[12] = {-1,  0, 1, 0,  2, 2, 1, -1, -2, -2, -1,  1 };

struct Node
{
	int r, c;
	int	cnt; // knight 이동 횟수
	Node(int rr, int cc, int ccnt)
	{
		r = rr, c = cc;
		cnt = ccnt;
	}
};


/*
 * bfs에서 한칸 이동 4 방향과 knight 이동 8 방향, 총 12 방향으로 이동한다.
 *
 * isVisited는 3차원 배열을 두어 knight 이동을 몇 번하여 
 * 해당 좌표에 도달하는 지를 알 수 있다.
 *
 * 다음 좌표를 이미 방문하였거나 갈 수 없는 곳(map[i][j] == 1)이면 진행하지 않는다.
 * 다음 좌표를 knight 이동으로 갈 때, 현재 kight 이동 횟수에 1을 더하여 진행한다.
 * knight 이동이 정해진 기준보다 높아지면 더 이상 진행하지 않는다.
 */

int bfs(int sr, int sc)
{
	queue<struct Node> q;
	struct Node tmp_nod(sr, sc, 0);
	isVisited[sr][sc][0] = true;

	q.push(tmp_nod);

	int cnt = 1;

	while(!q.empty())
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			struct Node cur = q.front();
			q.pop();

			if (cur.r == N-1 && cur.c == M-1 && cur.cnt <= K)
				return cnt-1;

			for (int i = 0; i < 12; i++)
			{
				int nr = cur.r + dx[i];
				int nc = cur.c + dy[i];

				int nextCnt = cur.cnt;

				if (3 < i && i <12)
					nextCnt++;

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (isVisited[nr][nc][nextCnt] == true)
					continue;
				else if (map[nr][nc] == 1)
					continue;

				if (nr == N-1 && nc == M-1 && nextCnt <= K)
					return cnt;

				if (nextCnt > K)
					continue;

				struct Node add_nod(nr, nc, nextCnt);
				isVisited[nr][nc][nextCnt] = true;

				q.push(add_nod);
			}
		}
		cnt++;
	}
	return -1;
}

int main()
{
	freopen("in2.txt", "r", stdin);
	
	scanf("%d", &K);
	scanf("%d%d", &M, &N);
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			scanf("%d", &map[i][j]);

	printf("%d", bfs(0, 0));
	return 0;
}
