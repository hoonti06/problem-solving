#include<stdio.h>
#include<string.h>

int num = 0;
int board[6][6] = {0, };

int dr[4] = {1, -1,  0, 0};
int dc[4] = {0,  0, -1, 1};

char res[1000000][7], tmp[6];

int backtracking(int cnt, int r, int c)
{
	tmp[cnt] = board[r][c] + '0';
	
	if(cnt >= 5)
	{
		for(int i = 0; i < num; i++)
		{
			if(strncmp(res[i], tmp, 6) == 0)
				return 0;
		}
		strncpy(res[num], tmp, 6);
		res[num][6] = '\0';
		num++;
	}
	else
	{
		for(int i = 0; i < 4; i++)
		{
			int nR = r+dr[i];
			int nC = c+dc[i];
			if(nC < 1 || nC > 5 || nR < 1 || nR > 5)
				continue;
			
			backtracking(cnt+1, nR, nC);
		}
	}
}
			
int main()
{
	freopen("in.txt", "r", stdin);

	for(int i = 1; i <= 5; i++)
		for(int j = 1; j <= 5; j++)
			scanf("%d ", &board[i][j]);

	for(int i = 1; i <= 5; i++)
		for(int j = 1; j <= 5; j++)
			backtracking(0, i, j);

	printf("%d\n", num);
}
