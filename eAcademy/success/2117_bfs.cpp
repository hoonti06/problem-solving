#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 22

int N, K;

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

int board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

vector<int> range;

int res;

int bfs(int sr, int sc, int price)
{
	queue<pair<int, int> > q;
	q.push(make_pair(sr, sc));

	int cnt = 0;
	int sum = 0;
	while (!q.empty() && cnt <= N*5)
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().first;
			int c = q.front().second;
			q.pop();

			if (isVisited[r][c] == true)
				continue;
			isVisited[r][c] = true;

			if (board[r][c] == 1)
				sum++;

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;

				if (isVisited[nr][nc] == true)
					continue;

				q.push(make_pair(nr, nc));
			}
		}

		if (range[cnt] <= sum*price)
			if (res < sum)
				res = sum;
		cnt++;
	}
	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);

	range.push_back(1);
	int prev = 1;
	for (int i = 2; i <= MAX_N*8; i++)
	{
		range.push_back(prev + i*i);
		prev = i*i;
	}

	for (int testcase = 1; testcase <= T; testcase++)
	{
		res = 0;
		memset(isVisited, 0, sizeof(isVisited));

		scanf("%d%d", &N, &K);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				scanf("%d", &board[i][j]);

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				memset(isVisited, 0, sizeof(isVisited));
				bfs(i, j, K);
			}
		
		printf("#%d %d\n", testcase, res);
	}
}
