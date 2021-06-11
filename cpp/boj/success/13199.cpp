#include <cstdio>
#include <algorithm>

using namespace std;


int P, M, F, C;


int main()
{
	freopen("in.txt", "r", stdin);

	int T, test_case;
	scanf("%d", &T);
	for (test_case = 1; test_case <= T; test_case++)
	{
		scanf("%d%d%d%d", &P, &M, &F, &C);

		int countA = M / P * C;
		int countB = countA;

		countA /= F;

		int sum = 0;
		while(countB >= F)
		{
			countB -= F;
			countB += C;
			sum++;
		}

		printf("%d\n", sum - countA);
	}
}
