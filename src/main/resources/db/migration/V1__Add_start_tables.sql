create table pg_user
(
    id          serial primary key,
    first_name  varchar(20),
    last_name   varchar(20),
    middle_name varchar(20),
    role        varchar(20),
    login       varchar(30),
    password    varchar(30)
);

create table coffee_place
(
    id          serial primary key,
    place_name  varchar(30),
    latitude    numeric,
    longitude   numeric,
    rating      bytea,
    about_place text
);

create table coffee_place_review
(
    id              serial primary key,
    coffee_place_id bigserial,
    CONSTRAINT coffee_place_id_fk
        FOREIGN KEY (coffee_place_id)
            REFERENCES coffee_place (id),
    review_text     text
);