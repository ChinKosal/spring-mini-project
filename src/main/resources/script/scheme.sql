create DATABASE mini_spring_db;

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
INSERT INTO users_tb (email, password, profile_image) VALUES
                                                          ('user1@example.com', 'password1', 'profile1.jpg'),
                                                          ('user2@example.com', 'password2', 'profile2.jpg'),
                                                          ('user3@example.com', 'password3', 'profile3.jpg'),
                                                          ('user4@example.com', 'password4', 'profile4.jpg'),
                                                          ('user5@example.com', 'password5', 'profile5.jpg');

create table opts_tb(
                        opt_id SERIAL PRIMARY KEY,
                        opt_code VARCHAR(255),
                        issued_at TIMESTAMP DEFAULT LOCALTIMESTAMP,
                        expiration TIMESTAMP,
                        verify BOOLEAN,
                        user_id INT,
                        CONSTRAINT fk_opts_users_id_tb FOREIGN KEY (user_id) REFERENCES users_tb(user_id) on DELETE CASCADE on UPDATE CASCADE
);

create table categories_tb(
                              category_id SERIAL PRIMARY KEY,
                              name VARCHAR(255),
                              description VARCHAR(255),
                              user_id INT,
                              CONSTRAINT fk_categories_users_id_tb FOREIGN KEY (user_id) REFERENCES users_tb(user_id) on DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO categories_tb (name, description, user_id) VALUES
                                                           ('Category 1', 'Description 1', 1),
                                                           ('Category 2', 'Description 2', 2),
                                                           ('Category 3', 'Description 3', 3),
                                                           ('Category 4', 'Description 4', 4),
                                                           ('Category 5', 'Description 5', 5);

INSERT INTO opts_tb (opt_code, expiration, verify, user_id) VALUES
                                                                ('code1', '2024-05-01', true, 1),
                                                                ('code2', '2024-05-02', false, 2),
                                                                ('code3', '2024-05-03', true, 3),
                                                                ('code4', '2024-05-04', false, 4),
                                                                ('code5', '2024-05-05', true, 5);

CREATE Table expenses_tb(
                            expense_id SERIAL PRIMARY KEY,
                            amount NUMERIC(10,2),
                            description VARCHAR(255),
                            date TIMESTAMP DEFAULT LOCALTIMESTAMP,
                            user_id INT,
                            category_id INT,
                            CONSTRAINT fk_expenses_users_id_tb FOREIGN KEY (user_id) REFERENCES users_tb(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT fk_expenses_categories_id_tb FOREIGN KEY (category_id) REFERENCES categories_tb(category_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO expenses_tb (amount, description, user_id, category_id) VALUES
                                                                        (100.00, 'Expense 1', 1, 1),
                                                                        (200.00, 'Expense 2', 2, 2),
                                                                        (150.00, 'Expense 3', 3, 3),
                                                                        (300.00, 'Expense 4', 4, 4),
                                                                        (250.00, 'Expense 5', 5, 5);



select c.* from categories_tb c inner JOIN users_tb u on c.user_id =u.user_id
where u.email ='a@gmail.com' AND c.category_id = 1;

select * from expenses_tb WHERE expense_id = 1 AND user_id = 1;

delete from categories_tb WHERE user_id =1;

select c.* from categories_tb c inner JOIN users_tb u on c.user_id =u.user_id
where u.email ='a@gmail.com' AND c.category_id = 1;

select * from opts_tb WHERE user_id=14;

select * from opts_tb  ORDER BY expiration desc limit 1;

-- update opts_tb set verify = true where opt_code = '659010' order by expiration desc limit 1;



-- UPDATE opts_tb
-- SET verify = true
-- WHERE opt_code = '659010' (ORDER BY expiration desc limit 1);



delete from opts_tb where user_id = 31;