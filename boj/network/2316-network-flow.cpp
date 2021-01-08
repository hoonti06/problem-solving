#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>
#include <queue>

using namespace std;

#define MAX_V 800

int N, P;
int S = 0, E = 401;

struct Edge
{
	int c, f, to;
	Edge *dual;
	Edge(): Edge(-1, 0){}
	Edge(int to1, int c1): to(to1), c(c1), f(0), dual(NULL){}
	int spare(){
		return c - f;
	}
	void addFlow(int flow)
	{
		f += flow;
		dual->f -= flow;
	}
};

vector<Edge *> vec[MAX_V];

int main()
{
	freopen("in.txt", "r", stdin);
	
	scanf("%d%d", &N, &P);
	for (int i = 0; i < N; i++)
	{
		int u = 400 + i, v = i;
		Edge *e1 = new Edge(v, 1), *e2 = new Edge(u, 0);
		e1->dual = e2;
		e2->dual = e1;
		vec[u].push_back(e1);
		vec[v].push_back(e2);
	}

	for (int i = 0; i < P; i++)
	{
		int a, b;
		scanf("%d%d", &a, &b);
		a--, b--;

		Edge *e1 = new Edge(400 + b, 1), *e2 = new Edge(400 + a, 1);
		e1->dual = e2;
		e2->dual = e1;
		vec[a].push_back(e1);
		vec[b].push_back(e2);
	}

	int res = 0;
	while (true)
	{
		int prev[MAX_V];
		Edge *path[MAX_V] = {0, };
		memset(prev, -1, sizeof(prev));

		queue<int> q;
		q.push(S);

		while (!q.empty())
		{
			int cur = q.front();
			q.pop();

			for (int i = 0; i < vec[cur].size(); i++)
			{
				Edge *e = vec[cur][i];
				int next = e -> to;

				if (e->spare() > 0 && prev[next] == -1)
				{
					q.push(next);
					prev[next] = cur;
					path[next] = e;
					
					if (next == E)
						break;
				}
			}
		}

		if (prev[E] == -1)
			break;

		for (int i = E; i != S; i = prev[i])
			path[i]-> addFlow(1);

		res++;
	}
	printf("%d", res);
}
