create table currency_table
(
    currency_type           varchar primary key,
    close_currency          decimal,
    previous_close_currency decimal
);

create table limits_table
(
    limit_id           bigserial primary key,
    user_account       varchar(10) not null,
    account_limit      decimal,
    limit_balance      decimal,
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
    sum                decimal,
    limit_id           bigint references limits_table (limit_id)
);


