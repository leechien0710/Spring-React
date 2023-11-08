create table blog_entity (
     id integer identity,
     title varchar(255),
     category varchar(255),
     image TEXT,
     created_at date,
     description TEXT,
     primary key (id)
);

