#include <cstdio>


int a[50][50];
int c[100], d[100];
int n, N;
int ret = 0;

void backtr(int sum, int kk) {
    if (sum == N) {
        if (ret < kk) ret = kk;
        return;
    }
    if (N - sum + kk <= ret) return;

    if (c[sum] == 0) {
        backtr(sum + 1, kk);	
    } else {
        for (int i = 0; i < n; i++) {
            if (sum - i >= n && sum - i < 0) continue;
            if (a[i][sum - i] == 1 && d[i - (sum - i) + n] == 0) {
                a[i][sum - i] = 2;
                d[i - (sum - i) + n] = 1;
                backtr(sum + 1, kk + 1);
                d[i - (sum - i) + n] = 0;
                a[i][sum - i] = 1;
            }
        }
        backtr(sum + 1, kk);
    }
}

int main() {
    freopen("in.txt", "r", stdin);
    scanf("%d", &n); N = 2 * n - 1;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) {
            scanf("%d", &a[i][j]);
            if (a[i][j] == 1) c[i + j] = 1;
        }

    backtr(0, 0);
    printf("%d\n", ret);
    return 0;
}

