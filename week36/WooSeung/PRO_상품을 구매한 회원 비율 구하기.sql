-- 코드를 입력하세요
-- 2021년에 가입한 회원들 중 상품을 구매한 회원수 와
-- 구매한 회원의 비율 (=2021년에 가입한 회원 중 상품을 구매한 회원수 / 2021년에 가입한 전체 회원 수)
-- 구매한 회원의 비율은 소수점 두번째자리에서 반올림
-- 년을 기준으로 오름차순 정렬 년이 같다면 월을 기준으로 오름차순 정렬
SELECT DATE_FORMAT(SALES_DATE, '%Y') YEAR, 
DATE_FORMAT(SALES_DATE, '%m') MONTH, 
COUNT(DISTINCT U.USER_ID) AS PUCHASED_USERS,
ROUND(COUNT(DISTINCT U.USER_ID) /
     (SELECT COUNT(*) 
      FROM USER_INFO
      WHERE JOINED LIKE '2021%'), 1) PUCHASED_RATIO
FROM USER_INFO U
JOIN ONLINE_SALE O
ON U.USER_ID = O.USER_ID
WHERE JOINED LIKE '2021%'
GROUP BY YEAR, MONTH
ORDER BY YEAR, MONTH
