#include <cstdio>
#include <algorithm>
#include <cstring>
#include <cmath>
#include <vector>

using namespace std;

#define MAX_N 1005

int N, M;

vector<int> edge[MAX_N];
//vector<vector<int> > edge;

bool isVisited[MAX_N];

int dfs(int cur)
{
	if (isVisited[cur] == true)
		return 0;

	isVisited[cur] = true;
	printf("%d ", cur);

	for (int i = 0; i < edge[cur].size(); i++)
		dfs(edge[cur][i]);
}

int main()
{
	setbuf(stdout, NULL);

	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);

//	edge.resize(N + 1);

	for (int i = 0; i < M; i++)
	{
		int u, v;
		scanf("%d%d", &u, &v);
		edge[u].push_back(v);
	}

	dfs(1);

	return 0;
}

}
}
}
