drop table if exists products cascade;
create table products
(
    id          bigserial,
    title       varchar(255),
    description varchar(5000),
    price       numeric(10, 2),
    primary key (id)
);
insert into products
    (title, description, price)
values ('Pear', 'Fresh pear', 1.85),
       ('Melon', 'Fresh melons', 3.70),
       ('Cherry', 'Fresh cherries', 5.00),
       ('Peach', 'Fresh peaches', 2.77),
       ('Apricot', 'Fresh apricots', 2.20),
       ('Shrimp', 'Fresh shrimps', 12.00),
       ('Bagel', 'Fresh bagels', 0.50),
       ('Potato', 'Fresh potatoes', 6.00),
       ('Chocolate', 'Yam yam', 10.25),
       ('Corn', 'Fresh corns', 0.70),
       ('Gum', 'Sweet gum', 1.40),
       ('Soda', 'Sweet soda', 3.99),
       ('Milk', 'Fresh milk', 1.85),
       ('Apple', 'Fresh apples', 3.70),
       ('Orange', 'Fresh oranges', 5.00),
       ('Coconut', 'Fresh coconuts', 6.79),
       ('Meat', 'Fresh meat', 9.40),
       ('Rice', 'Fresh rices', 2.60),
       ('Nuts', 'Fresh nuts', 6.50),
       ('Perch', 'Fresh perches', 6.00),
       ('Notebook', 'Very powerful', 6.00);

drop table if exists categories cascade;
create table categories
(
    id    bigserial,
    title varchar(255),
    primary key (id)
);
insert into categories
    (title)
values ('Food'),
       ('Electronic'),
       ('Fruit');

drop table if exists products_categories cascade;
create table products_categories
(
    product_id  bigint not null,
    category_id bigint not null,
    primary key (product_id, category_id),
    foreign key (product_id) references products (id),
    foreign key (category_id) references categories (id)
);


insert into products_categories (product_id, category_id)
values (1, 1),
       (1, 3),
       (21, 2);

drop table if exists users cascade;
create table users
(
    id       bigserial,
    login    varchar(255) not null unique,
    password varchar(255) not null,
    primary key (id)
);

-- password 100
insert into users (login, password)
VALUES ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('manager', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

drop table if exists roles cascade;
create table roles
(
    id   smallserial,
    role varchar(255) not null unique,
    primary key (id)
);

insert into roles (role)
values ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_MANAGER');

drop table if exists users_roles cascade;
create table users_roles
(
    user_id bigint   not null,
    role_id smallint not null,
    primary key (user_id, role_id),
    foreign key (user_id)
        references users (id),
    foreign key (role_id)
        references roles (id)
);

insert into users_roles(user_id, role_id)
VALUES (1, 1),
       (1, 3),
       (2, 2),
       (3, 2),
       (3, 3);

