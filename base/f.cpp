#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

#define MAX_V 105

int src, sink;
bool isVisited[MAX_V];

struct network_edge
{
	int to, capacity, flow, residual_idx;


};

vector<network_edge> adj[MAX_V];


int main()
{
	int v, e;
	scanf("%d%d", &v, &e);
	scanf("%d%d", &src, &sink);

	for (int i = 0; i < e; i++)
	{
		int from, to, capacity;
		scanf("%d%d%d", &from, &to, &capacity);
		adj[from].push_back()



	}




}
