#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 30

short N, M;

unsigned short board[MAX_N][MAX_N];
//bool isVisited[MAX_N][MAX_N];
unsigned short isVisited[MAX_N][MAX_N];
unsigned short isVisited2[MAX_N][MAX_N];

short dx[8] = { -1, 1,  2, 2,  1, -1, -2, -2};
short dy[8] = {  2, 2, -1, 1, -2, -2, -1,  1};

long long res = 1e9, rCnt;

struct Node
{
	unsigned short num;
	long long cnt;

	Node(unsigned short r, unsigned short c, long long ccnt)
	{
		num = r*M + c, cnt = ccnt;
	}

//	bool operator<(const Node& y) const
//	{
//		cnt > y.cnt;
//	}
};

int bfs(short sr, short sc)
{
//	priority_queue<Node> pq;
	queue<Node> pq;
	Node initNode(sr, sc, 0);
//	isVisited[sr][sc] = true;
	pq.push(initNode);
	
//	long long cnt = 0;

	while (!pq.empty())
	{
		int pqSize = pq.size();
		for (int pqs = 0; pqs < pqSize; pqs++)
		{
			unsigned short r = pq.front().num / M;
			unsigned short c = pq.front().num % M;
			long long cnt = pq.front().cnt;
//			int r = pq.top().r;
//			int c = pq.top().c;
//			long long cnt = pq.top().cnt;
			pq.pop();

//			isVisited2[r][c] = cnt;

//			isVisited[r][c] = true;
			isVisited[r][c] = -1;

			if (res < cnt)
				continue;

			for (short i = 0; i < 8; i++)
			{
				unsigned short nr = r + dx[i];
				unsigned short  nc = c + dy[i];
				long long nCnt = cnt;

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

//				if (isVisited[nr][nc] == true)
//					continue;
				if (isVisited[nr][nc] == -1)
					continue;

				isVisited[nr][nc] = isVisited[r][c];

				if (isVisited2[nr][nc] == 0)
					isVisited2[nr][nc] = cnt + 1;

				else if (cnt + 1 >= isVisited2[nr][nc])
					continue;
				

//				isVisited[nr][nc] = true;

//				printf("nrc : %d %d\n", nr, nc);

				if (board[nr][nc] == 1)
					nCnt--;
				else if (board[nr][nc] == 2)
					continue;
				else if (board[nr][nc] == 4)
				{
//					printf("!!!  ");
//						printf("res cnt : %lld %lld\n", res , cnt);
					if (res > isVisited2[nr][nc])
					{
						res = isVisited2[nr][nc];
						rCnt = 1;
					}
					else if (res == isVisited[nr][nc])
					{
//						printf("res== cnt : %lld\n", cnt);
						rCnt++;
					}
					continue;
				}

				nCnt++;
				
				Node addNod(nr, nc, nCnt);
				pq.push(addNod);
			}
		}
	}
}


int main()
{
	freopen("in.txt", "r", stdin);

	short sr, sc;
	scanf("%hd%hd", &N, &M);
	for (short i = 0; i < N; i++)
	{
		for (short j = 0; j < M; j++)
		{
			isVisited[i][j] = 1;
			scanf("%hd", &board[i][j]);
			if (board[i][j] == 3)
				sr = i, sc = j;
		}
	}
	bfs(sr, sc);

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			printf("%d ", isVisited2[i][j]);
		}
		printf("\n");
	}


	printf("%lld\n%lld", res, rCnt);

}
