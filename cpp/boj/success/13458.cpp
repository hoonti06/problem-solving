#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 1000005

int N, B, C;

int A[MAX_N];

long long res;

int main()
{
	freopen("in3.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 0; i < N; i++)
		scanf("%d", &A[i]);
	scanf("%d%d", &B, &C);

	for (int i = 0; i < N; i++)
		A[i] -= B;

	res = N;

	for (int i = 0; i < N; i++)
	{
		if (A[i] <= 0)
			continue;

		int cnt = A[i] / C;
		int rem = A[i] % C;

		if (rem != 0)
			cnt++;
		res += cnt;
	}

	printf("%lld\n", res);
	return 0;
}
