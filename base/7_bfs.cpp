#include <cstdio>
#include <cstring>
#include <algorithm>
#include <cmath>
#include <queue>

using namespace std;


#define MAX_N 1005

int N, M;

vector<int> edge[MAX_N];
queue<int> q;

bool isVisited[MAX_N];

int bfs(int cur)
{
	q.push(cur);

	while (!q.empty())
	{
		int here = q.front();
		q.pop();

		if (isVisited[here])
			continue;

		isVisited[here] = true;
		printf("%d ", here);

		for (int i = 0; i < (int)edge[here].size(); i++)
		{
			int there = edge[here][i];

			if (isVisited[there] == false)
				q.push(there);

		}

	}
	return 0;
}


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < M; i++)
	{
		int u, v;
		scanf("%d%d", &u, &v);

		edge[u].push_back(v);
	}

	bfs(1);

	return 0;
}
