-- 코드를 입력하세요
SELECT YEAR(SALES_DATE) YEAR, MONTH(SALES_DATE) MONTH, B.GENDER, COUNT(DISTINCT A.USER_ID) USERS
FROM ONLINE_SALE A
INNER JOIN USER_INFO B ON A.USER_ID = B.USER_ID
WHERE B.GENDER IS NOT NULL
GROUP BY YEAR(A.SALES_DATE), MONTH(A.SALES_DATE), B.GENDER
ORDER BY YEAR ASC, MONTH ASC, GENDER ASC
;
