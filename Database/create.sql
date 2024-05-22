create table businesses
(
    business_id  int auto_increment
        primary key,
    busines_name varchar(120)         null,
    busines_code varchar(10)          null,
    is_active    tinyint(1) default 0 null,
    is_deleted   tinyint(1) default 0 null
);

create table customers
(
    customer_id  int auto_increment
        primary key,
    name         varchar(120)         not null,
    last_name    varchar(120)         not null,
    phone_number mediumtext           not null,
    email        varchar(200)         null,
    is_admin     tinyint(1) default 0 null
);

create table discount_cards
(
    discount_card_id    int auto_increment
        primary key,
    owner_id            int    null,
    creator_id          int    null,
    discount_percentage double null,
    constraint discount_cards_businesses_business_id_fk
        foreign key (creator_id) references businesses (business_id),
    constraint discount_cards_customers_customer_id_fk
        foreign key (owner_id) references customers (customer_id)
);

create table stamp_cards
(
    stamp_card_id int auto_increment
        primary key,
    owner_id      int null,
    creator_id    int null,
    stamps        int null,
    constraint stamp_cards_businesses_business_id_fk
        foreign key (creator_id) references businesses (business_id),
    constraint stamp_cards_customers_customer_id_fk
        foreign key (owner_id) references customers (customer_id)
);

create table customers_cards
(
    customers_cards_id int not null
        primary key,
    customer           int null,
    discount_cards     int null,
    stamp_cards        int null,
    constraint customers_cards_customers_customer_id_fk
        foreign key (customer) references customers (customer_id),
    constraint customers_cards_discount_cards_owner_id_fk
        foreign key (discount_cards) references discount_cards (owner_id),
    constraint customers_cards_stamp_cards_owner_id_fk
        foreign key (stamp_cards) references stamp_cards (owner_id)
);

