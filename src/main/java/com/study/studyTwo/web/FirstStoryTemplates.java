package com.study.studyTwo.web;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@RequiredArgsConstructor
public class FirstStoryTemplates {
	private final PebbleEngine pebbleEngine;

	private static Map<String, PebbleTemplate> pebbleTemplates;

	@PostConstruct
	private void init() throws Exception {
		// PebbleTemplate 등록
		Map<String, PebbleTemplate> templates = new HashMap<>();
		for (TemplatePath templatePath : TemplatePath.values()) {
			templates.put(templatePath.name(), pebbleEngine.getTemplate(templatePath.getUrl()));
		}
		pebbleTemplates = templates;
	}

	private static PebbleTemplate getTemplate(TemplatePath templatePath) {
		return pebbleTemplates.get(templatePath.name());
	}

	/**
	 * Static으로 정의해놓은 Pebble Template을 불러오는 기능 제공
	 * @param templatePath
	 * @param dataMap
	 * @return
	 * @throws IOException
	 */
	public static String evaluate(TemplatePath templatePath, Map<String, Object> dataMap) throws IOException {
		Writer writer = new StringWriter();
		PebbleTemplate template = FirstStoryTemplates.getTemplate(templatePath);
		template.evaluate(writer, dataMap, Locale.KOREA);
		return writer.toString();
	}
}
