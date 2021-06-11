#include <cstdio>
#include <algorithm>
#include <queue>
#include <list>

using namespace std;

#define MAX_N 20
#define MAX_K 1005

int N, M, K;
int sr, sc;

int map[MAX_N][MAX_N];
int dir[MAX_K];

int tp, bot;

//list<int> dice[2] = { {} };
deque<int> dice[2];
//vector<int> dice[2];

int dx[5] = {0, 0,  0, -1, 1 };
int dy[5] = {0, 1, -1,  0, 0 };

void init_dice()
{
	for (int i = 0; i < 2; i++)
		for (int j = 0; j < 4; j++)
			dice[i].push_back(0);
}

int roll_dice(int dir)
{
	if (dir <= 2)
	{
		if (dir == 1)
			reverse(dice[0].begin(), dice[0].end()); 
		
		int front = dice[0].front();
		dice[0].pop_front();

		dice[0].push_back(front);
		
		if (dir == 1)
			reverse(dice[0].begin(), dice[0].end()); 

		dice[1][1] = dice[0][1];
		dice[1][3] = dice[0][3];

	}
	else
	{
		if (dir == 4)
			reverse(dice[1].begin(), dice[1].end()); 
		
		int front = dice[1].front();
		dice[1].pop_front();

		dice[1].push_back(front);

		if (dir == 4)
			reverse(dice[1].begin(), dice[1].end()); 

		dice[0][1] = dice[1][1];
		dice[0][3] = dice[1][3];
	}

	tp = dice[0][1];
	bot = dice[0][3];
}


int process()
{
	int r = sr;
	int	c = sc;

	for (int k = 0; k < K; k++)
	{
		r += dx[dir[k]];
		c += dy[dir[k]];

//		printf("k : %d, r : %d, c : %d\n", k, r, c);
		if (r < 0 || r >= N || c < 0 || c >= M)
		{
			r -= dx[dir[k]];
			c -= dy[dir[k]];
			continue;
		}

		roll_dice(dir[k]);

		if (map[r][c] == 0)
		{
			map[r][c] = dice[0][3];
		}
		else
		{
			dice[0][3] = dice[1][3] = map[r][c];
			map[r][c] = 0;
		}
		printf("%d\n", dice[0][1]);
		
	}
}

int main()
{
	freopen("in3.txt", "r", stdin);

	scanf("%d%d%d%d%d", &N, &M, &sr, &sc, &K);
//	printf("%d %d %d %d %d\n", N, M, sr, sc, K);

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			scanf("%d", &map[i][j]);

//	for (int i = 0; i < N; i++)
//	{
//		for (int j = 0; j < M; j++)
//		{
//			printf("%d ", map[i][j]);
//		}
//		printf("\n");
//	}
			

	for (int i = 0; i < K; i++)
		scanf("%d", &dir[i]);

//	for (int i = 0; i < K; i++)
//		printf("%d ", dir[i]);

//	printf("\n");

	init_dice();
	process();

	


	
	



	return 0;
}
