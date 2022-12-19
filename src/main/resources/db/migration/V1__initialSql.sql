create table currency_table
(
    currency_type           varchar,
    close_currency          double precision,
    previous_close_currency double precision
);

create table limits_table
(
    limit_id           bigserial primary key,
    user_account       varchar(10) not null,
    account_limit      double precision,
    limit_balance      double precision,
    limit_category     varchar,
    limit_setting_date varchar
);

create table transaction_table
(
    transaction_id     bigserial primary key,
    account_from       varchar(10) not null,
    account_to         varchar(10) not null,
    expense_category   varchar     not null,
    currency_shortname varchar     not null,
    datetime           varchar,
    limit_exceeded     boolean,
    sum                double precision,
    limit_id           bigint references limits_table (limit_id)
);


