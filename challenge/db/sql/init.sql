USE textiq;

DROP TABLE documentFile;
DROP TABLE document;
DROP TABLE token;
DROP TABLE token_document;
DROP TABLE token_document_location;

CREATE TABLE documentFile(
                id int NOT NULL AUTO_INCREMENT,
                hash VARCHAR(32),
                location VARCHAR(255),
                processed TINYINT,
                PRIMARY KEY (id)
);

CREATE TABLE document(
                  id int NOT NULL AUTO_INCREMENT,
                  title VARCHAR(255),
                  body TEXT,
                  PRIMARY KEY (id)
);


CREATE TABLE token(
                id int NOT NULL AUTO_INCREMENT,
                value VARCHAR(255),
                PRIMARY KEY (id),
                UNIQUE KEY `token_uidx` (`value`),
                INDEX `token_idx` (`value`)
);

CREATE TABLE token_document(
                id int NOT NULL AUTO_INCREMENT,
                token_id int,
                document_id int,
                PRIMARY KEY (id),
                UNIQUE KEY `token_document_uidx` (`token_id`,`document_id`)
);

CREATE TABLE token_document_location(
                id int NOT NULL AUTO_INCREMENT,
                token_document_id int,
                location int,
                PRIMARY KEY (id)

);