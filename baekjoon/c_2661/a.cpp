#include<stdio.h>

#define MAX_N 90 

int N;
int col[MAX_N], ans[MAX_N]; 
int isFirst = 1;

int promising(int k);

int backtracking(int k)
{
	if(promising(k))
	{
		if(k == N && isFirst == 1)
		{
			isFirst = 0;
			for(int i = 1; i <= N; i++)
				printf("%d", col[i]);
			printf("\n");
		}
		else
		{
			for(int i = 1; i <= 3; i++)
			{
				col[k+1] = i;
				backtracking(k+1);
			}
		}
	}
}

int promising(int k)
{
	if(k == 0)
		return 1;

	if(isFirst != 1)
		return 0;
	
	int i = 1;
	while(i <= k/2)
	{	
		int flag = 0;
		for(int j = 0; j < i; j++)
		{
			if(col[k-j-i] != col[k-j])
			{
				flag = 1;	
				break;
			}
		}
		if(!flag)
			return 0;	
		i++;	
	}
	return 1;
}

int main()
{
	freopen("in.txt", "r", stdin);
	scanf("%d", &N);
	backtracking(0);
}

