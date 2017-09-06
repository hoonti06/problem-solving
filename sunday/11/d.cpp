#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;


int N;

vector<int> dp;
vector<int> input;

int main()
{
	freopen("in.txt", "r", stdin);

	int maximum = 0;
	scanf("%d", &N);
	for(int i = 0; i < N; i++)
	{
		int a;
		scanf("%d", &a);
		vec.push_back(a);
		maximum = max(maximum, a);
	}

	dp.push_back(0);
	dp.push_back(1);
	for (int i = 2; i < MAX_N; i++)
	{
		if (dp[i-1] + dp[i-2] > maximum)
			break;
		dp.push_back(dp[i-1] + dp[i-2]);
	}
	stack<int> st;

	for (int i = 0; i < N; i++)
	{
		while (input[i] > 0)
		{
			for (int j = dp.size(); j >= 1; j--)
			{
				if (dp[j] <= input[i])
				{
					input[i] -= dp[j];
					dp.push

				}
					





			}


		}
	}






	return 0;
}
