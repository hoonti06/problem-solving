#include<cstdio>
#include<iostream>
#include<algorithm>
#include<vector>
#include<cstring>
#include<cmath>
#include<string>
#include<queue>

#define MAX_N 15
#define MAX_LEN 8

using namespace std;

int N;
string word[MAX_N];
int weight[26];
int num[26];
priority_queue<pair<int, int> > pq;

int main() {
	freopen("in.txt", "r", stdin);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> word[i];

		int power = pow(10, word[i].size());
		for (int j = 0; j < word[i].size(); j++) {
			char curAlphabet = word[i][j];
			weight[curAlphabet - 'A'] += power;
			power /= 10;
		}
	}
	for (int i = 0; i < 26; i++)
		pq.push(make_pair(weight[i], i));

	int curNum = 9;
	while (!pq.empty()) {
		num[pq.top().second] = curNum;

		pq.pop();
		curNum--;
	}
	
	int sum = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < word[i].size(); j++) {
			char curAlphabet = word[i][j];
			word[i][j] = num[word[i][j] - 'A'] + '0';
		}
		sum += stoi(word[i]);
	}

	cout << sum << endl;
    return 0;
}

