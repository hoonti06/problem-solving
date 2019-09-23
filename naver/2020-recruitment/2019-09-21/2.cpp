#include <cstdio>
#include <algorithm>
#include <string>
#include <iostream>
#include <vector>

using namespace std;

int solution(vector<string> drum)
{
   int answer = 0;

   int N = drum.size();
   for (int j = 0; j < N; j++)
   {
      int r = 0;
      int c = j;
      int starCnt = 0;
      while (r < N && c < N)
      {
         if (r < 0 || r >= N || c < 0 || c >= N)
            break;

         int cur = drum[r][c];
         switch (cur)
         {
         case '>':
            c++;
            break;
         case '<':
            c--;
            break;
         case '#':
            r++;
            break;
         }
         if (cur == '*')
         {
            if (starCnt < 1)
            {
               r++;
               starCnt++;
            }
            else
               break;
         }
      }
      if (r >= N && c < N)
      {
         answer++;
         cout << j << endl;
      }
   }
   return answer;
}

int main()
{
   freopen("in2.txt", "r", stdin);

   int N;
   cin >> N;
   vector<string> vec(N);

   for (int i = 0; i < N; i++)
      cin >> vec[i];

   for (int i = 0; i < N; i++)
      cout << vec[i] << endl;

   cout << solution(vec) << endl;
}
