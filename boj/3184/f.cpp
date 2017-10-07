#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 255
#define SHEEP 'o'
#define WOLF 'v'
#define FENCE '#'

int N, M;

char board[MAX_N][MAX_N];

bool isVisited[MAX_N][MAX_N];

int dx[4] = { -1,  0, 1 , 0 };
int dy[4] = {  0, -1, 0 , 1 };

int sCnt, wCnt;

int bfs(int sr, int sc)
{
	queue < pair<int, int> > q;
	q.push(make_pair(sr, sc));	
	if (board[sr][sc] == SHEEP)
		sCnt++;
	else if (board[sr][sc] == WOLF)
		wCnt++;

	while (!q.empty())
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().first;
			int c = q.front().second;
			q.pop();

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (isVisited[nr][nc] == true)
					continue;
				isVisited[nr][nc] = true;

				if (board[nr][nc] == SHEEP)
					sCnt++;
				else if (board[nr][nc] == WOLF)
					wCnt++;
				else if (board[nr][nc] == FENCE)
					continue;
					
				q.push(make_pair(nr, nc));
			}
		}
	}
	return 0;
}


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		scanf("%s", board[i]);

	int sRes = 0, wRes = 0;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (isVisited[i][j] == true || board[i][j] == '#')
				continue;

			isVisited[i][j] = true;
			bfs(i, j);

			if (sCnt > wCnt)
				sRes += sCnt;
			else
				wRes += wCnt;

			sCnt = 0, wCnt = 0;
		}
	}
	printf("%d %d", sRes, wRes);
}
