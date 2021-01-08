#include <cstdio>
#include <algorithm>

using namespace std;

int K;
bool isVisited[5][5];

int dx[4] = { 0, 1, -1,  0 };
int dy[4] = { 1, 0,  0, -1 };

int visit_num;	// 총 방문해야하는 횟수
int res;	// 결과값 

/*
 * (0, 0)과 (5, 5) 두 곳에서 동시에 뻗어나가는 형식
 * dfs 한번에 이중 for문으로 네 방향 x 네 방향, 총 16개의 방법이 생긴다.
 * dfs의 종료 검사는 다음 방문 위치가 두 곳에서 같아야 하고,
 * 현재 방문 횟수(cnt)가 총 방문해야하는 횟수-1과 같아야 한다.
 * (마지막으로 한 곳을 같이 방문해야하므로 1을 뺀다)
 *
 * 종료 검사 통과시 res 값을 하나씩 증가시키면서 
 * 마지막에 최종 결과를 출력한다.
 */

int dfs(int jr, int jc, int hr, int hc, int cnt)
{

	for (int i = 0; i < 4; i++)
	{
		int njr = jr + dx[i];
		int njc = jc + dy[i];

		if (njr < 0 || njr >= 5 || njc < 0 || njc >= 5)
			continue;

		if (isVisited[njr][njc] == true)
			continue;

		isVisited[njr][njc] = true;
		for (int j = 0; j < 4; j++)
		{
			int nhr = hr + dx[j];
			int nhc = hc + dy[j];
			
			if (nhr < 0 || nhr >= 5 || nhc < 0 || nhc >= 5)
				continue;
			if (isVisited[nhr][nhc] == true)
				continue;

			if (njr == nhr && njc == nhc)
			{
				if (cnt == visit_num - 1)
				{
					res++;
					return 0;
				}
				else
					continue;
			}

			isVisited[nhr][nhc] = true;
			dfs(njr, njc, nhr, nhc, cnt + 2);
			isVisited[njr][njc] = false;
		}
		isVisited[nhr][nhc] = false;
	}
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &K);
	for (int i = 0; i < K; i++)
	{
		int r, c;
		scanf("%d%d", &r, &c);
		isVisited[r - 1][c - 1] = true;
	}
	isVisited[0][0] = true;
	isVisited[4][4] = true;

	visit_num = 25 - K;

	dfs(0, 0, 4, 4, 2);
	printf("%d", res);

	return 0;
}
