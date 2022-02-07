package com.pandatype.typingtest.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZhQuoteEntity {
    private String text;
    private String source;
    private Integer length;

    public ZhQuoteEntity(String text, String source, Integer length) {
        this.text = text;
        this.source = source;
        this.length = length;
    }
}
