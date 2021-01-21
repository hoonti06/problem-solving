#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <algorithm>
#include <string>
#include <cstring>
#include <map>
#include <vector>
#include <queue>

#define MAX_N 105

#define SELECTED 2
#define DELETED 1
#define NOTHING 0

using namespace std;

int N;
vector<int> input;
int res = 0;

int binaryBacktracking(int idx, vector<int> flags, int sum) {
	if (idx >= N-1) {
		res = max(res, sum);
		return 0;
	}

	int curValue = input[idx];
	if (flags[curValue] == SELECTED) {
		binaryBacktracking(idx+1, flags, sum + curValue);
	} else if (flags[curValue] == DELETED) {
		binaryBacktracking(idx+1, flags, sum);
	} else {
		flags[curValue] = SELECTED;
		flags[curValue-1] = DELETED;
		flags[curValue+1] = DELETED;
		binaryBacktracking(idx+1, flags, sum + curValue);

		flags[curValue] = NOTHING;
		flags[curValue-1] = NOTHING;
		flags[curValue+1] = NOTHING;
		binaryBacktracking(idx+1, flags, sum);
	}
	return 0;
}

int main() {
    freopen("in.txt", "r", stdin);
    
    scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		int tmp;
		scanf("%d", &tmp);
		input.push_back(tmp);
	}

	vector<int> vec(MAX_N, NOTHING);
	binaryBacktracking(0, vec, 0);

	printf("%d\n", res);
}