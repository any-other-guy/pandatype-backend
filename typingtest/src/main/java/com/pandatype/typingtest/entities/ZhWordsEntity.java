package com.pandatype.typingtest.entities;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Getter
@Setter
public class ZhWordsEntity {
    private String word;
    private String zi;
    private String pinyin;
//    private List<String> zi;
//    private List<String> pinyin;

    public ZhWordsEntity(String word, String zi, String pinyin) {
//        Gson g = new Gson();
//        List<String> pinyinJSON = g.fromJson(pinyin, List.class);
//        List<String> ziJSON = g.fromJson(zi, List.class);
//
//        this.word = word;
//        this.pinyin = pinyinJSON;
//        this.zi = ziJSON;
//        LOGGER.info(this.pinyin.toString());
//        LOGGER.info(this.zi.toString());

        this.word = word;
        this.zi = zi;
        this.pinyin = pinyin;
    }
}
