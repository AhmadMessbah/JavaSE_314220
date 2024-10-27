create table products
(
    id               number primary key,
    name             nvarchar2(30),
    price            number,
    quantity         number,
    category         nvarchar2(20),
    expire_date      date,
    discount         number,
    catalogue        number(1),
    image            number(1),
    transaction_type nvarchar2(20)
);

create sequence product_seq start with 1 increment by 1;

-- Save
insert into products
    (id, name, price, quantity, category, expire_date, discount, catalogue, image, transaction_type)
VALUES (product_seq.nextval, 'mobile', 1000, 3, 'Electrical', null, 200, 0, 1, 'income;');

-- Edit
update products set name='Laptop', quantity=8, price=5000 where id=23;

-- Remove
delete from products where id = 23;




-- Find All
select * from products;