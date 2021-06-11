#include<cstdio>
#include<cstring>
int s, len, dp[3001] = { 1  };
char str[3001];
int main() {
	freopen("in.txt", "r", stdin);
	scanf("%d%s", &s, str);
	len = strlen(str);
	for (int i = 0; i < len; i++) {
		for (int j = S; j >= 1; j--) {
			for (int k = 1; k <= j&&k <= 25; k++) 
			{
				dp[j] = (dp[j] + dp[j - k]) % 1000000007;
				printf("%d %d, %d\n", j, j-k, dp[j]);

			}

		}

	}
	printf("%d", dp[s]);
	return 0;
}
