use pandatype_db;

DROP TABLE IF EXISTS zhQuote;
DROP TABLE IF EXISTS zhWords;
DROP TABLE IF EXISTS enQuote;
DROP TABLE IF EXISTS enWords;

CREATE TABLE enWords
(
    id   bigint auto_increment,
    word varchar(20) not null unique,
    PRIMARY KEY (id)
) character set = utf8mb4;

CREATE TABLE enQuote
(
    id     bigint auto_increment,
    text   varchar(10000) not null,
    source varchar(1000),
    length integer,
    PRIMARY KEY (id)
) character set = utf8mb4;

CREATE TABLE zhWords
(
    id   bigint auto_increment,
    ci_id bigint,
    word varchar(20) not null,
    zi varchar(20) not null,
    pinyin varchar(20) not null,
    PRIMARY KEY (id)
) character set = utf8mb4;

create table zhQuote
(
    id     bigint auto_increment,
    text   varchar(1000) not null,
    source varchar(100),
    length integer,
    PRIMARY KEY (id)
) character set = utf8mb4;

LOAD DATA INFILE '/var/lib/mysql-files/enWords.csv' INTO TABLE enWords CHARACTER SET utf8mb4
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES
    (@col1) set word = @col1;

LOAD DATA INFILE '/var/lib/mysql-files/enQuote.csv' INTO TABLE enQuote CHARACTER SET utf8mb4
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES
    (@col1, @col2, @col3, @col4) set text = @col1, source = @col2, length = @col3;

LOAD DATA INFILE '/var/lib/mysql-files/zhWords.csv' IGNORE INTO TABLE zhWords CHARACTER SET utf8mb4
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES
    (@col1, @col2, @col3, @col4) set ci_id = @col1, word = @col2, zi = @col3, pinyin = @col4;

LOAD DATA INFILE '/var/lib/mysql-files/zhQuote.csv' INTO TABLE zhQuote CHARACTER SET utf8mb4
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES
    (@col1, @col2, @col3, @col4) set text = @col2, source = @col3, length = @col4;

# SELECT zhWords.word AS word,
#     cast(concat('[', group_concat(json_quote(zi) SEPARATOR ','), ']') AS JSON)     AS zi,
#     cast(concat('[', group_concat(json_quote(pinyin) SEPARATOR ','), ']') AS JSON) AS pinyin
# FROM zhWords
# GROUP BY zhWords.ci_id, zhWords.word;

# SELECT zhWords.word                                                                   as word,
#        cast(concat('[', group_concat(json_quote(pinyin) SEPARATOR ','), ']') as json) as pinyin,
#        cast(concat('[', group_concat(json_quote(zi) SEPARATOR ','), ']') as json)     as zi
# FROM zhWords
#          INNER JOIN zhWords_pinyin zWp
#                     ON zhWords.id = zWp.zhWords_id
#          INNER JOIN zhWords_zi zWz
#                     ON zWp.zhWords_id = zWz.zhWords_id AND zWp.ziIndex = zWz.ziIndex
# GROUP BY zhWords.id;