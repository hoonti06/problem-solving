#include<stdio.h>
#include<string.h>

int num = 0;
int board[6][6] = {0, };
int dx[4] = {1, -1,  0, 0};
int dy[4] = {0,  0, -1, 1};
char ans[50000][7], col[7];

int promising(int k, int c, int r);

int backtracking(int k, int c, int r)
{
	if(promising(k, c, r))
	{
		if(k == 6)
		{
		 	for(int i = 0; i < num; i++)
			{
				if(strncmp(&ans[i][1], &col[1], 6) == 0)
				{
					if(0)
						//printf("hi\n");
						printf("%s\n", &ans[num][1]);
						
					return 0;
				}
			}
			strncpy(&ans[num][1], &col[1], 6);
			num++;
		}
		else
		{
			for(int i = 0; i < 4; i++)
			{
				int nC = c+dx[i];
				int nR = r+dy[i];
				//if(nC < 1 || nC > 5 || nR < 1 || nR > 5)
				//	continue;
				
				//if(num == 1)
				//	printf("num=1 : %d\n", k+1);

				col[k+1] = '0' + board[nC][nR];
				backtracking(k+1, nC, nR);
				col[k+1] = 'a';
				//ans[num][k+1] = 'a';
			}

			
//			for(int i = 1; i <= 5; i++)
//			{
//				for(int j = 1; j <= 5; j++)
//				{
//					ans[num][k+1] = board[i][j];
//					backtracking(k+1, i, j c, r);
//					ans[num][k+1] = 'a';
//				}
//			}
		}

	}
			


}

int promising(int k, int c, int r)
{
	if(k == 0)
		return 1;

	if(1 > c || c > 5 || 1 > r || r > 5)
		return 0;


}


int main()
{
	freopen("in.txt", "r", stdin);

	for(int i = 1; i <= 5; i++)
		for(int j = 1; j <= 5; j++)
			scanf("%d ", &board[i][j]);


//	for(int i = 1; i <= 5; i++)
//		for(int j = 1; j <= 5; j++)
//			printf("%d ", board[i][j]);

	for(int i = 1; i <= 5; i++)
		for(int j = 1; j <= 5; j++)
			backtracking(0, i, j);

	if(0)
	{
		for(int i = 0; i <= num; i++)
			printf("%2d : %s\n", i, &ans[i][1]);
	}

	printf("%d\n", num);




}
