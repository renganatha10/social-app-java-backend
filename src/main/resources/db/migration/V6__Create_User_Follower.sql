drop table if exists "userfollower" cascade;

create table "userfollower" (
    id uuid PRIMARY KEY,    
    user_id uuid references "user"(id),    
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL
);

