#include<cstdio>
#include<iostream>
#include<algorithm>
#include<vector>
#include<cstring>
#include<cmath>
#include<string>
#include<queue>

#define MAX_K 10010

using namespace std;

int N, L;
int res;
int startNum;

void init() {
	scanf("%d%d", &N, &L);
}

void solution() {
	res = -1;
	for (int i = L; i <= 100; i++) {
		int tmp = i * (i-1) / 2;
		if (N - tmp < 0)
			break;

		int rem = (N - tmp) % i;
		if (rem == 0) {
			res = i;
			startNum = (N - tmp) / i;
			break;
		}
	}
}

void print() {
	if (res == -1)
		printf("-1");
	else
		for (int i = 0; i < res; i++)
			printf("%d ", startNum + i);
}

int main() {
	freopen("in.txt", "r", stdin);

	init();
	solution();
	print();

    return 0;
}
