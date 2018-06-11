package com.melorean.springConfig;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import({AppConfigDao.class})
public class AppConfig {

}
