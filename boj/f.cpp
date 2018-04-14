#include <cstdio>
#include <algorithm>
#include <vector>
#include <stack>
#include <cstring>

#define MAX_N 1000000

int main()
{
	freopen("in.txt", "r", stdin);

	int i, j;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			d[i] = d[i-1] + d[i-2];
		}
	}

}
