create table if not exists usr (
    id int auto_increment primary key,
    name varchar(50),
    password varchar(50)
);

insert into usr(name, password)
values ('John Doe', 'qwerty'), ('Dogdurbekov Baiel', 'RiMuRu06');