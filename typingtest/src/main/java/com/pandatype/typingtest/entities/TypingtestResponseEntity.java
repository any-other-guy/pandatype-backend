package com.pandatype.typingtest.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TypingtestResponseEntity {
    private String language;
    private String type;
    private List<?> testContent;

    public TypingtestResponseEntity(String language, String type, List<?> testContent) {
        this.language = language;
        this.type = type;
        this.testContent = testContent;
    }

    public TypingtestResponseEntity() {
    }
}
