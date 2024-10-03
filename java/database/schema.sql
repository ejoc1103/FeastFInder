BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_voter;
DROP TABLE IF EXISTS voter;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS eatery;
DROP TABLE IF EXISTS user_group;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	user_email varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE groups (
	group_id SERIAL,
	group_name varchar(50) NOT NULL,
	CONSTRAINT PK_group PRIMARY KEY (group_id)
);

CREATE TABLE user_group (
	user_id int NOT NULL,
	group_id int NOT NULL,
	CONSTRAINT FK_group_id FOREIGN KEY (group_id) REFERENCES groups(group_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE eatery (
	eatery_id SERIAL,
	eatery_name varchar(50) NOT NULL,
	image_url varchar(200),
	eatery_address varchar(200),
	category varchar(50),
	website varchar(200),
	open_time time,
	close_time time,
	has_takeout boolean,
	rating int,
	phone varchar(50),
	price varchar(50),
	CONSTRAINT PK_eatery PRIMARY KEY (eatery_id)
);

CREATE TABLE vote (
	vote_id SERIAL,
	vote_name varchar(50),
	vote_description varchar(200),
	vote_start_date date NOT NULL,
	vote_end_date date NOT NULL,
	is_active boolean NOT NULL,
	eatery_id int NOT NULL,
	CONSTRAINT PK_vote PRIMARY KEY (vote_id),
	CONSTRAINT FK_eatery_id FOREIGN KEY (eatery_id) REFERENCES eatery(eatery_id)
);

CREATE TABLE voter (
	voter_id SERIAL,
	user_response boolean,
	user_id int,
	vote_id int NOT NULL,
	CONSTRAINT PK_voter PRIMARY KEY (voter_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_vote_id FOREIGN KEY (vote_id) REFERENCES vote(vote_id)
);

CREATE TABLE user_voter (
	user_id int NOT NULL,
	voter_id int NOT NULL,
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_voter_id FOREIGN KEY (voter_id) REFERENCES voter(voter_id)
);

COMMIT TRANSACTION;