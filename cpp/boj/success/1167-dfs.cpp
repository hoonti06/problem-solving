#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>
#include <stack>

using namespace std;

#define MAX_V 100005

int V;

struct node{
	int dest;
	int val;

	node(int d, int v)
	{
		dest = d;
		val = v;
	}
};

vector<node> tree[MAX_V];
bool isVisited[MAX_V];

int max_node;
int res;

int dfs(int src, int sum)
{
	for (int i = 0; i < tree[src].size(); i++)
	{
		int dest = tree[src][i].dest;
		int val = tree[src][i].val;

		if (isVisited[dest] == true)
			continue;

		isVisited[dest] = true;

		if (res < sum + val)
		{
			res = sum + val;
			max_node = dest;
		}

		dfs(dest, sum + val);
	}
}


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &V);
	for (int i = 0; i < V; i++)
	{
		int src;
		scanf("%d", &src);
		while(true)
		{
			int dest, val;
			scanf("%d", &dest);
			if (dest == -1)
				break;
			scanf("%d", &val);

			struct node tmp_node(dest, val);
			tree[src].push_back(tmp_node);
		}
	}

	isVisited[1] = true;
	dfs(1, 0);
	
	memset(isVisited, 0, sizeof(isVisited));
	isVisited[1] = false;
	isVisited[max_node] = true;

	res = 0;
	dfs(max_node, 0);

	printf("%d\n", res);

	return 0;
}
