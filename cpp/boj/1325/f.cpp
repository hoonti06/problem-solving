#include <cstdio>
#include <algorithm>
#include <cstring>
#include <cstdlib>
#include <cmath>
#include <vector>
#include <stack>
#include <queue>
#include <map>

using namespace std;

#define MAX_N 10005

vector<int> edge[MAX_N];
bool isVisited[MAX_N];
int dp[MAX_N];
int N, M;

int dfs(int cur)
{
	if (isVisited[cur])
		return 0;

	isVisited[cur] = true;
	int sum = 0;
	sum++;
	for (int i = 0; i < edge[cur].size(); i++)
	{
		int next = edge[cur][i];
		sum += dfs(next);
	}
	return sum;
}

int bfs(int start)
{
	int cnt = 0;

	queue<int> q;
	q.push(start);

	isVisited[start] = true;
	cnt++;

	while (!q.empty())
	{
		int cur = q.front();
		q.pop();

		for (int i = 0; i < edge[cur].size(); i++)
		{
			int next = edge[cur][i];
			if (isVisited[next])
				continue;

			isVisited[next] = true;
			cnt++;

			q.push(next);
		}
	}
	return cnt;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);

	for (int i = 0; i < M; i++)
	{
		int src, dest;
		scanf("%d%d", &dest, &src);
		edge[src].push_back(dest);
	}

	for (int i = 1; i <= N; i++)
	{
		memset(isVisited, 0, sizeof(isVisited));
		// 첫 번째 방법
		dp[i] = dfs(i);
		
		// 두 번째 방법
		dp[i] = bfs(i);
	}

	int res = 0;
	for (int i = 1; i <= N; i++)
		res = max(res, dp[i]);

	for (int i = 1; i <= N; i++)
		if (res == dp[i])
			printf("%d ", i);
}
