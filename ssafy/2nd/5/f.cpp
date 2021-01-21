#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <string>
#include <cstring>

using namespace std;

int main() {
    int N, M;
    int res = 0;
    scanf("%d%d", &N, &M);
    for (int i = 1; i <= N; i++) {
        string strN = to_string(i);
        for (int j = 0; j < strN.length(); j++) {
            if ('0' + M == strN[j])
                res++;
        }
    }
    printf("%d\n", res);
    return 0;
}
