SELECT DATE_FORMAT(o.sales_date,"%Y") as YEAR,
        DATE_FORMAT(o.sales_date,"%m") as MONTH,
        u.gender,
        count(DISTINCT u.user_id) as USERS
FROM user_info u 
JOIN online_sale o ON u.user_id = o.user_id
WHERE u.gender IS NOT NULL
GROUP BY year, month, gender
ORDER BY year, month, gender
