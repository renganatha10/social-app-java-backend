drop table if exists "comment" cascade;

create table "comment" (
    id uuid PRIMARY KEY,
    text character varying(255),    
    user_id uuid references "user"(id),
    post_id uuid references "post"(id),
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL
);

