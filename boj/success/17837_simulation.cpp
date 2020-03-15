#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

#define MAX_N 15
#define MAX_K 12

#define WHITE 0
#define RED 1
#define BLUE 2

#define MIN_GOAL_SIZE 4

int board[MAX_N][MAX_N];
vector<int> vec[MAX_N][MAX_N];

int N, K;

int dx[4] = {0,  0, -1, 1};
int dy[4] = {1, -1,  0, 0};

struct Marker{
	int r, c, dir, idx;
};

Marker marker[MAX_K];


int simulation()
{
	for (int cnt = 1; cnt <= 1000; cnt++)
	{
		for (int i = 0; i < K; i++)
		{
			int r = marker[i].r;
			int c = marker[i].c;
			int dir = marker[i].dir;
			int idx = marker[i].idx;

			int nr = r + dx[dir];
			int nc = c + dy[dir];
	
			if (board[nr][nc] == WHITE)
			{
				vector<int> &nextVec = vec[nr][nc];
				vector<int> &curVec = vec[r][c];

				for (int j = idx; j < curVec.size(); j++)
				{
					int curMarker = curVec[j];
					marker[curMarker].r = nr;
					marker[curMarker].c = nc;
					marker[curMarker].idx = nextVec.size();
					nextVec.push_back(curMarker);
				}
				
				curVec.erase(curVec.begin() + idx, curVec.end());
			}
			else if (board[nr][nc] == RED)
			{
				vector<int> &nextVec = vec[nr][nc];
				vector<int> &curVec = vec[r][c];

				vector<int> reverseVec;
				reverseVec.insert(reverseVec.end(), curVec.begin() + idx, curVec.end());
				reverse(reverseVec.begin(), reverseVec.end());

				for (int j = 0; j < reverseVec.size(); j++)
				{
					int curMarker = reverseVec[j];
					marker[curMarker].r = nr;
					marker[curMarker].c = nc;
					marker[curMarker].idx = nextVec.size();
					nextVec.push_back(curMarker);
				}

				curVec.erase(curVec.begin() + idx, curVec.end());
			}
			else // BLUE
			{
				int reverseDir;
				if (dir % 2 == 0)
					dir++;
				else
					dir--;

				marker[i].dir = dir;
				int pr = r + dx[dir];
				int pc = c + dy[dir];

				if (board[pr][pc] != BLUE)
					i--;

				continue;
			}

			if (vec[nr][nc].size() >= MIN_GOAL_SIZE)
				return cnt;
		}
	}
	return -1;
}


int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &K);
	for (int i = 0; i <= N+1; i++)
	{
		board[i][0] = board[0][i] = BLUE;
		board[N+1][i] = board[i][N+1] = BLUE;
	}

	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			scanf("%d", &board[i][j]);

	for (int i = 0; i < K; i++)
	{
		int r, c, d;
		scanf("%d%d%d", &r, &c, &d);

		marker[i].r = r;
		marker[i].c = c;
		marker[i].dir = d-1;
		marker[i].idx = 0;
		
		vec[r][c].push_back(i);
	}

	printf("%d\n", simulation());
	return 0;
}
