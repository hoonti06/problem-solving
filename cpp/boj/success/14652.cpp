#include <cstdio>
#include <algorithm>

using namespace std;

int N, M, K;
int main()
{
	scanf("%d%d%d", &N, &M, &K);

	printf("%d %d", K / M, K % M);
}
