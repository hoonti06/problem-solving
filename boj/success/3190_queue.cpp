#include <cstdio>
#include <algorithm>
#include <queue>

using namespace std;

#define MAX_N 105

int N, K, L;

int isVisited[MAX_N][MAX_N];

int li[MAX_N];
char lc[MAX_N];
int l_cnt;

int res; 

//왼 위 오 아
int dx[4] = { 0, -1, 0, 1};
int dy[4] = {-1, 0,  1, 0};

int dir;

struct node
{
	int r, c, dir;
	node(int x, int y, int z)
	{
		r = x;
		c = y;
		dir = z;
	}
};

queue <struct node> q;

int process()
{
	int r = 1;
	int c = 1;

	isVisited[r][c] = 1;
	dir = 2;

	struct node tmp1(r, c, dir);
	q.push(tmp1);

	res = 1;
	while(true)
	{

		r += dx[dir];
		c += dy[dir];

		if (r <= 0 || r > N || c <= 0 || c > N)
			break;

		if (isVisited[r][c] == 1)
			break;

		struct node tmp(r, c, dir);
		q.push(tmp);

		if (isVisited[r][c] == 0)
		{
			struct node tmp2 = q.front();
			isVisited[tmp2.r][tmp2.c] = 0;
			q.pop();
		}
		isVisited[r][c] = 1;
		
		if (l_cnt < L && li[l_cnt] == res)
		{
			if (lc[l_cnt] == 'L')
				dir = (dir + 3) % 4;
			else
				dir = (dir + 1) % 4;

			l_cnt++;
		}

		res++;
	}

	return 0;

}


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &K);

	for(int i = 0; i < K; i++)
	{
		int tr, tc;
		scanf("%d%d", &tr, &tc);
		isVisited[tr][tc] = 2;
	}
	
	scanf("%d", &L);
	for (int i = 0; i < L; i++)
		scanf("%d %c", &li[i], &lc[i]);

	process();
	printf("%d", res);
}
