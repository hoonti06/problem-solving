#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 102

int N, M, K;

int dx[4] = { -1, 1,  0, 0 };
int dy[4] = {  0, 0, -1, 1 };

int board[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N][10];

int stairs[2][2];
vector<pair<int, int> people;

struct Node
{
	int r, c, num;
	Node(int rr, int cc, int nn)
	{
		r = rr, c = cc, num = nn;
	}


};

int bfs()
{
	queue<Node> q;
	for (int i = 0; i < people.size(); i++)
	{
		Node initNod(people[i].first, people[i].second, i);
		q.push(initNod);
	}

	while (q)


}





int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);

	for (int testcase = 1; testcase <= T; testcase++)
	{
		memset(isVisited, 0, sizeof(isVisited));

		scanf("%d", &N);
		int cnt = 0;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				scanf("%d", &board[i][j]);
				if (board[i][j] == 1)
					people.push_back(make_pair(i, j));
				else if (board[i][j] > 0)
					stairs[cnt][0] = i, stairs[cnt++][1] = j;
			}

		}

	}
}
