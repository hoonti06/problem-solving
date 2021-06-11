#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 10005

int N, K;
int input[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);
	
	scanf("%d%d", &N, &K);

	long long left = 0, right = 0;
	for (int i = 0; i < N; i++)
	{
		scanf("%d", &input[i]);
		right += input[i];
	}

	long long ans;
	while (left <= right)
	{
		long long mid = (left + right) / 2;

		int cnt = 0;
		for (int i = 0; i < N; i++)
			cnt += input[i] / mid;

		if (cnt >= K)
		{
			left = mid+1;
			ans = mid;
		}
		else
			right = mid-1;
	}
	printf("%lld", ans);
}
