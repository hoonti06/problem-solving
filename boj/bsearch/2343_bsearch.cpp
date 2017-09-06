#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 100005

int N, M;
int input[MAX_N];

int main()
{
	freopen("in2.txt", "r", stdin);

	long long left = 0, right = 0;
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
	{
		scanf("%d", &input[i]);
		right += input[i];
	}

	long long ans;
	while(left <= right)
	{
		long long mid = (left + right) / 2;

		int sum;
		int j = 0;
		for (int i = 0; i < M; i++)
		{
			sum = 0;
			for (;j < N; j++)
			{
				if (sum + input[j] <= mid)
					sum += input[j];
				else
					break;
			}
		}

		if (j < N)
			left = mid+1;
		else
		{
			right = mid-1;
			ans = mid;
		}
	}
	printf("%lld", ans);
}
