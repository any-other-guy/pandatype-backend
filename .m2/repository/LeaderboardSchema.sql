use pandatype_db;

DROP TABLE IF EXISTS leaderboard_table;

CREATE TABLE leaderboard_table
(
    id           bigint auto_increment,
    userId      bigint,
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
VALUES(2, 1, "en", "time", "15", 222.22, 222.22, 100.00, 100.00, 20220207121155, 15.01);


# Select user_entities_table.username, wpm, rawWpm, accuracy, consistency, testLanguage, testType, testOption, date
# From leaderboard_table
# INNER JOIN user_entities_table ON leaderboard_table.userId = user_entities_table.userId
# WHERE testOption = 15
# ORDER BY wpm DESC;