#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <string>
#include <cstring>

using namespace std;

int main() {
    int N, M;
	int arr[10];
    scanf("%d", &N);
	int minRange = 1e9;
	int maxRange = 0;
	for  (int i = 0; i < N; i++) {
		scanf("%d", &arr[i]);
		minRange = min(minRange, arr[i]);
		maxRange = max(maxRange, arr[i]);
	}

    int res = 1e9;
	for (int i = minRange; i <= maxRange; i++) {
		int sum = 0;
		for (int j = 0; j < N; j++) {
			sum += abs(arr[j] - i);
		}
		res = min(res, sum);
	}

    printf("%d\n", res);
    return 0;
}
