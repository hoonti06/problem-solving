#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 100005

int N, K;
int input[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &K);

	int left = 0, right = K;
	int ans;
	while (left <= right)
	{
		int mid = (left + right) / 2;

		int cnt = 0;
		for (int i = 1; i <= N; i++)
			cnt += min(mid / i, N);

		if (cnt < K)
			left = mid + 1;
		else
		{
			right = mid - 1;
			ans = mid;
		}
	}
	printf("%d", ans);
	return 0;
}
