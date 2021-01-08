#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 1005

int N, M, V;

vector<int> vec[MAX_N];
bool isVisited[MAX_N];

int bfs(int start)
{
	queue<int> q;
	isVisited[start] = true;
	q.push(start);

	while(!q.empty())
	{
		int here = q.front();
		q.pop();
		printf("%d ", here);

		for (int i = 0; i < vec[here].size(); i++)
		{
			int there = vec[here][i];

			if (isVisited[there] == true)
				continue;

			isVisited[there] = true;

			q.push(there);
		}
	}
}

int dfs(int here)
{
	for (int i = 0; i < vec[here].size(); i++)
	{
		int there = vec[here][i];

		if (isVisited[there] == true)
			continue;
		isVisited[there] = true;

		printf("%d ", there);
		dfs(there);
	}
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d%d", &N, &M, &V);
	for (int i = 0; i < M; i++)
	{
		int tmp1, tmp2;
		scanf("%d%d", &tmp1, &tmp2);

		bool already = false;
		for (int i = 0 ; i < vec[tmp1].size(); i++)
		{
			if (vec[tmp1][i] == tmp2)
				already = true;
		}
		if (!already)
		{
			vec[tmp1].push_back(tmp2);
			vec[tmp2].push_back(tmp1);
		}
		else
			continue;
	}

	for (int i = 1; i <= N; i++)
		sort(vec[i].begin(), vec[i].end());
	
	isVisited[V] = true;
	printf("%d ", V);
	dfs(V);
	printf("\n");

	for (int i = 1; i <= N; i++)
		isVisited[i] = false;

	bfs(V);

	return 0;
}
