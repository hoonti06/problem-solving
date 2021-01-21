#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <algorithm>
#include <string>
#include <cstring>
#include <map>
#include <queue>

using namespace std;

int res;
int N;


int bfs(string startStr) {

	queue<pair<int, string> > q;
	q.push(make_pair(-1, startStr));
	int count = 1;
	map<string, int> mp;
	while (!q.empty()) {
		int qSize = q.size();
		for (int qs = 0; qs < qSize; qs++) {
			int idx = q.front().first;
			string curStr = q.front().second;
			q.pop();

			for (int i = 0; i < N; i++) {
				if (idx == i) break;

				string nextStr = curStr;
				for (int j = i; j < i + 3 && j < N; j++) {
					nextStr[j] = (nextStr[j] == '1')? '0': '1';
				}
				if (mp[nextStr] > 0)
					break;
				mp[nextStr]++;

				bool allZero = true;
				for (int k = 0; k < N; k++) {
					if (nextStr[k] != '0') {
						allZero = false;
						break;
					}
				}
				if (allZero)
					return count;

				q.push(make_pair(i, nextStr));
			}
		}
		count++;
	}
	return count;
}

int main() {
	string str;
	cin >> str;

	N = str.length();
	cout << bfs(str) << endl;
}
