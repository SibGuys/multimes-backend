create table if not exists multimes_inter (
    id int generated by default as identity primary key,
    msgr int not null
);

create table if not exists multimes_message (
    id int generated by default as identity primary key,
    inter_id int not null references multimes_inter(id),
    message text not null
);
