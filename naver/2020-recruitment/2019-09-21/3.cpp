#include <cstdio>
#include <algorithm>
#include <cstring>
#include <string>
#include <iostream>
#include <vector>
#include <stack>
#include <map>
#include <cmath>
#include <queue>

using namespace std;

class print
{
public:
   int id, time, cnt;
   print() 
   {
      id = time = cnt = 0;
   }
   print(int _i, int _t, int _c)
   {
      id = _i;
      time = _t;
      cnt = _c;
   }
   bool operator<(const print &b) const
   {
      if (this->cnt == b.cnt)
         return this->id > b.id;
      else
         return this->cnt > b.cnt;
   }
};

vector<int> solution(vector<vector<int>> data)
{
   vector<int> res;
   priority_queue<print> pq;

   int i = 0;
   int t = 0;
   while (true)
   {
      while (i < data.size())
      {
         int dataId = data[i][0];
         int dataTime = data[i][1];
         int dataCnt = data[i][2];

         if (t >= dataTime)
         {
            print newNod(dataId, dataTime, dataCnt);
            pq.push(newNod);
            i++;
         }
         else
            break;
      }
      if (pq.empty())
         t++;
      else
      {
         print curNod = pq.top();
         pq.pop();

         t += curNod.cnt;
         res.push_back(curNod.id);
      }
      if (i >= data.size())
         break;
   }
   while (!pq.empty())
   {
      print curNod = pq.top();
      pq.pop();

      t += curNod.cnt;
      res.push_back(curNod.id);
   }
   return res;
}

int main()
{
   freopen("in3.txt", "r", stdin);

   int testcase;
   cin >> testcase;
   for (int tc = 1; tc <= testcase; tc++)
   {
      int N;
      cin >> N;
      vector<vector<int>> vec(N);
      for (int i = 0; i < N; i++)
      {
         vec[i].resize(3);
         for (int j = 0; j < 3; j++)
            cin >> vec[i][j];
      }

      solution(vec);
   }
}
