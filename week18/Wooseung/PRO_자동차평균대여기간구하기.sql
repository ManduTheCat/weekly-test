-- 코드를 입력하세요
-- datediff -> 두 날짜간의 차이를 가져올 때 사용하는 함수
-- TIMESTAMPDIFF -> 두 날짜간의 차이를 구하는 것은 동일하나 연, 분기, 월, 주, 일, 시, 분, 초를 지정하여 가져올때 사용한다.
-- DATEDIFF(날짜1, 날짜2); -> 날짜1 - 날짜2 의 값을 준다.
-- ROUND(숫자, 반올림할 자릿수) -> 숫자를 반올림할 자릿수 + 1 자릿수에서 반올림한다.
SELECT CAR_ID, round(avg(datediff(END_DATE, START_DATE) + 1), 1) as AVERAGE_DURATION
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
HAVING avg(datediff(END_DATE, START_DATE) + 1) >= 7
ORDER BY AVERAGE_DURATION DESC, CAR_ID DESC
