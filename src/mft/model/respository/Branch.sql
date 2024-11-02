create table branches
(
    id               number primary key,
    title   nvarchar2(80),
    address nvarchar2(500),
    phone nvarchar2(11),
    area nvarchar2(120),
    active boolean
);

create sequence branches_seq start with 1 increment by 1;
