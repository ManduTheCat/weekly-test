-- where date_format(sales_date, "%Y-%m") = "2022-01"
-- 저자 별, 카테고리 별 매출액(TOTAL_SALES = 판매량 * 판매가)
-- group by author_id, category
-- total_sales = bs.sales * b.price
-- SELECT AUTHOR_ID, AUTHOR_NAME, CATEGORY, SALES
-- ORDER BY AUTHOR_ID ASC, CATEGORY DESC

# with book_id별 total_sales을 먼저 구한 풀이
# with tmp as (
#     select book_id, sum(sales) as total
#     from book_sales
#     where date_format(sales_date, "%Y-%m") = "2022-01"
#     group by book_id
# )
# SELECT b.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, sum(b.price * t.total) as total_sales
# from book b
# inner join author a
# on b.author_id = a.author_id
# inner join tmp t
# on b.book_id = t.book_id
# group by author_id, category
# ORDER BY AUTHOR_ID ASC, CATEGORY DESC;

# 세 테이블을 모두 join한 풀이
SELECT b.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, sum(b.price * bs.sales) as total_sales
from book b
inner join author a
on b.author_id = a.author_id
inner join book_sales bs
on b.book_id = bs.book_id
where date_format(bs.sales_date, "%Y-%m") = "2022-01"
group by author_id, category
ORDER BY AUTHOR_ID ASC, CATEGORY DESC;

# select book_id, sum(sales)
# from book_sales
# where date_format(sales_date, "%Y-%m") = "2022-01"
# group by book_id;

