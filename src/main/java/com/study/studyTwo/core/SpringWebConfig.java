package com.study.studyTwo.core;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.spring.servlet.PebbleViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan({"com.study.studyTwo.web"})
public class SpringWebConfig implements WebMvcConfigurer {

	/* pebble */
	/** a Loader that will pick template files ending in .pebble from /templates/ dir on the classpath */
	@Bean
	public Loader<?> pebbleLoader() {
		ClasspathLoader loader = new ClasspathLoader();
		loader.setPrefix("/templates/");
		loader.setSuffix(".peb");
		return loader;
	}

	/** a PebbleEngine with default settings, configured with the previous loader */
	@Bean
	public PebbleEngine pebbleEngine() {
		// loader 에서 설정해주고 .loader 로 가져올수 있는것 같음.
		return new PebbleEngine.Builder()
			.loader(pebbleLoader())
			.cacheActive(false)
			.build();
	}

	/** a ViewResolver that will output text/html in UTF-8 */
	@Bean
	public PebbleViewResolver pebbleViewResolver() {
		PebbleViewResolver pebbleViewResolver = new PebbleViewResolver(this.pebbleEngine());
		pebbleViewResolver.setPrefix("/templates/");
		pebbleViewResolver.setSuffix(".peb");
		pebbleViewResolver.setCache(false);
		return pebbleViewResolver;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/pebble/sample");
	}
}
