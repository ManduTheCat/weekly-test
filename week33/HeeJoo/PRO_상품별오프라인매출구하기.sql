-- 상품 별로 중복되지 않는 8자리 상품코드 값 / 앞 2자리는 카테고리 코드
-- 상품코드 별 매출액(판매가 * 판매량) 합계 출력
-- 매출액 내림차순  / 상품코드 오름차순

-- 1. OFFLINE_SALE에서 PRODUCT_ID를 기준으로 SALES_AMOUNT 합계 구하기
-- 2. PRODUCT_ID가 일치하는 경우 매출액 산출
SELECT P.PRODUCT_CODE, P.PRICE * SUM(S.SALES_AMOUNT) AS SALES
FROM PRODUCT P
INNER JOIN OFFLINE_SALE S
ON P.PRODUCT_ID = S.PRODUCT_ID
GROUP BY S.PRODUCT_ID
ORDER BY SALES DESC, P.PRODUCT_CODE ASC;
