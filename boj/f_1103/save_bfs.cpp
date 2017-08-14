#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

#define MAX_N 52

#define END 'H'

int N, M;
char map[MAX_N][MAX_N];

short int dx[4] = { -1,  0, 1, 0 };
short int dy[4] = {  0, -1, 0, 1 };

//int isVisited[MAX_N][MAX_N];

int max_cnt = 0;

//int dfs(int r, int c, int cnt)
//{
//	if (max_cnt > M*N)
//		return 0;
//
//	for (int i = 0; i < 4; i++)
//	{
//		int nr = r + dx[i] * (map[r][c] - '0');
//		int nc = c + dy[i] * (map[r][c] - '0');
//
////		printf("cur : %d %d\n", nr, nc);
//
//		if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == END)
//		{
//			max_cnt = max(max_cnt, cnt+1);
//			continue;
////			return 0;
//		}
//		if (max_cnt > M*N)
//		{
//			max_cnt = 1e9;
//			continue;
////			return 0;
//		}
//
//		dfs(nr, nc, cnt + 1);
//	}
//
//}

//int bfs(int sr, int sc)
//{
//	queue<short int> q;
//
//	q.push(sr*M + sc);
//	short int cnt = 0;
//
//	while (!q.empty())
//	{
//		int qSize = q.size();
//		
//		for (int qs = 0; qs < qSize; qs++)
//		{
//			short int r = q.front() / M;
//			short int c = q.front() % M;
//			q.pop();
//
//			if (cnt > N*M)
//				return -1;
//
//			for (int i = 0; i < 4; i++)
//			{
//				short int nr = r + dx[i] * (map[r][c] - '0');
//				short int nc = c + dy[i] * (map[r][c] - '0');
//
//				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
//					continue;
//
//				if (map[nr][nc] == END)
//					continue;
//
//				q.push(nr*M + nc);
//			}
//		}
//		cnt++;
//	}
//	return cnt;
//}


int process()
{
	int dp[MAX_N][MAX_N][2];

	dp[0][0][0] = 1;


	bool isFinish = false;
	int k = 0;
	while (!isFinish)
	{
		bool flag = false;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
//				if (dp[i][j][k%2] > k)
//				{
//					flag = true;
//					for (int d = 0; d < 4; d++)
//					{
//						int nr = i + dx[d] * (map[i][j] - '0');
//						int nc = j + dy[d] * (map[i][j] - '0');
//						
//						if (nr < 0 || nr >= N || nc < 0 || nc >= M)
//							continue;
//
//
//						dp[nr][nc][(k+1)%2] = dp[i][j][k%2] + 1;
//
//					} 
//				}

				{
					flag = true;
					for (int d = 0; d < 4; d++)
					{
						int nr = i + dx[d] * (map[i][j] - '0');
						int nc = j + dy[d] * (map[i][j] - '0');
						
						if (nr < 0 || nr >= N || nc < 0 || nc >= M)
							continue;
						if (dp[i][j][k%2] > k)
							dp[nr][nc][(k+1)%2] = dp[i][j][k%2] + 1;
						else 
							dp[nr][nc][(k+1)%2] = max(dp[nr][nc][(k+1)%2], dp[i][j][k%2]);
					} 
				}
			}
		}
		printf("k : %d\n", k);
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				printf("%d ", dp[i][j][(k+1)%2]);
			}
			printf("\n");
		}
		printf("\n");

		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				if (dp[i][j][k%2] != dp[i][j][(k+1)%2])
					flag = true;
			}
		}

		if (flag == false)
			break;

		if (k >= 10)
			break;
		k++;
	}
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		scanf("%s", map[i]);

	process();

//	int res = bfs(0, 0);
//	printf("%d", res);

//	dfs(0, 0, 0);

//	if (max_cnt == 1e9)
//		printf("-1");
//	else
//		printf("%d", max_cnt);
	
	
	return 0;
}
