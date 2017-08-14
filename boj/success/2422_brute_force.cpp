#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 205
#define MAX_M 10005

int N, M;
bool cc[MAX_N][MAX_N];


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < M; i++)
	{
		int a, b;
		scanf("%d%d", &a, &b);
		cc[a][b] = true, cc[b][a] = true;
	}

	int cnt = 0;
	for (int i = 1; i <= N; i++)
	{
		for (int j = i+1; j <= N; j++)
		{
			for (int k = j+1; k <= N; k++)
			{
				if (cc[i][j] == true || cc[i][k] == true || cc[j][k] == true)
					continue;
				cnt++;
			}
		}
	}
	printf("%d\n", cnt);
	return 0;
}
