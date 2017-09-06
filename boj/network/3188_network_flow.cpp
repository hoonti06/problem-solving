#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>
#include <queue>

using namespace std;

#define MAX_V 405

int N, M;
int res = 0, S = 0, E = 401;


vector<int> vec[MAX_V];

int main()
{
	freopen("in.txt", "r", stdin);
	int c[MAX_V][MAX_V] = {0, };
	int f[MAX_V][MAX_V] = {0, };

	scanf("%d%d", &N, &M);
	for (int i = 1; i <= N; i++)
	{
		vec[S].push_back(i);
		c[S][i] = 1;

		vec[200+i].push_back(E);
		c[200+i][E] = 1;

		int a;
		scanf("%d", &a);
		for (int j = 0; j < a; j++)
		{
			int b;
			scanf("%d", &b);
			b += 200;

			vec[i].push_back(b);
			vec[b].push_back(i);
			c[i][b] = 1;
		}
	}

//	for (int i = 0; i < MAX_V; i++)
//	{
//		if (vec[i].size() == 0)
//			continue;
//		printf("%d : ", i);
//		for (int j = 0; j < vec[i].size(); j++)
//		{
//			printf("%d, ", vec[i][j]);
//		}
//		printf("\n");
//	}

	while (true)
	{
		int prev[405];
		memset(prev, -1, sizeof(prev));

		queue<int> q;
		q.push(S);

		while(!q.empty())
		{
			int cur = q.front();
			q.pop();

			for (int i = 0; i < vec[cur].size(); i++)
			{
				int next = vec[cur][i];

				if (c[cur][next] - f[cur][next] > 0 && prev[next] == -1)
				{
					q.push(next);
					prev[next] = cur;

					if (next == E)
						break;
				}
			}
		}

		if (prev[E] == -1)
			break;

		for (int i = E; i != S; i = prev[i])
		{
			f[prev[i]][i]++;
			f[i][prev[i]]--;
		}
		res++;
	}
	printf("%d", res);

	return 0;
}
