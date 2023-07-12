DROP TABLE users;
DROP TABLE company;

CREATE TABLE company (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS users
(
id BIGSERIAL primary key,
user_name VARCHAR(128),
first_name VARCHAR(128),
last_name VARCHAR(128),
birth_date DATE,
death_date DATE,
company_id INT REFERENCES company(id),
role VARCHAR(32),
recipe JSONB
);


/*
CREATE [ { TEMPORARY | TEMP } | UNLOGGED ] SEQUENCE [ IF NOT EXISTS ] name
    [ AS data_type ]
    [ INCREMENT [ BY ] increment ]
    [ MINVALUE minvalue | NO MINVALUE ] [ MAXVALUE maxvalue | NO MAXVALUE ]
    [ START [ WITH ] start ] [ CACHE cache ] [ [ NO ] CYCLE ]
    [ OWNED BY { table_name.column_name | NONE } ]
*/