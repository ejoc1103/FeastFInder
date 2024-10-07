BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_voter;
DROP TABLE IF EXISTS voter;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS eatery;
DROP TABLE IF EXISTS user_group;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS eatery_vote;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
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
	open_time varchar,
	close_time varchar,
	has_takeout boolean,
	rating int,
	phone varchar(50),
	price varchar(50),
	CONSTRAINT PK_eatery PRIMARY KEY (eatery_id)
);

CREATE TABLE vote (
	vote_id SERIAL,
	vote_name varchar(50) NOT NULL UNIQUE,
	vote_description varchar(200),
	vote_start_date date,
	vote_end_date date,
	is_active boolean,
	eatery_id int,
	user_vote_id int,
	CONSTRAINT PK_vote PRIMARY KEY (vote_id)
);

CREATE TABLE voter (
	voter_id SERIAL,
	user_response boolean,
	user_id int,
	vote_id int NOT NULL,
	voter_name varchar(50),
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

CREATE TABLE eatery_vote (
	eatery_id int NOT NULL,
	vote_id int NOT NULL,
	CONSTRAINT FK_eatery_id FOREIGN KEY (eatery_id) REFERENCES eatery(eatery_id),
	CONSTRAINT FK_vote_id FOREIGN KEY (vote_id) REFERENCES vote(vote_id)
);

COMMIT TRANSACTION;