#include <cstdio>
#include <algorithm>
#include <cstring>
#include <queue>
#include <cmath>
#include <map>

using namespace std;

int mp;	// board[3][3]을 int 하나로 표현 : 배열[ 1 0 3 / 4 2 5 / 7 8 6 ]을 687524301로 표현	

map<int, int> isVisited;	// 방문 여부를 map 자료구조를 활용하여 체크

short int dx[4] = {1,  0, -1, 0};
short int dy[4] = {0, -1,  0, 1};

int temp[3][3];

// src 값에서 0의 위치를 찾고 dir 방향의 위치와 swap하여 dest에 저장
bool swap(int *dest, int src, int dir)
{
	int r, c;
	for (int i = 0; i < 9; i++)
	{
		if (src % 10 == 0)
		{
			r = i / 3;
			c = i % 3;
		}
		temp[i / 3][i % 3] = src % 10;

		src /= 10;
	}

	int nr = r + dx[dir];
	int nc = c + dy[dir];

	if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3)
		return false;

	int tmp = temp[nr][nc];
	temp[nr][nc] = 0;
	temp[r][c] = tmp;

	*dest = 0;
	int ten = 1;
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			*dest += temp[i][j] * ten;
			ten *= 10;
		}
	}
	
	if (isVisited[*dest] == 1)	// 이미 방문했으면 false를 리턴
		return false;
	
	return true;
}

queue<pair<int, int> > q;

int bfs()
{
	q.push(make_pair(mp, 0));
	
	while (!q.empty())
	{
		int curmp = q.front().first;
		int curcnt = q.front().second;
		q.pop();

		if (curmp == 87654321)
			return curcnt;

		for (int i = 0; i < 4; i++)
		{
			int new_mp;
			if (swap(&new_mp, curmp, i) == false)
				continue;

			isVisited[new_mp] = 1;
			q.push(make_pair(new_mp, curcnt + 1));
		}
	}
	return -1;
}

int main()
{
	freopen("in.txt", "r", stdin);

	int ten = 1;
	int tmp;
	for(int i = 0; i < 9; i++)
	{
		scanf("%d", &tmp);
		mp += tmp * ten;
		ten *= 10;
	}
	isVisited[mp] = 1;

	printf("%d", bfs());
	return 0;
}
