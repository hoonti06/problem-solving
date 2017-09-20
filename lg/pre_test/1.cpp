#include <cstdio>
#include <algorithm>

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 0; testcase < T; testcase++)
	{
		long long N, M;
		scanf("%lld%lld", &N, &M);
		
//		printf("%d %d\n", N, M);
		long long nCnt = 0, mCnt = 0;
		long long tmp = N;
		while(tmp > 0) {
			tmp /= 2, nCnt++;
			nCnt++;
		}

		tmp = M;
		while(tmp > 0) {
			tmp /= 2, mCnt++;
		}




		long long res = 0;
		for (int i = N+1; i <= M; i++)
		{
			if (i % 2 == 0)
			{
				long long t = i;
				while (t % 2 == 0)
				{
//					t = t >> 1;
					t /= 2;
					res++;
				}
				res++;
			}
			else
				res++;

		}
//		printf("res : %lld\n", res);
		printf("%lld\n", res);

	}
}
