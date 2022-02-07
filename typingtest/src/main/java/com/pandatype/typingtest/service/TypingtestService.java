package com.pandatype.typingtest.service;

import com.pandatype.typingtest.entities.*;
import com.pandatype.typingtest.mapper.TypingtestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypingtestService {
    @Resource
    private TypingtestMapper typingtestMapper;
    Logger LOGGER = LoggerFactory.getLogger(TypingtestService.class);

    public TypingtestResponseEntity getEnWords() {
        List<EnWordsEntity> list = typingtestMapper.selectEnWords();
        return new TypingtestResponseEntity("en", "words", list);
    }

    public TypingtestResponseEntity getEnQuote(String length) {
        int min = 0;
        int max = Integer.MAX_VALUE;
        switch (length) {
            case "short" -> {
                max = 100;
            }
            case "medium" -> {
                min = 100;
                max = 200;
            }
            case "long" -> {
                min = 200;
            }
            default -> {
            }
        }
        List<EnQuoteEntity> list = typingtestMapper.selectEnQuote(min, max);
        return new TypingtestResponseEntity("en", "quote", list);
    }

    public TypingtestResponseEntity getZhWords() {
        List<ZhWordsEntity> list = typingtestMapper.selectZhWords();
        return new TypingtestResponseEntity("zh", "words", list);
    }

    public TypingtestResponseEntity getZhQuote(String length) {
        int min = 0;
        int max = Integer.MAX_VALUE;
        switch (length) {
            case "short" -> {
                max = 100;
            }
            case "medium" -> {
                min = 100;
                max = 200;
            }
            case "long" -> {
                min = 200;
            }
            default -> {
            }
        }
        List<ZhQuoteEntity> list = typingtestMapper.selectZhQuote(min, max);
        return new TypingtestResponseEntity("zh", "quote", list);
    }
}
