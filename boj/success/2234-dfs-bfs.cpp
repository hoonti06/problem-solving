#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>
#include <queue>
#include <cmath>
#include <map>

using namespace std;

#define MAX_N 52

int N, M;

int board[MAX_N][MAX_N];

bool isVisited[MAX_N][MAX_N][2];

map<int, int> mp;

int dx[4] = { 0, -1, 0, 1 };
int dy[4] = { -1, 0, 1, 0 };

vector<pair<int, int> > vec;

int cnt;
int dfs(int r, int c)
{
	cnt++;

	int num = 16;
	int tmp = board[r][c];
	for (int i = 3; i >= 0; i--)
	{
		int nr = r + dx[i];
		int nc = c + dy[i];

		num >>= 1;
		if (tmp >= num)
		{
			tmp -= num;
			continue;
		}

		if (isVisited[nr][nc][0] == true)
			continue;

		isVisited[nr][nc][0] = true;
		dfs(nr, nc);
	}
	return 0;
}

struct Node 
{
	int r, c;
	bool isBroken;
	Node(int rr, int cc, bool flag)
	{
		r = rr;
		c = cc;
		isBroken = flag;
	}
};

int bfs(int sr, int sc)
{
	queue<Node> q;
	Node nod(sr, sc, false);
	q.push(nod);

	while (!q.empty())
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().r;
			int c = q.front().c;
			bool isBroken = q.front().isBroken;
			q.pop();

			int num = 16;
			int tmp = board[r][c];
			for (int i = 3; i >= 0; i--)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				num >>= 1;
				if (tmp >= num)
				{
					tmp -= num;

					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;

					if (isBroken == false && isVisited[nr][nc][1] == false)
					{
						isVisited[nr][nc][1] = true;

						Node addNod(nr, nc, true);
						q.push(addNod);
						continue;
					}
					else
						continue;
				}
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (isVisited[nr][nc][isBroken] == true)
					continue;

				isVisited[nr][nc][isBroken] = true;

				Node newNod(nr, nc, isBroken);
				q.push(newNod);
			}
		}
	}
	return 0;
}


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &M, &N);
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			scanf("%d", &board[i][j]);

	int maximum = 0;
	int roomCnt = 0;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (isVisited[i][j][0] == false)
			{
				vec.push_back(make_pair(i, j));

				isVisited[i][j][0] = true;
				cnt = 0;
				dfs(i, j);
				roomCnt++;
				maximum = max(maximum, cnt);

				mp[i*M+j] = cnt;
			}
		}
	}

	int res = 0;
	for (int i = 0; i < vec.size(); i++)
	{
		memset(isVisited, 0, sizeof(isVisited));

		int r = vec[i].first;
		int c = vec[i].second;

		bfs(r, c);

		for (int j = 0; j < vec.size(); j++)
		{
			if (i == j)
				continue;

			int nr = vec[j].first;
			int nc = vec[j].second;
			if (isVisited[nr][nc][1] == true)
				res = max(res, mp[nr*M + nc] + mp[r*M + c]);
		}
	}
	printf("%d\n", roomCnt);
	printf("%d\n", maximum);
	printf("%d", res);
}
