use pandatype_db;

DROP TABLE IF EXISTS zhQuote;
DROP TABLE IF EXISTS zhWords_zi;
DROP TABLE IF EXISTS zhWords_pinyin;
DROP TABLE IF EXISTS zhWords;
DROP TABLE IF EXISTS enQuote;
DROP TABLE IF EXISTS enWords;

CREATE TABLE enWords
(
    id   bigint auto_increment,
    word varchar(20) not null unique,
    PRIMARY KEY (id)
);

CREATE TABLE enQuote
(
    id     bigint auto_increment,
    text   varchar(10000) not null,
    source varchar(1000),
    length integer,
    PRIMARY KEY (id)
);

CREATE TABLE zhWords
(
    id   bigint auto_increment,
    word varchar(100) unique,
    PRIMARY KEY (id)
) character set = utf8;

CREATE TABLE zhWords_pinyin
(
    id         bigint auto_increment,
    zhWords_id bigint,
    ziIndex    bigint,
    pinyin     TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (zhWords_id) REFERENCES zhWords (id)
);

CREATE TABLE zhWords_zi
(
    id         bigint auto_increment,
    zhWords_id bigint,
    ziIndex    bigint,
    zi         varchar(100),
    PRIMARY KEY (id),
    FOREIGN KEY (zhWords_id) REFERENCES zhWords (id)
) character set = utf8;

create table zhQuote
(
    id     bigint auto_increment,
    text   varchar(1000) not null,
    source varchar(100),
    length integer,
    PRIMARY KEY (id)
) character set = utf8;

LOAD DATA INFILE '/var/lib/mysql-files/enWords.csv' INTO TABLE enWords
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES
    (@col1) set word = @col1;

LOAD DATA INFILE '/var/lib/mysql-files/enQuote.csv' INTO TABLE enQuote
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES
    (@col1, @col2, @col3, @col4) set text = @col1, source = @col2, length = @col3;


LOAD DATA INFILE '/var/lib/mysql-files/zhWords.csv' IGNORE INTO TABLE zhWords CHARACTER SET utf8
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES
    (@col1, @col2, @col3, @col4, @col5) set word = @col2;
LOAD DATA INFILE '/var/lib/mysql-files/zhWords.csv' INTO TABLE zhWords_pinyin CHARACTER SET utf8
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES
    (@col1, @col2, @col3, @col4, @col5) set zhWords_id = @col1, ziIndex = @col3, pinyin = @col4;
LOAD DATA INFILE '/var/lib/mysql-files/zhWords.csv' INTO TABLE zhWords_zi CHARACTER SET utf8
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES
    (@col1, @col2, @col3, @col4, @col5) set zhWords_id = @col1, ziIndex = @col3, zi = @col5;

LOAD DATA INFILE '/var/lib/mysql-files/zhQuote.csv' INTO TABLE zhQuote CHARACTER SET utf8
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES
    (@col1, @col2, @col3, @col4) set text = @col2, source = @col3, length = @col4;

# SELECT zhWords.word                                                                   as word,
#        cast(concat('[', group_concat(json_quote(pinyin) SEPARATOR ','), ']') as json) as pinyin,
#        cast(concat('[', group_concat(json_quote(zi) SEPARATOR ','), ']') as json)     as zi
# FROM zhWords
#          INNER JOIN zhWords_pinyin zWp
#                     ON zhWords.id = zWp.zhWords_id
#          INNER JOIN zhWords_zi zWz
#                     ON zWp.zhWords_id = zWz.zhWords_id AND zWp.ziIndex = zWz.ziIndex
# GROUP BY zhWords.id;
