DROP TABLE IF EXISTS employee;

CREATE TABLE IF NOT EXISTS employee(
                              name VARCHAR (20)     NOT NULL,
                              experience  DOUBLE    NOT NULL,
                              id   INT  NOT NULL AUTO_INCREMENT,
                              PRIMARY KEY (id)
                           );


DROP TABLE IF EXISTS project;

CREATE TABLE IF NOT EXISTS project(
                              projectName VARCHAR (30)     NOT NULL,
                              teamCount  INT    NOT NULL,
                              leadsID   INT  NOT NULL,
    						  id   INT  NOT NULL AUTO_INCREMENT,
                              PRIMARY KEY (id),
                              FOREIGN KEY (leadsID) REFERENCES employee(id)
                           );