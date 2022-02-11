use pandatype_db;

DROP TABLE IF EXISTS leaderboard_table;

CREATE TABLE leaderboard_table
(
    id           bigint auto_increment,
    userId       bigint,
    testLanguage varchar(10) not null,
    testType     varchar(10) not null,
    testOption   varchar(10) not null,
    wpm          double not null,
    rawWpm       double not null,
    accuracy     double not null,
    consistency  double not null,
    testDate     timestamp not null,
    elapsedTime  double not null,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user_entities_table (userId)
);

INSERT INTO leaderboard_table
VALUES(1, 1, "en", "time", "15", 222.22, 222.22, 100.00, 100.00, 20220207121155, 15.01);
INSERT INTO leaderboard_table
VALUES(2, 1, "en", "time", "15", 167, 167, 100.00, 100.00, 20220207121155, 15.01);
INSERT INTO leaderboard_table
VALUES(3, 1, "en", "time", "15", 188, 188, 100.00, 100.00, 20220207121155, 15.01);
INSERT INTO leaderboard_table
VALUES(4, 1, "en", "time", "15", 144, 144, 100.00, 100.00, 20220207121155, 15.01);
INSERT INTO leaderboard_table
VALUES(5, 1, "en", "time", "15", 122, 122, 100.00, 100.00, 20220207121155, 15.01);
INSERT INTO leaderboard_table
VALUES(6, 1, "en", "time", "15", 111, 111, 100.00, 100.00, 20220207121155, 15.01);
INSERT INTO leaderboard_table
VALUES(7, 1, "en", "time", "60", 180, 180, 100.00, 100.00, 20220207121155, 60);
INSERT INTO leaderboard_table
VALUES(8, 1, "en", "time", "60", 155, 155, 100.00, 100.00, 20220207121155, 60);
INSERT INTO leaderboard_table
VALUES(9, 1, "en", "time", "60", 177, 177, 100.00, 100.00, 20220207121155, 60);
INSERT INTO leaderboard_table
VALUES(10, 1, "en", "time", "60", 166, 166, 100.00, 100.00, 20220207121155, 60);
INSERT INTO leaderboard_table
VALUES(11, 1, "en", "time", "60", 144, 144, 100.00, 100.00, 20220207121155, 60);
INSERT INTO leaderboard_table
VALUES(12, 1, "en", "time", "60", 133, 133, 100.00, 100.00, 20220207121155, 60);

# Select user_entities_table.username, wpm, rawWpm, accuracy, consistency, testLanguage, testType, testOption, date
# From leaderboard_table
# INNER JOIN user_entities_table ON leaderboard_table.userId = user_entities_table.userId
# WHERE testOption = 15
# ORDER BY wpm DESC;