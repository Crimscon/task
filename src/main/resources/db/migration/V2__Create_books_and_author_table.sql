create table author
(
	id int8 not null
		constraint author_pk
			primary key,
	name text not null
);

create table books
(
	id int8 not null
		constraint books_pk
			primary key,
	name text not null,
	author_id int8 not null
		constraint books_author_id_fk
			references author (id)
				on update cascade on delete cascade
);

