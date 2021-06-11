#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 28

int N, M;
char input[MAX_N][MAX_N];
int board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

int dx[4] = { -1,  0, 1, 0 };
int dy[4] = {  0, -1, 0, 1 };


int sr, sc, er, ec;

int res;
int ans[2];

int dfs(int r, int c, int dir)
{
	if (r == er && c == ec)
		return 0;
//	printf("r, c : %d %d\n",r , c);

	if (board[r][c] == 0 || (sr == r && sc == c))
	{
//		printf("!!r, c : %d %d\n",r , c);
		for (int i = 0; i < 4; i++)
		{
			int nr = r + dx[i];
			int nc = c + dy[i];

			if (board[nr][nc] == 0)
				continue;

//			printf("board : %d\n", board[nr][nc]);

			if (board[nr][nc] & (1 << (i+2)%4))
			{
				printf("ss %d\n", i);
				board[r][c] += (1 << i);
			}

		}
		if (board[r][c] == 0)
			return 0;

		if (sr != r || sc != c)
			ans[0] = r, ans[1] = c;
	}

	for (int i = 0; i < 4; i++)
	{
		if (!(board[r][c] & (1 << i)))
		{
//			printf("hi\n"); 
			continue;
		}
		if (i == (dir + 2 ) % 4)
			continue;

		if (board[r][c] == 15 && i != dir)
			continue;

		int nr = r + dx[i];
		int nc = c + dy[i];

		dfs(nr, nc, i);
	}
}

int main()
{
	freopen("in.txt", "r", stdin);
	
	scanf("%d%d", &N, &M);

	for (int i = 0; i < N; i++)
		scanf("%s", input[i]); 

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (input[i][j] == 'M')
				sr = i, sc = j, board[i][j] = 100;
			else if (input[i][j] == 'Z')
				er = i, ec = j;
			
			switch(input[i][j])
			{
				case '|':
					board[i][j] = 5;
					break;
				case '-':
					board[i][j] = 10;
					break;

				case '+':
					board[i][j] = 15;
					break;

				case '1':
					board[i][j] = 12;
					break;
					
				case '2':
					board[i][j] = 9;
					break;

				case '3':
					board[i][j] = 3;
					break;
				case '4':
					board[i][j] = 6;
				default:
					break;
			}
		}
	}

	dfs(sr, sc, board[sr][sc]);

	char ch;
	switch(board[ans[0]][ans[1]])
	{
		case 5:
			ch = '|';
			break;
		case 10:
			ch = '-';
			break;
		case 15:
			ch = '+';
			break;
		case 12:
			ch = '1';
			break;
		case 9:
			ch = '2';
			break;
		case 3:
			ch = '3';
			break;
		case 6:
			ch = '4';
		default:
			break;
	}
	printf("%d %d %c", ans[0]+1, ans[1]+1, ch);
//	for (int i = 0; i < N; i++)
//	{
//		for (int j = 0; j < M; j++)
//		{
//			printf("%d ", board[i][j]);
//		}
//		printf("\n");
//	}



	return 0;
}
