package com.study.studyTwo.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TemplatePath {

	ERROR_FILE_NOT_FOUND("pebble/hello")

	// common page
    , COMMON_HEADER("commonHeader")
	, PEBBLE_SAMPLE("sample/pebbleSample")

	;
	private String url;
}
