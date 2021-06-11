#include <cstdio>
#include <algorithm>
#include <cmath>

using namespace std;

#define MAX_N 10005

int N;

int res;

int main()
{
	scanf("%d", &N);
	
	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < N; j++)
		{
			int tmp;
			scanf("%d", &tmp);
			res += abs(tmp);
		}
	}

	printf("%d", res);
}
