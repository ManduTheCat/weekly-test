-- 리뷰를 가장 많이 작성한 회원의 리뷰들을 조회하는 SQL문 작성
-- 회원 이름, 리뷰 텍스트, 리뷰 작성일이 출력되야한다.
-- 리뷰 작성일을 기준으로 오름차순 ASC 리뷰 작성일이 같으면 리뷰 텍스트를 기준으로 ASC로 정렬


-- 코드를 입력하세요
SELECT p.MEMBER_NAME, r.REVIEW_TEXT, DATE_FORMAT(REVIEW_DATE, "%Y-%m-%d")
FROM REST_REVIEW as r
JOIN MEMBER_PROFILE as p
ON r.MEMBER_ID = p.MEMBER_ID
WHERE p.MEMBER_ID = (
    SELECT MEMBER_ID
    FROM REST_REVIEW
    GROUP BY MEMBER_ID
    ORDER BY COUNT (*) DESC
    LIMIT 1
)
ORDER BY REVIEW_DATE ASC, REVIEW_TEXT ASC;
