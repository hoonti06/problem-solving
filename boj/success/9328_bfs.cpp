#include <cstdio>
#include <algorithm>
#include <cstring>
#include <queue>
#include <cctype>

using namespace std;

#define MAX_N 102

#define WALL '*'
#define PAPER '$'

int N, M;
char map[MAX_N][MAX_N];
bool isVisited[MAX_N][MAX_N];

int dx[4] = {  0, -1, 0, 1 };
int dy[4] = { -1,  0, 1, 0 };

bool key[26];	// key가 존재하면 true

queue<pair<int, int> > q;

/*
 * 시작점이 여러 곳 있을 수 있다.
 * 시작점에 대문자, 소문자, 문서가 올 수 있기 때문에
 * 다음 위치(nr, nc)가 아닌, 현재 위치(r, c)에서 방문 여부나 대문자, 소문자 여부 등을 확인하여야 한다.
 *
 * 현재 위치(r, c)가 대문자라면 key가 있는지를 확인한다. key가 없으면 다시 queue에 push하여 다음 차례를 기다린다.
 * 대문자의 여부는 방문을 한 적이 있는지의 여부를 확인하기 전에 이루어져야 한다.
 *
 * 현재 위치가 소문자라면 해당 key값을 true로 바꾸고 계속 진행한다.
 *
 * 갈 수 있는 곳을 전부 다 갔지만 queue에는 아직 key가 없어 진행하지 못하는 대문자가 존재해 무한루프에 빠진다.
 * 이를 방지하기 위해 한 턴에 queue에 저장된 위치개수와 진행하지 못하는 대문자의 개수가 같다면 
 * 더 이상 진행할 곳이 없기 때문에 그대로 paper의 개수를 리턴하고 종료한다.
 */

int bfs()
{
	int pCnt = 0;	// paper 개수
	while(!q.empty())
	{
		int capCnt = 0;	// 대문자 개수(종료 조건 위한 변수)

		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++)
		{
			int r = q.front().first;
			int c = q.front().second;
			q.pop();

			if ('A' <= map[r][c] && map[r][c] <= 'Z')
			{
				if (key[tolower(map[r][c])-'a'] == false)
				{
					capCnt++;
					q.push(make_pair(r, c));
					continue;
				}
			}
			if(isVisited[r][c] == true)
				continue;
			isVisited[r][c] = true;

			if ('a' <= map[r][c] && map[r][c] <= 'z')
				key[map[r][c]-'a'] = true;
			else if (map[r][c] == PAPER)
				pCnt++;

			for (int i = 0; i < 4; i++)
			{
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || isVisited[nr][nc])
					continue;
				if (map[nr][nc] == WALL)
					continue;

				q.push(make_pair(nr, nc));
			}
		}
		if (capCnt == qSize)
			return pCnt;
	}
	return pCnt;
}

int main()
{
	freopen("in2.txt", "r", stdin);

	int T, testcase;

	scanf("%d", &T);
	for (testcase = 0; testcase < T; testcase++)
	{
		//init
		memset(isVisited, 0, sizeof(isVisited));
		memset(key, 0, sizeof(key));
		while(!q.empty())
			q.pop();
		
		scanf("%d%d", &N, &M);
		for (int i = 0; i < N; i++)
			scanf("%s", map[i]);

		for (int i = 0; i < N; i++)
		{
			if (map[i][0] != WALL)
				q.push(make_pair(i, 0));

			if (map[i][M-1] != WALL)
				q.push(make_pair(i, M-1));
		}

		// queue에 시작점 push
		for (int i = 0; i < M; i++)
		{
			if (map[0][i] != WALL)
				q.push(make_pair(0, i));

			if (map[N-1][i] != WALL)
				q.push(make_pair(N-1, i));
		}

		char tmp[26];
		scanf("%s", tmp);
		if (tmp[0] != '0')
			for(int i = 0; i < strlen(tmp); i++)
				key[tmp[i]-'a'] = true;
			
		printf("%d\n", bfs());
	}
	return 0;
}
