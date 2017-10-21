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

int stairs[2][2];
int track[MAX_N];
vector<pair<int, int> > people;

int res;
bool isOne;

int simulation()
{
	queue<int> q[2];
	
	int dist[MAX_N];
	int running[2][MAX_N];

	memset(running, -1, sizeof(running));
	
	for (int i = 0; i < people.size(); i++)
	{
		dist[i] = abs(stairs[track[i]][0] - people[i].first);
		dist[i] += abs(stairs[track[i]][1] - people[i].second);
	}

	int time = 0;
	while (true)
	{
		bool isFin1 = true;
		for (int i = 0; i < people.size(); i++)
		{
			if (dist[i] != -1)
			{
				isFin1 = false;
				break;
			}
		}
		bool isFin2 = true;
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (running[i][j] != -1)
				{
					isFin2 = false;
					break;
				}
				
			}
		}
		bool isFin3 = false;
		{
			if (q[0].empty() && q[1].empty())
				isFin3 = true;
		}
		if (isFin1 && isFin2 && isFin3)
		{
			res = min(res, time);
			return 0;
		}
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (running[i][j] != -1)
				{
					running[i][j]--;
					if (running[i][j] > 0)
						continue;
					else
						running[i][j] = -1;
				}

				if (q[i].empty())
					continue;
				int cur = q[i].front();
				q[i].pop();

				running[i][j] = board[stairs[i][0]][stairs[i][1]];
			}
		}
		
		for (int i = 0; i < people.size(); i++)
		{
			if (dist[i] == -1)
				continue;

			dist[i]--;
			if (dist[i] == 0)
			{
				q[track[i]].push(i);
				dist[i] == -1;
			}
		}
		time++;
	}
}

int backtracking(int cnt)
{
	if (cnt >= people.size())
	{
		simulation();
		return 0;
	}

	track[cnt] = 0;
	backtracking(cnt+1);
	if (isOne)
		return 0;
	track[cnt] = 1;
	backtracking(cnt+1);
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);

	for (int testcase = 1; testcase <= T; testcase++)
	{
		memset(track, -1, sizeof(track));
		people.clear();
		res = 1e9;

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
		if (cnt == 1)
			isOne = true;
		else
			isOne = false;

		backtracking(0);

		printf("#%d %d\n", testcase, res);
	}
}
