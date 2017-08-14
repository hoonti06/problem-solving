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

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &V, &E);
	vec.resize(V+1);

	for (int i = 0; i < E; i++)
	{
		int a, b, c;
		scanf("%d%d%d", &a, &b, &c);
		vec[a].push_back(make_pair(b, c));
		vec[b].push_back(make_pair(a, b));
	}
	scanf("%d%d", &M, &x);
	for (int i = 0; i < M; i++)
	{
		int a;
		scanf("%d", &a);
		mac.push_back(a);
	}
	scanf("%d%d", &S, &y);
	for (int i = 0; i < S; i++)
	{
		int a;
		scanf("%d", &a);
		sbuck.push_back(a);
	}

	return 0;
}
