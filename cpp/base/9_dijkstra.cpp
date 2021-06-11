#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <list>
#include <stack>
#include <cstring>
#include <cmath>
#include <string>
#include <functional>

using namespace std;

#define MAX_V 20005
#define INF 1e9

int main()
{
	freopen("in.txt", "r", stdin);

	int V, E, K;
	vector<pair<int, int> > edge[MAX_V];

	scanf("%d%d%d", &V, &E, &K);
	K--;
	for (int i = 0; i < E; i++)
	{
		int u, v, w;
		scanf("%d%d%d", &u, &v, &w);
		edge[u-1].push_back(make_pair(v-1, w));
	}

	int dist[MAX_V];
	fill(dist, dist + MAX_V, INF);
//	int prev[MAX_V];
//	fill(prev, prev + MAX_V, -1);

	// first : dist , second : vertex_position
	priority_queue<pair<int, int> > pq;

	// Min-Heap처럼 사용하기 위해 앞의 거리 인자에 -값을 곱해줌.
	pq.push(make_pair(0, K));

	dist[K] = 0;


	while (!pq.empty())
	{
		int here = pq.top().second;
		int hereDist = -pq.top().first;
		pq.pop();

		if (dist[here] < hereDist)
			continue;

		for (int i = 0; i < edge[here].size(); i++)
		{
			int there = edge[here][i].first;
			int nextDist = hereDist + edge[here][i].second;

			if (dist[there] == -1 || dist[there] > nextDist)
			{
				dist[there] = nextDist;
				pq.push(make_pair(-nextDist, there));
//				prev[there] = here;
			}
		}
	}

	for (int i = 0; i < V; i++)
	{
		if (dist[i] == INF)
			printf("INF\n");
		else
			printf("%d\n", dist[i]);
	}
	return 0;
}
