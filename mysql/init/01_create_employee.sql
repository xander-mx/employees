CREATE TABLE employees_db.employee (
                                       id_employee      BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       first_name       VARCHAR(30)       NOT NULL,
                                       second_name      VARCHAR(30)       NOT NULL,
                                       last_name        VARCHAR(30)       NOT NULL,
                                       second_last_name VARCHAR(30)       NULL,
                                       age              INT               NULL,
                                       gender           VARCHAR(30)       NOT NULL,
                                       birth_date       DATE              NULL,
                                       job_title        VARCHAR(50)       NULL,
                                       created_date     DATE              DEFAULT (CURDATE()),
                                       status           TINYINT(1)        DEFAULT 1
);