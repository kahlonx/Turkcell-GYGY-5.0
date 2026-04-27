-- Toplam cirosu 50k'dan büyük müşteriler

SELECT c.customer_id, c.company_name, SUM(od.unit_price * od.quantity * (1 - od.discount)) AS total_turnover
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
JOIN order_details od ON o.order_id = od.order_id
GROUP BY c.customer_id,  c.company_name
HAVING SUM(od.unit_price * od.quantity * (1 - od.discount)) > 50000
ORDER BY  total_turnover DESC;


-- Her kategori için en az 5 farklı ürün satan kategoriler

SELECT c.category_name, COUNT(p.product_id) AS product_count
FROM categories c
JOIN products p ON c.category_id = p.category_id
GROUP BY  c.category_id,  c.category_name
HAVING COUNT(p.product_id) >= 5
ORDER BY product_count DESC;


-- Total sales amount per employee (unit price)

SELECT e.employee_id, e.first_name, e.last_name, 
    SUM(od.unit_price * od.quantity * (1 - od.discount)) AS total_sales_amount
FROM employees e
JOIN orders o ON e.employee_id = o.employee_id
JOIN order_details od ON o.order_id = od.order_id
GROUP BY e.employee_id, e.first_name, e.last_name
ORDER BY total_sales_amount DESC;