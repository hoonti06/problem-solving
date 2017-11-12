#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

#define MAX_N 10005

int V, E, M, S, x, y;

vector<vector<pair<int, int> > > vec;
vector<int> mac;
vector<int> sbuck;

int mCost[MAX_N];
int sCost[MAX_N];

int Dijkstra()
{
	memset(mCost, -1, sizeof(mCost));
	memset(sCost, -1, sizeof(sCost));

	priority_queue<pair<int, int> > pq; // first : cost, second : vertex

	for (int i = 0; i < M; i++)
		pq.push(make_pair(0, mac[i]));

	while (!pq.empty())
	{
		int here = pq.top().second;
		int hereCost = -pq.top().first;	// 우선순위를 오름차순으로 만들기 위해
		pq.pop();
//		printf("here : %d, %d\n", here, hereCost);

		if (mCost[here] != -1)
			continue;
		mCost[here] = hereCost;

//		if (hereCost > mCost[here])
//			continue;

		for (int i = 0; i < vec[here].size(); i++)
		{
			int there = vec[here][i].first;
			int nextCost = hereCost + vec[here][i].second;
			
			if (mCost[there] != -1)
				continue;
			pq.push(make_pair( -nextCost, there));

//			printf("there : %d %d\n", there, nextCost);

//			if (mCost[here] == -1 && nextCost < mCost[there])
//			{
//				mCost[there] = nextCost;
//				pq.push(make_pair(-nextCost, there));
//			}
		}
	}
	while(!pq.empty())
		pq.pop();

	for (int i = 0; i < S; i++)
		pq.push(make_pair(0, sbuck[i]));

	while (!pq.empty())
	{
		int here = pq.top().second;
		int hereCost = -pq.top().first;	// 우선순위를 오름차순으로 만들기 위해
		pq.pop();

		if (sCost[here] != -1)
			continue;
		sCost[here] = hereCost;

//		if (hereCost > sCost[here])
//			continue;

		for (int i = 0; i < vec[here].size(); i++)
		{
			int there = vec[here][i].first;
			int nextCost = hereCost + vec[here][i].second;

			if (sCost[there] != -1)
				continue;
			pq.push(make_pair( -nextCost, there));

//			if (sCost[here] == -1 && nextCost < sCost[there])
//			{
//				sCost[there] = nextCost;
//				pq.push(make_pair(-nextCost, there));
//			}
		}
	}
	int res = 1e9;

	for (int i = 1; i <= V; i++)
	{
		if (mCost[i] == -1 || sCost[i] == -1)
			continue;
		if (mCost[i] != 0 && mCost[i] <= x && sCost[i] && sCost[i] <= y)
			res = min(res, mCost[i] + sCost[i]);
	}
	if (res == 1e9)
		printf("-1");
	else
		printf("%d", res);
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &V, &E);
	vec.resize(V+1);

	for (int i = 0; i < E; i++)
	{
		int here, there, w;
		scanf("%d%d%d", &here, &there, &w);
		vec[here].push_back(make_pair(there, w));
		vec[there].push_back(make_pair(here, w));
	}
	scanf("%d%d", &M, &x);
	for (int i = 0; i < M; i++)
	{
		int tmp;
		scanf("%d", &tmp);
		mac.push_back(tmp);
	}
	scanf("%d%d", &S, &y);
	for (int i = 0; i < S; i++)
	{
		int tmp;
		scanf("%d", &tmp);
		sbuck.push_back(tmp);
	}

//	for (int i = 1; i <= V; i++)
//	{
//		printf("%d : ", i);
//		for (int j = 0; j < vec[i].size(); j++)
//			printf("%d(%d), ", vec[i][j].first, vec[i][j].second);
//		printf("\n");
//	}
//	printf("\n");
//	for (int i = 0; i < M; i++)
//		printf("%d ", mac[i]);
//	printf("\n");
//
//	for (int i = 0; i < S; i++)
//		printf("%d ", sbuck[i]);
//	printf("\n");
		
	Dijkstra();




	return 0;
}
