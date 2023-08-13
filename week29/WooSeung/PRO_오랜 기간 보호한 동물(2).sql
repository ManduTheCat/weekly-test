-- 코드를 입력하세요
-- 입양을 간 동물 중, 보호 기간이 가장 길었던 동물 두 마리의 아이디와 이름을 조회
-- ANIMAL_OUTS 테이블을 기준으로 ANIMAL_INS를 조인
SELECT outs.ANIMAL_ID, outs.NAME
FROM ANIMAL_OUTS as outs
JOIN ANIMAL_INS as ins
ON outs.ANIMAL_ID = ins.ANIMAL_ID
ORDER BY DATEDIFF(outs.DATETIME, ins.DATETIME) DESC
LIMIT 2
