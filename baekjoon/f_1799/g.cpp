#include<stdio.h>
#include<vector>
#include<utility>
#include<stdlib.h>

using namespace std;

#define MAX_N 11

int N;
int board[MAX_N][MAX_N];
vector <pair <int, int> > vec;
int max_sum = 0, sum = 0;
int dr[4] = {-1, -1,  1, 1};
int dc[4] = {-1,  1, -1, 1};
diag[4][25] = {0, };

void printBoard();
int promising(int k, int flag);
int backtracking(int k, int sum)
{
		if(k == vec.size() - 1)
		{
			if(max_sum < sum)
				max_sum = sum;
		}
		else
		{
			int diff = vec[k+1].first - vec[k+1].second; 
			int added = vec[k+1].first + vec[k+1].second; 

			printf("diff : %d\n", diff);

			if(diag[2][added] == 1)
			{
				return 0;	
			}
			else if(diff <= 0)
			{
				if(diag[0][abs(diff)] == 0)
				{
					diag[0][abs(diff)] = 1;
					backtracking(k+1, sum+1);
				}
				else
					backtracking(k+1, sum);
			
			}
			else
			{
				if(diag[1][diff] == 0)
				{
					diag[1][diff] = 1;
					backtracking(k+1, sum+1);
				}
				else
					backtracking(k+1, sum);
			
			}
			
//			for(int i = 0; i <= 3; i++)
//			{
//				for(int j = 0; j <= 20; j++)
//				{
//				
//					printf("%d ", diag[i][j]);
//				}
//				printf("\n");
//
//			}

		}

	}


}

int promising(int k, int flag)
{
	printf("hi\n");
	if(k == 0)
		return 1;

	if(flag == 0)
		return 1;

	int added = vec[k].first + vec[k].second; 
	int diff = vec[k].first - vec[k].second; 

	if(diag[2][added] == 1)
		return 0;

	if(diff <= 0)
	{
		if(diag[0][abs(diff)] == 1)	
			return 0;	
	}
	else
	{
		if(diag[1][diff] == 1)
			return 0;	
	}

//	for(int i = 0; i < 4; i++)
//	{	
//		int nR = vec[k].first;
//		int nC = vec[k].second;
//		while(1)
//		{
//			nR += dr[i];
//			nC += dc[i];
//			if(nR < 1 || nR > N || nC < 1 || nC > N)
//				break;
//			if(board[nR][nC] == 2)
//				return 0;
//		}
//	}

	if(1)
	{
		printf("return 1\n");
		printBoard();
	}
	return 1;
}

void printBoard()
{
	for(int i = 1; i <= N; i++)
	{	
		for(int j = 1; j <= N; j++)
			printf("%d ", board[i][j]);
		printf("\n");
	}


}

int main()
{
	freopen("in.txt", "r", stdin);

	vec.push_back(make_pair(0, 0));
	
	scanf("%d\n", &N);
	
	for(int i = 1; i <= N; i++)
	{
		for(int j = 1; j <= N; j++)
		{
			scanf("%d ", &board[i][j]);
			if(board[i][j] == 1)
			{
				vec.push_back(make_pair(i, j));
				diag[2][i+j] = 1;
				sum++;
			}

		}

	}
//	if(0)
//	{
//		printf("%d\n", N);
//		printf("size : %d\n", (int)vec.size());
//	}
	backtracking(0, 0);
	printf("%d\n", max_sum);

}
