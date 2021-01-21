#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
#include <cstdlib>
//#include <cmath>
#include <string>

using namespace std;

#define MAX_N 210

int tc;
int N;
int res;
int cnt[MAX_N];

int getAsile(int num) {
	if (num % 2 == 0)
		return num/2 - 1;
	else
		return num/2;
}

void swap(int &x, int &y) {
	int tmp = x;
	x = y;
	y = tmp;
}

void solution() {
	memset(cnt, 0, sizeof(cnt));
	res = 0;
	
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		int src, dest;
		scanf("%d%d", &src, &dest);
		if (src > dest)
			swap(src, dest);

		src = getAsile(src);
		dest = getAsile(dest);
		for (int i = src; i <= dest; i++)
			cnt[i]++;
	}

	for (int i = 0; i < MAX_N; i++)
		res = max(res, cnt[i]);
}

void print() {
	printf("#%d %d\n", tc, res);
}

int main() {
	freopen("in.txt", "r", stdin);

	int testcase;
	scanf("%d", &testcase);
	for (tc = 1; tc <= testcase; tc++) {
		solution();
		print();
	}
}
