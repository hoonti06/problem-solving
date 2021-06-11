#include <cstdio>
#include <algorithm>
#include <vector>
#include <string>
#include <cstring>

using namespace std;

#define MAX_N 4005

int N;
int a[MAX_N], b[MAX_N], c[MAX_N], d[MAX_N];
vector<int> ab, cd;

int main()
{
	freopen("in.txt", "r", stdin);


	scanf("%d", &N);

	ab.resize(N*N);
	cd.resize(N*N);

	for (int i = 0; i < N; i++)
		scanf("%d%d%d%d", &a[i], &b[i], &c[i], &d[i]);

	int k = 0;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			ab[k] = a[i] + b[j];
			cd[k] = c[i] + d[j];
			k++;
		}
	}

	sort(ab.begin(), ab.end());
	sort(cd.begin(), cd.end());

	unsigned long long res = 0;
	for (int i = 0; i < k; i++)
	{
		pair<vector<int>::iterator, vector<int>::iterator> iterPair;
		iterPair = equal_range(cd.begin(), cd.end(), -ab[i]);
		res += iterPair.second - iterPair.first;
	}

	printf("%llu\n", res);
	return 0;
}
