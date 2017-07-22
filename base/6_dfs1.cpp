#include <cstdio>
#include <algorithm>
#include <cstring>
#include <cmath>

#define MAX_N 1005

int input[MAX_N];

int N, M;

bool edge[MAX_N][MAX_N];
bool isVisited[MAX_N];

int dfs(int cur)
{
	if (isVisited[cur] == true)
		return 0;

	isVisited[cur] = true;

	for (int i = 0; i < N; i++)
	{
		if (edge[cur][i] == true)
			dfs(i);

	}


int main(void)
{
	setbuf(stdout, NULL);

	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < M; i++)
	{
		int u, v;
		scanf("%d%d", &u, &v);
		edge[u][v] = true;

	}
	dfs(1);

	return 0;
}
