CREATE TABLE users_database.todo
(
   id             int(20) NOT NULL DEFAULT 0,
   username       varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
   target         date NULL,
   done           tinyint(1) NULL,
   description    varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL
)
ENGINE InnoDB
COLLATE 'utf8_bin'
ROW_FORMAT DEFAULT

CREATE TABLE users_database.employees
(
   id               int(20) NOT NULL AUTO_INCREMENT,
   first_name       varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
   last_name        varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
   email_address    varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
   PRIMARY KEY(id)
)
ENGINE InnoDB
COLLATE 'utf8_bin'
ROW_FORMAT DEFAULT

