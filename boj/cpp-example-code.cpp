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

#define MAX_N 1005


int board[MAX_N][MAX_N];
int N;

int dx[4] = { 0, -1, 0, 1 };
int dy[4] = {-1,  0, 1, 0 };

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);

	for (int i = 0; i < N; i++)
	{

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
}
