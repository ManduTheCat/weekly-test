# Write your MySQL query statement below
# 연속해서 최소 3번 이상 나타나는 num 구하기
# LAG(컬럼명, 칸 수) OVER (PARTITION BY 컬럼명 ORDER BY 컬럼명) : 현재 행에 대해서 칸 수만큼 이전 행 탐색. 칸 수(생략 시 default_value 값을 가져옴)와 PARTITION BY(지정 시 GROUP 별로 행 값을 가져옴) 생략 가능
# 동일한 방식으로 현재 행 기준 다음 행 값을 가져오는 LEAD() 존재

with tmp as (
  select num, LAG(num, 1) OVER (ORDER BY id) as first, LAG(num, 2) OVER (ORDER BY id) as second
  from logs
)
select distinct num as ConsecutiveNums # distinct를 해줘야 연속이 4개 이상인 경우 중복 안됨.. 안해주면 3333인 경우 33이 나옴
from tmp
where tmp.num = tmp.first
and tmp.first = tmp.second 
