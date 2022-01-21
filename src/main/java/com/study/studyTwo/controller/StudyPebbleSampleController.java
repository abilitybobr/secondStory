package com.study.studyTwo.controller;

import com.study.studyTwo.web.FirstStoryTemplates;
import com.study.studyTwo.web.TemplatePath;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class StudyPebbleSampleController {

    @GetMapping({"/pebble/sample"})
    public ModelAndView pebbleSample() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("locationMove","https://bobr2.tistory.com/");
        modelAndView.setViewName(TemplatePath.ERROR_FILE_NOT_FOUND.getUrl());
        return modelAndView;
    }

    @GetMapping({"/pebble/memory/sample"})
    public ModelAndView pebbleMemorySample() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(TemplatePath.PEBBLE_SAMPLE.getUrl());

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("sample1", "Pebble Template을 Static 메모리에서 읽어 오는 예제 입니다.");
        dataMap.put("sample2", "File에서 매번 조회를 하게 되면 I/O가 발생하여 별로 인듯 합니다.");

        String strPebble = FirstStoryTemplates.evaluate(TemplatePath.COMMON_HEADER, dataMap);

        modelAndView.addObject("commonHeaderStr",strPebble);
        return modelAndView;
    }


}
