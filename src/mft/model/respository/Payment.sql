create TABLE Payment(
  int number PRIMARY KEY ,
  title nvarchar2(20),
  amount number,
  date_time timestamp,
  description nvarchar2(40),
  payment_type nvarchar2(40)
);

create sequence payment_seq start with 1 increment by 1;
