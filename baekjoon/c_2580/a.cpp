#include<stdio.h>
#include<vector>
#include<utility>

using namespace std;

int N = 9;
int board[11][11];
int ans[11][11];
vector <pair <int , int> > vec;

int promising(int k);

int backtracking(int k)
{
	if(promising(k))
	{
		if(k == vec.size()-1)
		{
			int i;
			for(i = 1; i <= N; i++)
			{
				int j;
				for(j = 1; j <= N; j++)
				{
					ans[i][j] = board[i][j];	
				}
			}
		}
		else
		{
			int i;
			for(i = 1; i <= N; i++)
			{
				board[vec[k+1].first][vec[k+1].second] = i;
				backtracking(k+1);
				board[vec[k+1].first][vec[k+1].second] = 0;  //이게 핵 중요!!
			}
		}
	}

}

int promising(int k)
{
	if(k == 0)
		return 1;
	
	int i, gR, gC;
	
	for(i = 1; i <= N; i++)
	{
		if(vec[k].second != i && board[vec[k].first][vec[k].second] == board[vec[k].first][i])
			return 0;		

		if(vec[k].first != i && board[vec[k].first][vec[k].second] == board[i][vec[k].second])
			return 0;
	}

	gR = (vec[k].first-1) / 3;
	gC = (vec[k].second-1) / 3;

	for(i = gR*3+1; i <= gR*3+3; i++)
	{
		int j;
		for(j = gC*3+1; j <= gC*3+3; j++)
		{
			if(vec[k].first == i && vec[k].second == j)
				continue;
			if(board[vec[k].first][vec[k].second] == board[i][j])
				return 0;
		}
	}
	return 1;

}


int main()
{
	freopen("in.txt", "r", stdin);

	vec.push_back(make_pair(0,0));

	int i;
	for(i = 1; i <= N; i++)
	{
		int j;
		for(j = 1; j <= N; j++)
		{
			scanf("%d ", &board[i][j]);
			if(board[i][j] == 0)
				vec.push_back(make_pair(i, j));
		}
	}
	
	backtracking(0);
		
	for(i = 1; i <= N; i++)
	{
		int j;
		for(j = 1; j < N; j++)
		{
			printf("%d ", ans[i][j]);
		}
		printf("%d", ans[i][j]);
		printf("\n");
	}
	
	return 0;
}
