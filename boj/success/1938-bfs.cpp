#include <cstdio>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

#define MAX_N 52

int N;
char map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N][2];	/*
									 * 통나무가 중심점이 (i, j) 일 때,
									 * [i][j][0] : 가로로 긴 상태일 때 방문 여부
									 * [i][j][1] : 세로로 긴 상태일 때 방문 여부
									 */

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

int er[3], ec[3];	// 도착점 좌표
bool eisCol;		// 도착점이 세로로 긴 상태인지

struct Node
{
	int r, c;	// 통나무의 중심 좌표
	bool isCol;	// 세로로 긴 상태라면 true

	Node(int rr, int cc, bool iisCol)
	{
		r = rr, c = cc;
		isCol = iisCol;
	}
};

// 경계 안에 존재하면 true
bool check_inbound(int r, int c)
{
	if (r < 0 || r >= N || c < 0 || c >= N)
		return false;
	return true;
}


/*
 * 상하좌우, 회전까지 총 5가지의 방법에 대해 고려.
 * 통나무의 중심 좌표를 활용하여 중심 좌표를 포함하여 세로로 긴 상태이면 중심의 위아래,
 * 가로로 긴 상태이면 양 옆, 총 세 군데가 경계 안에 존재하는지,
 * 벽(map[i][j] == 1)이 존재하는지 체크한다.
 *
 * bfs로 횟수의 최솟값을 구한다.
 */

bool move(struct Node *next, struct Node nod, int way)
{
	int nr, nc;
	bool nisCol = nod.isCol;

	switch(way)
	{
		// 상하좌우 이동
		case 0:
		case 1:
		case 2:
		case 3:
			nr = nod.r + dx[way];
			nc = nod.c + dy[way];

			if (!check_inbound(nr, nc) || map[nr][nc] == '1')
				return false;

			if (isVisited[nr][nc][nisCol] == true)
				return false;

			if (nisCol == true)
			{
				if (!check_inbound(nr-1, nc) || map[nr-1][nc] == '1')
					return false;
				if (!check_inbound(nr+1, nc) || map[nr+1][nc] == '1')
					return false;
			}
			else
			{
				if (!check_inbound(nr, nc-1) || map[nr][nc-1] == '1')
					return false;
				if (!check_inbound(nr, nc+1) || map[nr][nc+1] == '1')
					return false;
			}
			break;

		// 회전
		case 4:
			nr = nod.r;
			nc = nod.c;

			if (isVisited[nr][nc][!nisCol] == true)
				return false;

			// 9칸 검사
			for (int i = nr-1; i < nr+2; i++)
				for (int j = nc-1; j < nc+2; j++)
					if (check_inbound(i, j) == false || map[i][j] == '1')
						return false;

			nisCol = !nisCol;
			break;
	}

	next->r = nr;
	next->c = nc;
	next->isCol = nisCol;

	isVisited[nr][nc][nisCol] = true;
	return true;
}

int bfs(int sr, int sc, bool sisCol)
{
	queue<struct Node> q;
	struct Node tmp_nod(sr, sc, sisCol);
	isVisited[sr][sc][sisCol] = true;

	q.push(tmp_nod);

	int cnt = 1;

	while(!q.empty())
	{
		int qSize = q.size();

		for (int i = 0; i < qSize; i++)
		{
			struct Node cur = q.front();
			q.pop();

			for (int j = 0; j < 5; j++)
			{
				struct Node add_nod(0, 0, false);

				if (move(&add_nod, cur, j) == false)
					continue;

				// 도착점의 중심 좌표와 같은 방향으로 긴 상태인지 체크
				if (add_nod.r == er[1] && add_nod.c == ec[1] && add_nod.isCol == eisCol)
					return cnt;

				q.push(add_nod);

			}// for j
		}//for i
		
		cnt++;
	}
	return 0;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 0; i < N; i++)
		scanf("%s", map[i]);

	int br[3], bc[3]; 
	int bCnt = 0, eCnt = 0;
	bool bisCol;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (map[i][j] == 'B')
			{
				br[bCnt] = i, bc[bCnt] = j;
			 	bCnt++;
			}
			else if (map[i][j] == 'E')
			{
				er[eCnt] = i, ec[eCnt] = j;
				eCnt++;
			}
		}
	}
	if (br[0] == br[1])
		bisCol = false;
	else
		bisCol = true;

	if (er[0] == er[1])
		eisCol = false;
	else
		eisCol = true;

	printf("%d\n", bfs(br[1], bc[1], bisCol));
	return 0;
}
