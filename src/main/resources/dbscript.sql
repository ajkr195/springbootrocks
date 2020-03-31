drop database if exists springbootrocks;
create database springbootrocks;
use springbootrocks;

create table app_user(
id BIGINT NOT NULL AUTO_INCREMENT,
username VARCHAR(150) NOT NULL,
password VARCHAR(150) NOT NULL,
useremail VARCHAR(150) NOT NULL,
userfirstname VARCHAR(150) NOT NULL,
userlastname VARCHAR(150) NOT NULL,
useraddress VARCHAR(150) NOT NULL,
PRIMARY KEY (id),
UNIQUE (username)) ENGINE=InnoDB;

create table app_role(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
PRIMARY KEY (id),
UNIQUE (name)) ENGINE=InnoDB;
   
CREATE TABLE app_user_role (
id BIGINT NOT NULL AUTO_INCREMENT,
userid BIGINT NOT NULL,
roleid INT NOT NULL,
PRIMARY KEY (id))ENGINE=InnoDB;

create table persistent_logins (
username varchar(64) not null,
series varchar(64) primary key,
token varchar(64) not null,
last_used timestamp not null)ENGINE=InnoDB;
 

ALTER TABLE app_user_role ADD CONSTRAINT FK_AURUSERID FOREIGN KEY (userid) REFERENCES app_user (id);
ALTER TABLE app_user_role ADD CONSTRAINT FK_AURROLEID FOREIGN KEY (roleid) REFERENCES app_role (id); 


INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin@admin', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin@admin', 'admin@admin', 'admin@admin', 'admin@admin');   
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin1@admin1', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin1@admin1', 'admin1@admin1', 'admin1@admin1', 'admin1@admin1');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin2@admin2', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin2@admin2', 'admin2@admin2', 'admin2@admin2', 'admin2@admin2');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin3@admin3', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin3@admin3', 'admin3@admin3', 'admin3@admin3', 'admin3@admin3');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin4@admin4', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin4@admin4', 'admin4@admin4', 'admin4@admin4', 'admin4@admin4');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin5@admin5', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin5@admin5', 'admin5@admin5', 'admin5@admin5', 'admin5@admin5');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin6@admin6', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin6@admin6', 'admin6@admin6', 'admin6@admin6', 'admin6@admin6');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin7@admin7', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin7@admin7', 'admin7@admin7', 'admin7@admin7', 'admin7@admin7');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin8@admin8', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin8@admin8', 'admin8@admin8', 'admin8@admin8', 'admin8@admin8');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin9@admin9', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin9@admin9', 'admin9@admin9', 'admin9@admin9', 'admin9@admin9');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin10@admin10', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin10@admin10', 'admin10@admin10', 'admin10@admin10', 'admin10@admin10');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin11@admin11', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin11@admin11', 'admin11@admin11', 'admin11@admin11', 'admin11@admin11');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin12@admin12', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin12@admin12', 'admin12@admin12', 'admin12@admin12', 'admin12@admin12');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin13@admin13', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin13@admin13', 'admin13@admin13', 'admin13@admin13', 'admin13@admin13');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin14@admin14', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin14@admin14', 'admin14@admin14', 'admin14@admin14', 'admin14@admin14');
INSERT INTO `springbootrocks`.`app_user` (`username`, `password`, `useremail`, `userfirstname`, `userlastname`, `useraddress`) VALUES ('admin15@admin15', '$2a$10$EVfGJ5O6YLQs5Jj5ZOAKGuZ/2sLqXkNLw8j.MotNnYgHa1h2qUyIW', 'admin15@admin15', 'admin15@admin15', 'admin15@admin15', 'admin15@admin15');

INSERT INTO `springbootrocks`.`app_role` (`id`, `name`) VALUES ('1', 'ADMIN');
INSERT INTO `springbootrocks`.`app_role` (`id`, `name`) VALUES ('2', 'EDITOR');
INSERT INTO `springbootrocks`.`app_role` (`id`, `name`) VALUES ('3', 'VIEWER');


INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('1', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('1', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('1', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('2', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('2', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('2', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('3', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('3', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('3', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('4', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('4', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('4', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('5', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('5', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('5', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('6', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('6', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('6', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('7', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('7', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('7', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('8', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('8', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('8', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('9', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('9', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('9', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('10', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('10', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('10', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('11', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('11', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('11', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('12', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('12', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('12', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('13', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('13', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('13', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('14', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('14', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('14', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('15', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('15', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('15', '3');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('16', '1');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('16', '2');
INSERT INTO `springbootrocks`.`app_user_role` (`userid`, `roleid`) VALUES ('16', '3');

