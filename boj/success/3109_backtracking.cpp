#include <cstdio>
#include <algorithm>

#define MAX_N 10005
#define MAX_M 505

int N, M, res;
char board[MAX_N][MAX_M];

int backtracking(int r, int c)
{
	if (r < 0 || r >= N || board[r][c] == 'x')
		return false;

	board[r][c] = 'x';

	if (c >= M-1)
		return true;

	int nc = c + 1;

	//무조건 위쪽부터 방문해야 최댓값이 나올 수 있으므로 위쪽부터 방문하고,
	//그 결과가 true라면 오른쪽이나 아래 대각선으로 가는 경우는 할 필요가 없다.
	//(위쪽 대각선, 가운데, 아래쪽 대각선의 순서)
	return backtracking(r-1, nc) || backtracking(r, nc) || backtracking(r+1, nc);
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d %d\n", &N, &M);
	for(int i = 0; i < R; i++)
		scanf("%s", board[i]);
	
	for(int i = 0; i < R; i++)
		res += backtracking(i, 0);

	printf("%d", res);
}
