DROP TABLE users;
DROP TABLE users_teams;
DROP TABLE teams;

CREATE TABLE company (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(32)
);

CREATE TABLE profile
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users(id),
    street VARCHAR(128)
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

CREATE TABLE chat
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(65) NOT NULL  UNIQUE
);

CREATE TABLE  users_chat
(
    user_id BIGINT REFERENCES users(id),
    chat_id BIGINT REFERENCES chat(id),
    PRIMARY KEY (user_id,chat_id)
)