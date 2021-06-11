#include <cstdio>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

int N, K, M;
vector<int> vec;

int solution() {
	int cur = -1;
	int size = N;
	int cnt = 1;
	while (size > 0) {
		cur = (cur + K) % size;
		if (vec[cur] == M) break;
		vec.erase(vec.begin() + cur);
		cur--;
		size--;
		cnt++;
	}

	return cnt;
}

int main() {
	freopen("in.txt", "r", stdin);

	scanf("%d%d%d", &N, &K, &M);
	for (int i = 1; i <= N; i++)
		vec.push_back(i);
	
	printf("%d\n", solution());
}


//#include <cstdio>
//#include <iostream>
//#include <algorithm>
//#include <vector>
//#include <queue>
//#include <cstring>
//#include <cstdlib>
//#include <cmath>
//#include <string>
//#include <set>
//
//using namespace std;
//
//#define MAX_N 100010
//
//int N, K;
//int input[MAX_N];
//
//int solution() {
//	sort(input, input + N);
//
//	int cnt = 0;
//	int minVal = input[0];
//	N = N - K;
//	cnt++;
//	K--;
//	if (N <= 0 || K <= 0) return 1;
//	
//	if (N % K != 0) cnt++;
//	cnt += N / K;
//
//	return cnt;
//}
//
//int main() {
//	freopen("in.txt", "r", stdin);
//	scanf("%d%d", &N, &K);
//	for (int i = 0; i < N; i++)
//		scanf("%d", &input[i]);
//
//	printf("%d\n", solution());
//}

