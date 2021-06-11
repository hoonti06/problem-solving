#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <set>

using namespace std;

#define MAX_N 100005
#define MAX_M 1005

int N, M, K;
//vector <int> vec[MAX_N];
//map<int, int>  mp[MAX_M];
set<int> st[MAX_N];
bool isVisited[MAX_N];

int bfs(int start)
{
	queue<int> q;
	isVisited[start] = true;
	q.push(start);

	int cnt = 1;

	while(!q.empty())
	{
		int qSize = q.size();
//		printf("cnt : %d\n", cnt);

		for (int qs = 0; qs < qSize; qs++)
		{
			int here = q.front();
			q.pop();

			if (here == N)
				return cnt;

//			for (int i = 0; i < vec[here].size(); i++)
			set<int>::iterator it;
			for (it = st[here].begin(); it != st[here].end(); it++)
//			for (int i = 0; i < vec[here].size(); i++)
			{
//				int there = vec[here][i];
				int there = *it;

				if (isVisited[there] == true)
					continue;
				isVisited[there] = true;
//				printf("%d -> %d\n", here, there);

				q.push(there);
			}
		}
		cnt++;
	}
	return -1;
}

int tmp[MAX_N];

int main()
{
	freopen("in2.txt", "r", stdin);

	scanf("%d%d%d", &N, &K, &M);
	for (int i = 0; i < M; i++)
	{
		for (int j = 0; j < K; j++)
			scanf("%d", &tmp[j]);

		for (int j = 0; j < K-1; j++)
		{
			for (int l = j+1; l < K; l++)
			{
				int here = tmp[j];
				int there = tmp[l];

//				vec[here].push_back(there);
//				vec[there].push_back(here);
				st[here].insert(there);
				st[there].insert(here);
			}
		}
	}

//	for (int i = 1; i <= N; i++)
//	{
//		sort(vec[i].begin(), vec[i].end());
//		vector<int>::iterator it;
//		it = unique(vec[i].begin(), vec[i].end());
//		vec[i].erase(it, vec[i].end());
//	}



//	it = unique(vec[1].begin(), vec[1].end());

//	for (int j = 1; j <= N; j++)
//	{
//		for (int i = 0; i < vec[j].size(); i++)
//		{
//			printf("%d ", vec[j][i]);
//		}
//		printf("\n");
//	}
//	printf("\n");

//	set<int>::iterator it;
//
//	for (int i = 1; i <= N; i++)
//	{
//		for (it = st[i].begin(); it != st[i].end(); it++)
//		{
//			printf("%d ", *it);
//
//		}
//		printf("\n");
////		for (int j = 0; j < vec[i].size(); j++)
////		{
////			printf("%d ", vec[i][j]);
////		}
////		printf("\n");
//	}

	printf("%d", bfs(1));

	return 0;
}
