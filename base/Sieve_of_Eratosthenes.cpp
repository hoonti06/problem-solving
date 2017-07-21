#include <cstdio>
#include <algorithm>
#include <cstring>
#include <cmath>

#define MAX_N 1000005

bool isPrime[MAX_N];

int N;

int main(void)
{
	int T, test_case;
	setbuf(stdout, NULL);

//	freopen("in.txt", "r", stdin);

	scanf("%d", &T);

	for (int i = 2; i <= MAX_N; i++)
		isPrime[i] = true;

	for (int i = 2; i <= sqrt(MAX_N); i++)
	{
		if (isPrime[i] == false)
			continue;

		for (int j = i + i; j < MAX_N; j += i)
			isPrime[j] = false;
	}

	for (test_case = 1; test_case <= T; test_case++)
	{
		scanf("%d", &N);

		printf("Case #%d\n", test_case);
		if (isPrime[N])
			printf("YES\n");
		else
			printf("NO\n");
	}
	return 0;

}
