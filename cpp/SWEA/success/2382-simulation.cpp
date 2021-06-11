#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 102

int N, M, K;

int dx[4] = { -1, 1,  0, 0 };
int dy[4] = {  0, 0, -1, 1 };

int board[MAX_N][MAX_N];
int isVisited[MAX_N][MAX_N][2];	// 0 : 총합, 1 : 방향 

map<int, int> mp;	// 모이는 것 중 값이 가장 큰 값 저장

struct Node
{
	int r, c, num, dir;
	Node(int rr, int cc, int nn, int dd)
	{
		r = rr, c = cc, num = nn, dir = dd;
	}
};

vector<Node> vec;

bool check_boundary(int r, int c)
{
	if (r < 0 || r >= N || c < 0 || c >= N)
		return false;
	else if (0 < r && r < N-1 && 0 < c && c < N-1)
		return false;
	return true;
}


int simulation()
{
	int cnt = 0;
	while (cnt < M)
	{
		cnt++;
		memset(isVisited, 0, sizeof(isVisited));
		
		for (int i = 0; i < vec.size(); i++)
		{
			int nr = vec[i].r + dx[vec[i].dir];
			int nc = vec[i].c + dy[vec[i].dir];

			if (isVisited[nr][nc][0] > 0)
			{
				if (mp[nr*N+nc] < vec[i].num)
				{
					mp[nr*N+nc] = vec[i].num;
					isVisited[nr][nc][1] = vec[i].dir;
				}
				isVisited[nr][nc][0] += vec[i].num;
			}
			else
			{
				isVisited[nr][nc][0] = vec[i].num;
				isVisited[nr][nc][1] = vec[i].dir;
				mp[nr*N+nc] = vec[i].num;
			}


			if (check_boundary(nr, nc))
			{
				isVisited[nr][nc][0] /= 2;
				switch(isVisited[nr][nc][1])
				{
					case 0:
						isVisited[nr][nc][1] = 1;
						break;
					case 1:
						isVisited[nr][nc][1] = 0;
						break;
					case 2:
						isVisited[nr][nc][1] = 3;
						break;
					case 3:
						isVisited[nr][nc][1] = 2;
						break;
				}
			}
		}

		mp.clear();
		vec.clear();

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (isVisited[i][j][0] > 0)
				{
					Node addNod(i, j, isVisited[i][j][0], isVisited[i][j][1]);
					vec.push_back(addNod);
				}
			}
		}
	}
	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);

	for (int testcase = 1; testcase <= T; testcase++)
	{
		memset(isVisited, 0, sizeof(isVisited));

		scanf("%d%d%d", &N, &M, &K);
		for (int i = 0; i < K; i++)
		{
			int a, b, c, d;
			scanf("%d%d%d%d", &a, &b, &c, &d);
			Node tmp(a, b, c, d-1);
			vec.push_back(tmp);
		}

		simulation();
		int sum = 0;
		for (int i = 0; i < vec.size(); i++)
			sum += vec[i].num;
		
		printf("#%d %d\n", testcase, sum);

		vec.clear();
		mp.clear();
	}
}
