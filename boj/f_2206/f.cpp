#include <cstdio>
#include <algorithm>
#include <queue>

using namespace std;

#define MAX_N 1005

int N, M;
int map[MAX_N][MAX_N];

int dx[4] = { 1, 0, -1,  0 };
int dy[4] = { 0, 1,  0, -1 };

bool isVisited[MAX_N][MAX_N];

struct box
{
	int r, c, wcnt, cnt;
	box(int rr, int cc)
	{
		r = rr;
		c = cc;
		wcnt = 0;
		cnt = 0;
	}
};

queue<struct box> q;

int bfs()
{
	struct box sbox(1, 1);
	sbox.cnt = 1;
	q.push(sbox);

	while(!q.empty())
	{
		struct box curbox = q.front();
		q.pop();

		if (isVisited[curbox.r][curbox.c] == true)
			continue;

		isVisited[curbox.r][curbox.c] = true;
		
//		printf("cur : %d %d, wcnt : %d\n", curbox.r, curbox.c, curbox.wcnt);

		if (curbox.r == N && curbox.c == M)
			return curbox.cnt;

		for (int i = 0; i < 4; i++)
		{
			int nr = curbox.r + dx[i];
			int nc = curbox.c + dy[i];
			
			if (nr <= 0 || nr > N || nc <= 0 || nc > M)
				continue;

			if (isVisited[nr][nc] == true)
				continue;
//			printf("hi\n");

//			printf("nr : %d, nc : %d\n", nr, nc);

			struct box addbox(nr, nc);
			addbox.cnt = curbox.cnt + 1;
//			printf("wcnt : %d\n", curbox.wcnt + map[nr][nc]);
			
			addbox.wcnt = curbox.wcnt + map[nr][nc];

			if (addbox.wcnt >= 2)
			{
//				printf("bye\n");
				continue;
			}
			
			q.push(addbox);
		}
	}
	return -1;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= M; j++)
			scanf("%1d", &map[i][j]);


//	for (int i = 1; i <= N; i++)
//	{
//		for (int j = 1; j <= M; j++)
//			printf("%d ", map[i][j]);
//		printf("\n");
//	}
//	printf("\n");

	printf("%d", bfs());

}
