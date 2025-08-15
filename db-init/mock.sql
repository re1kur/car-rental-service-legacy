-- admin user
INSERT INTO users(email, username, password, role)
VALUES ('admin@mail.com', 'admin', 'admin', 'ADMIN');

-- обычные пользователи
INSERT INTO users (email, username, password, role) VALUES
('user1@example.com', 'user1', 'password1', 'USER'),
('user2@example.com', 'user2', 'password2', 'USER'),
('user3@example.com', 'user3', 'password3', 'USER'),
('user4@example.com', 'user4', 'password4', 'USER'),
('user5@example.com', 'user5', 'password5', 'USER'),
('user6@example.com', 'user6', 'password6', 'USER'),
('user7@example.com', 'user7', 'password7', 'USER'),
('user8@example.com', 'user8', 'password8', 'USER');

-- компании
INSERT INTO companies (name, img_key) VALUES
('BMW', 'companies/bmw_logo.jpeg'),
('Ford', 'companies/ford_logo.png'),
('Honda', 'companies/honda_logo.jpg'),
('Toyota', 'companies/toyota_logo.png'),
('Mersedes', 'companies/mersedes_logo.jpg'),
('Volkswagen', 'companies/wolkswagen_logo.jpg');

-- автомобили
INSERT INTO cars (name, company_id, year_release) VALUES
('BMW M5', (SELECT id FROM companies WHERE name='BMW'), '2021-01-01'),
('Ford Mustang', (SELECT id FROM companies WHERE name='Ford'), '2020-01-01'),
('Honda Civic', (SELECT id FROM companies WHERE name='Honda'), '2019-01-01'),
('Toyota Camry', (SELECT id FROM companies WHERE name='Toyota'), '2022-01-01'),
('Mersedes C-Class', (SELECT id FROM companies WHERE name='Mersedes'), '2021-01-01'),
('Volkswagen Polo', (SELECT id FROM companies WHERE name='Volkswagen'), '2018-01-01'),
('Toyota Supra', (SELECT id FROM companies WHERE name='Toyota'), '2020-01-01'),
('Ford Fusion', (SELECT id FROM companies WHERE name='Ford'), '2015-01-01');

-- аренда
INSERT INTO rentals (owner_id, img_key, car_id, price, description) VALUES
((SELECT id FROM users WHERE username='user1'), 'rentals/bmwm5.jpg', (SELECT id FROM cars WHERE name='BMW M5'), 150, 'Мощный и быстрый седан BMW M5.'),
((SELECT id FROM users WHERE username='user2'), 'rentals/fordmustang.jpg', (SELECT id FROM cars WHERE name='Ford Mustang'), 200, 'Классический спортивный Ford Mustang.'),
((SELECT id FROM users WHERE username='user3'), 'rentals/honda_civic.jpg', (SELECT id FROM cars WHERE name='Honda Civic'), 90, 'Надежный и экономичный Honda Civic.'),
((SELECT id FROM users WHERE username='user4'), 'rentals/toyotacamrywhite.jpg', (SELECT id FROM cars WHERE name='Toyota Camry'), 120, 'Комфортная Toyota Camry белого цвета.'),
((SELECT id FROM users WHERE username='user5'), 'rentals/mersedesh.jpg', (SELECT id FROM cars WHERE name='Mersedes C-Class'), 180, 'Элегантный и премиальный Mersedes C-Class.'),
((SELECT id FROM users WHERE username='user6'), 'rentals/wolkswagenpolo.jpg', (SELECT id FROM cars WHERE name='Volkswagen Polo'), 80, 'Небольшой и экономичный Volkswagen Polo.'),
((SELECT id FROM users WHERE username='user7'), 'rentals/db259391ba36418d8d4b79ceae426e2f2020_Toyota_Supra_front_in_red_NYIAS_2019.jpg', (SELECT id FROM cars WHERE name='Toyota Supra'), 220, 'Спортивная Toyota Supra для адреналина.'),
((SELECT id FROM users WHERE username='user8'), 'rentals/ford-fusion-2013-2016-1604313782.8258727.jpg', (SELECT id FROM cars WHERE name='Ford Fusion'), 100, 'Ford Fusion среднего класса для повседневных поездок.');
