#include <cstdio>
#include <algorithm>
#include <cstring>

using namespace std;

#define MAX_N 50

int N, len;

char input[MAX_N * 2];

bool used[MAX_N + 1];
int save[MAX_N + 1];

bool isFinish;

bool backtracking(int idx, int count)
{
//	printf("idx : %d\n", idx);
//
	if (isFinish)
		return true;
		
	if(count == N)
	{
		for (int i = 0; i < count; i++)
			printf("%d ", save[i]);
		isFinish = true;
		
		return true;
		
	}

	int num = input[idx] - '0';
	if (0 < num && used[num] == false)
	{
		save[count] = num;
		used[num] = true;
		
		if (backtracking(idx + 1, count + 1) == true)
			return true;
		used[num] = false;

	}
	if (!input[idx + 1])
		return false;

	num = num*10 + input[idx + 1] - '0';

	if (10 <= num && num <= N && used[num] == false)
	{
		save[count] = num;
		used[num] = true;

		if (backtracking(idx + 2, count + 1) == true)
			return true;
		used[num] = false;
	}
}

int main()
{
	freopen("in.txt", "r", stdin);

	scanf("%s", input);

	len = strlen(input);
	if (len < 10)
		N = len;
	else
		N = 9 + (len - 9) / 2;

	backtracking(0, 0);

	return 0;
}
