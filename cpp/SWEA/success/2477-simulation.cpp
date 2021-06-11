#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <functional>
#include <cstring>

using namespace std;

#define MAX_N 102

int N, M, K, A, B;

int aa[11], bb[11], tt[1005];
int desk[11][2], repair[11][2];	// 0 : customer No. ,  1 : remain time
int customer[1005][2];

int simulation()
{
	int time = 0;
	queue<int> deskQ;
	int deskCnt = 1;
	queue<int> repairQ;
	int repairCnt = 1;

	memset(desk, -1, sizeof(desk));
	memset(repair, -1, sizeof(repair));
	
	while (true)
	{
		for (; deskCnt <= K; deskCnt++)
		{
			if (time == tt[deskCnt])
				deskQ.push(deskCnt);
			else
				break;
		}

		for (int i = 1; i <= N; i++)
		{
			if (desk[i][0] != -1)
			{
				desk[i][1]--;
				if (desk[i][1] > 0)
					continue;
				else
				{
					repairQ.push(desk[i][0]);
					desk[i][0] = -1;
				}
			}

			if (deskQ.empty())
				continue;

			int cur = deskQ.front();
			deskQ.pop();

			customer[cur][0] = i;
			desk[i][0] = cur;
			desk[i][1] = aa[i];
		}

		for (int i = 1; i <= M; i++)
		{
			if (repair[i][0] != -1)
			{
				repair[i][1]--;
				if (repair[i][1] > 0)
					continue;
				else
					repair[i][0] = -1;
			}

			if (repairQ.empty())
				continue;

			int cur = repairQ.front();
			repairQ.pop();

			customer[cur][1] = i;
			repair[i][0] = cur;
			repair[i][1] = bb[i];
		}

		bool isFin = true;
		for (int i = 1; i <= K; i++)
		{
			if (customer[i][1] == 0)
			{
				isFin = false;
				break;
			}
		}

		time++;
		if (isFin)
			break;
	}
}

int main()
{
	int T;
	scanf("%d", &T);

	for (int testcase = 1; testcase <= T; testcase++)
	{
		memset(aa, 0, sizeof(aa));
		memset(bb, 0, sizeof(bb));
		memset(customer, 0, sizeof(customer));

		scanf("%d%d%d%d%d", &N, &M, &K, &A, &B);

		for (int i = 1; i <= N; i++)
			scanf("%d", &aa[i]);
		for (int i = 1; i <= M; i++)
			scanf("%d", &bb[i]);
		for (int i = 1; i <= K; i++)
			scanf("%d", &tt[i]);

		simulation();

		int sum = 0;
		for (int i = 1; i <= K; i++)
			if (customer[i][0] == A && customer[i][1] == B)
				sum += i;

		printf("#%d %d\n", testcase, sum == 0? -1 : sum);
	}
}
