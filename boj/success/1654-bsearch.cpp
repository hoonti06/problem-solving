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

int K, N;
long long input[MAX_K];
long long res;

void solution() {
	long long left = 1, right = 0;

	scanf("%d%d", &K, &N);
	for (int i = 0; i < K; i++) {
		scanf("%lld", &input[i]);
		right = max(right, input[i]);
	}

	while (left <= right) {
		// 더할 때 int의 범위를 넘어갈 수 있다.
		long long mid = (left + right) / 2; 

		int cnt = 0;
		for (int i = 0; i < K; i++) {
			cnt += (input[i] / mid);
		}

		if (cnt >= N) {
			left = mid+1;
			res = max(mid, res);
		}
		else
			right = mid-1;
	}
}

void print() {
	printf("%lld\n", res);
}

int main() {
	freopen("in.txt", "r", stdin);

	solution();
	print();

    return 0;
}
