/* BOJ 1987 알파벳 */

#include <cstdio>
#include <cstring>
#include <algorithm>
#include <cmath>

using namespace std;

#define MAX_N 21

int R, C;

char input[MAX_N][MAX_N];

int dx[4] = { 1, 0, -1,  0  };
int dy[4] = { 0, 1,  0, -1  };

char save[MAX_N];

int res;

bool promising(int r, int c, int count);

int backtracking(int r, int c, int count)
{
	if (promising(r, c, count) == false)
	{
		res = max(res, count);
		return 0;

	}

	for (int i = 0; i < 4; i++)
		backtracking(r + dx[i], c + dy[i], count + 1);
}

bool promising(int r, int c, int count)
{
	if (r < 0 || r >= R || c < 0 || c >= C)
		return false;

	for (int i = 0; i < count; i++)
	{
		if (input[r][c] == save[i])
			return false;

	}
	save[count] = input[r][c];

	return true;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &R, &C);
	for (int i = 0; i < R; i++)
		scanf("%s", &input[i]);
	save[0] = input[0][0];

	backtracking(0, 0, 0);
	printf("%d\n", res);

	return 0;
}
