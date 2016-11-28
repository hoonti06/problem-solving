#include<stdio.h>
#include<vector>
#include<utility>

using namespace std;

int N = 9;
int board[11][11];
int ans[11][11];
vector <pair <int , int> > vec;
int isFirst = 1;

int promising(int k);

int backtracking(int k)
{
	if(promising(k))
	{
		if(k == vec.size()-1 && isFirst == 1)
		{
			isFirst = 0;
			int i;
			for(i = 1; i <= N; i++)
			{
				int j;
				for(j = 1; j <= N; j++)
				{
					ans[i][j] = board[i][j];	
				//	printf("%d ", board[i][j]);
				}
			//	printf("\n");
			}
		}
		else
		{
			int i;
			for(i = 1; i <= N; i++)
			{
				//printf("k : %d\n", k);
				board[vec[k+1].first][vec[k+1].second] = i;
				backtracking(k+1);
			//	printf("hi\n");
				board[vec[k+1].first][vec[k+1].second] = 0;
			}
		}
	}

}

int promising(int k)
{
	if(k == 0)
		return 1;
	if(isFirst == 0)
		return 0;
	
	int i, gR, gC;
	gR = (vec[k].first-1) / 3;
	gC = (vec[k].second-1) / 3;
	
	if(0)
	{
		printf("gR : %d, gC : %d\n", gR, gC);
		printf("vec : b-> %d,  %d, %d\n", board[vec[k].first][vec[k].second], vec[k].first, vec[k].second);
	}
	for(i = 1; i <= N; i++)
	{
	//	printf("0 vec : %d, %d\n", vec[k].first, vec[k].second);
		if(vec[k].second != i && board[vec[k].first][vec[k].second] == board[vec[k].first][i])
		{
			if(0)
			{
				printf("1 vec : b -> %d, %d, %d\n", board[vec[k].first][i], vec[k].first, i);
			}
			return 0;		
		}
		if(vec[k].first != i && board[vec[k].first][vec[k].second] == board[i][vec[k].second])
		{
			if(0)
			{
				printf("2 vec : b -> %d, %d, %d\n", board[i][vec[k].second], i, vec[k].second);
			}
			return 0;
		}
	
	}

	for(i = gR*3+1; i <= gR*3+3; i++)
	{
		int j;
		for(j = gC*3+1; j <= gC*3+3; j++)
		{
			if(vec[k].first == i && vec[k].second == j)
				continue;
			if(board[vec[k].first][vec[k].second] == board[i][j])
			{
				if(0)
				{
					printf("3 vec : b -> %d, %d, %d\n", board[i][j], i, j);
				}
				return 0;
			}	
		}
	}
	//printf("3\n");
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
			scanf("%1d", &board[i][j]);
			if(board[i][j] == 0)
			{
				vec.push_back(make_pair(i, j));
	//			printf("hi\n");
			}
		}
	}
	
	if(0)
	{

		for(i = 1; i <= N; i++)
		{
			int j;
			for(j = 1; j <= N; j++)
			{
				printf("%d ", board[i][j]);	
			}
			printf("\n");
		}
//	for(i = 0; i <= vec.size()-1; i++)
//	{
//		printf("%d\n", board[vec[i].first][vec[i].second]);
//		printf("vec : %d, %d\n", vec[i].first, vec[i].second);
//	}
	}
	backtracking(0);
		
	for(i = 1; i <= N; i++)
	{
		int j;
		for(j = 1; j < N; j++)
		{
			printf("%d", ans[i][j]);
		}
			printf("%d", ans[i][j]);
		//if(i != N)
			printf("\n");
	}
	
	return 0;
}
