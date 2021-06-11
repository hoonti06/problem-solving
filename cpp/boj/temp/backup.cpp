#include <cstdio>
#include <iostream>
#include <algorithm>
#include <cmath>
#include <string>
#include <cstring>

using namespace std;

#define MAX_N 55

int N, M, K;
int res;
//String board[MAX_N];
bool board[MAX_N][MAX_N];

void print() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << (board[i][j]? 1: 0);
		}
		cout << "\n";
	}
	cout << "\n";
}

void dfs(int c, int cnt) {
	if (c >= M) {
		if (K % 2 != cnt % 2)
			return;

		int onCnt = 0;
		for (int i = 0; i < N; i++) {
			onCnt++;
			for (int j = 0; j < M; j++) {
				if (!board[i][j]) {
					onCnt--;
					break;
				}
			}
		}
		res = max(res, onCnt);
		return;
	}

//	dfs(c+1, cnt);
//	if (cnt < K) {
//		for (int i = 0; i < N; i++)
//			board[i][c] = !board[i][c];
//		dfs(c+1, cnt+1);
//		for (int i = 0; i < N; i++)
//			board[i][c] = !board[i][c];
//	}

	
	if (cnt < K) {
		for (int i = 0; i < N; i++)
			board[i][c] = !board[i][c];
		for (int i = c+1; i <= M; i++) {
			dfs(i, cnt+1);
		for (int i = 0; i < N; i++)
			board[i][c] = !board[i][c];
		}
	}


//	for (int next = c+1; next < N; next++) {
//
//		cout << "before\n";
//		print();
//		for (int i = 0; i < N; i++)
//			board[i][next] = !board[i][next];
//		cout << "after\n";
//		print();
//
//		dfs(next, cnt+1);
//
//		for (int i = 0; i < N; i++)
//			board[i][next] = !board[i][next];
//		dfs(next, cnt);
//	}

}

int main() {
	freopen("in.txt", "r", stdin);

//	scanf("%d%d", &N, &M);
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < M; j++) {
			board[i][j] = (str[j] == '1')? true : false;
		}
//		cin >> board[i];
	}
//	print();
	cin >> K;

	dfs(0, 0);
	cout << res << "\n";
}

