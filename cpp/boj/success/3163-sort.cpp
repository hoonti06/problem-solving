#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

#define MAX_N 10005

vector<pair<int, int> > vec;
vector<int> order;

int L;

bool cmp(pair<int, int> x, pair<int, int> y)
{
	int a = x.second > 0? L - x.first : x.first;
	int b = y.second > 0? L - y.first : y.first;
	return a < b;
}

bool cmp2(pair<int, int> x, pair<int, int> y)
{
	if (x.first == y.first)
		return x.second < y.second;
	else
		return x.first < y.first;
}

/* 
 * 사실 개미가 위치하는 순서는 변하지 않는다.
 *
 * 양 옆으로 가는 거리로 오름차순 정렬을 한다.
 * 왼쪽으로 도착하면 처음 개미의 순서에서 왼쪽부터,
 * 오른쪽으로 도착하면 처음 개미의 순서에서 오른쪽부터 지워나간다.
 *
 * 다시 정렬할 때, 거리가 같으면 더 낮은 ID가 먼저 정렬될 수 있도록 한다.
 */

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 0; testcase < T; testcase++)
	{
		vec.clear();
		order.clear();

		int N, K;
		scanf("%d%d%d", &N, &L, &K);
		vec.resize(N);
		order.resize(N);
		
		for (int i = 0; i < N; i++)
		{
			int p, a;
			scanf("%d%d", &p, &a);
			vec[i].first = p;
			vec[i].second = a;

			order[i] = a;
		}
		sort(vec.begin(), vec.end(), cmp);

		vector<pair<int, int> > ans;
		int left = 0, right = N-1;

		for (int i = 0; i < N; i++)
		{
			if (vec[i].second > 0)
			{
				ans.push_back(make_pair(L - vec[i].first, order[right]));
				right--;
			}
			else
			{
				ans.push_back(make_pair(vec[i].first, order[left]));
				left++;
			}
		}
		sort(ans.begin(), ans.end(), cmp2);
		printf("%d\n", ans[K-1].second);
	}
	return 0;
}
