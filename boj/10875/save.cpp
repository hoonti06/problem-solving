#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>

using namespace std;

#define MAX_N 10005

int N, M;

map<int, int> snake;
vector<pair<int, char> > order;

int dx[4] = { 0, 1,  0, -1 };
int dy[4] = { 1, 0, -1,  0 };

int move(int sr, int sc)
{
	int cnt = 1;
	int oIdx = 0;

	int r = sr, c = sc;
	int dir = 0;

//	int aa = N;
	while(true)
	{
		r += dx[dir];
		c += dy[dir];
//		printf("%d, %d\n", r, c);
		
		if (r < 0 || r > 2*N || c < 0 || c > 2*N)
			break;
		if (snake[r*N + c] == 1)
			break;

		snake[r*N + c] = 1;

		if (cnt == order[oIdx].first)
		{
			if (order[oIdx].second == 'R')
				dir = (dir + 1) % 4;
			else 
				dir = (dir + 3) % 4;

			oIdx++;
		}
		cnt++;
	}
	return cnt;
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d", &N, &M);
	int t = 0;
	for (int i = 0; i < M; i++)
	{
		int a; char b;
		scanf("%d %c", &a, &b);
		t += a;
		order.push_back(make_pair(t, b));
	}
	
//	for (int i = 0; i < order.size(); i++)
//		printf("%d, %c\n", order[i].first, order[i].second);


	int res = move(N, N);
//	printf("res : ");
	printf("%d", res);
	


	return 0;
}
