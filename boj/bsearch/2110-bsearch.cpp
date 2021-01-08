#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 200005

int N, C;
int input[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	long long left = 0, right = 0;
	scanf("%d%d", &N, &C);
	for (int i = 0; i < N; i++)
		scanf("%d", &input[i]);

	sort(input, input + N);

	right = input[N-1] - input[0] + 1;

	long long ans;
	while (left <= right)
	{
		long long mid = (left + right) / 2;

		int cnt = 1;
		int inp = input[0];
		for (int i = 0; i < N && cnt <= C; i++)
		{
			if (input[i] - inp >= mid)
			{
				cnt++;
				inp = input[i];
			}
		}

		if (cnt >= C)
		{
			left = mid + 1;
			ans = mid;

		}
		else
			right = mid - 1;
	}
	printf("%lld", ans);
}
