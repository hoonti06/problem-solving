#include <cstdio>
#include <algorithm>

using namespace std;

#define MAX_N 200005
#define MAX_H 500005

int N, H;

// process 1
int up[MAX_H], down[MAX_H];
// 1 end

// process 2
int arr[MAX_H];
int min_num = 1e9;
// 2 end

int main()
{
	freopen("in.txt", "r", stdin);
	scanf("%d%d", &N, &H);
	
	// process 1
	/*
	 * 하단과 상단을 각각 up, down 이름의 배열로 나눈다.
	 * 해당 배열은 최대 높이를 크기로 하는 배열이고,
	 * 입력으로 들어온 높이를 인덱스로 하여 해당 높이를 지나가는 횟수를 값으로 저장한다.
	 * 먼저, 해당 높이에 해당하는 개수를 센 후,
	 * 낮은 높이의 수에 높은 높이의 수를 더한다. 
	 * 낮은 높이로 지나가게 되면 보다 높은 높이를 반드시 지나가야하기 때문이다.
	 * up 배열에 down 배열을 더할 때, 
	 * 동굴의 높이인 H에서 up의 [인덱스 - 1] 만큼 뺀 값의 down 배열을 더한다.
	 * 
	 * 정렬하여 가장 적게 지나가는 개수와 동일한 횟수를 출력한다.
	 */
	for(int i = 0; i < N/2; i++)
	{
		int u, d;
		scanf("%d%d", &u, &d);
		up[u]++;
		down[d]++;
	}

	// 낮은 높이를 지나게 되면 자연스럽게 높은 높이를 지나야한다.
	for (int i = H; i >= 2; i--)	
	{
		up[i-1] += up[i];		
		down[i-1] += down[i];
	}

	for (int i = 1; i <= H; i++)
		up[i] += down[H-i+1];
	up[0] = 1e9;

	sort(up, up + H + 1);

	int res = 1;
	for (int i = 1; i <= H; i++)
	{
		if (up[0] == up[i])
			res++;
		else
			break;
	}
	printf("%d %d", up[0], res);
	// 1 end

//			 OR

	// process 2
	/* 
	 * 크기를 최대 높이로 하는 배열 arr
	 * 배열 arr은 해당 높이일 때, 지나는지 여부를 증가와 감소로 나타낸다.
	 * up의 경우 동굴의 높이(H)에서 현재 높이를 뺀 높이의 count를 증가시킨다.
	 * down의 경우 현재 높이의 count를 감소시킨다.
	 * passed의 값을 N/2로 초기화하여 down의 경우를 전부 지나가게 된다고 가정한다.
	 * arr 값을 더해가면서 최솟값과 동일한 횟수를 출력한다.
	 */
	for (int i = 0; i < N/2; i++)
	{
		int up, down;
		scanf("%d%d", &up, &down);
		
		arr[H-up]++;
		arr[down]--;
	}

	int passed = N/2;
	int cnt = 1;

	for (int i = 0; i < H; i++)
	{
		passed += arr[i];
		printf("passed : %d\n", passed);
		if (passed <= min_num)
		{
			if (passed == min_num)
				cnt++;
			else
			{
				cnt = 1;
				min_num = passed;
			}
		}
	}
	printf("%d %d", min_num, cnt);
	// 2 end


	return 0;
}
