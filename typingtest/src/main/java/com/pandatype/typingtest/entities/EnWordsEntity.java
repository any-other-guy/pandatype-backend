package com.pandatype.typingtest.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnWordsEntity {
    private String word;

    public EnWordsEntity(String word) {
        this.word = word;
    }
}
