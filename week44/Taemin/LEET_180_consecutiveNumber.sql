WITH T AS (
  SELECT  num,
          LEAD(num, 1) over() num1,
          LEAD(num, 2) over() num2
  FROM    logs
)

SELECT DISTINCT(num) as ConsecutiveNums
FROM T
WHERE (num = num1) AND (num = num2);
