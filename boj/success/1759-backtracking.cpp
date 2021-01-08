#include<stdio.h>

#define MAX_C 20

int L, C;
char alpha[MAX_C], col[MAX_C];

int promising(int k);

int backtracking(int k)
{
	if(promising(k))
	{

		if(k == L)
		{
			int vow = 0, cons = 0;
			int i;
			for(i = 1; i <= k; i++)
			{	
				if(col[i] == 'a' || col[i] == 'e' || col[i] == 'i' || col[i] == 'o' || col[i] == 'u')
					vow++;
				else
					cons++;
			}
			if(vow < 1 || cons < 2)
				return 0;	

			for(i = 1; i <= L; i++)
				printf("%c", col[i]);	
			printf("\n");

			return 0;
		}
		else
		{
			int i;
			for(i = 1; i <= C; i++)
			{
				col[k+1] = alpha[i];
				backtracking(k+1);	
			}
		}
	}
}

int promising(int k)
{
	if(k == 0)
		return 1;
	
	int i;
	for(i = 1; i < k; i++)
	{	
		if(col[i] >= col[k])
			return 0;
	}
	return 1;
}

int main()
{
	freopen("in.txt", "r", stdin);
	
	scanf("%d%d\n", &L, &C);
	int i;
	for(i = 1; i <= C; i++)
		scanf("%c ", &alpha[i]);		

	for(i = 1; i < C; i++)
	{
		int j;
		for(j = i+1; j <= C; j++)
		{
			if(alpha[i] > alpha[j])
			{
				int tmp = alpha[i];
				alpha[i] = alpha[j];
				alpha[j] = tmp;
			}
		}
	}
	backtracking(0);	
	return 0;
}
