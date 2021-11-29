create sequence hibernate_sequence start 1 increment 1;

create table company (
    id_company int8 not null,
    budget_company int8,
    inn varchar(255),
    owner_company varchar(255),
    primary key (id_company)
);

create table menu (
    id_food int8 not null,
    cost_food float8,
    filename varchar(255),
    is_have boolean not null,
    name_food varchar(255),
    tag_food varchar(255),
    user_id int8,
    primary key (id_food)
);

create table past_order (
    id_past_order int8 not null,
    client_id int8,
    is_gotov boolean,
    order_date_past timestamp,
    past_order_text varchar(255),
    past_id int8,
    primary key (id_past_order)
);

create table past_order_order_meal (
    past_order_id_past_order int8 not null,
    order_meal int4,
    order_meal_key int8 not null,
    primary key (past_order_id_past_order, order_meal_key)
);

create table real_order (
    id_order int8 not null,
    client_id int8,
    is_gotov boolean,
    primary key (id_order)
);

create table real_order_order_meal (
    real_order_id_order int8 not null,
    order_meal int4,
    order_meal_key int8 not null,
    primary key (real_order_id_order, order_meal_key)
);

create table restaurant (
    id_restaurant int8 not null,
    adress varchar(255),
    phone_rest varchar(255),
    primary key (id_restaurant)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255) not null,
    phone varchar(255),
    sur_name varchar(255),
    username varchar(255) not null,
    primary key (id)
);

alter table if exists menu
    add constraint usr_menu_fk
    foreign key (user_id) references usr;

alter table if exists past_order_order_meal
    add constraint past_meal_fk
    foreign key (past_order_id_past_order) references past_order;

alter table if exists real_order_order_meal
    add constraint real_meal_fk
    foreign key (real_order_id_order) references real_order;

alter table if exists user_role
    add constraint usr_role_fk
    foreign key (user_id) references usr;