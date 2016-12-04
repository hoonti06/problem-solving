#include<stdio.h>

#define MAX_R 10010
#define MAX_C 505

int R, C, res;
char board[MAX_R][MAX_C];

bool backtracking(int r, int c)
{
	if(r < 0 || r >= R || board[r][c] == 'x')
		return false;	

	board[r][c] = 'x';

	if(c >= C-1)
		return true;

	int nC = c + 1;

	//무조건 위쪽부터 방문해야 최댓값이 나올 수 있으므로 위쪽부터 방문하고,
	//그 결과가 true라면 오른쪽이나 아래 대각선으로 가는 경우는 할 필요가 없다.
	//(위쪽 대각선, 가운데, 아래쪽 대각선의 순서)
	return backtracking(r-1, nC) || backtracking(r, nC) || backtracking(r+1, nC);
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d %d\n", &R, &C);
	for(int i = 0; i < R; i++)
		scanf("%s", board[i]);
	
	for(int i = 0; i < R; i++)
		res += backtracking(i, 0);

	printf("%d\n", res);
}
