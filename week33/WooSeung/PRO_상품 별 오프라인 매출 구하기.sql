-- 코드를 입력하세요
SELECT PRODUCT_CODE, SUM(PRICE * SALES_AMOUNT) AS SALES
FROM PRODUCT AS P
JOIN OFFLINE_SALE AS O
ON P.PRODUCT_ID = O.PRODUCT_ID
GROUP BY PRODUCT_CODE
ORDER BY SALES DESC, PRODUCT_CODE ASC
