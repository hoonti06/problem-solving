#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

int N, M;
int u, v;

vector<vector<int> > edge;
vector<int> ind;

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	edge.resize(N+1);
	ind.resize(N+1);

	for(int i = 0; i < M; i++)
	{
		scanf("%d%d", &u, &v);
		edge[u].push_back(v);
		ind[v]++;
	}

	queue<int> q;
	for (int i = 1; i <= N; i++)
		if (ind[i] == 0)
			q.push(i);

	while(!q.empty())
	{
		int here = q.front();
		q.pop();
		printf("%d ", here);
	
		for(int i = 0; i < edge[here].size(); i++)
		{
			int there = edge[here][i];
			ind[there]--;
			printf("ind[%d] : %d\n", there, ind[there]);
			if (ind[there] == 0)
				q.push(there);
		}
	}
}
