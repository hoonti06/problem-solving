#include<stdio.h>

char board[5][6];
int dr[4] = {-1, 1,  0, 0};
int dc[4] = { 0, 0, -1, 1};
int res; 

// 25개의 모든 bit가 0인 경우와 25개의 모든 bit가 1인 경우까지의 모든 경우를 체크하는 변수이다. 
bool check[1 << 25];


void backtracking(int cnt, int sCnt, int status)
{
	check[status] = true;

	if(cnt >= 7)
	{
		if(sCnt >= 4)
			res++;
		return; 	
	}
	for(int i = 0; i < 25; i++)
	{
		if(!(status & (1 << i)))
			continue;

		int r = i / 5;
		int c = i % 5;
		for(int j = 0; j < 4; j++)
		{
			int nR = r + dr[j];
			int nC = c + dc[j];
			if(nR < 0 || nR > 4 || nC < 0 || nC > 4)
				continue;
			
			// 먼저, 비트에서 & 는 주로 해당 bit만을 가져올 때 사용하고,
			// | 는 주로 해당 비트를 기존의 기록에 추가할 때 사용한다. 
			//
			// 첫번째 조건은 nR, nC의 위치를 이미 방문한 곳 인지를 체크하는 것이다
			// status는 int형으로 32bit이며 그 중 25bit를 활용하여 방문했으면
			// 해당 위치의 bit를 1로 두어 기록하는 변수이다.
			//
			// 두번째 조건은 해당 경우의 수가 이전에 수행됐었는지 확인하는 것이다. 
			if( status & (1 << (nR*5 + nC)) || check[status | (1 << (nR*5 + nC))] )
				continue;

			backtracking( cnt+1, sCnt + (board[nR][nC] == 'S'), status | (1 << (nR*5 + nC)) );
		}
	}
}

int main()
{
	freopen("in.txt", "r", stdin);

	int i, j;
	for(i = 0; i < 5; i++)
		scanf("%s", board[i]);

	for(i = 0; i < 5; i++)
		for(j = 0; j < 5; j++)
			backtracking(1, board[i][j] == 'S', (1 << (i*5 + j)));
	
	printf("%d\n", res);
	return 0;
}
