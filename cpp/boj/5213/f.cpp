#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 502

int N;

int board[MAX_N][MAX_N*2];
int num[MAX_N][MAX_N*2];
int d[MAX_N*MAX_N], r[MAX_N*MAX_N];

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

vector<int> vec[MAX_N*MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);

	int cnt = 2;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < 2*N; j++)
		{
			if (i % 2 == 1 && (j == 0 || j == 2*N-1))
			{
				isVisited[i][j] = true;
				continue;
			}
			scanf("%d", &board[i][j]);

			num[i][j] = cnt/2;
			cnt++;
		}
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < 2*N; j++)
		{
			for (int k = 0; k < 4; k++)
			{
				if (i % 2 == 1 && (j == 0 || j == 2*N-1))
					continue;
				int nr = i + dx[k];
				int nc = j + dy[k];

				if (nr < 0 || nr >= N || nc < 0 || nc >= 2*N)
					continue;

				if (board[i][j] == 0 || num[i][j] == num[nr][nc] || board[i][j] != board[nr][nc])
					continue;

				vec[num[i][j]].push_back(num[nr][nc]);

			}
		}
	}

	memset(d, -1, sizeof(d));
	queue<int> q;

	d[1] = 0;
	r[1] = -1;
	q.push(1);

	while (!q.empty())
	{
		int here = q.front();
		q.pop();

		for (int i = 0; i < vec[here].size(); i++)
		{
			int there = vec[here][i];

			if (d[there] != -1)
				continue;

			d[there] = d[here] + 1;
			r[there] = here;
			q.push(there);
		}
	}

	int res;
	for (res = N*N - N/2; d[res] == -1; res--);

	vector<int> p;
	for (int x = res; x != -1; x = r[x])
		p.push_back(x);

	printf("%d\n", p.size());
	for (int i = p.size()-1; i >= 0; i--)
		printf("%d ", p[i]);
}

