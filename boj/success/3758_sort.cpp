#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>

using namespace std;

#define MAX_N 105

int N;
int score[MAX_N][MAX_N]; // score[i][j] : i팀의 j문제 성공 점수

struct team 
{
	int num;
	int sum;
	int cnt;
	int late;
};

vector<struct team> vec;

bool cmp(struct team x, struct team y)
{
	if (x.sum == y.sum)
	{
		if (y.cnt == x.cnt)
			return y.late > x.late;
		else
			return y.cnt > x.cnt;
	}
	else
		return x.sum > y.sum;
}

/* 
 * score를 더 큰 값으로 갱신한다.
 * score의 합(sum)이 같으면 제출 횟수(cnt)로,
 * 제출 횟수(cnt)도 같으면 제출 시간(late)로 정렬한다.
 */

int main()
{
	freopen("in.txt", "r", stdin);
	
	int T;
	scanf("%d", &T);
	for (int testcase = 0; testcase < T; testcase++)
	{
		vec.clear();
		memset(score, 0, sizeof(score));
		int n, k, t, m;
		scanf("%d%d%d%d", &n, &k, &t, &m);
		vec.resize(n+1);
		for (int i = 1; i <= n; i++)
		{
			vec[i].sum = 0;
			vec[i].cnt = 0;
			vec[i].num = i;
		}

		for (int i = 0; i < m; i++)
		{
			int a, b, s;
			scanf("%d%d%d", &a, &b, &s);
			score[a][b] = max(score[a][b], s);

			vec[a].late = i;
			vec[a].cnt++;
		}

		for (int i = 1; i <= n; i++)
			for (int y = 1; y <= k; y++)
				vec[i].sum += score[i][y];

		sort(vec.begin(), vec.end(), cmp);

		for (int i = 0; i < vec.size(); i++)
		{
			if (vec[i].num == t)
			{
				printf("%d\n", i + 1);
				break;
			}
		}
	}
	return 0;
}
