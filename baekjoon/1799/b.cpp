#include<stdio.h>
#include<vector>
#include<utility>

using namespace std;

#define MAX_N 11

int N;
int board[MAX_N][MAX_N];
vector <pair <int, int> > vec;
int max_sum = 0, sum = 0;
int dr[2] = {-1, 1};
int diag[20] = {0, };

//void printBoard();
int promising(int k, int flag);
int backtracking(int k, int flag)
{
	if(promising(k, flag))
	{
		if(k == vec.size() - 1)
		{
			if(max_sum < sum)
				max_sum = sum;
		}
		else
		{
			if(diag[vec[k+1].first+vec[k+1].second] == 0)
			{			
				board[vec[k+1].first][vec[k+1].second] = 2;
				diag[vec[k+1].first+vec[k+1].second] = 1;
				sum++;
				backtracking(k+1, 1);
			}
			board[vec[k+1].first][vec[k+1].second] = 1;
			diag[vec[k+1].first+vec[k+1].second] = 0;
			sum--;	
			backtracking(k+1, 0);
		}

	}


}

int promising(int k, int flag)
{
	if(k == 0)
		return 1;

	if(flag == 0)
		return 1;
	for(int i = 0; i < 2; i++)
	{	
		int nR = vec[k].first;
		int nC = vec[k].second;
		while(1)
		{
			nR += dr[i];
			nC += dr[i];
			if(nR < 1 || nR > N || nC < 1 || nC > N)
				break;
			if(board[nR][nC] == 2)
				return 0;
		}
	}
//	if(0)
//	{
//		printf("return 1\n");
//		printBoard();
//	}
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
				vec.push_back(make_pair(i, j));

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
