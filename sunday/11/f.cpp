#include <cstdio>
#include <algorithm>
#include <cstring>
#include <stack>

using namespace std;

#define MAX_N 105

int N;
int input[MAX_N];
int isVisited[MAX_N];
int res[MAX_N];

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 0; testcase < T; testcase++)
	{
		memset(isVisited, 0, sizeof(isVisited));

		int n;
		scanf("%d", &n);

		for(int i = 0; i < n; i++)
		{
			scanf("%d", &input[i]);
		}

		stack<int> st;
		for (int i = n-1; i >= 0; i--)
		{
			
			int cnt = 0;
			for (int j = 1; j <= n; j++)
			{
				if (isVisited[j] == false)
					cnt++;

				if (cnt > input[i])
				{
					isVisited[j] = true;
					st.push(j);
					break;
				}
			}
		}
		if (st.size() != n)
		{
			printf("IMPOSSIBLE\n");
			continue;
		}

		bool flag = true;
		for (int i = 1; i <= n; i++)
		{
			if(isVisited[i] == false)
			{
				printf("IMPOSSIBLE\n");
				flag = false;
				break;
			}
		}
		if (flag == false)
			continue;

		while(!st.empty())
		{
			printf("%d ", st.top());
			st.pop();
		}
		printf("\n");
	}

	return 0;
}
