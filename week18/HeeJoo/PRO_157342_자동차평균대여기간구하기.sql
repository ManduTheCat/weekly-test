-- 평균 대여기간 7일 이상 car_id, 평균 대여기간(average_duration)
-- 소수점 두번째 자리 반올림
-- 평균 대여기간 내림차순, car_id 내림차순 정렬
select * from car_rental_company_rental_history;

select car_id, datediff(end_date,start_date) + 1
from car_rental_company_rental_history;

select car_id, avg(end_date - start_date) 
from car_rental_company_rental_history
where car_id = 1; 

select car_id, round(avg(datediff(end_date,start_date) + 1), 1 )
from car_rental_company_rental_history
where car_id = 1; 

select car_id as CAR_ID, round(avg(datediff(end_date,start_date) + 1), 1 ) as AVERAGE_DURATION
from car_rental_company_rental_history
group by car_id
having round(avg(datediff(end_date,start_date) + 1), 1 ) >= 7
order by AVERAGE_DURATION desc, car_id desc;