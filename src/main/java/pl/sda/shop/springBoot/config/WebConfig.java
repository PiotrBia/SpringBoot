package pl.sda.shop.springBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Bean pozwoli skorzystać z innego serwisu, aktualnie włączonego.
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
