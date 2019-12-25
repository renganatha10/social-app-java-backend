drop table if exists "userfollowing" cascade;

create table "userfollowing" (
    id uuid PRIMARY KEY,    
    user_id uuid references "user"(id),
    follower_id uuid references "user"(id),
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL
);

