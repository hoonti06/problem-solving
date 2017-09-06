#include <cstdio>
#include <iostream>
#include <algorithm>
#include <stack>

using namespace std;

#define MAX_N 10005

int N;

string str;



int main()
{
	freopen("in.txt", "r", stdin);

	int T;
//	scanf("%d", &T);
	cin >> T;
	for (int testcase = 0; testcase < T; testcase++)
	{
		cin >> str;
		stack<char> st;

		int flag = true;
		for (int i = 0; i < str.length(); i++)
		{
			if (str[i] == '(')
				st.push('(');
			else
			{
				if (st.empty())
				{
					printf("NO\n");
					flag = false;
					break;
				}
				else
				{
					st.pop();
				}
			}
		}
		if (flag == false)
			continue;
		
		if (!st.empty())
			printf("NO\n");
		else 
			printf("YES\n");
	}
	return 0;
}
