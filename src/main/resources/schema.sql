CREATE TABLE app_user (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    username VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    useremail VARCHAR(128) NOT NULL,
    userfirstname VARCHAR(128) NOT NULL,
    userlastname VARCHAR(128) NOT NULL,
    useraddress VARCHAR(128) NOT NULL,
    userdatecreated VARCHAR(128) NOT NULL,
    usercreatedby VARCHAR(128) NOT NULL,
    userdatemodified VARCHAR(128) NOT NULL,
    usermodifiedby VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE app_role (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE app_user_role (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    userid VARCHAR(128) NOT NULL,
    roleid VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE app_department (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE app_user_department (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    userid VARCHAR(128) NOT NULL,
    departmentid VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);
