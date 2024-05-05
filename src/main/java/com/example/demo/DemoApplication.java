package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public FileTemplateResolver fileTemplateResolver() {
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setPrefix("/workspaces/PocSpringBoot/mail/");
		templateResolver.setSuffix(".txt");
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheTTLMs(10000L);

		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine thymeleafTemplateEngine(FileTemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}
}
