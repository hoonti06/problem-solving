#include <cstdio>
#include <algorithm>
#include <iostream>
#include <cstring>
#include <vector>
#include <functional>
#include <string>
#include <cstring>
#include <climits>
#include <cmath>
#include <map>

using namespace std;

#define MAX_N 1010
#define ll long long

int T, N, M;
int a[MAX_N], b[MAX_N];
int aSum[MAX_N], bSum[MAX_N];
map<ll, int> aMap, bMap;
vector<int, int> aVec, bVec;

int bsearch(vector<pair<ll, int> > &vec, int goal) {
	int left = 0;
	int right = vec.size()-1;
	while (left <= right) {
		int mid = (left + right) / 2;
		if (vec[mid].first == goal) {
			return vec[mid].second;
		}
		else if (goal < vec[mid].first) {
			right = mid - 1;
		}
		else {
			left = mid + 1;
		}
	}
	return 0;
}

int solution() {
	for (int i = 1; i <= N; i++) {
//		int sum = a[i]; aVec.push_back(a[i]);
		int sum = 0;
		for (int j = i; j <= N; j++) {
			sum += a[i];
			aVec.push(sum);
		}
	}



	for (int i = 1; i <= N; i++) {
		for (int j = i+1; j <= N; j++) {
			aSum[j] -= a[i];
			aMap[aSum[j]]++;
		}
	}
	for (int i = 1; i <= M; i++) {
		for (int j = i+1; j <= M; j++) {
			bSum[j] -= b[i];
			bMap[bSum[j]]++;
		}
	}

	map<ll, int>::iterator bit;
	vector<pair<ll, int> > vec;
	int idx = 0;
	for (bit = bMap.begin(); bit != bMap.end(); bit++) {
		int bSubSum = bit->first;
		int bCnt = bit->second;
		vec.push_back(make_pair(bSubSum, bCnt));
	}

//	printf("vec\n");
//	sort(vec.begin(), vec.end());
//	for (int i = 0; i < vec.size(); i++) {
//		printf("%d ", vec[i].first);
//	}
//	printf("\n");
//

	ll cnt = 0;
	map<ll, int>::iterator ait;
	for (ait = aMap.begin(); ait != aMap.end(); ait++) {
		int aSubSum = ait->first;
		int aCnt = ait->second;
		int ret = bsearch(vec, T - aSubSum);
		cnt += (aCnt * ret);
//		map<int, int>::iterator bit;
//		for (bit = bMap.begin(); bit != bMap.end(); bit++) {
//			int aSubSum = ait->first;
//			int aCnt = ait->second;
//
//			int bSubSum = bit->first;
//			int bCnt = bit->second;
//			if (aSubSum + bSubSum == T) {
//				cnt += (aCnt * bCnt);
//				break;
//			}
//		}
	}
	return cnt;
}


int main() {
	freopen("in.txt", "r", stdin);

	scanf("%d", &T);
	scanf("%d", &N);
	for (int i = 1; i <= N; i++) {
		scanf("%d", &a[i]);
		aSum[i] = aSum[i-1] + a[i];
		aMap[aSum[i]]++;
	}
	for (int i = 1;)

	scanf("%d", &M);
	for (int i = 1; i <= M; i++) {
		scanf("%d", &b[i]);
		bSum[i] = bSum[i-1] + b[i];
		bMap[bSum[i]]++;
	}

	int res = solution();
	printf("%d\n", res);
}
