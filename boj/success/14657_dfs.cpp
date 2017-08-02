#include <cstdio>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

#define MAX_N 50005

int N, T;
int res;

struct node
{
	int dest;
	int val;
	node(int d, int v)
	{
		dest = d;
		val = v;
	}
};

vector<struct node> vec[MAX_N];

int max_cnt, max_src;
int min_sum = 1e9;

bool dfs(int src, int prev, int cnt, int sum)
{
	if (max_cnt < cnt)
	{
		max_cnt = cnt;
		min_sum = sum;
		max_src = src;
	}
	else if (max_cnt == cnt)
	{
		if (min_sum > sum)
		{
			min_sum = sum;
			max_src = src;
		}
	}

	for (int i = 0; i < vec[src].size(); i++)
	{
		int dest = vec[src][i].dest;
		int val = vec[src][i].val;

		if (dest == prev)
			continue;

		if (dfs(dest, src, cnt + 1, sum + val) == false)
			return false;
	}

	return true;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &T);

	for (int i = 0; i < N - 1; i++)
	{
		int A, B, C;
		scanf("%d%d%d", &A, &B, &C);
		struct node tmp_nod(B, C);
		vec[A].push_back(tmp_nod);

		tmp_nod.dest = A;
		vec[B].push_back(tmp_nod);
	}

	dfs(1, -1, 0, 0);

	max_cnt = 0;
	min_sum = 1e9;
	dfs(max_src, -1, 0, 0);

	res = min_sum / T;
	if (min_sum % T != 0)
		res++;

	printf("%d", res);

	return 0;
}
