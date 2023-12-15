# Write your MySQL query statement below
SELECT DISTINCT a.*
FROM Stadium AS a,Stadium AS b,Stadium AS c
WHERE ((a.id + 1 = b.id AND a.id + 2 = c.id ) OR (a.id - 1 = b.id AND a.id + 1 = c.id) OR(a.id - 2 = b.id AND a.id - 1 = c.id)) AND a.people >= 100 AND b.people >= 100 AND c.people >= 100
ORDER BY a.id;
