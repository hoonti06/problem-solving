# -*- coding: utf-8 -*-

import os
import sys

sys.stdin = open('in.txt', 'r')

# 데이터 저장 Start
N = input()
col = sys.stdin.readline().replace('\n','').split(' ')

table = []
for i in range(N1):
    split = sys.stdin.readline().replace('\n','').split(' ')
    table.append(split)
table.sort(key=lambda x:x[0])
# 데이터 저장 End


# 연산 Start
res_table = table

for i in range(1, N):
    res_table.append(table[i])

i = 0
while i < N:
    id1 = table1[i][0]

    if id1 == 0:
        i = i + 1
    elif id1 > 1:
        i = i + 2
    else:
        i = i + 3
# 연산 End


# 결과 출력 Start
# 출력에는 sys.stdout.write와 string 변수를 통해 한 번에 출력하는 2가지 방법이 있다.
for i, item in enumerate(col):
    sys.stdout.write(item)
    if i != len(col)-1:
        sys.stdout.write(' ')
sys.stdout.write('\n')

for row in res_table:
    output = ""
    for i, item in enumerate(row):
        output = output + item
        if i != len(row)-1:
            output = output + ' '
    print(output)
# 결과 출력 End
