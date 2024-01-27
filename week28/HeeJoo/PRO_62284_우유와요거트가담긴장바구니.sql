# Milk와 Yogurt를 동시에 구입한 장바구니 아이디 구하기
# order by card_id asc;
# group by cart_id ? where in "Milk" ans "Yogurt"

select cart_id
from (
    select distinct cart_id
    from cart_products
    where name = "Milk"
    union all
    select distinct cart_id
    from cart_products
    where name = "Yogurt"
    ) tmp
group by cart_id
having count(cart_id) = 2
order by cart_id asc;
