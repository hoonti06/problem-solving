#include <cstdio>
#include <algorithm>
#include <cstring>
#include <cstdlib>
#include <cmath>
#include <vector>
#include <stack>
#include <queue>
#include <map>

using namespace std;

void printBoard();
void printVisit();


#define MAX_N 25
#define JEWELRY 2 
#define TRASH 1 


int board[MAX_N][MAX_N];
int N;
deque<pair<int, int> > jewelryDeq;
deque<pair<int, int> > trashDeq;
bool isVisited[MAX_N][MAX_N];
bool isVisited2[MAX_N];

// 상 좌 하 우
int dx[4] = { -1,  0, 1, 0 };
int dy[4] = {  0, -1, 0, 1 };

bool dfs(bool isRow, int cnt)
{
	if (cnt >= )



	for (int i = 0; i < trashDeq.size(); i++)
	{
		if (isVisited[i][j])
			continue;

		isVisited[i][j] = true;

		if (isRow)
		dfs(!isRow, cnt+1);

		isVisited[i][j] = false;
	}





}

int func(int sr, int sc, int er, int ec)
{
	vector<int, int> tmpVec;

	for (int i = 0; i < jewelryDeq.size(); i++)
	{
		int r = jewelryDeq[i].first;
		int c = jewelryDeq[i].first;

		if (j <= )



	}
		

	for (int i = sr; i < er; i++)
	{
		for (int j = sc, j < ec; j++)
		{
			if (board[i][j])





		}

	}





}


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
		{
			scanf("%d", &board[i][j]);
			if (board[i][j] == JEWELRY)
				jewelryDeq.push_back(make_pair(i, j));
			else if (board[i][j] == TRASH)
				trashDeq.push_back(make_pair(i, j));
		}

	int sum = 0;
	for (int i = 0; i < trashDeq.size(); i++)
	{
		memset(isVisited, 0, sizeof(isVisited));
		sum += dfs(0);
		printf("%d, %d : %d\n", trashDeq[0].first, trashDeq[0].second, sum);
		pair<int, int> tmp = trashDeq[0];
		trashDeq.pop_front();
		trashDeq.push_back(tmp);
	}



}

void printBoard()
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
			printf("%d", board[i][j]);
		printf("\n");
	}
	printf("\n");
}

void printVisit()
{
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
			printf("%d", isVisited[i][j]);
		printf("\n");
	}
	printf("\n");
}
