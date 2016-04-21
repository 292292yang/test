DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
	userId			varchar(12) 		NOT NULL,
	password		varchar(12)			NOT NULL,
	name			varchar(20)			NOT NULL,
	email			varchar(50),
	
	PRIMARY KEY (userId)
);

insert into USERS values('Hwarang', 'password', 'Truelite', 'michael.jung@truelitetrace.com');

DROP TABLE IF EXISTS t_account;

CREATE TABLE t_account (
	id			INTEGER(12) 		NOT NULL AUTO_INCREMENT,
	name			varchar(20)			NOT NULL,
	num			INTEGER ,

	PRIMARY KEY (id)
);

