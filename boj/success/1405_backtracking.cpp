#include <cstdio>
#include <algorithm>

using namespace std;


#define MAX_N 15

int N;

bool isVisited[MAX_N*2][MAX_N*2];

int dx[4] = { 0,  0, 1, -1 };
int dy[4] = { 1, -1, 0,  0 };

double pb[4];

double cur_pb = 1;
double sum = 0;

bool backtracking(int r, int c, int cnt)
{
	isVisited[r][c] = true;

	if (cnt >= N)
	{
		sum += cur_pb;
		return true;
	}
	
	for (int i = 0; i < 4; i++)
	{

		int nr = r + dx[i];
		int nc = c + dy[i];

		if (isVisited[nr][nc] == true)
			continue;

		double tmp_pb = cur_pb;
		cur_pb *= pb[i];

		backtracking(nr, nc, cnt + 1);
		isVisited[nr][nc] = false;

		cur_pb = tmp_pb;
	}
	return true;
}





int main()
{
	freopen("in.txt", "r", stdin);
	
	scanf("%d", &N);
	for(int i = 0; i < 4; i++)
	{
		int tmp;
		scanf("%d", &tmp);
		pb[i] = tmp / 100.0;
	}
	
	backtracking(14, 14, 0);

	printf("%lf", sum);
}
