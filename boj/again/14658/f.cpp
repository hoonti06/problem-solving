#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

#define MAX_N 100005

int N, M, L, K;

struct star
{
	int r, c;
	star(int rr, int cc)
	{
		r = rr;
		c = cc;
	}
};

vector <struct star> vec;

int compare(struct star s1, struct star s2)
{
	if (s1.r == s2.r)
		return s1.c < s2.c;

	else
		return s1.r < s2.r;
}
//int arr[MAX_N][MAX_N];

//int res;

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%d%d%d%d", &N, &M, &L, &K);
	for(int i = 0; i < K; i++)
	{
		int r, c;
		scanf("%d%d", &r, &c);
		struct star tmp(r, c);
		vec.push_back(tmp);
//		map[r][c] = true;
//		input[r].push_back(c);
	}
//	for (int i = 0; i <= N; i++)
//		sum[i][0] = map[i][0];
	
//	for (int i = 0; i <= N; i++)
//	{
//		for (int j = 1; j <= M; j++)
//		{
//			sum[i][j] += sum[i][j - 1] + map[i][j];
//		}
//
//	}
//
	sort(vec.begin(), vec.end(), compare);

	for (int i = 0; i < vec.size(); i++)
	{
		printf("%d %d\n", vec[i].r, vec[i].c);


	}



//	printf("%d", res);




	return 0;
}
