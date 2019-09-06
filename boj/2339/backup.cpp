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

// 상 좌 하 우
int dx[4] = { -1,  0, 1, 0 };
int dy[4] = {  0, -1, 0, 1 };

bool bfs(int sr, int sc)
{
	vector<pair<int, int> > visitedVec;
	visitedVec.push_back(make_pair(sr, sc));
	queue<pair<int ,int> > q;
	q.push(make_pair(sr, sc));
	isVisited[sr][sc] = true;

	int cnt = 0;
	while (!q.empty())
	{
		int r = q.front().first;
		int c = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++)
		{
			int nr = r + dx[i];
			int nc = c + dy[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			if (isVisited[nr][nc])
				continue;

			if (board[nr][nc] == JEWELRY)
				cnt++;

			isVisited[nr][nc] = true;
			visitedVec.push_back(make_pair(sr, sc));
			q.push(make_pair(nr, nc));
		}
	}
	for (int i = 0; i < visitedVec.size(); i++)
		isVisited[visitedVec[i].first][visitedVec[i].second] = false;
	visitedVec.clear();
	if (cnt == 1)
		return true;
	else
		return false;
}
int dfs(int cnt)
{
	if (cnt >= jewelryDeq.size()-1)
	{
		printVisit();
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (!isVisited[i][j] && !bfs(i, j))
					return 0;
			}

		}
		printf("!!\n");
		return 1;
	}


	int ret = 0;

	int r = trashDeq[cnt].first;
	int c = trashDeq[cnt].second;
	isVisited[r][c] = true;

	vector<pair<int, int> > visitedVec;
	int nr = r;
	while (true)
	{
		nr--;
		if (nr < 0)
			break;
//		if (board[nr][c] == JEWELRY)
//			return 0;
		if (isVisited[nr][c])
			break;

		isVisited[nr][c] = true;
		visitedVec.push_back(make_pair(nr, c));
	}

	nr = r;
	while (true)
	{
		nr++;
		if (nr >= N)
			break;
//		if (board[nr][c] == JEWELRY)
//			return 0;
		if (isVisited[nr][c])
			break;
		isVisited[nr][c] = true;
		visitedVec.push_back(make_pair(nr, c));
	}

	ret += dfs(cnt+1);

	for (int i = 0; i < visitedVec.size(); i++)
		isVisited[visitedVec[i].first][visitedVec[i].second] = false;
	visitedVec.clear();

	int nc = c;
	while (true)
	{
		nc--;
		if (nc < 0)
			break;
//		if (board[r][nc] == JEWELRY)
//			return 0;
		if (isVisited[r][nc])
			break;
		isVisited[r][nc] = true;
		visitedVec.push_back(make_pair(r, nc));
	}

	nc = c;
	while (true)
	{
		nc++;
		if (nc >= N)
			break;
//		if (board[r][nc] == JEWELRY)
//			return 0;
		if (isVisited[r][nc])
			break;
		isVisited[r][nc] = true;
		visitedVec.push_back(make_pair(r, nc));
	}

	ret += dfs(cnt+1);

	return ret;
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
