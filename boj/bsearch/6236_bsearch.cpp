#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 100005

int N, M;
int input[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	long long left = 0, right = 0;
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
	{
		scanf("%d", &input[i]);
		right += input[i];
	}

	long long ans;
	while (left <= right)
	{
		long long mid = (left + right) / 2;

		long long sum;
		int i = 0;
		for (int j = 0; j < M; j++)
		{
			sum = 0;
			for (; i < N; i++)
			{
				if (sum + input[i] <= mid)
					sum += input[i];
				else
					break;
			}
		}
		if (i < N)
			left = mid + 1;
		else
		{
			right = mid - 1;
			ans = mid;
		}
	}
	printf("%lld", ans);
}
