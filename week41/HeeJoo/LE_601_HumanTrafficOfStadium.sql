# 3번 이상 연속된 아이디, 100명 이상의 인원
# visit_date asc
# 1. 100명 이상
# 2. 연속 id

with tmp as (
  # (id - rnk)로 연속덩어리 구분
  # 예제의 경우 (id, rnk)가 (2, 1), (3, 2), (5, 3), (6, 4), (7, 5), (8, 6)
  # piece -> 1, 1 / 2, 2, 2, 2
  SELECT id, visit_date, people, rnk, (id - rnk) AS piece
  FROM (
    # 인원이 100명 이상인 행
    # RANK() OVER은 동일 순위인 경우 1, 1, 2가 아닌 1, 1, 3으로 건너뜀
    SELECT id, visit_date, people, RANK() OVER(ORDER BY id) AS rnk
    FROM stadium
    WHERE people >= 100
    )  AS tmp2
)
SELECT id, visit_date, people
FROM tmp
WHERE piece IN (
  SELECT piece
  FROM tmp
  GROUP BY piece
  HAVING COUNT(*) >= 3
)
