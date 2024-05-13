create table bussineses
(
    business_id   int auto_increment
        primary key,
    bussines_name varchar(120) null,
    bussines_code varchar(10)  null
);

create table card_type
(
    type_id int auto_increment
        primary key,
    type    varchar(50) not null
);

create table customers
(
    customer_id  int auto_increment
        primary key,
    name         varchar(120) not null,
    last_name    varchar(120) not null,
    phone_number mediumtext   not null
);

create table cards
(
    card_id   int auto_increment
        primary key,
    card_name varchar(120) not null,
    type_id   int          null,
    owner     int          not null,
    creator   int          null,
    constraint cards_creator__fk
        foreign key (creator) references bussineses (business_id),
    constraint cards_owner__fk
        foreign key (owner) references customers (customer_id),
    constraint cards_type__fk
        foreign key (type_id) references card_type (type_id)
);

create table customers_cards
(
    customers_cards_id int not null
        primary key,
    card               int null,
    customer           int null,
    constraint customers_cards_cards_card_id_fk
        foreign key (card) references cards (card_id),
    constraint customers_cards_customers_customer_id_fk
        foreign key (customer) references customers (customer_id)
);

