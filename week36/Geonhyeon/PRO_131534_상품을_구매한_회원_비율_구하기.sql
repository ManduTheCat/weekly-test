
SET @2021_TOTAL_USER_COUNT = (
    SELECT count(*)
    FROM user_info
    WHERE year(joined) = '2021'
);

SELECT year(o.sales_date) as year, month(o.sales_date) as month
    , count(distinct o.user_id) as purchased_users, round(count(distinct o.user_id) / @2021_TOTAL_USER_COUNT, 1) 'PUCHASED_RATIO'
    # format(count(distinct u.user_id)/user_count.joined2022,2 )
    #     as purchased_ratio
FROM user_info u JOIN online_sale o ON u.user_id = o.user_id
WHERE DATE_FORMAT(u.joined,'%Y') = '2021'
GROUP BY year, month
ORDER BY year, month
