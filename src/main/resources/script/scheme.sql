-- Active: 1707989179952@@127.0.0.1@5432@expense_tracking_db@public
create DATABASE expense_tracking_db;

drop Table opts_tb;
drop table expenses_tb;
drop Table categories_tb;
drop Table users_tb;

create table users_tb(
                         user_id SERIAL PRIMARY KEY,
                         email VARCHAR(255) UNIQUE ,
                         password VARCHAR(255),
                         profile_image VARCHAR(255)
);
INSERT INTO users_tb (email, password, profile_image)
VALUES ('user1@example.com', 'hashed_password_1', 'avatar.jpg'),
       ('user2@example.com', 'hashed_password_2', 'profile_pic.png'),
       ('user3@example.com', 'hashed_password_3', 'default_image.svg');

create table opts_tb(
                        opt_id SERIAL PRIMARY KEY,
                        opt_name VARCHAR(255),
                        issued_at TIMESTAMP DEFAULT LOCALTIMESTAMP,
                        expiration TIMESTAMP,
                        verify BOOLEAN,
                        user_id Int,
                        CONSTRAINT fk_opts_users_id_tb FOREIGN KEY (user_id) REFERENCES users_tb(user_id) on DELETE CASCADE on UPDATE CASCADE
);
INSERT INTO opts_tb (opt_name, issued_at, expiration, verify, user_id)
VALUES ('Email Verification', CURRENT_TIMESTAMP, NULL, FALSE, 1),
       ('Password Reset', '2024-04-18 12:30:00+07', '2024-04-19 12:30:00+07', FALSE, 2),
       ('Account Activation', '2024-04-17 10:00:00+07', NULL, TRUE, 3);


create table categories_tb(
                              category_id SERIAL PRIMARY KEY,
                              name VARCHAR(255),
                              description VARCHAR(255),
                              user_id Int,
                              CONSTRAINT fk_categories_users_id_tb FOREIGN KEY (user_id) REFERENCES users_tb(user_id) on DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO categories_tb (name, description, user_id)
VALUES ('Groceries', 'For buying food items for daily meals', 1),
       ('Entertainment', 'Expenses related to movies, hobbies, etc.', 2),
       ('Transportation', 'Costs associated with travel (gas, tickets)', 3);


CREATE Table expenses_tb(
                            expense_id SERIAL PRIMARY KEY,
                            amount NUMERIC(10,2),
                            description VARCHAR(255),
                            date TIMESTAMP DEFAULT LOCALTIMESTAMP,
                            user_id Int,
                            category_id Int,
                            CONSTRAINT fk_expenses_users_id_tb FOREIGN KEY (user_id) REFERENCES users_tb(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT fk_expenses_categories_id_tb FOREIGN KEY (category_id) REFERENCES categories_tb(category_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO expenses_tb (amount, description, date, user_id, category_id)
VALUES (50.25, 'Weekly grocery shopping', CURRENT_TIMESTAMP, 1, 1),
       (12.99, 'Movie ticket', '2024-04-17 20:00:00+07', 2, 2),
       (25.00, 'Bus fare to work', '2024-04-18 08:00:00+07', 3, 3);
