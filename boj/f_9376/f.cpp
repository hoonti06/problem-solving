#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 105

#define WALL '*'
#define DOOR '#'

int N, M;
char map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N][2];

int dx[4] = {  0, -1, 0 , 1 };
int dy[4] = { -1,  0, 1 , 0 };

vector<pair<int, int> > vec;

vector<pair<int, int> > min_vec;

int min_cnt = 1e9;

bool check_escape(int r, int c)
{
	if (0 < r && r < N-1 && 0 < c && c < M-1)
		return false;
	else if (r < 0 || r >= N || c < 0 || c >= M)
		return false;

	return true;
}

struct Node
{
	int r1, c1, r2, c2;
	int cnt;
	Node(int rr1, int cc1, int rr2, int cc2, int ccnt)
	{
		r1 = rr1, c1 = cc1;
		r2 = rr2, c2 = cc2;
		cnt = ccnt;
	}
};

queue<struct Node> q;

int bfs(int sr1, int sc1, int sr2, int sc2)
{
	struct Node tmp_nod(sr1, sc1, sr2, sc2, 0);
	q.push(tmp_nod);

	while (!q.empty())
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			struct Node cur = q.front();
			q.pop();

			for (int i = 0; i < 4; i++)
			{
				int nr1 = cur.r1, nc1 = cur.c1;
				int curCnt = cur.cnt;

				if (!check_escape(cur.r1, cur.c1))
				{
					nr1 = cur.r1 + dx[i];
					nc1 = cur.c1 + dy[i];

					if (nr1 < 0 || nr1 >= N || nc1 < 0 || nc1 >= M)
						continue;
					if (isVisited[nr1][nc1][0] == true)
						continue;

					isVisited[nr1][nc1][0] = true;

					if (map[nr1][nc1] == WALL)
						continue;
					else if (map[nr1][nc1] == DOOR && !isVisited[nr1][nc1][1])
						curCnt++;

				}

				for (int j = 0; j < 4; j++)
				{
					int nr2 = cur.r2, nc2 = cur.c2;

					if (!check_escape(cur.r1, cur.c2))
					{
						nr2 = cur.r2 + dx[j];
						nc2 = cur.c2 + dy[j];

						if (nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= M)
							continue;
						if (isVisited[nr2][nc2][1] == true)
							continue;

						isVisited[nr2][nc2][1] = true;

						if (map[nr2][nc2] == WALL)
						{
							isVisited[nr1][nc1][0] = false;
							continue;
						}
						else if (map[nr2][nc2] == DOOR && !isVisited[nr2][nc2][0])
							curCnt++;
					}

					if (min_cnt < curCnt)
						continue;

					if (check_escape(nr1, nc1) || check_escape(nr2, nc2))
					{
						if (min_cnt > curCnt)
							min_cnt = curCnt;
					}
					if (check_escape(nr1, nc1) && check_escape(nr2, nc2))
					{
						break;
					}

					struct Node add_nod(nr1, nc1, nr2, nc2, curCnt);

					q.push(add_nod);

				}

			} // for i
		} // for qs
	} // while 
}

//int dfs(int r1, int c1, int r2, int c2, int cnt)
//{
//	for (int i = 0; i < 4; i++)
//	{
//		int nr1 = r1 + dx[i];
//		int nc1 = c1 + dy[i];
//
//		int curCnt = cnt;
//
//		if (nr1 < 0 || nr1 >= N || nc1 < 0 || nc1 >= M)
//			continue;
//
//		if (isVisited[nr1][nc1][0] == true)
//			continue;
//
//		if (map[nr1][nc1] == WALL)
//			continue;
//		else if (map[nr1][nc1] == DOOR && !isVisited[nr1][nc1][1])
//			curCnt++;
//
//		for (int j = 0; j < 4; j++)
//		{
//			int nr2 = r2 + dx[j];
//			int nc2 = c2 + dy[j];
//
//			if (nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= M)
//				continue;
//			if (isVisited[nr2][nc2][1] == true)
//				continue;
//
//			isVisited[nr1][nc1][0] = true;
//
//			if (map[nr2][nc2] == WALL)
//			{
//				isVisited[nr1][nc1][0] = false;
//				continue;
//			}
//			else if (map[nr2][nc2] == DOOR && !isVisited[nr2][nc2][0])
//				curCnt++;
//
//			if (check_escape(nr1, nc1) || check_escape(nr2, nc2))
//			{
//				if (min_cnt > curCnt)
//					min_cnt = curCnt;
//
//				//				isVisited[nr1][nc1][0] = false;
//				//				continue;
//			}
//
//			if (min_cnt < curCnt)
//			{
//				isVisited[nr1][nc1][0] = false;
//				continue;
//			}
//
//			isVisited[nr2][nc2][1] = true;
//			dfs(nr1, nc1, nr2, nc2, curCnt);
//
//			isVisited[nr1][nc1][0] = false;
//			isVisited[nr2][nc2][1] = false;
//
//
//		}
//
//
//	}
//
//}


int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for(int testcase = 0; testcase < T; testcase++)
	{
		//		printf("\n\n\n");

		scanf("%d%d", &N, &M);
		for (int i = 0; i < N; i++)
			scanf("%s", map[i]);

		int pr[2], pc[2], pCnt = 0;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				if (map[i][j] == '$')
				{
					pr[pCnt] = i, pc[pCnt] = j;
					pCnt++;
				}
			}
		}

		isVisited[pr[0]][pc[0]][0] = true;
		isVisited[pr[1]][pc[1]][1] = true;

//		dfs(pr[0], pc[0], pr[1], pc[1], 0);
		bfs(pr[0], pc[0], pr[1], pc[1]);

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				for(int k = 0; k < 2; k++)
					isVisited[i][j][k] = false;

			}
		}

		//				printf("\n");
		//		}
		//		printf("\n");

		printf("%d\n", min_cnt);

		min_cnt = 1e9;

		//		dfs(pr[1], pc[1]);

		//		res += min_cnt;
		//		printf("2. tc : %d, min_cnt : %d\n", testcase, min_cnt);
		//		for (int i = 0; i < min_vec.size(); i++)
		//		{
		//			printf("%d, %d/ ", min_vec[i].first, min_vec[i].second);
		//		}
		//		printf("\n");


}



return 0;
}
