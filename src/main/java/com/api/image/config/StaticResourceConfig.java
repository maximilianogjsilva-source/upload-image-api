package com.api.image.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Value("${image.upload.dir:opt/upload}")
    private String uploadDir;

    @Value("${image.upload.folder:uploads}")
    private String folder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/"+folder+"/**")
                .addResourceLocations("file:"+uploadDir);
    }
}
