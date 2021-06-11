#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 30005

int N;
int input[MAX_N];

int res;

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 0; i < N; i++)
		scanf("%d", &input[i]);


	for (int i = 0; i < N - 1; i++)
	{
		int sum = 0;
		
		int j;
		for (j = i + 1; j < N; j++)
		{
			if (input[i] < input[j])
			{
				break;
			}

			sum++;


		}

		i = j - 1;
		res = max(res, sum);

	}

	printf("%d", res);

	return 0;
}
