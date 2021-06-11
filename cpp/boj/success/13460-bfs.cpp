#include <cstdio>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

#define MAX_N 11

#define WALL '#'
#define RED 'R'
#define BLUE 'B'
#define DOT '.'
#define END 'O'

int N, M;

char map[MAX_N][MAX_N];

struct Node
{
	char m[MAX_N][MAX_N];	// 맵
	int preDir;	// 이전의 방향

};

// 90도 회전
void rotate90(char a[][MAX_N], int n, int m)
{
	char tmp[MAX_N][MAX_N];

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			tmp[j][n - 1 - i] = a[i][j];

	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			a[i][j] = '\0';

	for (int i = 0; i < m; i++)
		memcpy(a[i], tmp[i], n);
}

// 맵 복사 함수
void copy_map(char dest[][MAX_N], char src[][MAX_N], int n, int m)
{
	for (int i = 0; i < n; i++)
		memcpy(dest[i], src[i], m);

}

// 맵 비교 함수
bool cmp_map(char a[][MAX_N], char b[][MAX_N])
{
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			if (a[i][j] != b[i][j])
				return false;

	return true;

}

/*
 * 구슬 이동 함수
 * mm을 임시 맵(tmp)에 복사하고, 방향에 맞게 배열을 rotate한다.
 * (그렇게 해서 구슬을 무조건 왼쪽으로 이동시킬 수 있다)
 *
 * 한 줄씩 일정 조건에 맞추어 vector에 구슬의 위치를 이동시키면서 넣는다.
 * 벽(#)과 벽(#)사이에서 구멍 오른쪽에 어떤 구슬이 존재하는지로
 * 성공 여부를 판단한다.
 * 빨간 구슬만 있다면 성공, 파란 구슬이 무조건 존재하면 실패이다.
 *
 * 임시 맵(tmp)를 다시 rotate하여 원래의 모습으로 되돌려 놓는다.
 *
 * bfs()로 10번째 횟수까지 성공하지 못하거나 애초에 갈 수 없는 경우
 * -1을 리턴한다.
 *
 */
int move(char nextm[][MAX_N], char mm[][MAX_N], int dir)
{
	char tmp[MAX_N][MAX_N];
	copy_map(tmp, mm, N, M);

	for (int i = 0; i < dir; i++)
	{
		int n, m;
		if (i % 2 == 0)
			n = N, m = M;
		else
			n = M, m = N;

		rotate90(tmp, n, m);
	}

	int row, col;
	if (dir % 2 == 0)
		row = N, col = M;
	else
		row = M, col = N;

	for (int i = 1; i < row - 1; i++)
	{
		vector <char> vec;
		int dotCnt = 0;
		int endIdx = -1;
		for (int j = 0; j < col; j++)
		{
			if (tmp[i][j] == WALL)
			{
				for (int dc = 0; dc < dotCnt; dc++)
					vec.push_back(DOT);
				vec.push_back(WALL);
				dotCnt = 0;

			}
			else if (tmp[i][j] == RED || tmp[i][j] == BLUE)
			{
				vec.push_back(tmp[i][j]);


			}
			else if (tmp[i][j] == END)
			{
				for (int dc = 0; dc < dotCnt; dc++)
					vec.push_back(DOT);
				vec.push_back(END);
				endIdx = vec.size() - 1;

				dotCnt = 0;

			}
			else
				dotCnt++;

		}
		for (int j = 0; j < col; j++)
		{
			tmp[i][j] = vec[j];
		}
		if (endIdx != -1)
		{
			bool isRed = false;
			bool isBlu = false;
			for (int i = endIdx + 1; i < vec.size(); i++)
			{
				if (vec[i] == RED)
					isRed = true;
				else if (vec[i] == BLUE)
					isBlu = true;
				else if (vec[i] == WALL)
					break;


			}
			if (isBlu)
			{
				return 1;

			}
			else if (isRed)
			{
				return 2;

			}
		}
	}

	for (int i = dir; i < 4; i++)
	{
		if (dir == 0)
			break;
		int n, m;
		if (i % 2 == 0)
			n = N, m = M;
		else
			n = M, m = N;

		rotate90(tmp, n, m);

	}

	copy_map(nextm, tmp, N, M);


	return 0;

}

int bfs()
{
	queue<struct Node> q;

	struct Node tmp_nod;
	copy_map(tmp_nod.m, map, N, M);
	tmp_nod.preDir = -1;
	q.push(tmp_nod);

	int cnt = 1;
	bool isFin = false;

	while (!q.empty())
	{
		int qSize = q.size();
		int ret = 0;

		for (int i = 0; i < qSize; i++)
		{
			struct Node cur = q.front();
			q.pop();

			for (int j = 0; j < 4; j++)
			{
				if (tmp_nod.preDir == j)
					continue;

				struct Node add_nod;
				ret = move(add_nod.m, cur.m, j);

				if (ret == 2)
					return cnt;
				else if (ret == 1)
					continue;

				if (cmp_map(add_nod.m, cur.m) == true)
					continue;

				add_nod.preDir = j;
				q.push(add_nod);


			}

		}

		if (cnt >= 10)
			return -1;

		cnt++;
	}

	return -1;
}

int main()
{
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		scanf("%s", map[i]);

	printf("%d", bfs());
	return 0;
}
