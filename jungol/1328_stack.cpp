/**************************************************************
    Problem: 1328
    User: hoonti06
    Language: C++
    Result: Success
    Time:31 ms
    Memory:9812 kb
****************************************************************/
 
 
#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <stack>
#include <algorithm>
#include <cmath>
 
using namespace std; 
#define MAX_N 1000010
 
int board[MAX_N];
int dist[MAX_N]; 
 
int main(void) 
{
//  freopen("in.txt", "r", stdin);
 
    int N;
    scanf("%d", &N);
    for (int i = 1; i <= N; i++)
        scanf("%d", &board[i]);
 
    stack<int> st;
 
    for (int i = 1; i <= N; i++)
    {
        bool isPushed = false;
        while (!st.empty())
        {
            int topIdx = st.top();
            if (board[topIdx] < board[i])
            {
                st.pop();
                dist[topIdx] = i;
            }
            else
            {
                st.push(i);
                isPushed = true;
                break;
            }
        }
        if (isPushed)
            continue;
 
        st.push(i);
    }
    // 아직 stack에 남아있다면 더 큰 빌딩을 못 만난 것
    while (!st.empty())
    { 
        int idx = st.top();
        dist[idx] = 0;
        st.pop(); 
    }
    for (int i = 1; i <= N; i++)
        printf("%d\n", dist[i]);
    return 0;
}
 
//http://hancom.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=607&sca=3010
