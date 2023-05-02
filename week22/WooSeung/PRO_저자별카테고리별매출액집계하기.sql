-- 코드를 입력하세요
SELECT b.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, SUM(s.SALES * b.PRICE) AS SALES
FROM BOOK_SALES AS s
JOIN BOOK AS b 
ON s.BOOK_ID = b.BOOK_ID
JOIN AUTHOR AS a
ON b.AUTHOR_ID = a.AUTHOR_ID
WHERE YEAR(s.SALES_DATE) = 2022 AND MONTH(s.SALES_DATE) = 1
GROUP BY AUTHOR_ID, CATEGORY
ORDER BY AUTHOR_ID ASC, CATEGORY DESC;
