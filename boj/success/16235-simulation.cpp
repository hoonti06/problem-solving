#include <cstdio>
#include <algorithm>
#include <vector>
#include <deque>
#include <queue>
#include <cstring>
#include <string>

using namespace std;

#define MAX_N 12

int A[MAX_N][MAX_N];
int board[MAX_N][MAX_N];

int N, M, K;
int dx[8] = { -1,  0, 1, 0, -1, -1,  1, 1 };
int dy[8] = {  0, -1, 0, 1, -1,  1, -1, 1 };

vector<int> vec[MAX_N][MAX_N];

void init() {
	scanf("%d%d%d", &N, &M, &K);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &A[i][j]);
			board[i][j] = 5;
		}
	}

	for (int i = 0; i < M; i++) {
		int x, y, z;
		scanf("%d%d%d", &x, &y, &z);
		vec[x-1][y-1].push_back(z);
	}
}

int solution() {
	while (K--) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sort(vec[i][j].begin(), vec[i][j].end());

				int size = vec[i][j].size();
				for (int k = 0; k < size; k++) {
					// spring
					if (board[i][j] >= vec[i][j][k]) {
						board[i][j] -= vec[i][j][k];
						vec[i][j][k]++;
					}
					// summer
					else {
						for (int s = size-1; s >= k; s--) {
							int lastIdx = vec[i][j].size()-1;
							board[i][j] += vec[i][j][lastIdx]/2;
							vec[i][j].pop_back();
						}
						break;
					}
				}
			}
		}

		// fall
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;

				int size = vec[i][j].size();
				for (int k = 0; k < size; k++) {
					if (vec[i][j][k] % 5 == 0){
						cnt++;
					}
				}

				if (cnt > 0) {
					for (int k = 0; k < 8; k++) {
						int nr = i + dx[k];
						int nc = j + dy[k];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;

						for (int s = 0; s < cnt; s++)
							vec[nr][nc].push_back(1);
					}
				}
			}
		}

		// winter
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] += A[i][j];
			}
		}
	}
	int res = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			res += vec[i][j].size();
		}
	}
	return res;
}

int main() {
	freopen("in.txt", "r", stdin);

	int testcase = 1;
	//scanf("%d", &testcase);
	for (int tc = 1; tc <= testcase; tc++) {
		init();

		printf("%d\n", solution());
	}
}
