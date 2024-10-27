create table Contact
(
    int         number primary key,
    state       nvarchar2(30),
    city        nvarchar2(30),
    region      nvarchar2(30),
    address     nvarchar2(100),
    postalCode  nvarchar2(11),
    phone       nvarchar2(12),
    description nvarchar2(100)
);
create sequence product_seq start with 1 increment by 1;

