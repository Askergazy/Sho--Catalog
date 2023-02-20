drop table  if exists  categories cascade ;
drop table  if exists  products cascade ;
drop table  if exists  options cascade ;


create table categories
(
    id   serial8,
    name varchar not null,
    primary key (id)
);

create table products
(
    id          serial8,
    category_id int8    not null,
    name        varchar not null,
    price       int4    not null,
    primary key (id),
    foreign key (category_id) references categories (id)
);

create table options
(
    id          serial8,
    category_id int8    not null,
    name        varchar not null,
    primary key (id),
    foreign key (category_id) references categories (id)
);

create table values
(
    id         serial8,
    product_id int8    not null,
    option_id  int8    not null,
    value      varchar not null,
    primary key (id),
    foreign key (product_id) references products (id),
    foreign key (option_id) references options (id)
);


