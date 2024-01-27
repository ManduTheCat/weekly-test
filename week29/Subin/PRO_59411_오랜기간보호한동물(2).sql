SELECT AI.ANIMAL_ID, AO.NAME
FROM ANIMAL_INS AI
JOIN ANIMAL_OUTS AO ON AI.ANIMAL_ID = AO.ANIMAL_ID
ORDER BY DATEDIFF(AO.DATETIME, AI.DATETIME) DESC
LIMIT 2
;
