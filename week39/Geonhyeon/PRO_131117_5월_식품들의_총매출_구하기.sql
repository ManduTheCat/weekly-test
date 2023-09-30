-- 코드를 입력하세요
SELECT p.product_id, p.product_name, sum(p.price * o.amount) as total_sales 
FROM food_product p JOIN food_order o ON p.product_id = o.product_id
WHERE DATE_FORMAT(o.produce_date,'%Y-%m') = '2022-05'
GROUP BY p.product_id
ORDER BY total_sales DESC, p.product_id;
