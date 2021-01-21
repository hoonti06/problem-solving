#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
#include <cstdlib>
#include <cmath>
#include <string>
#include <set>

using namespace std;

#define MAX_N 105

int N;
int tc;
bool isVisited[MAX_N * MAX_N];
int input[MAX_N];
vector<int> saved;

void init() {
	memset(isVisited, 0, sizeof(isVisited));
	saved.clear();

	scanf("%d", &N);
	for (int i = 0; i < N; i++)
		scanf("%d", &input[i]);
}

void solution() {
	saved.push_back(0);
	for (int i = 0; i < N; i++) {
		int cur = input[i];

		int savedSize = saved.size();
		for (int j = 0; j < savedSize; j++) {
			int next = cur + saved[j];
			if (isVisited[next])
				continue;

			isVisited[next] = true;
			saved.push_back(next);
		}
	}

}

void print() {
	printf("#%d %lu\n", tc, saved.size());
}

int main() {
	int testcase;
	scanf("%d", &testcase);
	for (tc = 1; tc <= testcase; tc++) {
		init();
		solution();
		print();
	}
}
