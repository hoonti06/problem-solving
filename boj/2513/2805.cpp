#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 1000005

int N, M;
int input[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	int left = 0, right = 1000000000;
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		scanf("%d", &input[i]);

	while(left+1 < right)
	{
		int mid = (left + right) / 2;

		long long sum = 0;
		for (int i = 0; i < N; i++)
			if (input[i] > mid)
				sum += input[i] - mid;

		if (sum >= M)
			left = mid;
		else 
			right = mid;
	}
	printf("%d", left);

	return 0;
}
