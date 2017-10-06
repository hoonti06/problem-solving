#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 102

int N, M, A, B;

int map[MAX_N][MAX_N];
int dp[MAX_N][MAX_N][2];

vector <pair<int, int> > vec;

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d%d%d", &N, &M, &A, &B);
	vec.push_back(make_pair(0, 0));
	for (int i = 0; i < A; i++)
	{
		int a, b;
		scanf("%d%d", &a, &b);
		map[a - 1][b - 1] = 1;
		vec.push_back(make_pair(a - 1, b - 1));
	}
	vec.push_back(make_pair(N-1, M-1));
	map[0][0] = 1, map[N - 1][M - 1] = 1;
	sort(vec.begin(), vec.end());

	for (int i = 0; i < B; i++)
	{
		int a, b;
		scanf("%d%d", &a, &b);
		map[a - 1][b - 1] = 2;
	}

	dp[0][0][0] = 1;
	for (int t = 0; t < vec.size()-1; t++)
	{
		int r = vec[t].first;
		int c = vec[t].second;

		int nr = vec[t + 1].first;
		int nc = vec[t + 1].second;

		if (r == nr && c == nc)
			continue;
		if (r == nr)
		{
			dp[nr][nc][0] = dp[r][c][0] + dp[r][c][1];
			continue;
		}
		else if (c == nc)
		{
			dp[nr][nc][1] = dp[r][c][0] + dp[r][c][1];
			continue;
		}
		
		for (int i = r; i <= nr; i++)
		{
			for (int j = c; j <= nc; j++)
			{
				if (i == r && j == c)
					continue;
				if (map[i][j] == 2)
					continue;

				if (j - 1 >= 0)
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
				if (i - 1 >= 0)
					dp[i][j][1] = dp[i - 1][j][0] + dp[i - 1][j][1];
			}
		}
	}
	printf("%d", dp[N - 1][M - 1][0] + dp[N - 1][M - 1][1]);
}

