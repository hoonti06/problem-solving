#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
#include <cmath>
#include <functional>
#include <map>

using namespace std;

#define WALL '#'
#define PIN 'o'
#define EMPTY '.'

int N = 5, M = 9;

char initboard[5][10];
char input[5][10];

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

int res;

int backtracking(map<int, int> pin)
{
	int pCnt = pin.size();

	if (pCnt <= 1)
	{
		res = 1;
		return 0;
	}
	if (res <= 1)
		return 0;

	char board[5][10];
	memcpy(board, initboard, sizeof(board));

	map<int, int>::iterator it;
	int r, c;
	for (it = pin.begin(); it != pin.end(); it++)
	{
		r = it->first / M;
		c = it->first % M;
		board[r][c] = PIN;
	}

	int cnt = 0;
	for (it = pin.begin(); it != pin.end(); it++)
	{		
		r = it->first / M;
		c = it->first % M;

		int nr, nc, nnr, nnc;
		for (int i = 0; i < 4; i++)
		{
			nr = r + dx[i];
			nc = c + dy[i];

			nnr = nr + dx[i];
			nnc = nc + dy[i];
			
			if (nnr < 0 || nnr >= 5 || nnc < 0 || nnc >= 9)
				continue;

			if (board[nnr][nnc] == '.' && board[nr][nc] == PIN)
			{
				cnt++;
				map<int, int> nPin(pin);
				nPin.erase(r*M + c);
				nPin.erase(nr*M + nc);
				nPin[nnr*M + nnc] = 1;

				backtracking(nPin);
			}
		}
	}
	if (cnt == 0)
		res = min(res, pCnt);
}



int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);

	for (int testcase = 0; testcase < T; testcase++)
	{
		for (int i = 0; i < N; i++)
			scanf("%s", input[i]);

		res = 1e9;
		int pCnt = 0;
		map<int, int> pin;

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				if (testcase == 0)
					initboard[i][j] = input[i][j];

				if (input[i][j] == 'o')
				{
					pCnt++, pin[i*M+j] = 1;
					if (testcase == 0)
						initboard[i][j] = '.';
				}
			}
		}

		backtracking(pin);
		
		printf("%d %d\n", res, pCnt - res);
	}
}
