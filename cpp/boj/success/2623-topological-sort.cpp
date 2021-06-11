#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 1005

int N, M;

vector<vector<int> > edge;
vector<int> ind; // ind[i]는 i번 정점을 향하는 간선의 개수

/*
 * Topological Sort
 * 루프를 N번 돌기 전에 q가 비어버리면 싸이클이 생겼다는 의미. 즉, 위상정렬 불가능
 * 간선이 삭제될 때에는 ind[i]의 값을 하나 줄여준다.
 */

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	edge.resize(N+1);
	ind.resize(N+1);

	for(int i = 0; i < M; i++)
	{
		int a;
		scanf("%d", &a);
		if (a == 0)
			continue;

		int from, to;
		scanf("%d", &from);
		for (int j = 1; j < a; j++)
		{
			scanf("%d", &to);
			edge[from].push_back(to);
			ind[to]++;
			from = to;
		}
	}

	int result[MAX_N];
	queue<int> q;

	for (int i = 1; i <= N; i++)
		if (ind[i] == 0)
			q.push(i);
	
	for (int j = 0; j < N; j++)
	{
		if(q.empty())
		{
			printf("0");
			return 0;
		}
			
		int here = q.front();
		q.pop();
		result[j] = here;

		for (int i = 0; i < edge[here].size(); i++)
		{
			int there = edge[here][i];
			ind[there]--;
			if (ind[there] == 0)
				q.push(there);
		}
	}

	for (int i = 0; i < N; i++)
		printf("%d\n", result[i]);
}
