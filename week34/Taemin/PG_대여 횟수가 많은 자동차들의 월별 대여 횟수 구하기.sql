SELECT 
    MONTH(START_DATE) AS MONTH,
    CAR_ID,
    COUNT(HISTORY_ID) AS RECORDS
FROM
    CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE
    CAR_ID IN (
                SELECT
                    CAR_ID
                FROM
                    CAR_RENTAL_COMPANY_RENTAL_HISTORY
                WHERE
                    START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
                GROUP BY 
                    CAR_ID
                HAVING
                    COUNT(HISTORY_ID) >= 5
              )
    AND START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
GROUP BY
    MONTH, CAR_ID
ORDER BY
    MONTH ASC, CAR_ID DESC
