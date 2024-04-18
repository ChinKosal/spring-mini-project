-- Active: 1707989179952@@127.0.0.1@5432@expense_tracking_db@public
create DATABASE expense_tracking_db;

drop Table opts_tb;
drop table expenses_tb;
drop Table categories_tb;
drop Table users_tb;

create table users_tb(
                         user_id SERIAL PRIMARY KEY,
                         email VARCHAR(255),
                         password VARCHAR(255),
                         profile_image VARCHAR(255)
);

create table opts_tb(
                        opt_id SERIAL PRIMARY KEY,
                        opt_name VARCHAR(255),
                        issued_at TIMESTAMP DEFAULT LOCALTIMESTAMP,
                        expiration TIMESTAMP,
                        verify BOOLEAN,
                        user_id SERIAL,
                        CONSTRAINT fk_opts_users_id_tb FOREIGN KEY (user_id) REFERENCES users_tb(user_id) on DELETE CASCADE on UPDATE CASCADE
);

create table categories_tb(
                              category_id SERIAL PRIMARY KEY,
                              name VARCHAR(255),
                              description VARCHAR(255),
                              user_id SERIAL,
                              CONSTRAINT fk_categories_users_id_tb FOREIGN KEY (user_id) REFERENCES users_tb(user_id) on DELETE CASCADE ON UPDATE CASCADE
);

CREATE Table expenses_tb(
                            expense_id SERIAL PRIMARY KEY,
                            amount NUMERIC(10,2),
                            description VARCHAR(255),
                            date TIMESTAMP DEFAULT LOCALTIMESTAMP,
                            user_id SERIAL,
                            category_id SERIAL,
                            CONSTRAINT fk_expenses_users_id_tb FOREIGN KEY (user_id) REFERENCES users_tb(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT fk_expenses_categories_id_tb FOREIGN KEY (category_id) REFERENCES categories_tb(category_id) ON DELETE CASCADE ON UPDATE CASCADE
)