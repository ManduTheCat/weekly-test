-- 고양이와 개각 각각 몇 마리인지 조회
-- 고양이를 개보다 먼저 조회
use pro_59040;

select animal_type, count(animal_type)
from animal_ins
group by animal_type
order by animal_type;