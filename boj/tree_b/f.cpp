#include <cstdio>
#include <iostream>
#include <algorithm>
#include <map>
#include <cctype>

using namespace std;

#define MAX_N 10005

int N, M;
map<int, string> mp1;
map<string, int> mp2;

int main()
{
	freopen("in.txt", "r", stdin);

	cin >> N >> M;
	for (int i = 1; i <= N; i++)
	{
		string str;
		cin >> str;
		
		mp1[i] = str;
		mp2[str] = i;
	}

	for (int i = 0; i < M; i++)
	{
		string str;
		cin >> str;

		if (isdigit(str[0]))
		{
			int num = atoi(str.c_str());
			cout << mp1[num] << endl;
		}
		else
		{
			cout << mp2[str] << endl;
		}
	}
}
