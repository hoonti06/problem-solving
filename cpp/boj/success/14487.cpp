#include <cstdio>
#include <algorithm>

using namespace std;

int N;

int main()
{
	freopen("in.txt", "r", stdin);

	int sum = 0, max_n, tmp;

	scanf("%d", &N);
	while (N--)
	{
		scanf("%d", &tmp);
		sum += tmp;
		max_n = max(max_n, tmp);
	}
	printf("%d\n", sum - max_n);
}
