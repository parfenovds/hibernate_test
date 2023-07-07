DROP TABLE test;
CREATE TABLE IF NOT EXISTS test(
  id BIGSERIAL,
  text TEXT,
  birth_date DATE
);

CREATE TABLE IF NOT EXISTS users
(
id BIGSERIAL PRIMARY KEY,
user_name VARCHAR(128),
first_name VARCHAR(128),
last_name VARCHAR(128),
birth_date DATE,
death_date DATE,
role VARCHAR(32),
recipe JSONB
);

DROP TABLE users;