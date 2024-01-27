SELECT A.APNT_NO, P.PT_NAME, P.PT_NO, D.MCDP_CD, D.DR_NAME, A.APNT_YMD
FROM APPOINTMENT A
JOIN PATIENT P ON A.PT_NO = P.PT_NO
JOIN DOCTOR D ON A.MDDR_ID = D.DR_ID
WHERE A.APNT_CNCL_YN = 'N'
AND DATE(A.APNT_YMD) = '20220413'
AND D.MCDP_CD = 'CS'
ORDER BY A.APNT_YMD
;
