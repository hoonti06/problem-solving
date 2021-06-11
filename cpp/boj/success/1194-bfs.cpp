#include <cstdio>
#include <algorithm>
#include <queue>
#include <cctype>

using namespace std;

#define MAX_N 52

#define WALL '#'
#define END '1'

int N, M;
char map[MAX_N][MAX_N];
unsigned char isVisited[MAX_N][MAX_N][64]; /*
											* 2^6 = 64로, 6개의 key 중에서 어느 것을 가지고 있는 상태로 방문하느냐의 의미이다.
											* 
											* [i][j][5] : (i, j)를 5(2^2 + 2^0)의 상태로 방문을 하였으면 true, 아니면 false이다.
											*             2^2 -> 2 -> 'c' ,  2^0 -> 0 -> 'a' 로, a와 c를 가지고 있는 상태
											*
											*/


int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

struct Node
{
	int idx;	// r*M + c
	unsigned char key;
	Node(int i, unsigned char k)
	{
		idx = i, key = k;
	}
};

/* 
 * 최소 이동 횟수를 구하기 위해서 BFS를 이용한다.
 * 
 * 키(소문자)가 있으면 비트 마스킹으로 해당 위치를 1로 채워나간다.
 *
 *
 * 문(대문자)이 있는 경우에는 cur의 key를 확인하여 key가 존재하면 진행하고, 없으면 진행을 그만둔다.
 * cur.key를 가지고 이전에 해당 좌표에 방문하였는지와 해당 좌표가 벽인지 확인한다.
 * 
 * 현재 위치가 도착점(map[r][c] == 1)인지 확인하여 맞다면 cnt를 리턴하고 종료한다.
 * 도착점에 도달하지 못하고 queue가 비게 된다면 -1을 리턴하고 종료한다.
 */

int bfs(int sr, int sc)
{
	queue<struct Node> q;

	int ii = sr * M + sc;	
	struct Node tmp_nod(sr*M + sc, 0);
	q.push(tmp_nod);

	int cnt = 0;

	while(!q.empty())
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			struct Node cur = q.front();
			q.pop();

			int r = cur.idx / M;
			int c = cur.idx % M;
			unsigned char key = cur.key;
 
			if ('A' <= map[r][c] && map[r][c] <= 'F')
				if (!(key & 1 << (map[r][c] - 'a')))
					continue;

			if (isVisited[r][c][key] == true)
				continue;

			if (map[r][c] == WALL)
				continue;
			else if ('a' <= map[r][c] && map[r][c] <= 'f')
				key |= 1 << (map[r][c] - 'a');

			isVisited[r][c][key] = true;

			if (map[r][c] == END)
				return cnt;

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (map[nr][nc] == WALL)
					continue;

				struct Node add_nod(nr*M + nc, key);
				q.push(add_nod);
			}

		}
		cnt++;
	}
	return -1;
}

int main()
{
	freopen("in.txt", "r", stdin);

	int sr, sc;
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
	{
		scanf("%s", map[i]);
		for (int j = 0; j < M; j++)
			if (map[i][j] == '0')
				sr = i, sc = j;
	}

	printf("%d", bfs(sr, sc));
}
