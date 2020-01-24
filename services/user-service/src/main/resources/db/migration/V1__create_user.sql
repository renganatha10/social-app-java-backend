drop table if exists "user" cascade;

drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start 1 increment 1;

create table "user" (
    id uuid PRIMARY KEY,
    email character varying(255) NOT NULL UNIQUE,
    gender character VARYING(2) NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    nick_name character varying(255) UNIQUE,
    date_of_birth timestamp with time zone,
    bio character varying(255),
    cover_photo character varying(255),
    profile_photo character varying(255),
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL
);

