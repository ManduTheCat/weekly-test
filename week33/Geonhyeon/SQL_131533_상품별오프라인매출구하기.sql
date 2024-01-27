SELECT p.product_code, sum(price * sales_amount) as sales
FROM product p JOIN offline_sale o ON p.product_id = o.product_id
GROUP BY p.product_code
ORDER BY sales DESC, p.product_code ASC
