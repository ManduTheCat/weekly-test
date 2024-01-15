-- 입양 간 동물 중, 보호 기간이 가장 길었던 동물 두 마리의 아이디와 이름
-- 보호 기간이 긴 순으로 조회
SELECT ins.animal_id, ins.name
from animal_ins as ins
inner join animal_outs as outs
on ins.animal_id = outs.animal_id
order by (outs.datetime - ins.datetime) desc
limit 2;
