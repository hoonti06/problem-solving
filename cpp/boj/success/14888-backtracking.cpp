#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <list>
#include <functional>
#include <cstring>
#include <cmath>
#include <stack>

using namespace std;

#define MAX_N 12

int N;

int oper[4];
int operand[MAX_N];

stack<int> st;

int maximum = -1e9, minimum = 1e9;

int backtracking(int cnt)
{
	if (cnt >= N-1)
	{
		maximum = max(maximum, st.top());
		minimum = min(minimum, st.top());
		return 0;
	}
	
	for (int i = 0; i < 4; i++)
	{
		if (oper[i] <= 0)
			continue;

		int a = st.top();
		st.pop();
		int b = st.top();
		st.pop();

		int ret;
		switch (i)
		{
			case 0:
				ret = a + b;
				break;
			case 1:
				ret = a - b;
				break;
			case 2:
				ret = a * b;
				break;
			case 3:
				ret = a / b;
				break;
		}
		st.push(ret);
		oper[i]--;
		backtracking(cnt+1);

		oper[i]++;
		st.pop();
		
		st.push(b);
		st.push(a);
	}
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 0; i < N; i++)
		scanf("%d", &operand[i]);
	for (int i = N-1; i >= 0; i--)
		st.push(operand[i]);
	for (int i = 0; i < 4; i++)
		scanf("%d", &oper[i]);

	backtracking(0);

	printf("%d\n%d", maximum, minimum);
}
