#include <cstdio>
#include <algorithm>
#include <stack>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 30005

int N, K, S;
int input[MAX_N];

int res = 0;

bool cmp(pair<int, int> x, pair<int, int> y)
{
	return x.first > y.first;
}
int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d%d", &N, &K, &S);
	for (int i = 0; i < N; i++)
	{
		int a, b;
		scanf("%d%d", &a, &b);
	}







}
