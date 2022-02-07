package com.pandatype.typingtest.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnQuoteEntity {
    private String text;
    private String source;
    private Integer length;

    public EnQuoteEntity(String text, String source, Integer length) {
        this.text = text;
        this.source = source;
        this.length = length;
    }
}
