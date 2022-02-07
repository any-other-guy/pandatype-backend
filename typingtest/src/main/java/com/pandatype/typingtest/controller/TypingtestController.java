package com.pandatype.typingtest.controller;

import com.pandatype.typingtest.entities.TypingtestResponseEntity;
import com.pandatype.typingtest.service.TypingtestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/typingtest")
public class TypingtestController {
    Logger logger = LoggerFactory.getLogger(TypingtestController.class);

    @Resource
    private TypingtestService typingtestService;

    @GetMapping(value = "/getTest")
    public TypingtestResponseEntity getTest(@RequestParam(required = true) String language, @RequestParam(required = true) String type, @RequestParam(required = false) String quoteLength) {
        TypingtestResponseEntity res = new TypingtestResponseEntity();
        if (language.equals("en") && type.equals("words")) {
            res = typingtestService.getEnWords();
        } else if (language.equals("en") && type.equals("quote")) {
            res = typingtestService.getEnQuote(quoteLength);
        } else if (language.equals("zh") && type.equals("words")) {
            res = typingtestService.getZhWords();
        } else if (language.equals("zh") && type.equals("quote")) {
            res = typingtestService.getZhQuote(quoteLength);
        }
        return res;
    }
}
