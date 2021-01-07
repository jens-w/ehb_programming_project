CREATE TABLE Users (
	Id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	Userkey varchar(255) NOT NULL,
    Snowflake bigint NOT NULL
);