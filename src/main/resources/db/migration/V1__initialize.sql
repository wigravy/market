drop table if exists products cascade;
create table products (id bigserial, title varchar(255), description varchar(5000), price numeric(10, 2), primary key(id));
insert into products
(title, description, price) values
('Pear', 'Fresh pear', 1.85),
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
('Perch', 'Fresh perches', 6.00);