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

using namespace std;

int main() {
    freopen("in.txt", "r", stdin);
    
    int N, M;
    scanf("%d%d", &N, &M);
    vector<vector<int> > vec(100);

    for (int i = 0; i < M; i++) {
        int a, b;
        scanf("%d%d", &a, &b);
        vec[a].push_back(b);
        vec[b].push_back(a);
        cout << a << ", " << b << endl;
    }
    for (int i = 1; i <= N; i++) {
        for (int j = 0; j < vec[i].size(); j++) {
            cout << vec[i][j] << " ";
        }
        cout << endl;
    }
    
    int res = 0;
    for (int i = 1; i <= N; i++) {
        map<int, int> myFriendMap;
        for (int j = 0; j < vec[i].size(); j++) {
            int myFriend = vec[i][j];
            myFriendMap[myFriend] = 1;
        }

        int cnt = 0;
        for (int j = 0; j < vec[i].size(); j++) {
            int myFriend = vec[i][j];
            for (int k = 0; k < vec[myFriend].size(); k++) {
                int ff = vec[myFriend][k];
                if (myFriendMap[ff] == 0) {
                    myFriendMap[ff] = 1;
                    cnt++;
                }
            }
        }
        res = max(res, cnt);
    }
    cout << res << endl;
}
