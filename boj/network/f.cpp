#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

#define MAX_N 100
#define MAX_V 2*(MAX_N+1)

int N, M;
int S = MAX_V-2;
int E = MAX_V-1;

int c[MAX_V][MAX_V];
int d[MAX_V][MAX_V];
int f[MAX_V][MAX_V];

vector<int> vec[MAX_V];

int main()
{
	freopen("in.txt", "r", stdin);
	
	scanf("%d%d", &N, &M);
	for (int i = MAX_N; i < MAX_N+N; i++)
	{
		scanf("%d", &c[i][E]);
		vec[i].push_back(E);
		vec[E].push_back(i);
	}

	for (int i = 0; i < M; i++)
	{
		scanf("%d", &c[S][i]);
		vec[S].push_back(i);
		vec[i].push_back(S);
	}

	for (int i = 0; i < M; i++)
	{
		for (int j = MAX_N; j < MAX_N+N; j++)
		{
			scanf("%d", &d[i][j]);
			d[j][i] = -d[i][j];
			c[i][j] = 1e9;
			vec[i].push_back(j);
			vec[j].push_back(i);
		}
	}

	int res = 0;
	while (true)
	{
		int prev[MAX_V], dist[MAX_V];
		bool inQ[MAX_V] = {0, };
		queue<int> q;
		fill(prev, prev+MAX_V, -1);
		fill(dist, dist+MAX_V, 1e9);

		dist[S] = 0;
		inQ[S] = true;
		q.push(S);

		while (!q.empty())
		{
			int cur = q.front();
			q.pop();
			inQ[cur] = false;
			for (int i = 0; i < vec[cur].size(); i++)
			{
				int next = vec[cur][i];

				if (c[cur][next] - f[cur][next] > 0 && dist[next] > dist[cur] + d[cur][next])
				{
					dist[next] = dist[cur] + d[cur][next];
					prev[next] = cur;

					if (!inQ[next])
					{
						q.push(next);
						inQ[next] = true;
					}
				}
			}
		}
		if (prev[E] == -1)
			break;

		int flow = 1e9;
		for (int i = E; i != S; i = prev[i])
			flow = min(flow, c[prev[i]][i] - f[prev[i]][i]);

		for (int i = E; i != S; i = prev[i])
		{ 
			res += flow * d[prev[i]][i];
			f[prev[i]][i] += flow;
			f[i][prev[i]] -= flow;
		}

	}
	printf("%d", res);
}
