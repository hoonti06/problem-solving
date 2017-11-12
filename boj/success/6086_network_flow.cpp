#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>
#include <queue>

using namespace std;

#define MAX_V 52

int N;

vector<int> vec[MAX_V];

int CtoI(char c)
{
	if (c <= 'Z')
		return c - 'A';
	else
		return c - 'a' + 26;
}


int main()
{
	freopen("in.txt", "r", stdin);

	int c[MAX_V][MAX_V] = {0, };
	int f[MAX_V][MAX_V] = {0, };

	scanf("%d\n", &N);
	for (int i = 0; i < N; i++)
	{
		char a, b;
		int d;
		scanf("%c %c %d\n", &a, &b, &d);
		a = CtoI(a), b = CtoI(b);
//		printf("%d %d\n", a, b);
		c[a][b] += d;
		vec[a].push_back(b);
		vec[b].push_back(a);
	}
//	for (int i = 0; i < N; i++)
//	{
//		for (int j = 0; j < vec[i].size(); j++)
//		{
//			printf("%d ", vec[i][j]);
//		}
//		printf("\n");
//	}

	int res = 0, S = CtoI('A'), E = CtoI('Z');
	while (true)
	{
		int prev[MAX_V];
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

		int flow = 1e9;
		for (int i = E; i != S; i = prev[i])
			flow = min(flow, c[prev[i]][i] - f[prev[i]][i]);

		for (int i = E; i != S; i = prev[i])
		{
			f[prev[i]][i] += flow;
			f[i][prev[i]] -= flow;
		}
		res += flow;
	}
	printf("%d", res);
}
