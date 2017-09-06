#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 10005
int N, budget;

int input[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	int left = 0, right = 0;
	for (int i = 0; i < N; i++)
	{
		scanf("%d", &input[i]);
		right = max(right, input[i]);
	}
	scanf("%d", &budget);

	int ans;
	while(left <= right)
	{
		int mid = (left + right) / 2;

		int sum = 0;
		for (int i = 0; i < N; i++)
		{
			if (input[i] > mid)
				sum += mid;
			else
				sum += input[i];
		}

		if (sum <= budget)
		{
			left = mid+1;
			ans = mid;
		}
		else
			right = mid-1;
	}
	printf("%d", ans);
}
