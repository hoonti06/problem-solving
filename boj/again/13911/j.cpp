#include <cstdio>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring>
#define INF 987654321
using namespace std;
int v, e, a, b, c, mx, my, x, y, res;
vector<vector<pair<int, int> > > vt;
vector<int> m;
vector<int> s;
int mdp[10010];
int sdp[10010];
int main()
{
	freopen("in.txt", "r", stdin);

    scanf("%d%d", &v, &e);
    vt.resize(v + 1);

    for (int i = 0; i < e; i++) {
        scanf("%d%d%d", &a, &b, &c);
        vt[a].push_back({ b,c });
        vt[b].push_back({ a,c });
    }
    scanf("%d%d", &mx, &x);
    for (int i = 0; i < mx; i++) {
        scanf("%d", &a);
        m.push_back(a);
    }
    scanf("%d%d", &my, &y);
    for (int i = 0; i < my; i++) {
        scanf("%d", &a);
        s.push_back(a);
    }
    memset(mdp, -1, sizeof(mdp));
    memset(sdp, -1, sizeof(sdp));
    priority_queue<pair<int, int> > pq;
    for (int i = 0; i < mx; i++) 
        pq.push({ 0,m[i] });
    while (pq.size()) {
        int here = pq.top().second;
        int cost = -pq.top().first;
		printf("here : %d cost : %d\n", here, cost);
        pq.pop();
        if (mdp[here] != -1)continue;
        mdp[here] = cost;
        for (int i = 0; i < vt[here].size(); i++) {
            if (mdp[vt[here][i].first] != -1)continue;
            pq.push({ -cost - vt[here][i].second,vt[here][i].first });
			printf("there : %d %d\n", vt[here][i].first, cost + vt[here][i].second);
        }
    }
    priority_queue<pair<int, int> > spq;
    for (int i = 0; i < my; i++) 
        spq.push({ 0,s[i] });
    while (spq.size()) {
        int here = spq.top().second;
        int cost = -spq.top().first;
        spq.pop();
        if (sdp[here] != -1)continue;
        sdp[here] = cost;
        for (int i = 0; i < vt[here].size(); i++) {
            if (sdp[vt[here][i].first] != -1)continue;
            spq.push({ -cost - vt[here][i].second,vt[here][i].first });
        }
    }
    res = INF;
    for (int i = 1; i <= v; i++) {
        if (mdp[i] == -1 || sdp[i] == -1)
            continue;
        if (mdp[i] != 0 && sdp[i] != 0 && mdp[i] <= x&&sdp[i] <= y) {
            res = min(res, mdp[i] + sdp[i]);
        }
    }
    if (res == INF)
        printf("-1");
    else
        printf("%d", res);
    return 0;
}


