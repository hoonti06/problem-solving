#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 10005

int N, M;

vector<vector<pair<int, int> > > vec;

struct Node 
{
	int here, prev, cost;
	Node(int he, int pr, int co) 
	{
		here = he, prev = pr, cost = co;
	}
};


int process(int start, int end)
{
//	memset(cost, -1, sizeof(cost));

//	priority_queue<pair<int, int> > pq;
//	pq.push(0, start);

	queue<struct Node> q;
	struct Node start_nod(start, -1, 0);

	q.push(start_nod);

	int max_cost = 0;

	while (!q.empty())
	{
		struct Node cur = q.front();
		q.pop();

		if (cur.here == end)
		{
			max_cost = max(max_cost, cur.cost);
			continue;
		}

		if (max_cost > cur.cost)
			continue;

		for (int i = 0; i < vec[cur.here].size(); i++)
		{
			int there = vec[cur.here][i].first;
			if (there == cur.prev)
				continue;

			int thereCost = vec[cur.here][i].second;
			
			if (cur.cost == 0)
			{
				struct Node add_nod(there, cur.here, thereCost);
				q.push(add_nod);
			}
			else
			{
				struct Node add_nod(there, cur.here, min(thereCost, cur.cost));
				q.push(add_nod);
			}
		}
	}
	return max_cost;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	vec.resize(N + 1);

	for (int i = 0; i < M; i++)
	{
		int a, b, c;
		scanf("%d%d%d", &a, &b, &c);

		vec[a].push_back(make_pair(b, c));
		vec[b].push_back(make_pair(a, c));
	}
	
	int s, e;
	scanf("%d%d", &s, &e);

//	printf("N, M %d %d\n", N, M);

//	printf("hi\n");
	int res = process(s, e);
	printf("res : %d\n", res);
		




	return 0;
}
