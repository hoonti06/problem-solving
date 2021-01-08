#include<stdio.h>

#define MAX_N 35
#define MAX_M 10

int N, M;
int w[MAX_N], bead[MAX_M];
int sum[MAX_N] = {0, }; 
int isFinish;

int promising(int k, int left, int right);
int backtracking(int k, int left, int right, int flag)	//flag는 어느쪽에 추를 놓았는지를 확인
{
	if(promising(k, left, right))
	{
		if(left == right)
			isFinish = 1;	
		else
		{
			if(k != N)
			{
				if(w[k] != w[k+1] || flag == 3 || flag == 0)	
					backtracking(k+1, left, right - w[k+1], 3);	// 왼쪽에 추를 추가 

				if(w[k] != w[k+1] || flag == 2 || flag == 0)	
					backtracking(k+1, left, right + w[k+1], 2);	// 오른쪽에 추를 추가

				backtracking(k+1, left, right, 1);	//추를 어느쪽에도 추가하지 않음

			}
		}	
	}

}

int promising(int k, int left, int right)
{
	if(left > right + sum[k+1] || left < right - sum[k+1])
		return 0;
	if(k == 0)
		return 1;
	
	if(isFinish == 1)
		return 0;

	return 1;
	
}

int main()
{

	freopen("in.txt", "r", stdin);
	
	int i;
	scanf("%d\n", &N);
	for(i = N; i >= 1; i--)
	{
		scanf("%d ", &w[i]);
		sum[i] = sum[i+1] + w[i];
	}

	scanf("%d\n", &M);
	for(i = 1; i <= M; i++)
		scanf("%d ", &bead[i]);

	for(int i = 1; i <= M; i++)
	{
		isFinish = 0;
		backtracking(0, bead[i], 0, 0);	
		if(isFinish)
			printf("Y");
		else
			printf("N");

		if(i != M)	
			printf(" ");
		else
			printf("\n");
	}
}
