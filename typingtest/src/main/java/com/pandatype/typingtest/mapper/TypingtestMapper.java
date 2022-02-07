package com.pandatype.typingtest.mapper;

import com.pandatype.typingtest.entities.EnQuoteEntity;
import com.pandatype.typingtest.entities.EnWordsEntity;
import com.pandatype.typingtest.entities.ZhQuoteEntity;
import com.pandatype.typingtest.entities.ZhWordsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypingtestMapper {
    List<EnWordsEntity> selectEnWords();

    List<EnQuoteEntity> selectEnQuote(Integer minLength, Integer maxLength);

    List<ZhWordsEntity> selectZhWords();

    List<ZhQuoteEntity> selectZhQuote(Integer minLength, Integer maxLength);
}
