DROP TABLE users;
DROP SEQUENCE users_custom_id_seq;

CREATE SEQUENCE IF NOT EXISTS users_custom_id_seq
    INCREMENT 1;

CREATE TABLE IF NOT EXISTS users
(
id int default nextval('users_custom_id_seq') primary key,
user_name VARCHAR(128),
first_name VARCHAR(128),
last_name VARCHAR(128),
birth_date DATE,
death_date DATE,
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