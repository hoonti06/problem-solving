#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>
#include <stack>

using namespace std;

#define MAX_V 100005

int N;

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
	freopen("input.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 0; i < N; i++)
	{
		int src, dest, val;
		scanf("%d%d%d", &src, &dest, &val);

		struct node tmp_node(dest, val);
		tree[src].push_back(tmp_node);

		struct node tmp_node2(src, val);
		tree[dest].push_back(tmp_node2);
	}

	isVisited[1] = true;
	dfs(1, 0);
	
	memset(isVisited, 0, sizeof(isVisited));
	isVisited[1] = false;
	isVisited[max_node] = true;
	res = 0;
	dfs(max_node, 0);

	printf("%d", res);

	return 0;
}
