#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <cstring>
#include <cmath>

using namespace std;

#define MAX_N 12

int N;

int board[MAX_N][MAX_N];

vector<int> vec;

int track[MAX_N*MAX_N];
bool isVisited[MAX_N*MAX_N];

bool isVisited2[2][2*MAX_N];

int res;
int backtracking(int idx, int cnt)
{

	if (idx >= vec.size())
	{
//		if (res < cnt)
//		{
//			for (int i = 0; i < vec.size(); i++)
//			{
//				if (isVisited[i] == true)
//					printf("%d ", i);
//
//			}
//			printf("\n");
//		}

		res = max(res, cnt);
		return 0;
	}

	if (res > vec.size()-idx + cnt)
		return 0;

	int curr = vec[idx] / N;
	int curc = vec[idx] % N;
//	bool isPossible = true;


	int a = curr - curc + MAX_N;
	int b = - curc - curr + MAX_N;
	if (!isVisited2[0][a] && !isVisited2[1][b]) 
	{
		isVisited2[0][a] = true;
		isVisited2[1][b] = true;

		isVisited[idx] = true;
		backtracking(idx+1, cnt+1);
		isVisited[idx] = false;
		isVisited2[0][a] = false;
		isVisited2[1][b] = false;
	}

	backtracking(idx+1, cnt);
//	for (int i = 0; i < idx; i++)
//	{
//		if (isVisited[i] == false)
//			continue;
//
//		int r = vec[i] / N;
//		int c = vec[i] % N;
//		if (abs(curr - r) == abs(curc - c))
//		{
//			isPossible = false;
//			break;
//		}
//	}
//	if (isPossible == true)
//	{
//		isVisited[idx] = true;
//		backtracking(idx+1, cnt+1);
//	}
//
//	isVisited[idx] = false;
//	backtracking(idx+1, cnt);
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
		{
//			int a;
			scanf("%d", &board[i][j]);
			if (board[i][j] == 1)
			{
				vec.push_back(i*N + j);
			}
		}


	backtracking(0, 0);

	printf("%d", res);








}
