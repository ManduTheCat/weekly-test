WITH MaxPriceByCategory AS (
    SELECT
        CATEGORY,
        MAX(PRICE) AS MAX_PRICE
    FROM
        FOOD_PRODUCT
    WHERE
        CATEGORY IN ('과자', '국', '김치', '식용유')
    GROUP BY
        CATEGORY
)
SELECT
    MaxPriceByCategory.CATEGORY,
    MaxPriceByCategory.MAX_PRICE,
    FOOD_PRODUCT.PRODUCT_NAME
FROM
    MaxPriceByCategory
JOIN
    FOOD_PRODUCT
ON
    MaxPriceByCategory.CATEGORY = FOOD_PRODUCT.CATEGORY
    AND MaxPriceByCategory.MAX_PRICE = FOOD_PRODUCT.PRICE
ORDER BY
    MaxPriceByCategory.MAX_PRICE DESC;
