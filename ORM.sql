USE crm_app;
SELECT * FROM users u ;

CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jobs (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    job_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (user_id) REFERENCES users (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (job_id) REFERENCES jobs (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD FOREIGN KEY (status_id) REFERENCES status (id)  ON DELETE CASCADE;

INSERT INTO roles( name, description ) VALUES ("ROLE_ADMIN", "Quản trị hệ thống");
INSERT INTO roles( name, description ) VALUES ("ROLE_MANAGER", "Quản lý");
INSERT INTO roles( name, description ) VALUES ("ROLE_USER", "Nhân viên");

INSERT INTO status( name ) VALUES ("Chưa thực hiện");
INSERT INTO status( name ) VALUES ("Đang thực hiện");
INSERT INTO status( name ) VALUES ("Đang thực hiện");

INSERT INTO users( email, password, fullname, avatar, role_id) VALUES ("ltqhuy.th0112@gmail.com","1234","Le Tran Quang Huy", "",1);
INSERT INTO users( email, password, fullname, avatar, role_id) VALUES ("lamtuyenle2302@gmail.com","1234","Le Vo Lam Tuyen", "",2);


INSERT INTO jobs (name, start_date, end_date) VALUES
    ('Job 1', '2023-06-19', '2023-06-20'),
    ('Job 2', '2023-06-21', '2023-06-23');

INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id) VALUES
    ('Task 1', '2023-06-19', '2023-06-20', 1, 1, 1),
    ('Task 2', '2023-06-21', '2023-06-23', 3, 2, 2);
    
-- select tasks.id, tasks.user_id, tasks.name, status.id as status_id, tasks.start_date, tasks.end_date from tasks
-- inner join users on tasks.user_id = users.id
-- inner join status on tasks.status_id = status.id
-- where users.id = 1;