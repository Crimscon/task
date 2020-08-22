insert into usr (id, username, password)
values (1, 'user', 'qwerty');

insert into user_role (user_id, roles)
values (1, 'USER');

insert into author (id, name)
values (10, 'Булгаков'),
       (11, 'Толстой'),
       (12, 'Лермонтов'),
       (13, 'Пушкин');

insert into books (id, name, author_id)
values (100, 'Бег', 10),
       (101, 'Мастер и Маргарита', 10),
       (102, 'Батум', 10),
       (103, 'Мертвые души', 10),
       (104, 'Дни турбинных', 10);

insert into books (id, name, author_id)
values (105, 'Война и мир', 11),
       (106, 'Детство', 11),
       (107, 'Казаки', 11),
       (108, 'Анна Каренина', 11),
       (109, 'Воскресение', 11);

insert into books (id, name, author_id)
values (110, 'Герой нашего времени', 12),
       (111, 'Демон', 12),
       (112, 'Мцыри', 12),
       (113, 'Бородино', 12),
       (114, 'Парус', 12);

insert into books (id, name, author_id)
values (115, 'Капитанская дочка', 13),
       (116, 'Пиковая дама', 13),
       (117, 'Руслан и Людмила', 13),
       (118, 'Выстрел', 13),
       (119, 'Дубровский', 13);
