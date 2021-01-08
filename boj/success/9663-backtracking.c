#include<stdio.h>
#include<stdlib.h>

#define MAX_N 20 

int N, sum = 0;
int col[MAX_N] = {0, };
int board[MAX_N][MAX_N];

int promising(int k);

int backtracking(int k)
{
	if(promising(k))
	{
		if(k == N)
		{
			sum++;
			return 0; 
		}
		else
		{
			int i = 1;
			for(; i <= N; i++)
			{
				col[k+1] = i;
				backtracking(k+1);
			}
		}
	}
}

int promising(int k)
{
	if(k == 1)
		return 1;
	
	int i = 1;
	for(; i < k; i++)
	{
		if(col[i] == col[k])
			return 0;

		if(abs(col[i] - col[k]) == abs(i - k))
			return 0;
	}
	return 1;

}



int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	backtracking(0);
	printf("%d\n", sum);

	return 0;
}
