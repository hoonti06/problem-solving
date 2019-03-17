#include <cstdio>
#include <algorithm>
#include <vector>
#include <string>
#include <cstring>

using namespace std;


/*
 이 문제는 번호를 누른다는 것이 핵심이다.
 포함되는 string이 맨 앞부터 존재해야 한다는 것이다.
 일반 string compare를 통해 정렬된 상태 그대로 사용하면 된다.
 */

int main()
{
	freopen("in.txt", "r", stdin);
	
	int testcase;
	scanf("%d", &testcase);

	for (int tc = 1; tc <= testcase; tc++)
	{
		int N;
		scanf("%d", &N);

		vector<string> vec;
		char tmpcstr[20];
		for (int i = 0; i < N; i++)
		{
			scanf("%s", tmpcstr); 
			string tmpStr(tmpcstr);
			vec.push_back(tmpStr);
		}

		sort(vec.begin(), vec.end());

		bool isExist = false;
		for (int i = 0; i < vec.size()-1; i++)
		{
			if (vec[i].size() > vec[i+1].size())
				continue;

			// strstr(dest, src)
			const char* res = strstr(vec[i+1].c_str(), vec[i].c_str());
			if(!res)
				continue;
			else
			{
				isExist = true;
				break;
			}
		}
		if (isExist)
			printf("NO\n");
		else
			printf("YES\n");
	}
	return 0;
}
