CREATE TABLE customers (
    id number primary key,
    name nvarchar2(25),
    familyName nvarchar2(25),
    username nvarchar2(20),
    password varchar2(20),
    phone char(11),
    active number(1)
);

CREATE SEQUENCE customer_seq START WITH 1 INCREMENT BY 1;