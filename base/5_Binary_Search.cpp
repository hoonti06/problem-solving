#include <cstdio>
#include <algorithm>
#include <cstring>
#include <cmath>

#define MAX_N 1000005

int input[MAX_N];

int N, M;

bool binary_search(int val)
{
	int left = 0;
	int right = N - 1;

	while (left <= right)
	{
		int mid = (left + right) / 2;

		if (input[mid] < val)
			left = mid + 1;
		else if (input[mid] > val)
			right = mid - 1;
		else
			return true;

	}
	return false;

}

int main(void)
{
	setbuf(stdout, NULL);

	freopen("in.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 0; i < N; i++)
		scanf("%d", &input[i]);

	scanf("%d", &M);
	for (int i = 0; i < M; i++)
	{
		int val;
		scanf("%d", &val);

		if (binary_search(val) == true)
			printf("exist\n");
		else
			printf("not exist\n");


	}
	return 0;

}
