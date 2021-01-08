#include <cstdio>
#include <algorithm>

#define MAX_N 55

int map[MAX_N][MAX_N];

/*
 * 문제에서는 북 : 0, 동 : 1, 남 : 2, 서 : 3 이지만
 * dx, dy는 서 : 0, 북 : 1, 동 : 2, 남 : 3 이다.
 * 그 이유는 맨 처음 입력을 받은 값 자체를 바로 왼쪽으로 회전된 상태로 만들기 위함이다.
 */
int dx[4] = { 0, -1, 0, 1};
int dy[4] = {-1,  0, 1, 0};

int res;

/*
 * 한 방향씩 확인하면서 청소되지 않은 곳부터 방문하기 때문에 DFS나 BFS가 아닌 Simulation이다.
 *
 * 모든 방향이 청소되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진하지 못하는 경우를 
 * 확인하여야 하기 때문에, 4 방향을 확인하는 for문을 다 돌아야 하는 경우도 생긴다.
 */

int main()
{
	freopen("in.txt", "r", stdin);

	int N, M;
	scanf("%d%d", &N, &M);

	int r, c, dir;
	scanf("%d%d%d", &r, &c, &dir);

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			scanf("%d", &map[i][j]);

	bool isFinish = false;
	while(!isFinish)
	{
		if (map[r][c] == 0)
			res++;
		map[r][c] = 2;
			
		for (int i = 0; i < 5; i++)
		{
			int nr, nc;

			if (i != 4)
			{
				nr = r + dx[dir];
				nc = c + dy[dir];

				// for문 진행 여부를 확인하기 전에 미리 방향을 회전시켜준다.
				dir = (dir + 3) % 4;

				// 청소했거나 벽인 경우는 for문을 계속 진행한다.
				if (map[nr][nc] == 0)
				{
					r = nr, c = nc;
					break;
				}
			}
			// i == 4인 경우, 뒤쪽 방향으로 후진하는 경우이다.
			else
			{
				nr = r + dx[(dir + 3) % 4];
				nc = c + dy[(dir + 3) % 4];

				// 벽이라면 이대로 종료된다.
				if (map[nr][nc] == 1)
					isFinish = true;
				else
					r = nr, c = nc;
			}
		}
	}
	printf("%d", res);
}
