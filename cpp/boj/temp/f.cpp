#include <cstdio>
#include <algorithm>
#include <iostream>
#include <cstring>
#include <vector>
#include <functional>
#include <string>
#include <climits>
#include <cmath>
#include <map>
#include <stack>

using namespace std;

#define MAX_N 2000000001
#define ll long long

int N, M;
stack<int> st;

void solution() {
	do {
		if (N < 0) {
			int rem = 0;
			if (abs(N) % 2 == 1) {
				N--;
				rem = 1;
			}
			st.push(rem);

			N = N / -2;
		}
		else if (N > 0) {
			st.push(N % -2);
			N = N / -2;
		} 
		else
			st.push(0);
	} while (N != 0);
}


int main() {
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
//	if (N == 0) {
//		printf("0");
//		return 0;
//	}

	solution();
	while (!st.empty()) {
		printf("%d", st.top());
		st.pop();
	}
}

//#include <cstdio>
//#include <algorithm>
//#include <iostream>
//#include <cstring>
//#include <vector>
//#include <functional>
//#include <string>
//#include <climits>
//#include <cmath>
//#include <map>
//
//using namespace std;
//
//#define MAX_N 2000000001
//#define ll long long
//
//int N, M;
//
//ll cal(int num, int div) {
//	ll cnt = 0;
//	while (num > 0) {
//		cnt += num / div;
//		num /= div;
//	}
//	return cnt;
//}
//
//ll solution() {
//	int num = N;
//	ll cnt2 = 0, cnt5 = 0;
//	cnt2 = cal(N, 2);
//	cnt5 = cal(N, 5);
//
//	cnt2 -= cal(M, 2);
//	cnt5 -= cal(M, 5);
//
//	cnt2 -= cal(N-M, 2);
//	cnt5 -= cal(N-M, 5);
//
//	return min(cnt2, cnt5);
//}
//
//
//int main() {
//	freopen("in.txt", "r", stdin);
//
//	scanf("%d%d", &N, &M);
//	printf("%lld\n", solution());
//}
