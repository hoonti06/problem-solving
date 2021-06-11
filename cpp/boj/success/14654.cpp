#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 305

int N;
int input[2][MAX_N];
int prev_win;

int comp(int x, int y)
{
	int ret;
	
	if (x == y)
		ret = (prev_win == 1)? 0 : 1;

	else if ((x == 1 && y == 3) || (x == 3 && y == 1))
	{
		ret = (x == 1? 1 : 0);
	}
		
	else
		ret = (x > y? 1 : 0);

	return ret;
}

int cnt, res;

int main()
{
	scanf("%d", &N);
	for (int i = 0; i < 2; i++)
		for (int j = 0; j < N; j++)
			scanf("%d", &input[i][j]);

	prev_win = -1;
	for (int n = 0; n < N; n++)
	{
		int cp = comp(input[0][n], input[1][n]);

		if (prev_win == cp)
			cnt++;
		else
		{
			prev_win = cp;

			if (prev_win == -1)
				 res = 1;

			res = max(res, cnt);
			cnt = 1;
		}
	}
	res = max(res, cnt);
	printf("%d", res);
	
	return 0;
}
