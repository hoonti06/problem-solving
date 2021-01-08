#include <cstdio>
#include <algorithm>
#include <vector>
#include <deque>
#include <queue>
#include <cstring>

using namespace std;

#define MAX_N 52
#define pii pair<int, int>
#define makepii(a, b) (make_pair((a), (b)))

int board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

int N, L, R;

int dx[4] = {  0, -1, 0 , 1 };
int dy[4] = { -1,  0, 1 , 0 };

int bfs(int sr, int sc)
{
	queue<pii > q;
	vector<pii> vec;
	q.push(makepii(sr, sc));
	isVisited[sr][sc] = true;
	int sum = board[sr][sc];
	vec.push_back(makepii(sr, sc));
	
	while (!q.empty())
	{
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().first;
			int c = q.front().second;
			int cur = board[r][c];
			q.pop();

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];
				int next = board[nr][nc];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || isVisited[nr][nc])
					continue;

				int diff = abs(cur - next);
				if (L > diff || diff > R)
					continue;

				sum += board[nr][nc];
				isVisited[nr][nc] = true;
				q.push(makepii(nr, nc));
				vec.push_back(makepii(nr, nc));
			}
		}
	}
	if (vec.size() == 1)
		return 0;

	int average = sum / vec.size();
	for (int i = 0; i < vec.size(); i++)
		board[vec[i].first][vec[i].second] = average;

	return true;
}

int main()
{
	freopen("in.txt", "r", stdin);

	int testcase = 1;
	//scanf("%d", &testcase);
	for (int tc = 1; tc <= testcase; tc++)
	{
		scanf("%d%d%d", &N, &L, &R);

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				scanf("%d", &board[i][j]);

		int cnt = 0;
		while (true)
		{
			memset(isVisited, 0, sizeof(isVisited));
			int sum = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (!isVisited[i][j])
						sum += bfs(i, j);

			if (sum == 0)
				break;
			else
				cnt++;
		}
		printf("%d\n", cnt);
	}
}
