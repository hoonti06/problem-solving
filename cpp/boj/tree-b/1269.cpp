#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 200005

int A, B;
int a[MAX_N], b[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &A, &B);
	for (int i = 0; i < A; i++)
		scanf("%d", &a[i]);
	for (int i = 0; i < B; i++)
		scanf("%d", &b[i]);

	sort(a, a + A);
	sort(b, b + B);

	int i = 0, j = 0, cnt = 0;
	while (i < A && j < B)
	{
		if (a[i] == b[j])
		{
			i++, j++;
			cnt++;
		}
		else if (a[i] > b[j])
			j++;
		else
			i++;
	}
	printf("%d", A + B - cnt*2);
}
