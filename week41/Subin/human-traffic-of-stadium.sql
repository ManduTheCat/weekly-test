SELECT s.id, s.visit_date, s.people
FROM Stadium s
INNER JOIN (
    # cons 최댓값으로 맞춰주기
    SELECT a.id, 
            IF(id = @prev - 1, @cons := @cons, @cons := a.cons) as cons, @prev := id
    FROM (
        # consecutive id's 개수 구하기 (cons)
        SELECT s.id, 
                IF(id = @prev + 1, @cons := @cons + 1, @cons := 1) as cons, @prev := id
        FROM Stadium s
        WHERE people >= 100
    ) a
    ORDER BY a.id desc
) a ON s.id = a.id
WHERE a.cons >= 3 # 3개 이상 연속되는 경우만 선택
ORDER BY visit_date ASC
;
