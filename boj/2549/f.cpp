#include<stdio.h>

int board[4][4];




void backtracking()
{



}



int main()
{
	freopen("in.txt", "r", stdin);

	for(int i = 0; i < 4; i++)
		for(int j = 0; j < 4; j++)
			scanf("%d ", &board[i][j]);


	if(0)
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
				printf("%d ", board[i][j]);
			printf("\n");
		}
}
