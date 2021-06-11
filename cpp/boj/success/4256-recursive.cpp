#include <cstdio>
#include <algorithm>
#include <cstring>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

#define MAX_N 1005

int n;

int pre[MAX_N];
int in[MAX_N];
int post[MAX_N]; 

stack<int> st;

/*
 * preorder(Root, l, r), inorder(l, Root, r), post(l, r, Root)
 *
 * pre에서 맨 처음이 Root라는 것으로 inorder에서 Root를 찾아 l과 r의 위치와 길이를 구한다.
 * post는 <l, r, Root>의 순서이기 때문에 root를 stack에 넣고, <r, l>의 순서로 재귀함수를 호출한다.
 */
int recur(int preS, int inS, int size)
{
	if (size <= 0)
		return 0;

	int root = pre[preS];
	st.push(pre[preS]);

	if (size == 1)
		return 0;

	int i;
	for (i = 0; i < size; i++)
		if (in[inS + i] == root)
			break;

	//right
	recur(preS + i+1, inS + i+1, size - (i+1));
	//left
	recur(preS + 1, inS, i);
}

int main()
{
	freopen("in.txt", "r", stdin);

	int T;
	scanf("%d", &T);
	for (int testcase = 0; testcase < T; testcase++)
	{
		memset(post, 0, sizeof(post));
		scanf("%d", &n);
		
		for (int i = 0; i < n; i++)
			scanf("%d", &pre[i]);
		for (int i = 0; i < n; i++)
			scanf("%d", &in[i]);

		recur(0, 0, n);

		while (!st.empty())
		{
			printf("%d ", st.top());
			st.pop();
		}
		printf("\n");
	}
}
