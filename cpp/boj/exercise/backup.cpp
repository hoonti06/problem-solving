#include <cstdio>
#include <iostream>
#include <algorithm>
#include <string>
#include <functional>
#include <vector>
#include <queue>

using namespace std;

int N;
vector<pair<int, int> > vec;


struct Point {
	int x, y;
	Point(int x, int y) : x(x), y(y) {
	}
};
bool cmp(const Point &u, const Point &v) {
	if (u.x == v.x)
		return u.y < v.y;
	else
    	return u.x < v.x;
}

vector<Point> vec2;


void solution() {
	sort(vec.begin(), vec.end());

	sort(vec2.begin(), vec2.end(), cmp);


//	for (int i = 0; i < N; i++) {
//		printf("x : %d, y : %d\n", vec[i].first, vec[i].second);
//	}
//	for (int i = 0; i < N; i++) {
//		printf("x : %d, y : %d\n", vec2[i].x, vec2[i].y);
//	}



}

int main() {
	freopen("in.txt", "r", stdin);

	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		int x, y;
		scanf("%d%d", &x, &y);
		vec.push_back(make_pair(x, y));
		vec2.push_back(Point(x, y));
	}
	solution();

}
