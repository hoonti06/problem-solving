#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 30

int N, M;

int board[MAX_N][MAX_N];
//bool isVisited[MAX_N][MAX_N];
int isVisited[MAX_N][MAX_N];

int dx[8] = { -1, 1,  2, 2,  1, -1, -2, -2};
int dy[8] = {  2, 2, -1, 1, -2, -2, -1,  1};

long long res = 1e9, rCnt;

map<pair<int, int>, int> mp;

struct Node
{
	unsigned short num;

	Node(int r, int c)
	{
		num = r*M + c;
	}
};


int bfs(int sr, int sc)
{
//	mp[make_pair(sr, sc)] = 1;

	priority_queue<pair<int, int> > pq;
	pq.push(make_pair(0, sr*M + sc));
	mp[make_pair(sr*M+sc, 0)] = 1;

//	isVisited[sr][sc] = true;
	
	while (!pq.empty())
	{
//		int qSize = q.size();
//		for (int qs = 0; qs < qSize; qs++)
//		{
			int r = pq.top().second / M;
			int c = pq.top().second % M;
			int cnt = pq.top().first;
			pq.pop();
			printf("%d %d\n", r, c);

			isVisited[r][c] = cnt;

			for (int i = 0; i < 8; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];
				int nCnt = cnt;

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (isVisited[nr][nc] < cnt && cnt == 0)
					continue;

				if (board[nr][nc] == 1)
					nCnt--;
				else if (board[nr][nc] == 2)
					continue;
				else if (board[nr][nc] == 4)
				{
					res = cnt;
					rCnt = mp[make_pair(r*M+c, cnt)];

					return 0;
//					if (res > cnt) {
//						res = cnt;
//						rcnt = map[]
//					}

				}
				nCnt++;

				if (mp[make_pair(nr*M+nc, nCnt)] <= 0)
				{
					mp[make_pair(nr*M+nc, nCnt)] += mp[make_pair(r*M+c, cnt)];
				
					pq.push(make_pair(nCnt, nr*M + nc));
				}
				else
				{
					mp[make_pair(nCnt, nr*M+nc)] += mp[make_pair(r*M+c, cnt)];
				}

			}
//		}
	}
}


int main()
{
	freopen("in.txt", "r", stdin);

	int sr, sc;
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
//			isVisited[i][j] = 1;
			scanf("%d", &board[i][j]);
			if (board[i][j] == 3)
				sr = i, sc = j;
		}
	}
	bfs(sr, sc);

//	for (int i = 0; i < N; i++)
//	{
//		for (int j = 0; j < M; j++)
//		{
//			printf("%d ", isVisited2[i][j]);
//		}
//		printf("\n");
//	}

	printf("%lld\n%lld", res, rCnt);
}
