#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 10005

int gcd(int a, int b)
{
	return ( a % b == 0 ? b : gcd(b, a%b));
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 0; testcase < T; testcase++)
	{
		int M, N, x, y;
		scanf("%d%d%d%d", &M, &N, &x, &y);

		int maximum = M * N / gcd(M, N);
		while (x != y && x < maximum)
		{
			if (x < y)
				x += M;
			else
				y += N;
		}
		if (x == y)
			printf("%d\n", x);
		else
			printf("-1\n");
	}
	return 0;
}
