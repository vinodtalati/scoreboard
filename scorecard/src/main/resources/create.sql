
-- Statement separator within SQL scripts: ";".
-- Start delimiter for block comments within SQL scripts: "/*".
-- End delimiter for block comments within SQL scripts: "*/".
-- Prefix for single-line comments within SQL scripts: "--".
CREATE TABLE sc_teams (
	id INT PRIMARY KEY,
    name  varchar(255) NOT NULL,
    city varchar(255),
    abbreviation varchar(10)    
);


CREATE TABLE sc_players (
	id INT PRIMARY KEY,
    teamId  INT NOT NULL,
    playerName varchar(255),
    firstName varchar(255),
    lastName varchar(255),
    birthDate TIMESTAMP,
    position varchar(255)
);

CREATE TABLE sc_games (
	id INT PRIMARY KEY,
    homeId  INT NOT NULL,
    awayId  INT NOT NULL,
    season varchar(255),
    gameDate TIMESTAMP,
    result varchar(30),
    score varchar(30)
);

CREATE TABLE sc_player_stats (
	id INT PRIMARY KEY,
    gameId  INT NOT NULL,
	teamId  INT NOT NULL,
	playerId INT NOT NULL,
    opponentId  INT NOT NULL,
    period varchar(255),
    season varchar(255),
    minutes varchar(255),
    off_rating numeric,
    def_rating numeric,
    ast_pct numeric,
	ast_tov numeric,
	ast_ratio numeric,
	oreb_pct numeric,
	dreb_pct numeric,
	treb_pct numeric,
	tm_tov_pct numeric,
	efg_pct numeric,
	ts_pct numeric,
	usg_pct numeric,
	pace  numeric,
	pie numeric   
);


CREATE SEQUENCE sc_playerstat_sequence START WITH 1 INCREMENT BY 1 NO CYCLE;

CREATE TABLE sc_store (
    version INT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO sc_store (version) VALUES (1);
