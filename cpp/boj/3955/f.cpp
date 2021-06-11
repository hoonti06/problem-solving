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


long long K, C;

int gcd(long long a, long long b)
{
	return (if (b == 0): a : gcd(b, a%b));
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int ts = 1; ts <= T; ts++)
		scanf("%ld%ld", &K, &C);

	if (C == 1)
	{
		if (K+1 > 1e9)
			printf("IMPOSSIBLE\n");
		else
			printf("%lld\n", K+1);
	}
	else if (K == 1)
		printf("1\n");
	else if (gcd(K, C) != 1)
	{
		printf("IMPOSSIBLE\n");
	}
	else
	{



	}



	if (res )
}

