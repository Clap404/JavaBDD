drop table if exists cart_contains_article ;
drop table if exists article ;
drop table if exists category ;
drop table if exists cart ;
drop table if exists user ;

create table if not exists user (
    id_user int not null auto_increment,
    name_user varchar(32) not null,
    password varchar(64) not null,
    primary key (id_user)
);

create table if not exists cart (
    id_cart int not null auto_increment,
    id_user int not null,
    date_checkout timestamp,
    primary key (id_cart),
    foreign key (id_user) references user(id_user)
);

create table if not exists category (
    id_cat int not null auto_increment,
    name_cat varchar(32) not null,
    primary key (id_cat)
);

create table if not exists article (
    id_article int not null auto_increment,
    id_cat int not null,
    price_article float not null,
    name_article varchar(128) not null,
    stock_article int default 0 not null,
    description_article text,
    primary key (id_article),
    foreign key (id_cat) references category(id_cat)
);

create table if not exists cart_contains_article (
    id_article int not null,
    id_cart int not null,
    qty_contains int default 1,
    primary key (id_article, id_cart),
    foreign key (id_article) references article(id_article),
    foreign key (id_cart) references cart(id_cart)
);