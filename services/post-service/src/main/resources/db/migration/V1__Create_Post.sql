drop table if exists "post" cascade;

create table "post" (
    id uuid PRIMARY KEY,
    text character varying(255),
    photo_url character VARYING(255),
    video_url character varying(255),
    user_id uuid,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL
);
