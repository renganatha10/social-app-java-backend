drop table if exists "postlike" cascade;

create table "postlike" (
    id uuid PRIMARY KEY,    
    user_id uuid references "user"(id),
    post_id uuid references "post"(id),
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL
);

